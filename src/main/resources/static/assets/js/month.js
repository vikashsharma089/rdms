var MONTH = function() {

    this.show = function() {
        var response = ajax.get("/setting/loadItems/" + villageId);
        var response2 = ajax.get("/month/loadMonth");
        var obj = response.data;
        var monthObj = response2.data;
        $("#mainContent").empty();
        var headerMap = new Map();
        var table = "";
        table = table + '<div class="card">';
        table = table + '<div class="card-body">';
        table = table + '<button type="button" onclick="" class="btn btn-primary" style="float:right;" data-bs-toggle="modal" data-bs-target="#verticalycentered" onclick="month.fillStockPopup()" ><i class="plus-square-fill me-1"></i>Add Stock</button>'

        table = table + '<div class="modal fade" id="verticalycentered" tabindex="-1">'
        table = table + '<div class="modal-dialog modal-dialog-centered">'
        table = table + '<div class="modal-content">'
        table = table + '<div class="modal-header">'
        table = table + '<h5 class="modal-title">Vertically Centered</h5>'
        table = table + '<button type="button" id="closepopup" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>'
        table = table + '</div>'
        table = table + '<div class="modal-body">'


        table = table + '<form>'

        table = table + '<div class="row mb-3">'
        table = table + '<label for="inputEmail3" class="col-sm-2 col-form-label">Month</label>'
        table = table + '<div class="col-sm-10">'
        table = table + '<select class="form-select" aria-label="Default select example" id="month">'

        for (var i = 0; i < monthObj.length; i++) {
            table = table + '<option value="'+monthObj[i].id+'">'+monthObj[i].monthName+'</option>'
             
        }

        table = table + '</select>'
        table = table + '</div>'
        table = table + '</div>'

        table = table + '<div class="row mb-3">'
        table = table + '<label for="inputEmail3" class="col-sm-2 col-form-label">Details</label>'
        table = table + '<div class="col-sm-10">'
        table = table + '<input type="text" class="form-control" id="details" required>'
        table = table + '</div>'
        table = table + '</div>'


        for (var i = 0; i < obj.length; i++) {
            table = table + '<th scope="col"></th>';
            table = table + '<div class="row mb-3">'
            table = table + '<label for="inputEmail3" class="col-sm-2 col-form-label">' + obj[i].itemName + '</label>'
            table = table + '<div class="col-sm-10">'
            table = table + '<input type="number" class="form-control stockItem" id=' + obj[i].id + ' required placeholder="Enter value in Kg only" >'
            table = table + '</div>'
            table = table + '</div>'
            headerMap.set(i,obj[i].itemName);
        }

        table = table + '</form>'

        table = table + '</div>'
        table = table + '<div class="modal-footer">'
        table = table + '<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>'
        table = table + '<button type="button" class="btn btn-primary" onclick="month.saveMonth()">Save changes</button>'
        table = table + '</div>'
        table = table + '</div>'
        table = table + '</div>'
        table = table + '</div>'

        table = table + '<table class="table">';
        table = table + '<thead>';
        table = table + '<tr>';
        table = table + '<th scope="col">Month</th>';
        table = table + '<th scope="col">Description</th>';

        for (var i = 0; i < obj.length; i++) {
            table = table + '<th scope="col">' + obj[i].itemName + '</th>';
        }
        table = table + '</tr>';
        table = table + '</thead>';
        table = table + '<tbody id="cardTable">';
        table = table + '</div>';
        table = table + '</div>';

        $("#mainContent").append(table);
        var response = ajax.get("/month/loadAll");
        var obj = response.data;
        var dataMap = {};
        var table = "";
        for (var i = 0; i < obj.length; i++) {
            table = table + '<tr class="table-light">';
            table = table + '<td scope="row">' + obj[i].monthId.monthName + '</td>';
            table = table + '<td>' + obj[i].details + '</td>';
            var items = obj[i].items;
            
            for(var j=0; j<items.length; j++){
              dataMap[items[j].stockItem.itemName] = items[j];
             }
             
             for(var j =0; j<headerMap.size; j++){
               var key = headerMap.get(j);
                  var quantityObj ={};
                  quantityObj = dataMap[key];
                  
                  $.each(quantityObj,function(key, val) {
                     if(key==="quantity"){
                     table = table + '<td scope="row">' +val+ '</td>';
                     return false;
                     }
           
        		  });
                         
             }
            if (obj[i].distributed == true) {
               
            }
            table = table + '</tr>';
        }
        $("#cardTable").append(table);

    }

    this.saveMonth = function() {
        var months = $("#month").val();
        var desc = $("#details").val();
        var items = [];
        $(".stockItem").each(function() {
            items.push({
                "stockItem": {"id":$(this).attr("id")},
                "quantity": $(this).val()
            });
        });

        var obj = {
            "monthId":{"id":months},
            "details": desc,
            "items": items,
            "village": {
                "id": villageId
            }
        };
        var response = ajax.post("/month/save", obj);
        if (response.status == "success") {
            ajax.alert(response.status, "Added Successfully.");
            $("#closepopup").click();
            month.show();
        } else {
            ajax.alert(response.status, response.error);
        }


        this.fillStockPopup = function() {

        }
    }

}