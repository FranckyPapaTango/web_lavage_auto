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

            <title>CLIENT FORM</title>
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
                .error {
                    color:red;                  
                }
            </style>
        </head>
        <body>

            <jsp:include page="topNavigationBarInclude_1.jsp"/>
        <section class="mbr-section mbr-section-hero mbr-section-full mbr-parallax-background mbr-section-with-arrow" id="header1-g" style="background-image: url(assets/images/jumbotron.jpg);">

            <div style="margin-left: 10%;margin-top: 10%;">
                <h1 style="color: #F1C050">Créer un Utilisateur</h1>
                <h3 style="color: #F1C050">${consigne}</h3>
                <h1 style="color: #67b168">${message}</h1>
            </div >

            <%--            <form:form method="POST" modelAttribute="client" class="form-horizontal">
                <table  style="margin-left: 10%;">
                    <tr class="form-group">                
                        <td><form:label path="nom" class="control-label col-sm-2" for="nom">nom:</form:label></td>
                        <td ><div class=form-control" style="margin-top: 2%; width:100%;"><form:input path="nom" class="form-control" id="nom" placeholder="Enter nom"/></div></td>
                    <div small id="emailHelp" class=form-control"><form:errors path="nom" cssClass="error"/></div>
                    </tr> 
                    <tr class="form-group">                
                        <td><form:label path="prenom" class="control-label col-sm-2" for="prenom">prenom:</form:label></td>
                        <td style="width:100%;"><div class="col-sm-10" style="margin-top: 2%;"><form:input path="prenom" class="form-control" id="prenom" placeholder="Enter prenom"/></div></td>
                        <td><form:errors path="prenom" cssClass="error"/></td>
                    </tr> 
                    <tr class="form-group">                
                        <td><form:label path="statut" class="control-label col-sm-2" for="statut">statut:</form:label></td>
                        <td style="width:100%;"><div class="col-sm-10" style="margin-top: 2%;"><form:input path="statut" class="form-control" id="statut" placeholder="Enter statut"/></div></td>
                        <td><form:errors path="statut" cssClass="error"/></td>
                    </tr> 
                    <tr class="form-group">                
                        <td><form:label path="loginEmail" class="control-label col-sm-2" for="loginEmail">loginEmail:</form:label></td>
                        <td style="width:100%;"><div class="col-sm-10" style="margin-top: 2%;"><form:input path="loginEmail" class="form-control" id="loginEmail" placeholder="Enter loginEmail"/></div></td>
                        <td><form:errors path="loginEmail" cssClass="error"/></td>
                    </tr> 
                    <tr class="form-group">                
                        <td><form:label path="password" class="control-label col-sm-2" for="password">password:</form:label></td>
                        <td style="width:100%;"><div class="col-sm-10" style="margin-top: 2%;"><form:input path="password" class="form-control" id="password" placeholder="Enter password"/></div></td>
                        <td><form:errors path="password" cssClass="error"/></td>
                    </tr> 
                    <tr class="form-group">                
                        <td><form:label path="telFixe" class="control-label col-sm-2" for="telFixe">telFixe:</form:label></td>
                        <td style="width:100%;"><div class="col-sm-10" style="margin-top: 2%;"><form:input path="telFixe" class="form-control" id="telFixe" placeholder="Enter telFixe"/></div></td>
                    </tr> 
                    <tr class="form-group">                
                        <td><form:label path="telMobile" class="control-label col-sm-2" for="telMobile">telMobile:</form:label></td>
                        <td style="width:100%;"><div class="col-sm-10" style="margin-top: 2%;"><form:input path="telMobile" class="form-control" id="telMobile" placeholder="Enter telMobile"/></div></td>
                    </tr> 
                    <tr class="form-group">                
                        <td><form:label path="numVoieTypeVoieLibelleVoie" class="control-label col-sm-2" for="numVoieTypeVoieLibelleVoie">numVoieTypeVoieLibelleVoie:</form:label></td>
                        <td style="width:100%;"><div class="col-sm-10" style="margin-top: 2%;"><form:input path="numVoieTypeVoieLibelleVoie" class="form-control" id="numVoieTypeVoieLibelleVoie" placeholder="Enter numVoieTypeVoieLibelleVoie"/></div></td>
                        <td><form:errors path="numVoieTypeVoieLibelleVoie" cssClass="error"/></td>
                    </tr> 
                    <tr class="form-group">                
                        <td><form:label path="complement" class="control-label col-sm-2" for="complement">complement:</form:label></td>
                        <td style="width:100%;"><div class="col-sm-10" style="margin-top: 2%;"><form:input path="complement" class="form-control" id="complement" placeholder="Enter complement"/></div></td>
                    </tr> 
                    <tr class="form-group">                
                        <td><form:label path="codePostale" class="control-label col-sm-2" for="codePostale">codePostale:</form:label></td>
                        <td style="width:100%;"><div class="col-sm-10" style="margin-top: 2%;"><form:input path="codePostale" class="form-control" id="codePostale" placeholder="Enter codePostale"/></div></td>
                        <td><form:errors path="codePostale" cssClass="error"/></td>
                    </tr> 
                    <tr class="form-group">                
                        <td><form:label path="ville" class="control-label col-sm-2" for="ville">ville:</form:label></td>
                        <td style="width:100%;"><div class="col-sm-10" style="margin-top: 2%;"><form:input path="ville" class="form-control" id="ville" placeholder="Enter ville"/></div></td>
                    </tr> 
                    <tr class="form-group">                
                        <td><form:label path="pays" class="control-label col-sm-2" for="pays">pays:</form:label></td>
                        <td style="width:100%;"><div class="col-sm-10" style="margin-top: 2%;"><form:input path="pays" class="form-control" id="pays" placeholder="Enter pays"/></div></td>
                    </tr> 
                    <tr class="form-group">
                        <td class="control-label col-sm-2" for="submit"></td>
                        <td><button type="submit" class="btn btn-default" style="margin: 2% 2% 2% 2.5%;">submit</button></td>                   
                    </tr>
                </table>
                    
                    

            </form:form>
            --%>

            <c:if test="${not empty msg}">
                ${msg}
            </c:if>

            <div class="container">
                <!--  <h2>Horizontal form</h2>-->
                <form:form method="POST" modelAttribute="client" class="form-horizontal">


                    <div class="form-group">
                        <label class="control-label col-sm-2" for="nom">Nom:</label>
                        <div class="col-sm-10">
                            <form:input path="nom" class="form-control" id="nom" placeholder="Entrez nom" name="nom"/>
                        </div>
                        <div  class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="nom" cssClass="error"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-2" for="prenom">Prénom:</label>
                        <div class="col-sm-10">
                            <form:input path="prenom" class="form-control" id="prenom" placeholder="Entrez prénom" name="prenom"/>
                        </div>
                        <div class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="prenom" cssClass="error"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-2" for="statut">Statut:</label>
                        <div class="col-sm-10 selectContainer">
                            <div class="input-group"> 
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-list"></i>
                                </span>
                                <form:select path="statut" name="state" class="form-control selectpicker" >
                                    <%--   <form:option value=""></form:option>
                                    <form:option value="Client">Client</form:option>
                                    <form:option value="Laveur/Micro Entreprenneur">Laveur/Micro Entreprenneur</form:option>
                                    <form:option value="Admin">Admin(*SOUS RESERVE)</form:option> --%> 

                                    <!--pour ne permettre que le statut "Client" dans l'option "commande" nous mettons la liste sous condition : -->
                                    <c:forEach items="${listStatut}" var="stat">
                                        <form:option value="${stat.libelleStatut}" style="padding-left: 20%;" >${stat.idStatut}:  ${stat.libelleStatut}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class=form-control" style="text-align: center;">
                                <form:errors htmlEscape="false" path="statut" cssClass="error"/>
                            </div>
                        </div>
                    </div>




                    <div class="form-group">
                        <label class="control-label col-sm-2" for="loginEmail">Login Email:</label>
                        <div class="col-sm-10">
                            <form:input type="email" path="loginEmail" class="form-control" id="email" placeholder="Entrez Email" name="loginEmail"/>
                        </div>
                        <div class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="loginEmail" cssClass="error"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-2" for="password">Password:</label>
                        <div class="col-sm-10">
                            <form:input type="password" path="password" class="form-control" id="userPw" placeholder="Créez password" name="password"/>
                        </div>
                        <div class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="password" cssClass="error"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-2" for="telFixe">Tél Fixe:</label>
                        <div class="col-sm-10">
                            <form:input path="telFixe" class="form-control" id="telFixe" placeholder="Entrez telFixe"/>
                        </div>
                        <div class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="telFixe" cssClass="error"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-2" for="telMobile">Tél Mobile:</label>
                        <div class="col-sm-10">
                            <form:input path="telMobile" class="form-control" id="telMobile" placeholder="Entrez telMobile"/>
                        </div>
                        <div class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="telMobile" cssClass="error"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-2" for="numVoieTypeVoieLibelleVoie">Adresse:</label>
                        <div class="col-sm-10">
                            <form:input path="numVoieTypeVoieLibelleVoie" class="form-control" id="numVoieTypeVoieLibelleVoie" placeholder="Entrez numéro de Voie, type de voie et nom de la Voie"/>
                        </div>
                        <div class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="numVoieTypeVoieLibelleVoie" cssClass="error"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-2" for="complement">Complément d'adresse:</label>
                        <div class="col-sm-10">
                            <form:input path="complement" class="form-control" id="complement" placeholder="Entrez complement d'adresse"/>
                        </div>
                        <div class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="complement" cssClass="error"/>
                        </div>
                    </div>



                    <div class="form-group">
                        <label class="control-label col-sm-2" for="codePostale">Code postale :</label>
                        <div class="col-sm-10">
                            <form:input path="codePostale" class="form-control" id="codePostale" placeholder="Entrez codePostale"/>
                        </div>
                        <div class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="codePostale" cssClass="error"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-2" for="ville">Ville :</label>
                        <div class="col-sm-10">
                            <form:input path="ville" class="form-control" id="ville" placeholder="Entrez ville"/>
                        </div>
                        <div class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="ville" cssClass="error"/>
                        </div>
                    </div>



                    <div class="form-group">
                        <label class="control-label col-sm-2" for="pays">Pays :</label>
                        <div class="col-sm-10">
                            <form:input path="pays" class="form-control" id="pays" placeholder="Entrez pays"/>
                        </div>
                        <div class=form-control" style="text-align: center;">
                            <form:errors htmlEscape="false" path="pays" cssClass="error"/>
                        </div>
                    </div>
                    <!--    <div class="form-group">        
                          <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">
                              <label><input type="checkbox" name="remember"> Remember me</label>
                            </div>
                          </div>
                        </div>-->
                    <label class="control-label col-sm-2"></label>
                    <div class="col-sm-10">
                        <%--<button type="submit" style="visibility: ${client_submit_visibility}" class="btn btn-success no-border  btn-block" style="width: 50%;">Submit</button>--%>
                        <button type="submit" class="btn btn-success no-border  btn-block" style="width: 50%;">Submit</button>
                    </div>
                </form:form>
            </div>

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

