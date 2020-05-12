package mgw.web_lavage_auto.controller;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDateTime; //JAVA 8
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CurrencyEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.validation.Valid;
import mgw.domaine_lavage_auto.entities.Adminn;
import mgw.domaine_lavage_auto.entities.Client;
import mgw.domaine_lavage_auto.entities.Commande;
import mgw.domaine_lavage_auto.entities.Laveur;
import mgw.domaine_lavage_auto.entities.Prestation;
import mgw.persistence_lavage_auto.services.AdminnService;
import mgw.persistence_lavage_auto.services.ClientService;
import mgw.persistence_lavage_auto.services.LaveurService;
import mgw.persistence_lavage_auto.services.PrestationService;
import mgw.domaine_lavage_auto.tools.ModuleTools;
import mgw.web_lavage_auto.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author BigWave
 */
@Controller
//@SessionAttributes("client")
public class ClientController extends AbstractControleur {

    @Autowired
    ClientValidator clientValidator;

// UNCOMMENT THE FOLLOWING IF NEEDED TO SET THE INDEX PAGE :
/* @RenderMapping
	public String view(RenderRequest request, RenderResponse response) {
		return "index";
	}*/
    @Autowired
    ClientService clientService;

    @Autowired
    AdminnService adminService;

    @Autowired
    LaveurService laveurService;

    String operation;

    Collection<Prestation> listPrestation;

    @Autowired
    PrestationService prestationService;

    Long id;

//    String client_submit_visibility;// <= INUTILE (mauvaise stratégie)
    //AFFICHAGE DU FORMULAIRE POUR LA CREATION D'UN CLIENT_POJO (& INSCRIPTION): ==========================================================================
    //in the controller use Model as parameter and add new bean object into the model while loading
    @RequestMapping(value = "/newClient", method = RequestMethod.GET) // <===sans l'extension de la page
    public String newClient(Model model,
            HttpSession session,
            HttpServletResponse response,
            HttpServletRequest request
    ) {
        initCommon(session, model);
        /**********************/
        session.setAttribute("client_in_session", null);//<= util ?
        this.id = null;
        /************************/
        if ("inscription".equals(request.getParameter("operation"))) {//si c'est une inscription
            model.addAttribute("listStatut", ModuleTools.getList1());//liste de statuts en contexte d'inscription
            model.addAttribute("client", new Client());
            model.addAttribute("consigne", "Renseignez vos coordonnées");
            //                response.sendRedirect("/web_lavage_auto/newCommande");
            return "/WEB-INF/jsp/newClient.jsp?operation=inscription";
        }

        if ("commande".equals(request.getParameter("operation"))) {//si c'est une COMMANDE
//            System.out.println("2");
            model.addAttribute("listStatut", ModuleTools.getList2());//liste de statuts en contexte de commande
            model.addAttribute("consigne", "Veuillez d'abord vous inscrire. Renseignez vos coordonnées");
            return "/WEB-INF/jsp/newClient.jsp?operation=commande";
        }
        return null;
    }

