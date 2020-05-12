<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%-- <%@ include file="../layout/taglib.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>

            <title>COMMANDE FORM</title>
            <meta charset="UTF-8" enctype="multipart/form-data">
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
                .error {
                    color:red;                  
                }
            </style>
        </head>
        <body>

            <jsp:include page="topNavigationBarInclude_1.jsp"/>
        <section class="mbr-section mbr-section-hero mbr-section-full mbr-parallax-background mbr-section-with-arrow" id="header1-g" style="background-image: url(assets/images/jumbotron.jpg);">

            <div style="margin-left: 10%;margin-top: 10%;">
                <h1 style="color: #F1C050">Créer une commande</h1>
                <h3 style="color: #F1C050">${consigne}</h3>
                <h1 style="color: #67b168">${message}</h1>
            </div >

            <form:form method="POST" modelAttribute="commande" class="form-horizontal">
                <table  style="margin-left: 10%;">
                    <%--                    <tr class="form-group">
                                            <td><form:label  path="dateLavage" class="control-label col-sm-2" for="dateLavage" ></form:label></td>
                                            <td style="width:100%;"><div class="col-sm-10" style="margin-top: 2%;" ><form:input   type="text" path="dateLavage" class="form-control" id="dateLavagedatepicker" placeholder="Enter dateLavage" value="03-09-2017 00:00:00" onBlur="onlyDateAndTimeFormat(this.id);"/></div></td>
                                        </tr>--%>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="dateLavage">dateLavage:</label>
                        <div class="col-sm-10">
                            <form:input   type="date" pattern="yyyy-MM-dd" path="dateLavage" class="form-control" id="dateLavage" placeholder="Entrez dateLavage" name="dateLavage"/>
                        </div>
                        <div  class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="dateLavage" cssClass="error"/>
                        </div>
                    </div>

                    <%--                    <tr class="form-group">                
                                            <td><form:label path="idPrestation" class="control-label col-sm-2" for="idPrestation">idPrestation:</form:label></td>
                                            <td style="width:100%;"><div class="col-sm-10" style="margin-top: 2%;"><form:input path="idPrestation" class="form-control" id="idPrestation" placeholder="Enter idPrestation"onBlur="onlyNumbersFilter(this.id);"/></div></td>
                                        </tr>  --%> 
                    <%--                    <tr class="form-group">
                                            <td >
                                                <label for="prestation" class="col-sm-2 control-label">Prestation:</label>
                                                <div class="col-sm-14 text-xs-center"  style="margin-top: 2%;" >
                                                    <select class="form-control center" name="prestationId"  id="prestation" >                                
                                                        <c:forEach items="${listPrestation}" var="prest">
                                                            <option value="${prest.idPrestation}" style="padding-left: 20%;" >${prest.idPrestation}:  ${prest.libellePrestation} -  ${prest.prixPrestation} euro TTC;</option>                      
                                                        </c:forEach>
                                                    </select>          

                            </div>
                        </td>     
                    </tr>
                    --%>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="idPrestation">Prestation:</label>
                        <div class="col-sm-10 selectContainer">
                            <div class="input-group"> 
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-list"></i>
                                </span>
                                <form:select path="idPrestation" name="idPrestation" class="form-control selectpicker" id="idPrestation" >
                                    <%--<form:option value=""></form:option>
                                        <form:option value="Client ">Client particulier</form:option>
                                        <form:option value="MicroEntr ">Micro Entreprenneur</form:option>
                                        <form:option value="Admin">Admin</form:option> 
                                    --%>
                                    <c:forEach items="${listPrestation}" var="prest">
                                        <form:option value="${prest.idPrestation}" style="padding-left: 20%;" >${prest.idPrestation}:  ${prest.libellePrestation} -  ${prest.prixPrestation} euro TTC;</form:option>                      
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class=form-control" style="text-align: center;">
                                <form:errors htmlEscape="false" path="idPrestation" cssClass="error"/>
                            </div>
                        </div>
                    </div>


                    <%--                    <tr class="form-group">
                                            <td><form:label  path="creneauHoraireI" class="control-label col-sm-2" for="creneauHoraireI" >creneauHoraireI:</form:label></td>
                                            <td style="width:100%;"><div class="col-sm-10" style="margin-top: 2%;" ><form:input   type="text" path="creneauHoraireI" class="form-control" id="creneauHoraireIdatepicker" placeholder="Enter creneauHoraireI" value="03-09-2017 00:00:00" onBlur="onlyDateAndTimeFormat(this.id);"/></div></td>
                                        </tr> --%>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="creneauHoraireI">creneauHoraireI:</label>
                        <div class="col-sm-10">
                            <form:input type="time" value="00:00" path="creneauHoraireI" class="form-control" id="creneauHoraireI" placeholder="Entrez creneauHoraireI" name="creneauHoraireI"/>
                        </div>
                        <div  class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="creneauHoraireI" cssClass="error"/>
                        </div>
                    </div>


                    <%--                   <tr class="form-group">
                                           <td><form:label  path="creneauHoraireF" class="control-label col-sm-2" for="creneauHoraireF" >creneauHoraireF:</form:label></td>
                                           <td style="width:100%;"><div class="col-sm-10" style="margin-top: 2%;" ><form:input   type="text" path="creneauHoraireF" class="form-control" id="creneauHoraireFdatepicker" placeholder="Enter creneauHoraireF" value="03-09-2017 00:00:00" onBlur="onlyDateAndTimeFormat(this.id);"/></div></td>
                                       </tr>  --%>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="creneauHoraireF">creneauHoraireF:</label>
                        <div class="col-sm-10">
                            <form:input type="time" value="00:00" path="creneauHoraireF" class="form-control" id="creneauHoraireF" placeholder="Entrez creneauHoraireF" name="creneauHoraireF"/>
                        </div>
                        <div  class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="creneauHoraireF" cssClass="error"/>
                        </div>
                    </div>



                    <div class="form-group">
                        <label class="control-label col-sm-2" for="immatriculationVehicule">Immatriculation du Vehicule:</label>
                        <div class="col-sm-10">
                            <form:input path="immatriculationVehicule" class="form-control" id="immatriculationVehicule" placeholder="Entrez immatriculationVehicule" name="immatriculationVehicule"/>
                        </div>
                        <div  class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="immatriculationVehicule" cssClass="error"/>
                        </div>
                    </div>



                    <div class="form-group">
                        <label class="control-label col-sm-2" for="marqueType">Marque/Type du Vehicule:</label>
                        <div class="col-sm-10">
                            <form:input path="marqueType" class="form-control" id="marqueType" placeholder="Entrez marqueType" name="marqueType"/>
                        </div>
                        <div  class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="marqueType" cssClass="error"/>
                        </div>
                    </div>
                        
                        
                        
                        <div class="form-group">
                        <label class="control-label col-sm-2" for="adresseStationnement">Adresse du Stationnement:</label>
                        <div class="col-sm-10">
                            <form:input path="adresseStationnement" class="form-control" id="adresseStationnement" placeholder="Entrez adresseStationnement" name="adresseStationnement"/>
                        </div>
                        <div  class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="adresseStationnement" cssClass="error"/>
                        </div>
                    </div>



                    <div class="form-group">
                        <label class="control-label col-sm-2" for="codePostaleSt">Code postale:</label>
                        <div class="col-sm-10">
                            <form:input path="codePostaleSt" class="form-control" id="codePostaleSt" placeholder="Entrez codePostaleSt"/>
                        </div>
                        <div class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="codePostaleSt" cssClass="error"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-2" for="villeSt">Ville:</label>
                        <div class="col-sm-10">
                            <form:input path="villeSt" class="form-control" id="villeSt" placeholder="Entrez villeSt"/>
                        </div>
                        <div class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="villeSt" cssClass="error"/>
                        </div>
                    </div>



                    <div class="form-group">
                        <label class="control-label col-sm-2" for="couleur">Couleur du Véhicule:</label>
                        <div class="col-sm-10">
                            <form:input path="couleur" class="form-control" id="couleur" placeholder="Entrez couleur"/>
                        </div>
                        <div class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="couleur" cssClass="error"/>
                        </div>
                    </div>




                    <%--                    <div class="form-group">
                                            <label class="control-label col-sm-2" for="handled">Lavage attribué:</label>
                                            <div class="col-sm-10">
                                                <form:radiobutton path="handled" class="form-control" id="handled" placeholder="Entrez handled" value="false" cssClass="radio"/>
                                                <form:radiobutton path="handled" class="form-control" id="handled" placeholder="Entrez handled" value="true" cssClass="radio"/>
                                            </div>
                                            <div class=form-control" style="text-align: center;">
                                                <form:errors htmlEscape="false" path="handled" cssClass="error"/>
                                            </div>
                                        </div>--%>




                    <div class="form-group">
                        <label class="control-label col-sm-2" for="handled">Lavage attribué:</label>
                        <div class="col-sm-10 selectContainer">
                            <div class="input-group"> 
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-list"></i>
                                </span>
                                <form:select path="handled" name="state" class="form-control selectpicker" >
                                    <%-- <form:option value="true">Oui</form:option> --%>
                                    <form:option value="false">Non</form:option> 
                                </form:select>
                            </div>
                            <div class=form-control" style="text-align: center;">
                                <form:errors htmlEscape="false" path="handled" cssClass="error"/>
                            </div>
                        </div>
                    </div>



                    <div class="form-group">
                        <label class="control-label col-sm-2" for="done">Lavage Effectué:</label>
                        <div class="col-sm-10 selectContainer">
                            <div class="input-group"> 
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-list"></i>
                                </span>
                                <form:select path="done" name="state" class="form-control selectpicker" >
                                    <%-- <form:option value="true">Oui</form:option> --%>
                                    <form:option value="false">Non</form:option> 
                                </form:select>
                            </div>
                            <div class=form-control" style="text-align: center;">
                                <form:errors htmlEscape="false" path="done" cssClass="error"/>
                            </div>
                        </div>
                    </div>



                    <label class="control-label col-sm-2"></label>
                    <div class="col-sm-10">
                        <button type="submit" class="btn btn-success no-border  btn-block" style="width: 50%;">Submit</button>
                    </div>
                </table>
            </form:form>

        </section>

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

        <script src="js/onlyNumbersFilter.js"></script>
        <script src="js/isThisAPrice.js"></script>
        <script src="js/isThisNumeric.js"></script>
        <script src="js/onlyDateAndTimeFormat.js"></script>

        <input name="animation" type="hidden">

        <script>
        </script>
        </body>
    </html>
</f:view>

