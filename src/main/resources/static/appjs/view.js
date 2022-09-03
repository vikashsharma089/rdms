var DVIEW = function(){

    this.show = function(){
        $(".toggle-sidebar-btn").click();
        $("#mainContent").empty();
        var content = "";
        content = '<div class="row">'
        content = content + '<div class="col-lg-12">'
        content = content + '<div class="card" id="cards">'
        content = content + '<div class="card-body">'
        content = content + '<div class="dataTable-top"><div class="dataTable-dropdown">'
        content = content + '<select class="dataTable-selector" id="monthSelector" onchange="dview.loadDistribution(this)" ><option value="">Select Month</option></select>'
        content = content + '</div> <div class="card-body row" style="width:83%;"><div class="col-sm-4"><label>Total:  </label><span id="total" style="font-weight: 600;padding: 8px;">0</span></div>    <div class="col-sm-3"><label>PHH:  </label><span id="PHH" style="font-weight: 600;padding: 8px;">0</span></div>  <div class="col-sm-3" style="color: #eb7575"><label>AAY:  </label><span id="AAY" style="font-weight: 600;padding: 8px;">0</span></div>  <div class="col-sm-3" style="color: green"><label>Amount:  </label><span id="totalAmmount" style="font-weight: 600;padding: 8px;">0</span></div>     <div class="col-sm-2"><button type="button" class="btn btn-primary" onclick="dview.print()" style="width:100%;float:right;">Download</button></div>      </div></div>'
        content = content + '<table class="table datatable" id="basic-table" >'
        content = content + '<thead>'
        content = content + '<tr id="tblHeader">'
        content = content + '<th scope="col">#</th>';
        content = content + '<th scope="col">Rationcard Number</th>';
        content = content + '<th scope="col">Cardholder</th>';
        content = content + '<th scope="col">Father/Husband</th>';
        content = content + '<th scope="col">Total Unit</th>';
        content = content + '<th scope="col">Month</th>';
        content = content + '<th scope="col">Signature</th>';
        content = content + '</tr>'
        content = content + '</thead>'
        content = content + '<tbody id="cardTable">'
        content = content + '<tr>'
        content = content + '</tr>'

        content = content + '</tbody>'
        content = content + '</table>'
        content = content + ' <div className = "dataTable-bottom" > <div className = "dataTable-info" id="dataTable-info-id" style="float: left;"> Showing 0  to  0  of 0 entries </div><nav id="data-pagination" class="dataTable-pagination" style="float: right"> </nav></div>'
        content = content + '</div>'
        content = content + '</div>'
        content = content + '</div>'
        content = content + '</div>'
        $("#mainContent").append(content);
        distribute.loadAndAppendPendingMonth();
    }

    this.loadDistribution = function(obj){
        $("#dataTable-info-id").empty();
        $("#dataTable-info-id").append("Showing 0  to  0  of 0 entries");
        distribute.changeTableHeader(obj);
        $("#tblHeader").find("th").last().remove();
        $("#tblHeader").append('<th scope="col">Signature</th>')
        var monthId = $("#monthSelector").val();
        var countResonse = ajax.get("/distribution/viewCount/"+monthId);
        var cdata = countResonse.data;
        var ctotal = 0;
        for(var i=0; i<cdata.length; i++){
            $("#"+cdata[i][0]).text(cdata[i][1]);
            if(i<=1){
                ctotal = ctotal+ cdata[i][1];
            }else{
                $("#totalAmmount").text(cdata[i][1]);
            }
        }
        $("#total").text(ctotal)


        $('#data-pagination').pagination({
            dataSource: "/distribution/view/"+monthId,
            locator: 'data',
            totalNumberLocator: function(response) {
                return response.totalElement;
            },
            pageSize: 10,

            position: 'bottom',
            ajax: {
                beforeSend: function() {
                    $("#cardTable").empty();
                    $("#cardTable").append('<tr><td></td> <td></td> <td></td><td><div class="spinner-border" role="status">' +
                        '                <span class="visually-hidden">Loading...</span>' +
                        '              </div></td><tr>');
                }
            },
            callback: function(obj, pagination) {
                var from =pagination.pageNumber;

                if(pagination.pageNumber >1){
                    from = pagination.pageNumber * pagination.pageSize;
                    from = (from -pagination.pageSize )+1;
                }


                var to = 0;
                if(obj.length<pagination.pageSize){
                    to =  (pagination.pageNumber * pagination.pageSize) - (pagination.pageSize-obj.length);
                }else{
                   to =  pagination.pageNumber * pagination.pageSize;
                }

                var info  = "Showing "+from+" to "+to+" of "+pagination.totalNumber+" entries";
                $("#dataTable-info-id").empty();
                $("#dataTable-info-id").append(info);

                $("#cardTable").empty();
                // template method of yourself

                var table = "";
                var counter =1;
                var bpl =0;
                var ant =0;
                var table ="";
                for (var i = 0; i < obj.length; i++) {
                    if (obj[i].rationCard.cartType == "AAY") {
                        table = table + '<tr class="table-danger">';
                        bpl= bpl+1;
                    } else {
                        table = table + '<tr class="table-light">';
                        ant = ant+1;
                    }
                    table = table + '<td scope="row">' + from + '</td>';
                    table = table + '<td scope="row">' + obj[i].rationCard.cardNumber + '</td>';
                    table = table + '<td>' + obj[i].rationCard.cardHolder + '</td>';
                    table = table + '<td>' + obj[i].rationCard.fatherOrHusband + '</td>';
                    table = table + '<td>' + obj[i].rationCard.unit + '</td>';

                    var detais = obj[i].details;

                    for(var j=0; j<detais.length; j++){
                        table = table + '<td>' + detais[j].quantity + ' Kg.</td>';
                    }
                    table = table + '<td>' + obj[i].totalAmount + ' Rs.</td>';
                    if(obj[i].signature !== null && obj[i].signature !== "null" ){
                        table = table + '<td><img src="data:image/png;base64, iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==" alt="Red dot" /></td>'
                    }else{
                        table = table + '<td></td>';
                    }
                    table = table + '</tr>';
                    from = from+1;
                }
                $("#cardTable").append(table);
            }
        })
    }


    this.showRemains = function(){
        $("#mainContent").empty();
        var table = "";
        table = table + '<div class="card">';
        table = table + '<div class="card-body">';
        table = table + '<div class="card-body">'
        table = table + '<div class="dataTable-top"><div class="dataTable-dropdown">'
        table = table + '<select class="dataTable-selector" id="monthSelector" onchange="dview.appendRemainigCard(this)" ><option value="">Select Month</option></select>'
        table = table + '</div><div class="card-body row" style="width:65%;"><div class="col-sm-4"><label>Total:  </label><span id="total" style="font-weight: 600;padding: 8px;">0</span></div>    <div class="col-sm-4"><label>PHH:  </label><span id="ant" style="font-weight: 600;padding: 8px;">0</span></div>  <div class="col-sm-4" style="color: #eb7575;"><label>AAY:  </label><span id="bpl" style="font-weight: 600;padding: 8px;">0</span></div>  </div></div>'

        table = table + '<table class="table">';
        table = table + '<thead>';
        table = table + '<tr>';
        table = table + '<th scope="col">#</th>';
        table = table + '<th scope="col">CardNo</th>';
        table = table + '<th scope="col">Holder</th>';
        table = table + '<th scope="col">Father/Husband</th>';
        table = table + '<th scope="col">Mother Name</th>';
        table = table + '<th scope="col">Unit</th>';
        table = table + '<th scope="col">Purwa</th>';



        table = table + '</tr>';
        table = table + '</thead>';
        table = table + '<tbody id="cardTable">';
        table = table + '<tr class="table-light">';
        table = table + '</tr>';



        table = table + '</div>';
        table = table + '</div>';
        $("#mainContent").append(table);
        distribute.loadAndAppendPendingMonth();
    }

    this.appendRemainigCard = function(obj){
        var stockId = $(obj).find('option:selected').val();

        $("#cardTable").empty();
        var response  = ajax.get("/rationcard/loadRemaining/"+stockId);
        var obj = response.data;
        var table = "";
        var counter=1;
        var bpl =0;
        var ant =0;
        for (var i = 0; i < obj.length; i++) {
            if (obj[i].cartType == "AAY") {
                table = table + '<tr class="table-danger">';
                bpl = bpl+1;
            } else {
                table = table + '<tr class="table-light">';
                ant= ant+1;
            }
            table = table + '<td scope="row">' + counter + '</td>';
            table = table + '<td scope="row">' + obj[i].cardNumber + '</td>';
            table = table + '<td>' + obj[i].cardHolder + '</td>';
            table = table + '<td>' + obj[i].fatherOrHusband + '</td>';
            table = table + '<td>' + obj[i].motherName + '</td>';
            table = table + '<td>' + obj[i].unit + '</td>';
            table = table + '<td>' + obj[i].purwa.purwaName+ '</td>';

            table = table + '</tr>';
            counter = counter+1;


        }
        $("#cardTable").append(table);
        $("#bpl").text(bpl);
        $("#ant").text(ant);
        counter = counter-1;
        $("#total").text(counter);
    }


    this.print = function(){
        var monthId = $("#monthSelector").val();
        window.open(
            'http://69.49.229.123/report/download/'+monthId,
            '_blank' // <- This is what makes it open in a new window.
        );
    }
}