var ALLCARD = function() {

    this.show = function() {
        $("#mainContent").empty();
        var table = "";
        table = table + '<div class="card">';
        table = table + '<div class="card-body">';
        table = table + '<table class="table">';
        table = table + '<thead>';
        table = table + '<tr>';
        table = table + '<th scope="col">#</th>';
        table = table + '<th scope="col">राशन कार्ड संख्या</th>';
        table = table + '<th scope="col">धारक का नाम</th>';
        table = table + '<th scope="col">पिता/पति का नाम</th>';
        table = table + '<th scope="col">माता का नाम</th>';
        table = table + '<th scope="col">कुल यूनिट</th>';

        table = table + '</tr>';
        table = table + '</thead>';
        table = table + '<tbody id="cardTable">';
        table = table + '<tr class="table-light">';
        table = table + '<td scope="row">Default</td>';
        table = table + '<td>Cell</td>';
        table = table + '<td>Cell</td>';
        table = table + '<td>Cell</td>';
        table = table + '<td>Cell</td>';
        table = table + '</tr>';

        table = table + '</div>';
        table = table + '</div>';

        $("#mainContent").append(table);
        $("#cardTable").empty();
        var response  = ajax.get("/rationcard/loadAll");
                var obj = response.data;
                var table = "";
                var counter=1;
                for (var i = 0; i < obj.length; i++) {
                    if (obj[i].cartType == "b") {
                        table = table + '<tr class="table-danger">';
                    } else {
                        table = table + '<tr class="table-light">';
                    }
                     table = table + '<td scope="row">' + counter + '</td>';
                    table = table + '<td scope="row">' + obj[i].cardNumber + '</td>';
                    table = table + '<td>' + obj[i].cardHolder + '</td>';
                    table = table + '<td>' + obj[i].fatherOrHusband + '</td>';
                    table = table + '<td>' + obj[i].motherName + '</td>';
                    table = table + '<td>' + obj[i].unit + '</td>';
                    table = table + '</tr>';
                    counter = counter+1;
                }
                $("#cardTable").append(table);
	}

    this.add = function() {

        $("#mainContent").empty();
        var content = "";
        content = content + '<div class="row">'
        content = content + '<div class="col-lg-10">'

        content = content + '<div class="card">'
        content = content + '<div class="card-body">'
        content = content + '<div class="dataTable-top"> <h6 class="card-title" style="float:left;">Add Ration Card</h6>'
        content = content + '<button type="button" onclick="allCard.openFile();" class="btn btn-primary" style="float:right;" ><i class="bi bi-folder me-1"></i>Upload</button>'
        content = content + '<input type="file" id="uploadFile" style="display:none;">'
        content = content + '</div>'
        content = content + '<form>'

        content = content + '<div class="row mb-3">'
        content = content + '<label for="inputEmail3" class="col-sm-2 col-form-label">राशन कार्ड संख्या</label>'
        content = content + '<div class="col-sm-10">'
        content = content + '<input type="number" class="form-control" id="cardNumber" required>'
        content = content + '</div>'
        content = content + '</div>'

        content = content + '<div class="row mb-3">'
        content = content + '<label for="inputEmail3" class="col-sm-2 col-form-label">धारक का नाम</label>'
        content = content + '<div class="col-sm-10">'
        content = content + '<input type="text" class="form-control" id="cardHolder" required>'
        content = content + '</div>'
        content = content + '</div>'

        content = content + '<div class="row mb-3">'
        content = content + '<label for="inputEmail3" class="col-sm-2 col-form-label">पिता/पति का नाम</label>'
        content = content + '<div class="col-sm-10">'
        content = content + '<input type="text" class="form-control" id="fatherOrHusband">'
        content = content + '</div>'
        content = content + '</div>'

        content = content + '<div class="row mb-3">'
        content = content + '<label for="inputEmail3" class="col-sm-2 col-form-label">माता का नाम</label>'
        content = content + '<div class="col-sm-10">'
        content = content + '<input type="text" class="form-control" id="mother">'
        content = content + '</div>'
        content = content + '</div>'

        content = content + '<div class="row mb-3">'
        content = content + '<label for="inputEmail3" class="col-sm-2 col-form-label">कुल यूनिट</label>'
        content = content + '<div class="col-sm-10">'
        content = content + '<input type="number" class="form-control" id="unit">'
        content = content + '</div>'
        content = content + '</div>'
        
        		content = content+'<div class="row mb-3">'
                content = content+'<label class="col-sm-2 col-form-label">Card Type</label>'
                content = content+'<div class="col-sm-10">'
                  content = content+'<select class="form-select" aria-label="Default select example" id="cardType">'
                    content = content+'<option value="a">Antyodaya</option>'
                    content = content+'<option value="b">BPL</option>'
                      
                  content = content+'</select>'
                content = content+'</div>'
              content = content+'</div>'

        content = content + '<div class="text-center">'
        content = content + '<button type="submit" onclick="allCard.saveCard();" class="btn btn-primary">Submit</button>'
        content = content + '<button type="reset" class="btn btn-secondary">Reset</button>'
        content = content + '</div>'
        content = content + '</form>'

        content = content + '</div>'
        content = content + '</div>'
        $("#mainContent").append(content);
    }


    this.openFile = function() {
        $("#uploadFile").click();
        $('#uploadFile').bind("change", this.fileSelected);
    }


    this.fileSelected = function() {
        var fileName = $(this).val();
        var files = $('#uploadFile')[0].files;
        var formData = new FormData();
        formData.append("file", files[0]);
        formData.append("upload_file", true);
        var response = ajax.upload("/rationcard/upload", formData)
        
    }
    
    this.saveCard = function(){
     var cardNumber = $("#cardNumber").val();
     var cardHolder = $("#cardHolder").val();
     var fatherOrHusband = $("#fatherOrHusband").val();
     var mother = $("#mother").val();
     var unit = $("#unit").val();
     var cardType = $("#cardType").val();
      var obj = {"cardNumber":cardNumber,"cardHolder":cardHolder, "fatherOrHusband":fatherOrHusband,"motherName":mother, "cartType":cardType,"unit":unit};
      var response = ajax.post("/rationcard/save",obj);
        if(response.status == "success"){
          ajax.alert(response.status, "Added Successfully.");
          $("#closepopup").click();
          allCard.show();
        }else{
        	ajax.alert(response.status, response.error);
        }
    }
}