<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="ISO-8859-1">
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Register - RDMS</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="/img/favicon.png" rel="icon">
    <link href="/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="/vendor/simple-datatables/style.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="/css/style.css" rel="stylesheet">

    <!-- =======================================================
    * Template Name: NiceAdmin - v2.3.1
    * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
    * Author: BootstrapMade.com
    * License: https://bootstrapmade.com/license/
    ======================================================== -->
</head>

<body>

<main>
    <div class="container">
        <div class="col-lg-6" style="position:absolute;width:40%;left: 40%;z-index:9;" id="alert">
        </div>

        <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

                        <div class="d-flex justify-content-center py-4">
                            <a href="/login" class="logo d-flex align-items-center w-auto">
                                <img src="/img/logo.png" alt="">
                                <span class="d-none d-lg-block">RDMS</span>
                            </a>
                        </div><!-- End Logo -->

                        <div class="card mb-3">

                            <div class="card-body">

                                <div class="pt-4 pb-2">
                                    <h5 class="card-title text-center pb-0 fs-4">Create an Account</h5>
                                    <p class="text-center small">Enter your details to create account</p>
                                </div>

                                <form class="row g-3 needs-validation" novalidate>


                                    <div class="col-12">
                                        <label for="yourVillage" class="form-label">Your Village</label>

                                            <select class="form-select" aria-label="Default select example" id="yourVillage">
                                                <option>Select Your Village</option>
                                                <option value="3174008001">ARJAW</option>
                                                <option value="3174008003">BACHHHAIPUR</option>
                                                <option value="3174008004">BARGADWA</option>
                                                <option value="3174008048">BHATAULI</option>
                                                <option value="3174008060">Bhotaha</option>
                                                <option value="3174008006">BUGHAIE</option>
                                                <option value="3174008007">BUJURGVAR</option>
                                                <option value="3174008008">CHAKIYA</option>
                                                <option value="3174008009">CHANDAULI MAFI</option>
                                                <option value="3174008010">CHAURA KALA</option>
                                                <option value="3174008011">CHHAPRA MAGRVI</option>
                                                <option value="3174008012">CHHITAONI</option>
                                                <option value="3174008016">DHAORAHARA</option>
                                                <option value="3174008013">DIHVA</option>
                                                <option value="3174008049">DULAMPUR</option>
                                                <option value="3174008014">DULHAPAR KALA</option>
                                                <option value="3174008015">GAGARGAD</option>
                                                <option value="3174008055">Gaurapar Gosai Singhpur</option>
                                                <option value="3174008050">Goapar</option>
                                                <option value="3174008017">HADIYA MAFI</option>
                                                <option value="3174008052">Karji Rustampur</option>
                                                <option value="3174008005">KATAHA BHITHA</option>
                                                <option value="3174008020">KHAIRA</option>
                                                <option value="3174008059">Kharcha</option>
                                                <option value="3174008021">KHEWASIYA</option>
                                                <option value="3174008022">KISHUNPUR</option>
                                                <option value="3174008023">KOHALWA</option>
                                                <option value="3174008024">KUDAWA</option>
                                                <option value="3174008058">Laukiha</option>
                                                <option value="3174008025">MADPAUNI</option>
                                                <option value="3174008026">MAHESAR PUR</option>
                                                <option value="3174008027">MAJHAKHARAGPUR</option>
                                                <option value="3174008053">Majhoura</option>
                                                <option value="3174008028">MAKDUMPUR</option>
                                                <option value="3174008030">MURADEEHA</option>
                                                <option value="3174008047">MUTHAHI KHURD</option>
                                                <option value="3174008029">MUTHHIKALA</option>
                                                <option value="3174008031">NAKAHA</option>
                                                <option value="3174008032">NARAYNPUR</option>
                                                <option value="3174008033">NIHAILA</option>
                                                <option value="3174008051">Pachra</option>
                                                <option value="3174008034">PADRIYA</option>
                                                <option value="3174008035">PARAHAR GOVIND</option>
                                                <option value="3174008037">PARASA</option>
                                                <option value="3174008057">Parasir</option>
                                                <option value="3174008036">PARMAHAR</option>
                                                <option value="3174008038">PAULI</option>
                                                <option value="3174008039">RANIPUR</option>
                                                <option value="3174008040">ROSYA BAZAR</option>
                                                <option value="3174008042">SAKURCHAK</option>
                                                <option value="3174008041">SANICHARA BAZAR</option>
                                                <option value="3174008054">Sewaipar</option>
                                                <option value="3174008043">SHIVBAKHRI</option>
                                                <option value="3174008044">SONHAN</option>
                                                <option value="3174008045">TEJPUR</option>
                                                <option value="3174008056">Udaha</option>
                                                <option value="3174008057">Guest 1</option>
                                                <option value="3174008058">Guest 2</option>
                                            </select>

                                        <div class="invalid-feedback">Please, enter your name!</div>
                                    </div>

                                    <div class="col-12">
                                        <label for="yourName" class="form-label">Your Name</label>
                                        <input type="text" name="name" class="form-control" id="yourName" required>
                                        <div class="invalid-feedback">Please, enter your name!</div>
                                    </div>

                                    <div class="col-12">
                                        <label for="yourmobile" class="form-label">Your Mobile</label>
                                        <input type="mobile" name="email" class="form-control" id="yourMobile" required>
                                        <div class="invalid-feedback">Please enter a valid mobile</div>
                                    </div>

                                    <div class="col-12">
                                        <label for="yourEmail" class="form-label">Your Email</label>
                                        <input type="email" name="email" class="form-control" id="yourEmail" required>
                                        <div class="invalid-feedback">Please enter a valid Email adddress!</div>
                                    </div>

                                    <div class="col-12">
                                        <label for="yourUsername" class="form-label">Username</label>
                                        <div class="input-group has-validation">
                                            <span class="input-group-text" id="inputGroupPrepend">@</span>
                                            <input type="text" name="username" class="form-control" id="yourUsername" required>
                                            <div class="invalid-feedback">Please choose a username.</div>
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <label for="yourPassword" class="form-label">Password</label>
                                        <input type="password" name="password" class="form-control" id="yourPassword" required>
                                        <div class="invalid-feedback">Please enter your password!</div>
                                    </div>

                                    <div class="col-12">
                                        <div class="form-check">
                                            <input class="form-check-input" name="terms" type="checkbox" value="" id="acceptTerms" required>
                                            <label class="form-check-label" for="acceptTerms">I agree and accept the <a href="#">terms and conditions</a></label>
                                            <div class="invalid-feedback">You must agree before submitting.</div>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <button class="btn btn-primary w-100" type="button" onclick="register()">Create Account</button>
                                    </div>
                                    <div class="col-12">
                                        <p class="small mb-0">Already have an account? <a href="/login">Log in</a></p>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </section>

    </div>
