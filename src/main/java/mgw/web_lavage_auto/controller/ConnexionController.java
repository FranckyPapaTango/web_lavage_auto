/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgw.web_lavage_auto.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import mgw.domaine_lavage_auto.entities.Adminn;
import mgw.domaine_lavage_auto.entities.Client;
import mgw.domaine_lavage_auto.entities.Laveur;
import mgw.domaine_lavage_auto.entities.Userpojo;
import mgw.persistence_lavage_auto.services.AdminnService;
import mgw.persistence_lavage_auto.services.ClientService;
import mgw.persistence_lavage_auto.services.LaveurService;
import mgw.web_lavage_auto.validator.ClientValidator;
import mgw.web_lavage_auto.validator.PasswordValidator;
import mgw.web_lavage_auto.validator.UserpojoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author BigWave
 */
@Controller
@SessionAttributes("connectedOrNot")
public class ConnexionController extends AbstractControleur {

//    @Autowired
//    @PersistenceContext
//    private EntityManager em;
    @Autowired
    UserpojoValidator userpojoValidator;
    
    @Autowired
    AdminnService adminService;

    @Autowired
    ClientService clientService;

    @Autowired
    LaveurService laveurService;

//    private String link_value = "./connexion.html";//<=rend l'onglet du menu bar cliquable
    //AFFICHAGE DU FORMULAIRE POUR LA CREATION D'UN CLIENT_POJO : ==========================================================================
    //in the controller use Model as parameter and add new bean object into the model while loading
    @RequestMapping(value = "/connexion", method = RequestMethod.GET) // <===sans l'extension de la page

    public String newUserpojo(Model model,
            HttpSession session,
            HttpServletRequest request
    ) {
        this.initCommon(session, model);
        model.addAttribute("userpojo", new Userpojo());
//        model.addAttribute("connectedOrNot", connectedOrNot);//<=pour l'onglet de connexion du menu bar

        // si le bouton "(deconnexion)" a été cliqué => mettre un user null, =>mettre connectedOrNot à "CONNEXION":
        if ("yes".equals(request.getParameter("deconnect"))) {
            //                  mettre userpojo dans la session sous la référence "user":
            Userpojo userpojo = new Userpojo();
            session.setAttribute("user", userpojo);//<=pour la session
            model.addAttribute("user", userpojo);//<=pour le menu bar
            connectedOrNot = "CONNEXION";
            session.setAttribute("connectedOrNot", connectedOrNot);
            model.addAttribute("connectedOrNot", connectedOrNot);
            this.initCommon(session, model);
        }

        return "/WEB-INF/jsp/connexion.jsp";
    }

    
    
    
    
