var DISTRIBUTE = function() {
    this.show = function() {
        $(".toggle-sidebar-btn").click();
        $("#mainContent").empty();
        var content = "";
        content = '<div class="row">'
        content = content + '<div class="col-lg-12">'
        content = content + '<div class="card">'
        content = content + '<div class="card-body">'
        content = content + '<div class="dataTable-top"><div class="dataTable-dropdown row mb-3">'
        content = content + '<select class="dataTable-selector" id="monthSelector" onchange="distribute.changeTableHeader(this)" ><option value="">Select Stock</option></select>'
        content = content + '</div><div class="dataTable-search row mb-3" style="float:left;"><input class="dataTable-input" id="searchId" onkeyup="distribute.searchCard(this)"  size="width:187px;margin-left: 34px;" placeholder="Search by ration card" type="text"></div></div>'
        content = content + '<table class="table datatable">'
        content = content + '<thead>'
        content = content + '<tr id="tblHeader">'
        content = content + '<th scope="col">#</th>';
        content = content + '<th scope="col">CardNo</th>';
        content = content + '<th scope="col">Holder</th>';
        content = content + '<th scope="col">Father/Husband</th>';
        content = content + '<th scope="col">Unit</th>';
        content = content + '</tr>'
        content = content + '</thead>'
        content = content + '<tbody id="cardTable">'
        content = content + '<tr>'

        content = content + '</tr>'

        content = content + '</tbody>'
        content = content + '</table>'

        content = content + '</div>'
        content = content + '</div>'
        content = content + '</div>'
        content = content + '</div>'
        $("#mainContent").append(content);
        distribute.loadAndAppendPendingMonth();

    }

    this.loadAndAppendPendingMonth = function() {
        var response = ajax.get("/month/loadPendingMonth");
        var obj = response.data;
        var content = '';
        for (var i = 0; i < obj.length; i++) {
            content = content + "<option value='" + obj[i].id + "' data=" + window.btoa(JSON.stringify(obj[i].items)) + ">" + obj[i].monthId.monthName + "-" + obj[i].details + "</option>"
        }
        $("#monthSelector").append(content);
    }

    this.changeTableHeader = function(obj) {
        var data = $(obj).find('option:selected').attr('data');
        var jsonData = window.atob(data);
        jsonData = JSON.parse(jsonData);
        $("#tblHeader").empty();
        $("#cardTable").empty();
        var content = "";
        content = '<th scope="col">#</th>';
        content = content + '<th scope="col">CardNo</th>';
        content = content + '<th scope="col">Holder</th>';
        content = content + '<th scope="col">Father/Husband</th>';
        content = content + '<th scope="col">Unit</th>';
        $("#tblHeader").append(content);

        for(var i =0; i<jsonData.length; i++){
            if(jsonData[i].quantity !== 0){
                $("#tblHeader").append('<th scope="col">'+jsonData[i].stockItem.itemName+'</th>')
            }
        }
        $("#tblHeader").append('<th scope="col">Amount</th>');
        $("#tblHeader").append('<th scope="col">Action</th>');

    }


    this.searchCard = function() {
        var searchKey = $("#searchId").val();
        if (searchKey.length >= 3) {



            var response = ajax.get("/rationcard/search/" + searchKey);
            $("#cardTable").empty();

            var data = $("#monthSelector").find('option:selected').attr('data');
            var jsonData = window.atob(data);
            jsonData = JSON.parse(jsonData);
            var obj = response.data;
            var table = "";
            var counter = 1;
            for (var i = 0; i < obj.length; i++) {
                var items = [];
                if (obj[i].cartType == "AAY") {
                    table = table + '<tr class="table-danger">';
                } else {
                    table = table + '<tr class="table-light">';
                }
                var unit =  obj[i].unit ;
                table = table + '<td scope="row">' + counter + '</td>';
                table = table + '<td scope="row">' + obj[i].cardNumber + '</td>';
                table = table + '<td>' + obj[i].cardHolder + '</td>';
                table = table + '<td>' + obj[i].fatherOrHusband + '</td>';
                table = table + '<th>' +unit+ '</th>';
                var totalkg =0;
                var totalAmount =0;
                for(var j =0; j<jsonData.length; j++){
                    var map = new Object(); // or var map = {};
                    if(jsonData[j].quantity !== 0){
                        var config = ruleSetting.get(obj[i].cartType+"_"+jsonData[j].stockItem.itemName);
                        var totalQuantity = 0;

                            totalQuantity = distribute.getTotalCalculatedKg(config,unit)
                        totalAmount = totalAmount+ distribute.getTotalCalculatedAmount(config,totalQuantity)
                        table = table + '<td scope="col">'+totalQuantity+'</td>';

                        map["quantity"] =totalQuantity;
                        map["amount"] =distribute.getTotalCalculatedAmount(config,totalQuantity);
                        map["stockItem"] ={"id":jsonData[j].stockItem.id};
                        items.push(map);
                    }
                }

                table = table + '<td scope="col">'+totalAmount+'</td>';

                table = table + '<td><button type="button" class="btn btn-primary" onclick="distribute.addTtoDistributed(' + obj[i].id + ',this,'+totalAmount+')" data='+ window.btoa(JSON.stringify(items))+' style="width:100%;">Add</button></td>';
                table = table + '</tr>';
            }
            $("#cardTable").append(table);

        }
    } // end of search card

    this.getTotalCalculatedKg = function(config, unit){
        var totalkg =0;
        if(config.perUnitOrCard ==="unit"){
            totalkg = unit*config.kgPerUnitOrCard;
        }

        if(config.perUnitOrCard ==="card"){
            totalkg = 1*config.kgPerUnitOrCard;
        }
        return totalkg;

    }

    this.getTotalCalculatedAmount= function (config, quantity){
        var totalAmount =0;
        totalAmount  = quantity * config.rate;
        return totalAmount;

    }

    this.addTtoDistributed = function(rationId,obj, totalAmount) {
        var map  = $(obj).attr("data");
        var quantityObj = JSON.parse(window.atob(map));
        var monthId = $("#monthSelector").val();
        if (monthId === "") {
            ajax.alert("error", "Please select distribution month");
        } else {
            var jsonObj ={};
            jsonObj["details"]=quantityObj;
            jsonObj["stock"] = {"id":monthId};
            jsonObj["rationCard"] = {"id":rationId}
            jsonObj["totalAmount"] = totalAmount;


            var response = ajax.post("/distribution/add", jsonObj);
            if (response.status == "success") {
                ajax.alert("success", "Added Successfully.");
                $("#cardTable").empty();
                $("#searchId").val("");
                $("#searchId").focus();
            } else {
                ajax.alert("error", response.error);
            }
        }

    }
}