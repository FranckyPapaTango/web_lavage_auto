package mgw.web_lavage_auto.controller;

import java.text.ParseException;
import java.util.ArrayList;
//import java.util.ArrayList;
//import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mgw.domaine_lavage_auto.entities.Adminn;
import mgw.domaine_lavage_auto.entities.Laveur;
import mgw.domaine_lavage_auto.entities.Commande;
import mgw.domaine_lavage_auto.entities.Laveur;
import mgw.domaine_lavage_auto.entities.Prestation;
import mgw.persistence_lavage_auto.services.LaveurService;
import mgw.persistence_lavage_auto.services.CommandeService;
import mgw.persistence_lavage_auto.services.PrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collection;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;

//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.boot.autoconfigure.domain.EntityScan;

/*@EnableJpaRepositories(basePackages = {"esic"})
@EntityScan(basePackages = {"esic"})*/
@Controller
public class CommandeCmsController extends AbstractControleur {

    @Autowired
    CommandeService commandeService;

    Laveur laveur;

    Page<Commande> pageCommande;

    String lastSearch;

    long id;

    //VERSION TOTAL LISTE : ========================================================================
    /*@RequestMapping("/commandes") // <===sans l'extension de la page
	public String commandes(Model model) {

		model.addAttribute("commandes", commandeService.getAllCommandes());
		return "/WEB-INF/jsp/commandes.jsp";
	}*/
    //VERSION PAGINABLE : ==========================================================================
    @RequestMapping(value="/commandesCms", method = RequestMethod.GET) // <===sans l'extension de la page
    public String commandes(Model model,
            HttpSession session,
            @RequestParam(name = "page", defaultValue = "0") int p,
            @RequestParam(name = "size", defaultValue = "8") int s,
            @RequestParam(name = "search", defaultValue = "tarzanboy!") String search
    ) {
        initCommon(session, model);
        this.lastSearch = search;
        try {//montrer uniquement les commandes non pris en charge ou affecté au laveur connecté (s'il est connecté) sinon tout montrer
            laveur = (Laveur) session.getAttribute("laveur_in_session");
            id = laveur.getIdLaveur();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (laveur != null) {
            id = laveur.getIdLaveur();
            if ("tarzanboy!".equals(search)) {
//                pageCommande = commandeService.findAllCommandeOfLaveurPage(id,p, s);//<=(int page, int size)}
                pageCommande = commandeService.findByIdLaveurPage(id, p, s);
            } else {
                pageCommande = commandeService.findCommandeOfLaveurByKeyWord(id, this.lastSearch, p, s);
            }
        } else {
            if ("tarzanboy!".equals(search)) {
                pageCommande = commandeService.findAllCommandePage(p, s);//<=(int page, int size)}

            } else {
                pageCommande = commandeService.findCommandeByKeyWord(this.lastSearch, p, s);
            }
        }

        model.addAttribute("commandes", pageCommande.getContent());
        //POUR LA PAGINATION PAR LIENS CLIQUABLE COTE JSP :
        int[] pages = new int[pageCommande.getTotalPages()];
        model.addAttribute("pages", pages);
        return "/WEB-INF/jsp/commandesCms.jsp";

    }

    // CREATE MODULES OF CURSUS (ADD) <=> CREATE LAVEUR OF COMMANDE (ADD)
    @RequestMapping(value = "/gererLaveursOfcommandes", method = RequestMethod.GET)
    public String addLaveur(
            //            @Valid @ModelAttribute("commande") Commande commande, 
            //            BindingResult result, 
            Model model,
            @RequestParam(name = "idCommandeParam", defaultValue = "0") long idCommande, HttpSession session,
            @RequestParam(name = "page", defaultValue = "0") int p,
            @RequestParam(name = "size", defaultValue = "8") int s,
            @RequestParam(name = "search", defaultValue = "tarzanboy!") String search) {
        Commande commande = commandeService.findCommandeById(idCommande);
        laveur = (Laveur) session.getAttribute("laveur_in_session");
        if (laveur != null) {
//            commande.setIdLaveur(laveur.getIdLaveur());
            commande.setIdLaveur(laveur);
            commande.setHandled(true);
        }
        commandeService.saveOrUpdateCommande(commande, null);//update 

        initCommon(session, model);
        this.lastSearch = search;

        if (laveur != null) {
            id = laveur.getIdLaveur();
            if ("tarzanboy!".equals(search)) {
                pageCommande = commandeService.findAllCommandeOfLaveurPage(id, p, s);//<=(int page, int size)}

            } else {
                pageCommande = commandeService.findCommandeOfLaveurByKeyWord(id, this.lastSearch, p, s);
            }
        } else {
            if ("tarzanboy!".equals(search)) {
                pageCommande = commandeService.findAllCommandePage(p, s);//<=(int page, int size)}

            } else {
                pageCommande = commandeService.findCommandeByKeyWord(this.lastSearch, p, s);
            }
        }
        model.addAttribute("commandes", pageCommande.getContent());
        //POUR LA PAGINATION PAR LIENS CLIQUABLE COTE JSP :
        int[] pages = new int[pageCommande.getTotalPages()];
        model.addAttribute("pages", pages);
//        model.addAttribute("page", p);
//        model.addAttribute("idCursusParam", 0);
        if (laveur == null) {
            model.addAttribute("consigne", "Vous devez être LAVEUR et vous CONNECTER pour prendre à charge un Lavage !!");
        }
        return "/WEB-INF/jsp/commandesCms.jsp";

    }

    /*   //CONSULTATION AFFINEE D'UN CURSUS : ==========================================================================
    @RequestMapping("/modulesOfcommandes") // <===sans l'extension de la page
    public String commandeAffine(Model model,
            @RequestParam(name = "idCommandeParam", defaultValue = "0") long idCommande
    ) {

        Commande commande = commandeService.findCommandeById(idCommande);
        Collection<Module> listModules = commande.getModuleCollection();
        model.addAttribute("commande", commande);
        model.addAttribute("listModules", listModules);
        return "/WEB-INF/jsp/modulesOfcommandes.jsp";
    }*/
    //la méthode méthode initDatas() ci-dessous a été transformée en tant qu'élément d'un service(initDbService)
//    @PostConstruct
//    private void initDatas() throws ParseException, InterruptedException {
//    }
}
