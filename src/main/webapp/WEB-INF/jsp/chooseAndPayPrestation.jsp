<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
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


    <section class="mbr-section article mbr-parallax-background" id="msg-box8-a" style="
             /*             background-image: url(assets/images/desert.jpg); */
             padding-top: 6%; padding-bottom: 120px;">

        <div class="mbr-overlay" style="opacity: 0.5; background-color: rgb(34, 34, 34);">
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-8 col-md-offset-2 text-xs-center">
                    <h3 class="mbr-section-title display-2"><div class="lead">CHOISISSEZ VOTRE PRESTATIONS</div></h3>
                    <div class="lead"><p style="font-size: 100%"></p></div>

                </div>
            </div>
        </div>

    </section>

    <section class="engine"><a rel="external" href="https://www.smallwave.besaba.com">Smallwaver</a></section><section class="mbr-cards mbr-section mbr-section-nopadding" id="features3-9" style="background-color: rgb(255, 255, 255);">


        <!--       <table class="table table-bordered table-hover table-striped" border="1">
       
                   <thead>
                       <tr>
                           <th></th>
                           <th></th>
                           <th></th>						
       
                       </tr>
                   </thead>                    
                   <tbody>   -->               
        <c:forEach items="${listPrestations}" var="prest">   


            <center>
                <h1>${prest.libellePrestation} <h6>(id:${prest.idPrestation})</h6></h1>

                <h3>${prest.detailPrestation}</h3>


                <div class="card cart-block">
                    <div  class="card-img"><img src="assets/images/table${prest.idPrestation}.jpg" class="card-img-top"></div>





                    <%--         <td style="width:20%"><div class="card cart-block" >
                                     <div class="card cart-block">                      
                                         <div class="card-block" >                            
                                             <h5 style="padding-top: 35%;font-size: 100%" class="card-subtitle">${prest.duree} Heures <br>Débute le 
                                                 <fmt:formatDate value="${prest.dateDansAgenda}" pattern="dd-MM-yyyy" /></h5>                     
                                         </div>
                                     </div>
                                 </div></td>
                    --%>







                    <!--            </tbody> 
                            </table>-->


                    <!--
                        </section>
                    
                    
                    
                        <section style="background-color: #2aabd2; padding-bottom: 5%; padding-top: 5%;text-align: center;">-->


                    <h1>POUR CHOISIR CETTE PRESTATION :</h1>
                    <p>cliquez sur "Acheter"</p> 
                    <h2>${prest.prixPrestation} euros</h2>



                    <form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top">
                        <input type="hidden" name="cmd" value="_s-xclick">
                        <input type="hidden" name="hosted_button_id" value="${prest.paypalId}">
                        <%--   <table>
                            <tr><td><input type="hidden" name="on0" value="Options">Options</td></tr><tr><td><select name="os0">
                                        <option value="Option 1">Option basique   :  ${cursus.prix1} &euro;</option>
                                        <option value="Option 2">Option avec streaming   :  ${cursus.prix2} &euro;</option>

                            </select> </td></tr>
                </table>
                        --%>
                        <input type="hidden" name="currency_code" value="EUR">
                        <input type="hidden" name="option_select0" value="Option 1">
                        <input type="hidden" name="option_amount0" value="${prest.prixPrestation}">
                        <%--   <input type="hidden" name="option_select1" value="Option 2">
                           <input type="hidden" name="option_amount1" value="${cursus.prix2}">  --%>
                        <input type="hidden" name="option_index" value="0">
                        <input type="image" src="https://www.paypalobjects.com/fr_FR/FR/i/btn/btn_buynowCC_LG.gif" border="0" name="submit" alt="PayPal, le réflexe sécurité pour payer en ligne">
                        <img alt="" border="0" src="https://www.paypalobjects.com/fr_FR/i/scr/pixel.gif" width="1" height="1">
                    </form>

            </center>



        </c:forEach>  
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
</body>
</html>
