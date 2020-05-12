<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Java unsuccessful page</title>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="generator" content="Mobirise v4.3.3, mobirise.com">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="assets/images/logo.png" type="image/x-icon">
        <meta name="description" content="">

        <link rel="stylesheet" href="assets/bootstrap-material-design-font/css/material.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,700">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic&subset=latin">
        <link rel="stylesheet" href="assets/tether/tether.min.css">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/animate.css/animate.min.css">
        <link rel="stylesheet" href="assets/dropdown/css/style.css">
        <link rel="stylesheet" href="assets/theme/css/style.css">
        <link rel="stylesheet" href="assets/mobirise/css/mbr-additional.css" type="text/css">

      <link rel="stylesheet" type="text/css" href="<c:url value="css/bootstrap.min.css"/>" />
        <script src="js/jquery.min.js"></script>  
        <style>
            .dropdown:last-child .dropdown-submenu {
                left:-100%;
            }
        </style>
    </head>
    <body>

        <jsp:include page="topNavigationBarInclude_1.jsp"/>
        <br><br>
        <center>     
            ${statut}
        </center>
        <br><br>

    <br><br><br>
    <footer class="mbr-small-footer mbr-section mbr-section-nopadding" id="footer1-2" style="background-color: rgb(50, 50, 50); padding-top: 1.75rem; padding-bottom: 1.75rem;">

        <% String maPage = "copyrights_on_footer.jsp";%>
        <jsp:include page="<%=maPage%>" />



    </footer>


    <script src="assets/web/assets/jquery/jquery.min.js"></script>
    <script src="assets/tether/tether.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/smooth-scroll/smooth-scroll.js"></script>
    <script src="assets/touch-swipe/jquery.touch-swipe.min.js"></script>
    <script src="assets/viewport-checker/jquery.viewportchecker.js"></script>
    <script src="assets/dropdown/js/script.min.js"></script>
    <script src="assets/bootstrap-carousel-swipe/bootstrap-carousel-swipe.js"></script>
    <script src="assets/jquery-mb-ytplayer/jquery.mb.ytplayer.min.js"></script>
    <script src="assets/jarallax/jarallax.js"></script>
    <script src="assets/theme/js/script.js"></script>
    <script src="assets/mobirise-slider-video/script.js"></script>


    <input name="animation" type="hidden">
</body>
</html>