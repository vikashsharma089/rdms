<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
HttpSession sessionsa = request.getSession(true);
String LoggedUserId = (String) sessionsa.getAttribute("userId");
String name =  (String) sessionsa.getAttribute("name");
String village =  (String) sessionsa.getAttribute("village");
Integer LoggedSno = (Integer) sessionsa.getAttribute("sno");
String username = SecurityContextHolder.getContext().getAuthentication().getName();

%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1.0" name="viewport">

	<title>Dashboard - RDMS</title>
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
    * Template Name: NiceAdmin - v2.2.2
    * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
    * Author: BootstrapMade.com
    * License: https://bootstrapmade.com/license/
    ======================================================== -->
</head>

<body>

<!-- ======= Header ======= -->
<header id="header" class="header fixed-top d-flex align-items-center">

	<div class="d-flex align-items-center justify-content-between">
		<a href="/login" class="logo d-flex align-items-center">
			<img src="/img/logo.png" alt="">
			<span class="d-none d-lg-block">RDMS</span>
		</a>
		<i class="bi bi-list toggle-sidebar-btn"></i>
	</div><!-- End Logo -->

	<div class="search-bar">
		<form class="search-form d-flex align-items-center" method="POST" action="#">
			<input type="text" name="query" placeholder="Search" title="Enter search keyword">
			<button type="submit" title="Search"><i class="bi bi-search"></i></button>
		</form>
	</div><!-- End Search Bar -->

	<nav class="header-nav ms-auto">
		<ul class="d-flex align-items-center">

			<li class="nav-item d-block d-lg-none">
				<a class="nav-link nav-icon search-bar-toggle " href="#">
					<i class="bi bi-search"></i>
				</a>
			</li><!-- End Search Icon-->

			<li class="nav-item dropdown">





			</li><!-- End Notification Nav -->

			<li class="nav-item dropdown">

			</li><!-- End Messages Nav -->

			<li class="nav-item dropdown pe-3">

				<a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
					<img src="/img/profile-img.jpg" alt="Profile" class="rounded-circle">
					<span class="d-none d-md-block dropdown-toggle ps-2"><%=name%></span>
				</a><!-- End Profile Iamge Icon -->

				<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
					<li class="dropdown-header">
						<h6><%=name%></h6>
						<span><%=village%></span>
					</li>
					<li>
						<hr class="dropdown-divider">
					</li>

					<li>
						<a class="dropdown-item d-flex align-items-center" href="/">
							<i class="bi bi-person"></i>
							<span>My Profile</span>
						</a>
					</li>
					<li>
						<hr class="dropdown-divider">
					</li>

					<li>
						<a class="dropdown-item d-flex align-items-center" href="/">
							<i class="bi bi-gear"></i>
							<span>Account Settings</span>
						</a>
					</li>
					<li>
						<hr class="dropdown-divider">
					</li>

					<li>
						<a class="dropdown-item d-flex align-items-center" href="/">
							<i class="bi bi-question-circle"></i>
							<span>Need Help?</span>
						</a>
					</li>
					<li>
						<hr class="dropdown-divider">
					</li>

					<li>
						<a class="dropdown-item d-flex align-items-center" href="/logout">
							<i class="bi bi-box-arrow-right"></i>
							<span>Sign Out</span>
						</a>
					</li>

				</ul><!-- End Profile Dropdown Items -->
			</li><!-- End Profile Nav -->

		</ul>
	</nav><!-- End Icons Navigation -->

</header><!-- End Header -->

<!-- ======= Sidebar ======= -->
<aside id="sidebar" class="sidebar">

	<ul class="sidebar-nav" id="sidebar-nav">

		<li class="nav-item">
			<a class="nav-link " href="#">
				<i class="bi bi-grid"></i>
				<span>Dashboard</span>
			</a>
		</li><!-- End Dashboard Nav -->


		<li class="nav-item" onclick="setting.show()">
			<a class="nav-link " href="#">
				<i class="bi bi-gear"></i>
				<span>Settings</span>
			</a>
		</li>

		<li class="nav-item" onclick="allCard.show();">
			<a class="nav-link " href="#" >
				<i class="bi bi-journal-text"></i>
				<span>All Card</span>
			</a>
		</li><!-- End Dashboard Nav -->

		<li class="nav-item" onclick="allCard.add();">
			<a class="nav-link " href="#" >
				<i class="bi bi-plus-circle"></i>
				<span>Add Card</span>
			</a>
		</li><!-- End Dashboard Nav -->



		<li class="nav-item">
			<a class="nav-link collapsed" data-bs-target="#components-nav" data-bs-toggle="collapse" href="#">
				<i class="bi bi-journal-text"></i><span>Ration Distribution</span><i class="bi bi-chevron-down ms-auto"></i>
			</a>
			<ul id="components-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
				<li onclick="month.show()">
					<a href="#">
						<i class="bi bi-circle"></i><span>Stock</span>
					</a>
				</li>

				<li onclick="distribute.show()">
					<a href="#">
						<i class="bi bi-circle"></i><span>Distribute</span>
					</a>
				</li>

				<li onclick="dview.show()">
					<a href="#">
						<i class="bi bi-circle"></i><span>Distributed</span>
					</a>
				</li>

				<li onclick="dview.showRemains()">
					<a href="#">
						<i class="bi bi-circle"></i><span>Remaining</span>
					</a>
				</li>

			</ul>
		</li><!-- End Components Nav -->

	</ul>

</aside><!-- End Sidebar-->

<main id="main" class="main">

	<div class="progress mt-3" id="progressbar" style="display:none;">
		<div class="progress-bar progress-bar-striped bg-success progress-bar-animated" role="progressbar" style="width: 0%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
	</div>
	<div class="col-lg-6" style="position:absolute;width:40%;left: 40%;z-index:9;" id="alert">
	</div>

	<div class="pagetitle">
		<h1>Dashboard</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">Home</a></li>
				<li class="breadcrumb-item active">Dashboard</li>
			</ol>
		</nav>
	</div><!-- End Page Title -->



	<section class="section dashboard" id="mainContent">
	</section>

</main><!-- End #main -->

<!-- ======= Footer ======= -->
<footer id="footer" class="footer">

</footer><!-- End Footer -->

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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>

<!-- Template Main JS File -->
<script src="/js/main.js"></script>
<script src="/appjs/allCard.js"></script>
<script src="/appjs/month.js"></script>
<script src="/appjs/distribute.js"></script>
<script src="/appjs/view.js"></script>
<script src="/appjs/ajax.js"></script>
<script src="/appjs/setting.js"></script>
<script src="/appjs/main.js"></script>

<script>
	var villageId=1;
	var ruleSetting = new Map();
	$( document ).ready(function() {
		var response = ajax.get("/setting/loadRules");
		var obj = response.data;
		for(var i=0; i<obj.length; i++){
			ruleSetting.set(obj[i].rationCardType+"_"+obj[i].stockItem.itemName, obj[i]);
		}
	});



</script>
</body>

</html>