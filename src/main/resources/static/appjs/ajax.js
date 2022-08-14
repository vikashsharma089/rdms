var AJAX = function() {

    this.post = function(url, data) {
        var response;
        $.ajax({
            type: "POST",
            url: url,
            success: function(data) {
                response = data;
            },
            error: function(error) {
                response = error;
            },
            contentType: "application/json",
            dataType: "json",
            async: false,
            data: JSON.stringify(data),
            cache: false,
            timeout: 60000
        });
        return response;
    }

    this.get = function(url) {
        var response;
        $.ajax({
            type: "GET",
            url: url,
            success: function(obj) {
                response = obj;
            },
            error: function(error) {
                response = error;
            },
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            timeout: 60000
        });
        return response;
    }

    this.upload = function(url, formData) {
        var bar = $('.progress-bar');
        var response;
        $.ajax({
            type: "POST",
            url: url,
            success: function(data) {
                response = data;
                if (response.status == "success") {
                    ajax.alert(response.status, response.total + " Record has been uploaded");
                }

                allCard.show();
                $("#progressbar").hide();
            },
            beforeSend: function() {
                $("#progressbar").show();
                var percentVal = '0%';
                bar.width(percentVal);
            },
            xhr: function() {
                var xhr = new window.XMLHttpRequest();
                xhr.upload.addEventListener("progress", function(evt) {
                    if (evt.lengthComputable) {
                        var percentComplete = evt.loaded / evt.total;
                        percentComplete = parseInt(percentComplete * 100);
                        bar.width(percentComplete + "%");
                    }
                }, false);
                return xhr;
            },
            error: function(error) {
                ajax.alert("error", error.responseJSON.error);
                $("#progressbar").hide();
            },
            async: true,
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            timeout: 60000
        });
        return response;

    }

    this.uploadProgressHandler = function uploadProgressHandler(event) {
        $("#loaded_n_total").html("Uploaded " + event.loaded + " bytes of " + event.total);
        var percent = (event.loaded / event.total) * 100;
        var progress = Math.round(percent);
        //$("#uploadProgressBar").html(progress + " percent na ang progress");
        bar.width(progress + "%");
        // $("#uploadProgressBar").css("width", progress + "%");
        //$("#status").html(progress + "% uploaded... please wait");
    }

    this.alert = function(status, message) {
        $('#alert').fadeIn( "fast" );
        $("#alert").empty();
        var html;
        if (status == "success") {
            html = '<div class="alert alert-success alert-dismissible fade show" role="alert">'
            html = html + '<i class="bi bi-check-circle me-1"></i>' + message + ''
            html = html + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>'
            html = html + '</div>'
        } else {
            html = '<div class="alert alert-danger alert-dismissible fade show" role="alert">'
            html = html + '<i class="bi bi-exclamation-octagon me-1"></i>' + message + ''
            html = html + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>'
            html = html + '</div>'
        }
        $("#alert").append(html);
        $('#alert').delay(1000).fadeOut('slow');
    }

}