    @RequestMapping(value = "/connexion", method = RequestMethod.POST)
    public String addInscription(
            @ModelAttribute("userpojo") Userpojo userpojo,//<=for user shown in menu bar
            BindingResult bindingResult, Model model //,@RequestParam(name = "idUserpojo", defaultValue = "0") long idUserpojo
            ,
             HttpServletResponse response,
            HttpSession session
    ) {
        initCommon(session, model);
        userpojoValidator.validate(userpojo, bindingResult);//<= validation via class validateur et .properties
        if (bindingResult.hasErrors()) {
            return "/WEB-INF/jsp/connexion.jsp";
        }

//        model.addAttribute("connectedOrNot", connectedOrNot);//<=pour l'onglet de connexion du menu bar
        String statut = "(EMPTY STATUT)";
        String password = userpojo.getPassword();
        String email = userpojo.getLoginEmail();

//        System.out.println("userpojo.getEmail() =" + email);//TEST
//        System.out.println("userpojo.getPassword() =" + password);//TEST
//user est-il un admin ?
        if (!adminService.findAdminnByLoginEmailAndPassword(email, password).isEmpty()) {
            try {
                Adminn admin = (adminService.findAdminnByLoginEmailAndPassword(email, password)).get(0);
                if (admin != null) {
                    userpojo.setId(admin.getIdAdminn());
                    userpojo.setStatut(admin.getStatut());
                    userpojo.setPrenom(admin.getPrenom());
//                  mettre userpojo dans la session sous la référence "user":
                    session.setAttribute("user", userpojo);//<=pour la session
                    session.setAttribute("admin_in_session", admin);//<=pour la session
                    model.addAttribute("user", userpojo);//<=pour le menu bar

//                  System.out.println("statut 1 =" + statut);// TEST
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

//            finally {
//                // CLOSE ENTITYMANAGER
//                em.close();
//            }
        } else if (!clientService.findClientByLoginEmailAndPassword(email, password).isEmpty()) {
//user est-il un client ?
            try {
                Client client = (clientService.findClientByLoginEmailAndPassword(email, password)).get(0);
                if (client != null) {
                    userpojo.setId(client.getIdClient());
                    userpojo.setStatut(client.getStatut());
                    userpojo.setPrenom(client.getPrenom());
//                  mettre userpojo dans la session sous la référence "user":
                    session.setAttribute("user", userpojo);//<=pour la session
                    session.setAttribute("client_in_session", client);//<=pour la session
                    model.addAttribute("user", userpojo);//<=pour le menu bar

                  System.out.println("From ConnexionController ==> client_in_session =" + client.getIdClient());// TEST
                }
            } catch (Exception e) {
            }

//            finally {
//                // CLOSE ENTITYMANAGER
//                em.close();
//            }
        } else if (!laveurService.findLaveurByLoginEmailAndPassword(email, password).isEmpty()) {
//user est-il un laveur ?
            try {
                Laveur laveur = (laveurService.findLaveurByLoginEmailAndPassword(email, password)).get(0);
                if (laveur != null) {
                    userpojo.setId(laveur.getIdLaveur());
                    userpojo.setStatut(laveur.getStatut());
                    userpojo.setPrenom(laveur.getPrenom());
//                  mettre userpojo dans la session sous la référence "user":
                    session.setAttribute("user", userpojo);//<=pour la session
                    session.setAttribute("laveur_in_session", laveur);//<=pour la session
                    model.addAttribute("user", userpojo);//<=pour le menu bar

//                  System.out.println("statut 3 =" + statut);// TEST
                }
            } catch (Exception e) {
            }

//            -
        } else {

            model.addAttribute("statut", "Vous n'êtes pas enregistré sur le système, veuillez vous inscrire ou commander directement");
            model.addAttribute("user", new Userpojo());  //menu bar attribute          
            return "/WEB-INF/jsp/successful.jsp";

        }
        /**
         * Si le user est déjà connecté iinterdire l'accès à la page de
         * "login/connexion" en //rendant NON CLIQUABLE (mais visible) l'onglet
         * "connexion" du menu bar : // cf l'onglet-lien du menu bar :
         * style="visibility:hidden"*
         */
        if (((Object) session.getAttribute("user")) != null) {
            link = "";//<=pour la non cliquabilité de l'onglet "connexion" mettre à vide !
//            session.setAttribute("link", link);//<=cliquabilité de l'onglet "connexion" mise en session
//            model.addAttribute("link", link);//<=rendu sur la vue menu bar
            connectedOrNot = "CONNECTED as";
            session.setAttribute("connectedOrNot", connectedOrNot);
//            model.addAttribute("connectedOrNot", connectedOrNot);//<=rendu sur la vue menu bar
            initCommon(session, model);
        }

        
        
        
        
        
        try {
            System.out.println("returned to index");                    
            response.sendRedirect("/web_lavage_auto/index");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    
    
    @RequestMapping(value = "/successful", method = RequestMethod.GET) // <===sans l'extension de la page
    public String successful(Model model,
            @ModelAttribute("user") Object o,
            @RequestParam(name = "statut", defaultValue = "(EMPTY STATUT)") String statut,
            HttpSession session
    ) {

        initCommon(session, model);
        model.addAttribute("statut", statut);
//        model.addAttribute("connectedOrNot", connectedOrNot);//<=pour l'onglet de connexion du menu bar
        return "/WEB-INF/jsp/successful.jsp";
    }
//    @InitBinder
//    public void initBinder(WebDataBinder binder) throws Exception {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        dateFormat.setLenient(false);
//        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
//        binder
//                .registerCustomEditor(Date.class,
//                        editor);
//
//    }

//    @RequestMapping(value = "/validation_example", method = RequestMethod.GET) // <===sans l'extension de la page
//    public String newClient2(Model model
//    ) {
////        model.addAttribute("userpojo", new Userpojo());
////        model.addAttribute("consigne", "Renseignez vos coordonnées");
//        return "/WEB-INF/jsp/validation_example.jsp";
//    }
}
