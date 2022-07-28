var DVIEW = function(){

   this.show = function(){
    $("#mainContent").empty();
        var content = "";
        content = '<div class="row">'
        content = content + '<div class="col-lg-12">'
        content = content + '<div class="card" id="cards">'
        content = content + '<div class="card-body">'
        content = content + '<div class="dataTable-top"><div class="dataTable-dropdown">'
        content = content + '<select class="dataTable-selector" id="monthSelector" onchange="dview.loadDistribution()" ><option value="">Select Month</option></select>'
        content = content + '</div><button type="button" class="btn btn-primary" onclick="dview.print()" style="width:11%;float:right;">Print</button></div>'
        content = content + '<table class="table datatable" id="basic-table" >'
        content = content + '<thead>'
        content = content + '<tr>'
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

        content = content + '</div>'
        content = content + '</div>'
        content = content + '</div>'
        content = content + '</div>'
        $("#mainContent").append(content);
        distribute.loadAndAppendPendingMonth();
   }
   
    this.loadDistribution = function(){
      var monthId = $("#monthSelector").val();
      var response = ajax.get("/distribution/view/"+monthId);
      $("#cardTable").empty();
			 var obj = response.data;
                var table = "";
                var counter =1;
                for (var i = 0; i < obj.length; i++) {
                    if (obj[i].rationCard.cartType == "b") {
                        table = table + '<tr class="table-danger">';
                    } else {
                        table = table + '<tr class="table-light">';
                    }
                    table = table + '<td scope="row">' + counter + '</td>';
                    table = table + '<td scope="row">' + obj[i].rationCard.cardNumber + '</td>';
                    table = table + '<td>' + obj[i].rationCard.cardHolder + '</td>';
                    table = table + '<td>' + obj[i].rationCard.fatherOrHusband + '</td>';
                    table = table + '<td>' + obj[i].rationCard.unit + '</td>';
                    table = table + '<td>' + obj[i].month.month+ '</td>';
                    table = table + '<td></td>';
                    table = table + '</tr>';
                    counter = counter+1;
                }
                $("#cardTable").append(table);
    }
    
    this.print = function(){
      var pdf = new jsPDF('p', 'pt', 'letter');
	//doc.fromHTML($('#basic-table').get(0));
	
	
	specialElementHandlers = {
        // element with id of "bypass" - jQuery style selector
        '#bypassme': function (element, renderer) {
            // true = "handled elsewhere, bypass text extraction"
            return true
        }
    };
	
	 margins = {
            top: 20,
            bottom: 60,
            left: 10,
            width: 1500
        };
        // all coords and widths are in jsPDF instance's declared units
        // 'inches' in this case
        pdf.fromHTML(
            $('#cards').get(0), // HTML string or DOM elem ref.
            margins.left, // x coord
            margins.top, { // y coord
                'width': margins.width,  
                'elementHandlers': specialElementHandlers
            },

            function (dispose) {
                // dispose: object with X, Y of the last line add to the PDF 
                //          this allow the insertion of new lines after html
                pdf.save('Test.pdf');
            }, margins
        );
	//doc.save("table.pdf");
    }
}