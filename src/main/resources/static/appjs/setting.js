var SETTING = function() {

    this.show = function() {
        $(".toggle-sidebar-btn").click();
        $("#mainContent").empty();
        var table = "";
        table = table + '<div class="row">'
        table = table + '<div class="col-lg-5">'
        table = table + '<div class="card">'
        table = table + '<div class="card-body">'
        table = table + '<h5 class="card-title" style="float:left;">Ration Items</h5>'
        table = table + '<button type="button" onclick="" class="btn btn-primary" style="float:right;" data-bs-toggle="modal" data-bs-target="#addItemPopup"><i class="plus-square-fill me-1"></i>Add Items</button>'


        table = table + '<div class="modal fade" id="addItemPopup" tabindex="-1">'
        table = table + '<div class="modal-dialog modal-dialog-centered">'
        table = table + '<div class="modal-content">'
        table = table + '<div class="modal-header">'
        table = table + '<h5 class="modal-title">Add Ration Items</h5>'
        table = table + '<button type="button" id="closepopup2" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>'
        table = table + '</div>'
        table = table + '<div class="modal-body">'


        table = table + '<form>'

        table = table + '<div class="row mb-6">'
        table = table + '<label for="inputEmail3" class="col-sm-3 col-form-label">Item Name</label>'
        table = table + '<div class="col-sm-7">'
        table = table + '<input type="text" class="form-control" id="itemName" required>'
        table = table + '</div>'
        table = table + '</div>'


        table = table + '</form>'

        table = table + '</div>'
        table = table + '<div class="modal-footer">'
        table = table + '<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>'
        table = table + '<button type="button" class="btn btn-primary" onclick="setting.saveRationItem()">Save changes</button>'
        table = table + '</div>'
        table = table + '</div>'
        table = table + '</div>'
        table = table + '</div>'


        table = table + '<table class="table">'
        table = table + '<thead>'
        table = table + '<tr>'
        table = table + '<th scope="col">#</th>'
        table = table + '<th scope="col">Item Name</th>'
        table = table + '<th scope="col">Action</th>'
        table = table + '</tr>'
        table = table + '</thead>'
        table = table + '<tbody id="itemTable">'
        table = table + '<tr>'
        table = table + '<th scope="row">1</th>'
        table = table + '<td>Wheat</td>'
        table = table + '<td><i class="bx bxs-edit"></i></td>'
        table = table + '</tr>'
        table = table + '</tbody>'
        table = table + '</table>'
        table = table + '</div>'
        table = table + '</div>'
        table = table + '</div>'
        table = table + '<div class="col-lg-7">'
        table = table + '<div class="card">'
        table = table + '<div class="card-body">'
        table = table + '<h5 class="card-title" style="float:left;">Distribution Rules</h5>'

        table = table + '<div class="modal fade" id="editRulePopup" tabindex="-1">'
        table = table + '<div class="modal-dialog modal-dialog-centered">'
        table = table + '<div class="modal-content">'
        table = table + '<div class="modal-header">'
        table = table + '<h5 class="modal-title">Add Distribution Rule</h5>'
        table = table + '<button type="button" id="closepopup" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>'
        table = table + '</div>'
        table = table + '<div class="modal-body">'


        table = table + '<form>'

        table = table + '<div class="row mb-3">'
        table = table + '<label for="inputEmail3" class="col-sm-3 col-form-label">Item</label>'
        table = table + '<div class="col-sm-7">'
        table = table + '<input type="text" class="form-control" id="ruleItemName"  required>'
        table = table + '</div>'
        table = table + '</div>'




        table = table + '<div class="row mb-3">'
        table = table + '<label for="inputEmail3" class="col-sm-3 col-form-label">By</label>'
        table = table + '<div class="col-sm-7">'
        table = table + '<select class="form-select" aria-label="Default select example" id="distributeBy">'
        table = table + '<option value="unit">Unit</option>'
        table = table + '<option value="card">Card</option>'
        table = table + '</select>'
        table = table + '</div>'
        table = table + '</div>'

        table = table + '<div class="row mb-3">'
        table = table + '<label for="inputEmail3" class="col-sm-3 col-form-label">Kg</label>'
        table = table + '<div class="col-sm-7">'
        table = table + '<input type="number" class="form-control" id="kg" required placeholder="Enter value in Kg only">'
        table = table + '</div>'
        table = table + '</div>'

        table = table + '<div class="row mb-3">'
        table = table + '<label for="inputEmail3" class="col-sm-3 col-form-label">For Card</label>'
        table = table + '<div class="col-sm-7">'
        table = table + '<select class="form-select" aria-label="Default select example" disabled id="cardType">'
        table = table + '<option value="PHH">PHH</option>'
        table = table + '<option value="AAY">AAY</option>'
        table = table + '</select>'
        table = table + '</div>'
        table = table + '</div>'

        table = table + '<div class="row mb-3">'
        table = table + '<label for="inputEmail3" class="col-sm-3 col-form-label">Rate</label>'
        table = table + '<div class="col-sm-7">'
        table = table + '<input type="number" class="form-control" id="rate" required placeholder="Rate per Kg.">'
        table = table + '</div>'
        table = table + '</div>'

        table = table + '</form>'

        table = table + '</div>'
        table = table + '<div class="modal-footer">'
        table = table + '<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>'
        table = table + '<button type="button" class="btn btn-primary" onclick="setting.saveRules()">Save changes</button>'
        table = table + '</div>'
        table = table + '</div>'
        table = table + '</div>'
        table = table + '</div>'

        table = table + '<table class="table">'
        table = table + '<thead>'
        table = table + '<tr>'
        table = table + '<th scope="col">#</th>'
        table = table + '<th scope="col">Item</th>'
        table = table + '<th scope="col">By</th>'
        table = table + '<th scope="col">K.G.</th>'
        table = table + '<th scope="col">Card</th>'
        table = table + '<th scope="col">Rate</th>'
        table = table + '<th scope="col">Action</th>'
        table = table + '</tr>'
        table = table + '</thead>'
        table = table + '<tbody id="ruleTable">'
        table = table + '<tr>'
        table = table + '<th scope="row">1</th>'
        table = table + '<td>Wheat</td>'
        table = table + '<td>Unit</td>'
        table = table + '<td>28</td>'
        table = table + '<td>BPL</td>'
        table = table + '<td><i class="bx bxs-edit"></i></td>'
        table = table + '</tr>'
        table = table + '</tbody id="ruleTable">'
        table = table + '</table>'
        table = table + '</div>'
        table = table + '</div>'
        table = table + '</div>'
        table = table + '</div>'
        $("#mainContent").append(table);
        setting.loadItems();
        setting.loadRules();
    }

    this.saveRationItem = function() {
        var itemName = $("#itemName").val();
        var itemId = $("#itemName").attr("name");
        var obj = {};
        obj["itemName"] = itemName;
        obj["village"] = {
            "id": villageId
        };
        var url = "/setting/saveItems"
        if (itemId !== undefined && itemId !== "") {
            obj["id"] = itemId;
            url = "/setting/updateItem"
        }
        var response = ajax.post(url, obj);
        if (response.status === "success") {
            $("#closepopup").click();
            $("#closepopup2").click();
            setting.loadItems();
            setting.loadRules();
        } else {
            ajax.alert("error", response.error)
        }
    }

    this.saveRules = function() {
        var ruleId = $("#ruleItemName").attr("name");
        var stockId = $("#ruleItemName").attr("data");
        var by = $("#distributeBy").val();
        var kg = $("#kg").val();
        var rate = $("#rate").val();
        var cardType = $("#cardType").val();


        var obj = {};
        obj["id"] = ruleId;
        obj["perUnitOrCard"] = by;
        obj["kgPerUnitOrCard"] = kg;
        obj["rationCardType"] = cardType;
        obj["rate"] = rate;

        obj["stockItem"]={"id":stockId}
        var url = "/setting/updateRule"

        var response = ajax.post(url, obj);
        if (response.status === "success") {
            $("#closepopup").click();
            $("#closepopup2").click();

            setting.loadRules();
            functionLoadConfig();
        } else {
            ajax.alert("error", response.error)
        }
    }

    this.loadItems = function() {
        var response = ajax.get("/setting/loadItems");
        $("#itemTable").empty();

        var obj = response.data;
        var table = "";
        var counter = 1;
        for (var i = 0; i < obj.length; i++) {
            table = table + '<tr class="table-light">';
            table = table + '<td scope="row">' + counter + '</td>';
            table = table + '<td scope="row">' + obj[i].itemName + '</td>';
            table = table + '<td><i class="bx bxs-edit" onclick="setting.editItems(' + obj[i].id + ',this)" data="' + obj[i].itemName + '"></i></td>'
            table = table + '</tr>';
            counter = counter + 1;
        }
        $("#itemTable").append(table);

    }

    this.loadRules = function() {
        var map = {};
        map["AAY"] = "AAY";
        map["PHH"] = "PHH";
        var response = ajax.get("/setting/loadRules");
        $("#ruleTable").empty();
        var obj = response.data;
        var table = "";
        var counter = 1;
        for (var i = 0; i < obj.length; i++) {

            var cardType = map[obj[i].rationCardType]
            if (cardType == "AAY") {
                table = table + '<tr class="table-danger">';
            } else {
                table = table + '<tr class="table-light">';
            }
            table = table + '<td scope="row">' + counter + '</td>';
            table = table + '<td scope="row">' + obj[i].stockItem.itemName + '</td>';
            table = table + '<td scope="row">' + obj[i].perUnitOrCard + '</td>';
            table = table + '<td scope="row">' + obj[i].kgPerUnitOrCard + '</td>';
            table = table + '<td scope="row">' +cardType + '</td>';
            table = table + '<td scope="row">' + obj[i].rate + '</td>';
            table = table + '<td><i class="bx bxs-edit"  onclick="setting.editRuleDetail(' + obj[i].id + ',this,' + obj[i].stockItem.id + ')" data="' + window.btoa(JSON.stringify(obj[i])) +' "> </i></td>'
            table = table + '</tr>';
            counter = counter + 1;
        }
        $("#ruleTable").append(table);
    }

    this.editRuleDetail = function(id, obj,stockId) {
        $("#editRulePopup").modal('show')

        $("#ruleItemName").attr("name",id);
        var map = $(obj).attr("data");
        var ruleObj = JSON.parse(window.atob(map));
        $("#ruleItemName").val(ruleObj.stockItem.itemName);
        $("#distributeBy").val(ruleObj.perUnitOrCard);
        $("#kg").val(ruleObj.kgPerUnitOrCard);
        $("#cardType").val(ruleObj.rationCardType);
        $("#rate").val(ruleObj.rate);
        $("#ruleItemName").attr("data",stockId);
    }

    this.editItems = function(id, obj) {

        $("#addItemPopup").modal('show')
        $("#itemName").attr("name", id);
        $("#itemName").val($(obj).attr("data"));

    }
}