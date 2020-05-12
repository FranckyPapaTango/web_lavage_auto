package mgw.web_lavage_auto.controller;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import mgw.domaine_lavage_auto.entities.Client;
import mgw.persistence_lavage_auto.services.InitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

//import com.esic.module.persistence.CursusService;
@Controller
@SessionAttributes("userSession")
public class IndexController extends AbstractControleur{

    @Autowired
    InitDbService initDbService;
//          @PostConstruct
//          public void init(){
//               try {  
//                   initDbService.init();
//               } catch (ParseException ex) {
//                   Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
//               }
//          }
//    @RequestMapping("/index")//<===sans l'extension de la page
    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index(Model model,
            HttpSession session) {        
        initCommon(session, model);        
        try {
            initDbService.init();
        } catch (ParseException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("submit_ButtonState", "visible");
        return "/WEB-INF/jsp/index.jsp";//<====avec le chemin et l'extension (ommise plus haut)
    }
    
    // POUR AFFICHAGE DES ERREURS ET DES CONSIGNES POST ERREURS :
//    @RequestMapping("/blankPage")//<===sans l'extension de la page
    @RequestMapping(value="/blankPage", method = RequestMethod.GET)
    public String blankPage(Model model, HttpSession session) {
          initCommon(session, model);
        return "/WEB-INF/jsp/blankPage.jsp";//<====avec l'extension ommise plus haut
    }
}