    // CREATE CLIENT (& CREATE INSCRIPTION)
    @RequestMapping(value = "/newClient", method = RequestMethod.POST)
    public ModelAndView addInscription(
            @Valid @ModelAttribute("client") Client client,
            BindingResult bindingResult, Model model //,@RequestParam(name = "idClient", defaultValue = "0") long idClient
            ,
             HttpServletResponse response,
            HttpServletRequest request//<=pour récupérer le paramètre passé dans le lien "inscription" du menu bar
            ,
             HttpSession session
    ) {
        operation = request.getParameter("operation"); //indice pour savoir si l'opération consiste en une inscription, ou en une inscription et une commande
//        model.addAttribute("connectedOrNot", connectedOrNot);//<=pour l'onglet de connexion du menu bar

        initCommon(session, model);
        clientValidator.validate(client, bindingResult);//validation par ".properties"
        if (bindingResult.hasErrors()) {
            if (operation.equalsIgnoreCase("commande")) {
                model.addAttribute("listStatut", ModuleTools.getList2());//liste de statuts en contexte de commande
            }
            if (operation.equalsIgnoreCase("inscription")) {
                model.addAttribute("listStatut", ModuleTools.getList1());//liste de statuts en contexte d'inscription
            }
//            return "/WEB-INF/jsp/newClient.jsp";
            return new ModelAndView("/WEB-INF/jsp/newClient.jsp?operation=" + operation + "");
        }
//y a t-il un "client-in_session" dans la session ? si oui de quel statut/type est-il ?:=> récupérer son id
        if (id == null) {
            try {
                Client cli = (Client) session.getAttribute("client_in_session");
                id = cli.getIdClient();
                System.out.println("y_cl");//TEST
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Adminn admin = (Adminn) session.getAttribute("admin_in_session");
                id = admin.getIdAdminn();
                System.out.println("y_ad");//TEST
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Laveur laveur = (Laveur) session.getAttribute("laveur_in_session");
                id = laveur.getIdLaveur();
                System.out.println("y_la");//TEST
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            System.out.println("id= " + id);
            // le client est-il déjà enregistré en base ?
            //           requeter sur les 3 statuts (3 tables : client, admin, microentre^preneur)
            // si oui => ne plus le sauvegarder 
            //           notifier qu'il est déjà inscrit   
            //           utiliser ses attributs si commande et inscription
            //           notifier son inscription si inscription 
            // sinon :   
            //CAS D'UNE COMMANDE
            if (operation.equalsIgnoreCase("commande")) {
                System.out.println("3");
                //  return "redirect:/results";
//            if (client.getIdClient() == null) {
                if (id == null) {
                    if ("Client".equals(client.getStatut())) {
                        clientService.saveOrUpdateClient(client, null);//le 2ème paramètre "curs" doit être mis à null si l'on crée !

                        session.setAttribute("client_in_session", client);
//                    System.out.println("IdClient() = " + client.getIdClient());//TEST
//                    System.out.println("4");
                    }
//                    if ("Adminn   *(sous réserve)".equals(client.getStatut())) {
//                        Adminn userpojo = new Adminn();
//                        userpojo.setIdAdminn(client.getIdClient());//null
//                        userpojo.setStatut(client.getStatut());
//                        userpojo.setPrenom(client.getPrenom());
//                        userpojo.setCodePostale(client.getCodePostale());
//                        userpojo.setComplement(client.getComplement());
//                        userpojo.setLoginEmail(client.getLoginEmail());
//                        userpojo.setNom(client.getNom());
//                        userpojo.setNumVoieTypeVoieLibelleVoie(client.getNumVoieTypeVoieLibelleVoie());
//                        userpojo.setPassword(client.getPassword());
//                        userpojo.setPays(client.getPays());
//                        userpojo.setTelFixe(client.getTelFixe());
//                        userpojo.setTelMobile(client.getTelMobile());
//                        userpojo.setVille(client.getVille());
//                        adminService.saveOrUpdateAdminn(userpojo, null);//le 2ème paramètre "curs" doit être mis à null si l'on crée !
//
//                        session.setAttribute("client_in_session", userpojo);
////                    System.out.println("IdClient() = " + userpojo.getIdAdminn());//TEST
////                    System.out.println("5");
//                    }
//                    if ("Laveur/Micro Entreprenneur".equals(client.getStatut())) {
//                        Laveur userpojo = new Laveur();
//                        userpojo.setIdLaveur(client.getIdClient());//null
//                        userpojo.setStatut(client.getStatut());
//                        userpojo.setPrenom(client.getPrenom());
//                        userpojo.setCodePostale(client.getCodePostale());
//                        userpojo.setComplement(client.getComplement());
//                        userpojo.setLoginEmail(client.getLoginEmail());
//                        userpojo.setNom(client.getNom());
//                        userpojo.setNumVoieTypeVoieLibelleVoie(client.getNumVoieTypeVoieLibelleVoie());
//                        userpojo.setPassword(client.getPassword());
//                        userpojo.setPays(client.getPays());
//                        userpojo.setTelFixe(client.getTelFixe());
//                        userpojo.setTelMobile(client.getTelMobile());
//                        userpojo.setVille(client.getVille());
//                        laveurService.saveOrUpdateLaveur(userpojo, null);//le 2ème paramètre "curs" doit être mis à null si l'on crée !
//
//                        session.setAttribute("client_in_session", userpojo);
////                    System.out.println("IdClient() = " + userpojo.getIdLaveur());//TEST
////                    System.out.println("6");
//                    }

//prepa à la redirection vers commande ctrl :
                    model.addAttribute("consigne", "");
                    model.addAttribute("message", "NEW " + client.getStatut() + " ADDED - SUCCESSFULLY !");
                    model.addAttribute("commande", new Commande());
                    model.addAttribute("consigne", "renseignez les détails de la Commande");

                    listPrestation = prestationService.findAll();
                    model.addAttribute("listPrestation", listPrestation);

                    System.out.println("7");
                    //                System.out.println("Operation =" + operation);//TEST
//                return "/WEB-INF/jsp/newCommande.jsp";
                    return new ModelAndView("redirect:/newCommande");//REDIRECTION depuis un POST
//                try {
//                    response.sendRedirect("/web_lavage_auto/newCommande");
//                } catch (IOException ex) {
//                    Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
//                }

                }
//            if (id != null) {// SI DEJA CONNECTE
//                model.addAttribute("message", "Vous êtes déjà enregistré, veuillez passer votre commande!");
////                return "/WEB-INF/jsp/newClient.jsp";
//                return new ModelAndView("/WEB-INF/jsp/newClient.jsp");
//            }
            }

            // CAS D'UNE INSCRIPTION :
            if (operation.equalsIgnoreCase("inscription")) {
//            if (client.getIdClient() == null && id == null) {
                if (id == null) {
                    System.out.println("a");//TEST
                    if ("Client".equals(client.getStatut())) {
                        clientService.saveOrUpdateClient(client, null);//le 2ème paramètre "curs" doit être mis à null si l'on crée !
                        session.setAttribute("client_in_session", client);
                    }
                    if ("Adminn   *(sous réserve)".equals(client.getStatut())) {
                        Adminn userpojo = new Adminn();
                        userpojo.setIdAdminn(client.getIdClient());
                        userpojo.setStatut(client.getStatut());
                        userpojo.setPrenom(client.getPrenom());
                        userpojo.setCodePostale(client.getCodePostale());
                        userpojo.setComplement(client.getComplement());
                        userpojo.setLoginEmail(client.getLoginEmail());
                        userpojo.setNom(client.getNom());
                        userpojo.setNumVoieTypeVoieLibelleVoie(client.getNumVoieTypeVoieLibelleVoie());
                        userpojo.setPassword(client.getPassword());
                        userpojo.setPays(client.getPays());
                        userpojo.setTelFixe(client.getTelFixe());
                        userpojo.setTelMobile(client.getTelMobile());
                        userpojo.setVille(client.getVille());
                        adminService.saveOrUpdateAdminn(userpojo, null);//le 2ème paramètre "curs" doit être mis à null si l'on crée !
                        session.setAttribute("admin_in_session", userpojo);
                    }
                    if ("Laveur/Micro Entreprenneur".equals(client.getStatut())) {
                        Laveur userpojo = new Laveur();
                        userpojo.setIdLaveur(client.getIdClient());
                        userpojo.setStatut(client.getStatut());
                        userpojo.setPrenom(client.getPrenom());
                        userpojo.setCodePostale(client.getCodePostale());
                        userpojo.setComplement(client.getComplement());
                        userpojo.setLoginEmail(client.getLoginEmail());
                        userpojo.setNom(client.getNom());
                        userpojo.setNumVoieTypeVoieLibelleVoie(client.getNumVoieTypeVoieLibelleVoie());
                        userpojo.setPassword(client.getPassword());
                        userpojo.setPays(client.getPays());
                        userpojo.setTelFixe(client.getTelFixe());
                        userpojo.setTelMobile(client.getTelMobile());
                        userpojo.setVille(client.getVille());
                        laveurService.saveOrUpdateLaveur(userpojo, null);//le 2ème paramètre "curs" doit être mis à null si l'on crée !
                        session.setAttribute("laveur_in_session", userpojo);
                    }
//                    System.out.println("idClient =" + session.getAttribute("client_in_session").toString());
//                model.addAttribute("message", "NEW CLIENT ADDED - SUCCESS");
//                model.addAttribute("commande", new Commande());                
                    model.addAttribute("message", "MERCI, Vous venez d'être enregistré! vous recevrez un email de confirmation de votre identifiant et mot de passe.");
                    model.addAttribute("listStatut", ModuleTools.getList1());
//                model.addAttribute("commande", new Commande());
                    model.addAttribute("consigne", "");
//                System.out.println("Operation =" + operation);//TEST
//
//                model.addAttribute("client_submit_visibility", "hidden");//<= INUTILE (mauvaise stratégie)

                    try {
//                operation = null;//réinitialisation de la variable
//                    return "/WEB-INF/jsp/newClient.jsp";
                        return new ModelAndView("/WEB-INF/jsp/newClient.jsp?operation=inscription");
//                    response.sendRedirect("/web_lavage_auto/newClient");
                    } catch (Exception ex) {
                        Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (id != null) {
//                System.out.println("je suis là !!! coucou");
                    model.addAttribute("listStatut", ModuleTools.getList1());
                    model.addAttribute("message", "Vous êtes déjà enregistré ! ");//comme "+client.getStatut()+" !");
//                return "/WEB-INF/jsp/newClient.jsp";
                    return new ModelAndView("/WEB-INF/jsp/newClient.jsp?operation=inscription");
                }
            }

            return null;

        }

        @InitBinder
        public void initBinder
        (WebDataBinder binder) throws Exception {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            dateFormat.setLenient(false);
            CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
            binder
                    .registerCustomEditor(Date.class,
                            editor);

        }

        @ModelAttribute("client")
        Client getUser
        
            () {
        return new Client(); //or however you create a default
        }
    }
