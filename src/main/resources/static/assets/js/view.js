var DVIEW = function(){

   this.show = function(){
    $("#mainContent").empty();
        var content = "";
        content = '<div class="row">'
        content = content + '<div class="col-lg-12">'
        content = content + '<div class="card" id="cards">'
        content = content + '<div class="card-body">'
        content = content + '<div class="dataTable-top"><div class="dataTable-dropdown">'
        content = content + '<select class="dataTable-selector" id="monthSelector" onchange="dview.loadDistribution(this)" ><option value="">Select Month</option></select>'
        content = content + '</div> <div class="card-body row" style="width:83%;"><div class="col-sm-4"><label>Total:  </label><span id="total" style="font-weight: 600;padding: 8px;">0</span></div>    <div class="col-sm-3"><label>Antyodaya:  </label><span id="ant" style="font-weight: 600;padding: 8px;">0</span></div>  <div class="col-sm-3"><label>BPL:  </label><span id="bpl" style="font-weight: 600;padding: 8px;">0</span></div>     <div class="col-sm-2"><button type="button" class="btn btn-primary" onclick="dview.print()" style="width:100%;float:right;">Print</button></div>     </div></div>'
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

        content = content + '</div>'
        content = content + '</div>'
        content = content + '</div>'
        content = content + '</div>'
        $("#mainContent").append(content);
        distribute.loadAndAppendPendingMonth();
   }
   
    this.loadDistribution = function(obj){
      distribute.changeTableHeader(obj);
      var monthId = $("#monthSelector").val();
      var response = ajax.get("/distribution/view/"+monthId);
      $("#cardTable").empty();
			 var obj = response.data;
                var table = "";
                var counter =1;
                var bpl =0;
                var ant =0;
                for (var i = 0; i < obj.length; i++) {
                    if (obj[i].rationCard.cartType == "b") {
                        table = table + '<tr class="table-danger">';
                        bpl= bpl+1;
                    } else {
                        table = table + '<tr class="table-light">';
                        ant = ant+1;
                    }
                    table = table + '<td scope="row">' + counter + '</td>';
                    table = table + '<td scope="row">' + obj[i].rationCard.cardNumber + '</td>';
                    table = table + '<td>' + obj[i].rationCard.cardHolder + '</td>';
                    table = table + '<td>' + obj[i].rationCard.fatherOrHusband + '</td>';
                    table = table + '<td>' + obj[i].rationCard.unit + '</td>';
                     var detais = obj[i].details;
                     for(var j=0; j<detais.length; j++){
                        table = table + '<td>' + detais[j].quantity + ' Kg.</td>';
                     }
                    table = table + '<td></td>';
                    table = table + '</tr>';
                    counter = counter+1;
                }
                $("#cardTable").append(table);
                $("#bpl").text(bpl);
                $("#ant").text(ant);
                counter = counter-1
                $("#total").text(counter);
                
                
    }
    
    
    this.showRemains = function(){
    	 $("#mainContent").empty();
        var table = "";
        table = table + '<div class="card">';
        table = table + '<div class="card-body">';
        table = table + '<div class="card-body">'
        table = table + '<div class="dataTable-top"><div class="dataTable-dropdown">'
        table = table + '<select class="dataTable-selector" id="monthSelector" onchange="dview.appendRemainigCard(this)" ><option value="">Select Month</option></select>'
        table = table + '</div><div class="card-body row" style="width:65%;"><div class="col-sm-4"><label>Total:  </label><span id="total" style="font-weight: 600;padding: 8px;">0</span></div>    <div class="col-sm-4"><label>Antyodaya:  </label><span id="ant" style="font-weight: 600;padding: 8px;">0</span></div>  <div class="col-sm-4"><label>BPL:  </label><span id="bpl" style="font-weight: 600;padding: 8px;">0</span></div>  </div></div>'
        
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
        var response  = ajax.get("/rationcard/loadRemaining/"+villageId+"/"+stockId);
                var obj = response.data;
                var table = "";
                var counter=1;
                var bpl =0;
                var ant =0;
                for (var i = 0; i < obj.length; i++) {
                    if (obj[i].cartType == "b") {
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