</main><!-- End #main -->

<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="/vendor/apexcharts/apexcharts.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/vendor/chart.js/chart.min.js"></script>
<script src="/vendor/echarts/echarts.min.js"></script>
<script src="/vendor/quill/quill.min.js"></script>
<script src="/vendor/simple-datatables/simple-datatables.js"></script>
<script src="/vendor/tinymce/tinymce.min.js"></script>

<script src="/vendor/php-email-form/validate.js"></script>

<!-- Template Main JS File -->
<script src="/js/main.js"></script>
<script src="/js/main.js"></script>
<script src="/appjs/allCard.js"></script>
<script src="/appjs/month.js"></script>
<script src="/appjs/distribute.js"></script>
<script src="/appjs/view.js"></script>
<script src="/appjs/ajax.js"></script>
<script src="/appjs/setting.js"></script>
<script src="/appjs/main.js"></script>

<script>

    function register(){
        var url = "/user/register";

        var villageName =$("#yourVillage").find('option:selected').text()
        var name =  $("#yourName").val();
        var mobile = $("#yourMobile").val();
        var email = $("#yourEmail").val();
        var userName = $("#yourUsername").val();
        var password = $("#yourPassword").val();
        var obj ={};
        obj["villageName"]= villageName;
        obj["name"]= name;
        obj["mobile"]= mobile;
        obj["email"]= email;
        obj["userName"]= userName;
        obj["password"]= password;
       var response = ajax.post(url, obj);
     if (response.status == "success") {
            ajax.alert("success", "Your registration is successfull , Please login to access application");
        } else {
            ajax.alert("error", response.error);
        }

    }



</script>

</body>

</html>