package mgw.web_lavage_auto.controller;

import java.text.ParseException;
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

import java.util.Collection;
import javax.servlet.http.HttpSession;
import mgw.domaine_lavage_auto.entities.Prestation;
import mgw.persistence_lavage_auto.services.PrestationService;
import org.springframework.web.bind.annotation.RequestMethod;

//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.boot.autoconfigure.domain.EntityScan;

/*@EnableJpaRepositories(basePackages = {"esic"})
@EntityScan(basePackages = {"esic"})*/
@Controller
public class ChooseAndPayPrestationController extends AbstractControleur{


    @Autowired
    private PrestationService prestationService;

    //VERSION TOTAL LISTE : ========================================================================
    @RequestMapping(value="/chooseAndPayPrestation", method = RequestMethod.GET) // <===sans l'extension de la page
    public String prestations(Model model, HttpSession session) {
        
        initCommon(session, model);
        
        model.addAttribute("connectedOrNot", connectedOrNot);//<=pour l'onglet de connexion du menu bar
        model.addAttribute("listPrestations", prestationService.findAll());
        return "/WEB-INF/jsp/chooseAndPayPrestation.jsp";
    }
    //VERSION PAGINABLE : ==========================================================================
//    @RequestMapping("/chooseAndPayPrestation") // <===sans l'extension de la page
//    public String prestations(Model model,
//            @RequestParam(name = "page", defaultValue = "0") int p,
//            @RequestParam(name = "size", defaultValue = "8") int s
//    ) {
//
//        Page<Prestation> pagePrestation = prestationService.findAllPrestationPage(p, s);//<=(int page, int size)
//        // Page<Prestation> pagePrestation=prestationRepository.findAll(new PageRequest(p,s));//<=(int page, int size)
//        model.addAttribute("prestations", pagePrestation.getContent());
//        //POUR LA PAGINATION PAR LIENS CLIQUABLE COTE JSP :
//        int[] pages = new int[pagePrestation.getTotalPages()];
//        model.addAttribute("pages", pages);       
//        return "/WEB-INF/jsp/chooseAndPayPrestation.jsp";
//        
//    }

    //méthode transformée en tant qu'élément d'un service(initDbService)
    @PostConstruct
    private void initDatas() //throws ParseException, InterruptedException
    {
        /*		//Prestation c1 = new Prestation(libellePrestation, annee, dateDeb, dateFin, detail)		
        Prestation c1 = new Prestation("CONCEPTEUR DEVELOPPEUR JAVA EE", 2017,Validateur.convertStringFrToDate("15/09/2017"), Validateur.convertStringFrToDate("25/06/2018"),"- CCP1 : développer la persistance des données - CCP2:Développer des composants d'interface IHM - CCP3:Développer une architecture N-TIERS");
        Prestation c2 = new Prestation("DEVELOPPEUR WEB", 2018,Validateur.convertStringFrToDate("08/01/2018"), Validateur.convertStringFrToDate("25/03/2018"),"- CCP1 : Designer des pages web - CCP2:valider les formulaires côté client");
        Prestation c3 = new Prestation("TECHNICIEN AUDIOVISUEL", 2017,Validateur.convertStringFrToDate("05/10/2017"), Validateur.convertStringFrToDate("26/07/2018"),"- CCP1 : Techniques de prise de vue et de son - CCP2:Mise en place d'une scène de tournage (éclairage, espace) - CCP3:Techniques de montage sonore et video");
        Prestation c4 = new Prestation("BTS COMPTABILITE", 2017,Validateur.convertStringFrToDate("20/10/2017"), Validateur.convertStringFrToDate("07/07/2018"),"ANGLAIS Commercial, GESTION, COMPTABILITE");
        

        List<Prestation> cursuss = new ArrayList<Prestation>();
        cursuss.add(c1);
        cursuss.add(c2);
        cursuss.add(c3);
        cursuss.add(c4);
        this.cursusRepository.save(cursuss);
        //=========EQUIVALENT SANS CREATION D'UNE LISTE : ===========================
        cursusRepository.save(c1);       
        cursusRepository.save(c2);        
        cursusRepository.save(c3);        
        cursusRepository.save(c4);*/
        
    }

}
