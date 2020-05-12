<%-- 
    Document   : gererCommandeOfLaveur
    Created on : 18 janv. 2019, 08:29:50
    Author     : BigWave
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  

<%-- <%@ page import="mgw.domaine_lavage_auto.tools.Validateur"%>--%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <style>
        #playlistsearchbar{
                    float: top;
                    width: 40%;
                    height: 25%;
                    margin: 1%;
                    border: 0.3px solid #73AD21;


                }
                </style>
        <title>COMMANDES(POUR LAVEURS) CMS</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="generator" content="Mobirise v3.12.1, mobirise.com">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="assets/images/logo.png" type="image/x-icon">
        <meta name="description" content="">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic&amp;subset=latin">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,700">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i">
        <link rel="stylesheet" href="assets/bootstrap-material-design-font/css/material.css">
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
    <body style="background-color: #2aabd2; ">

        <jsp:include page="topNavigationBarInclude_1.jsp"/>



        <div style="margin-left: 10%;margin-top: 4%;">
            <h1 style="color: #F1C050">LISTE DES COMMANDES DE LAVAGE</h1>
            <h3 style="color: #F1C050">${consigne}</h3>
            <h1 style="color: #67b168">${message}</h1>
        </div >

        <center>
            <form action="./commandesCms" class="form-wrapper" id="playlistsearchbar">                
                <input type="text" name="search" id="search" placeholder="Search for..." path="search" class="form-control" required/> 
                <input type="submit" value="SEARCH" id="submit" >
            </form>
        </center>

        <center><div style="width: 98%; padding-top: 5%;" class="taches table-responsive">

                <!--            <div style="width: 20%;padding-bottom: 3%;"><a href="newCommande.html"><button >CREER/AJOUTER UN NOUVEAU COMMANDE</button></a></div>-->

                <table class="table table-bordered table-hover table-striped" border="1">
                    <thead>
                        <tr>
                            <th>id</th>
                            <th>date de Lavage</th>
                            <th>Heure Début</th>
                            <th>Heure Fin</th>
                            <th>Prestation</th> 

                            <th>Adresse de Stationnement</th>
                            <th>code Postale</th>
                            <th>ville</th>
                            <th>immatriculation</th>
                            <th>Marque/Type</th>
                            <th>couleur</th>
                            <th>Lavage attribué</th>
                            <th>Lavage effectué</th>

                            <th>id Client</th>
                            <th>id Laveur</th>
                            <TH></TH>
                            <!--<TH></TH>-->
                            <!--<TH></TH>-->
                        </tr>
                    </thead>

                    <tbody>



                        <c:forEach items="${commandes}" var="comm" >
                            <tr class="${comm.cssRawColorCode}">
                                <td>${comm.idCommande}</td>
                                <td style="width: 34%;"><fmt:formatDate value="${comm.dateLavage}"
                                                pattern="dd-MM-yyyy" /></td>
                                <td type="time" style="width: 8%;"><fmt:formatDate value="${comm.creneauHoraireI}"
                                                pattern = "HH:mm" /></td>
                                <td type="time" style="width: 8%;"><fmt:formatDate value="${comm.creneauHoraireF}"
                                                pattern = "HH:mm" /></td>

                                <td style="width: 8%;">${comm.idPrestation} : ${comm.idPrestation.libellePrestation} </td>

                                <td>${comm.adresseStationnement}</td>
                                <td>${comm.codePostaleSt}</td>
                                <td>${comm.villeSt}</td>
                                <td>${comm.immatriculationVehicule}</td>
                                <td>${comm.marqueType}</td>
                                <td>${comm.couleur}</td>
                                <td>${comm.handled}</td>
                                <td>${comm.done}</td>

                                <td>${comm.idClient}</td>
                                <td>${comm.idLaveur}</td>
                                <td style="width: 4%;"><a href="gererLaveursOfcommandes.html?idCommandeParam=${comm.idCommande}" style="padding :3%;"><button style="color: black;background-color: #c9970d">Prendre à Charge</button></a></td>
                                <!--<td style="width: 4%;"><a href="showCommandeToUpdate.html?idCommandeParam=${comm.idCommande}" style="padding :3%;"><button style="color: black;background-color: #c0a375">Modifier Commande</button></a></td>-->
                                <!--<td style="width: 4%;"><a href="deleteCommande.html?idCommandeParam=${comm.idCommande}" style="padding :3%;"><button onclick="return confirm('Êtes-vous sûr de vouloir supprimer ? Are you sure you want to delete?')">Supprimer Commande</button></a></td>-->

                            </tr>
                        </c:forEach>



                    </tbody>
                </table>


                <!-- ============== PAGINATION COTE VUE JSP : ======================================================== -->
                <div>

                    <ul class="nav nav-pills" style="background-color: #F1C050">	
                        <c:forEach items="${pages}" var="pa" varStatus="p">	
                            <li>
                                <a href="./commandesCms.html?page=${p.index}" ><button >${p.index}</button></a>                            
                            </li>
                        </c:forEach>
                    </ul>

                </div>
            </div></center>


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



    <!--        <script src="assets/bootstrap-carousel-swipe/bootstrap-carousel-swipe.js"></script>
    
    <script src="js/isThisAPassword.js"></script>
        <script src="js/isThisAPhoneNumber.js"></script>
        <script src="js/isThisAPrice.js"></script>
        <script src="js/isThisAnEmail.js"></script>
        <script src="js/isThisNumeric.js"></script>
        <script src="js/onlyDateAndTimeFormat.js"></script>
        <script src="js/onlyNumbersFilter.js"></script>-->


    <!--    <script src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>
        <script src="js/validation.js"></script>-->

    <input name="animation" type="hidden">
</body>

</html>