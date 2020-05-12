package mgw.web_lavage_auto.controller;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Collection;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;

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

import mgw.persistence_lavage_auto.services.ClientService;
import mgw.persistence_lavage_auto.services.CommandeService;
import mgw.persistence_lavage_auto.services.PrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author BigWave
 */
@Controller
//@SessionAttributes("client")
public class CommandeController extends AbstractControleur {

// UNCOMMENT THE FOLLOWING IF NEEDED TO SET THE INDEX PAGE :
/* @RenderMapping
	public String view(RenderRequest request, RenderResponse response) {
		return "index";
	}*/
    @Autowired
    CommandeService commandeService;
    @Autowired
    ClientService clientService;
    @Autowired
    PrestationService prestationService;

    Collection<Prestation> listPrestation;

    Long id;

    Client client;

    //private long idClient_instance;
    //AFFICHAGE DU FORMULAIRE POUR LA CREATION D'UN COMMANDE_POJO : ==========================================================================
    //in the controller use Model as parameter and add new bean object into the model while loading
    @RequestMapping(value = "/newCommande", method = RequestMethod.GET) // <===sans l'extension de la page
    public String newCommande(Model model,
            //            @ModelAttribute("commande") Commande commande,
            HttpServletResponse response,
            HttpSession session
    //,@RequestParam(name = "idPrestation", defaultValue = "0") long idPrestation
    ) {

//        session.setAttribute("client_in_session", null);//<= inutile car nous forçons à ce qu'il y ait un client avant d'arriver içi !!!
        initCommon(session, model);

//        Client client_in_session = (Client) session.getAttribute("client_in_session");//TEST
//        System.out.println("From CommandeController => client_in_session =" + client_in_session.getIdClient());//TEST

        model.addAttribute("connectedOrNot", connectedOrNot);//<=pour l'onglet de connexion du menu bar
        model.addAttribute("consigne", "renseignez les détails de la Commande");
        model.addAttribute("commande", new Commande());
        listPrestation = prestationService.findAll();
        model.addAttribute("listPrestation", listPrestation);
        try {
            client = (Client) session.getAttribute("client_in_session");
            id = client.getIdClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Adminn admin = (Adminn) session.getAttribute("admin_in_session");
            id = admin.getIdAdminn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Laveur laveur = (Laveur) session.getAttribute("laveur_in_session");
            id = laveur.getIdLaveur();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("(9 comm): id =" + id);
        if (id == null) {//UTILISATEUR NON CONNECTE OU N'AYANT PAS REMPLI LE FORMULAIRE CLIENT (IMPOSSIBLE ICI ???) 
            //return "/WEB-INF/jsp/newClient.jsp";
            //return new ModelAndView("redirect:/WEB-INF/jsp/newClient.jsp");
            try {
                response.sendRedirect("/web_lavage_auto/newClient?operation=commande");//redirection depuis un GET
//                System.out.println("1");
            } catch (IOException ex) {
                Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return "/WEB-INF/jsp/newCommande.jsp";//VERS METHODE POST
    }

    // CREATE COMMANDE
    @RequestMapping(value = "/newCommande", method = RequestMethod.POST)
    public String addInscription(
            @ModelAttribute("commande") Commande commande,
            BindingResult result, Model model,
            // @RequestParam(name = "commande", defaultValue = "0") long idCommande,
            //            @ModelAttribute("client") Client client, //   ,@RequestParam(name = "idClient", defaultValue = "0") long idClient
            @RequestParam(name = "idPrestation", defaultValue = "0") long idPrestation,
            HttpServletResponse response,
            HttpServletRequest request,
            HttpSession session
    ) {
//        Client client = null;// <= inutile car nous forçons à ce qu'il y ait un client avant d'arriver içi !!!
//        = (Client) session.getAttribute("client");
        listPrestation = prestationService.findAll();
        model.addAttribute("listPrestation", listPrestation);
//        model.addAttribute("connectedOrNot", connectedOrNot);//<=pour l'onglet de connexion du menu bar
        //TEST: System.out.println("client id=" + client.getIdClient());
        initCommon(session, model);
        if (result.hasErrors()) {
            return "/WEB-INF/jsp/newCommande.jsp";
        }
        try {//récupération du client
            client = (Client) session.getAttribute("client_in_session");
            id = client.getIdClient();
            System.out.println("(10): id =" + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            Adminn admin = (Adminn) session.getAttribute("client");
//            id = admin.getIdAdminn();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            Laveur laveur = (Laveur) session.getAttribute("client");
//            id = laveur.getIdLaveur();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        if (id != null) {//UTILISATEUR CONNECTE OU AYANT REMPLI LE FORMULAIRE CLIENT 
//          Si le client est enregistré ou connecté :
//            commande.setIdClient(client.getIdClient());//l'id de type Long est assimilé à l'objet "client"
            commande.setIdClient(client);//l'id de type Long est assimilé à l'objet "client"
            //prestationId=Integer.parseInt(request.getParameter("pays"));
            Prestation prestation = prestationService.findPrestationByIdPrestation(idPrestation);
            //DANS LE CAS OU UN LAVEUR OU UN ADMIN SE VEUT ETRE UN CLIENT CANDIDAT AU SERVICE DE LAVAGE
            //IL FAUDRA LE FORCER A S'INSCRIRE COMME CLIENT (STATUT) AVEC UN AUTRE COMPTE
            //DANS LE CAS CONTRAIRE ON GENERE L'EXCEPTION :
            try {
//                commande.setIdPrestation(prestation.getIdPrestation());
                commande.setIdPrestation(prestation);
            } catch (Exception e) {
                try {
                    response.sendRedirect("/web_lavage_auto/blankPage");
                } catch (IOException ex) {
                    Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            commandeService.saveOrUpdateCommande(commande, null);//le 2ème paramètre doit être mis à null si l'on crée !

            model.addAttribute("consigne", "");
            model.addAttribute("message", "NEW COMMANDE ADDED - SUCCESS");
            model.addAttribute("commande", commande);

            //mettre client à null pour éviter de dupliquer la commande par inadvertance:
            client = new Client();
//            model.addAttribute("client", client);//<=non requis içi
            session.setAttribute("client_in_session", client);
            //status.setComplete();//<==trop puissant pour cet effet : vider tout le contenu de la session
            return "/WEB-INF/jsp/newCommande.jsp";//ACTION A ADAPTER VERS LA PAGE SUIVANTE DU TRAITEMENT ( A LAISSER STATIQUE SUR LA MÊME PAGE SI FIN DE TRAITEMENT)
        } else {//BLOC DEPLACABLE EN REEL FIN DE TRAITEMENT :
            //Si le client n'est pas enregistré :
            // model.addAttribute("consigne", "renseignez vos coordonnées");
            //return "/WEB-INF/jsp/newClient.jsp";
            //return new ModelAndView("redirect:/WEB-INF/jsp/newClient.jsp");
            try {
                response.sendRedirect("/web_lavage_auto/blankPage");
            } catch (IOException ex) {
                Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    
       
    
    
    
    

//    @InitBinder
//    public void initBinder(WebDataBinder binder) throws Exception {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
//        dateFormat.setLenient(false);
//        dateFormat2.setLenient(false);
//        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
//        CustomDateEditor editor2 = new CustomDateEditor(dateFormat2, true);
//        binder.registerCustomEditor(Date.class, editor);
//        binder.registerCustomEditor(Date.class, editor2);
//
//    }
    class PrestationEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            Long id = Long.parseLong(text);
            Prestation prestation = prestationService.findPrestationByIdPrestation(id);
            setValue(prestation);
        }
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Prestation.class, new PrestationEditor());
    }

//    @ModelAttribute("client")
//    Client getUser() {
//        return new Client(); //or however you create a default
//    }
}
