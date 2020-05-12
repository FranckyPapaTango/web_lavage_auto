package mgw.web_lavage_auto.controller;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;// JAVA 8
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


//import com.liferay.counter.kernel.service.CounterLocalService;
//import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
//import com.liferay.portal.kernel.exception.PortalException;
//import com.liferay.portal.kernel.util.ParamUtil;
//import com.sb.model.Cursus;
//import com.sb.model.Module;
//import com.sb.model.Video;
//import com.sb.service.CursusLocalService;
//import com.sb.service.CursusLocalServiceUtil;
//import com.sb.service.VideoLocalServiceUtil;
//import lombok.Generated;
//import webCms.portlet.pojo.Cursuspojo;
import javax.validation.Valid;
import mgw.domaine_lavage_auto.entities.Prestation;
import mgw.persistence_lavage_auto.services.PrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author BigWave
 */
@Controller
//@RequestMapping("VIEW")
@SessionAttributes("submit_ButtonState")
public class PrestationController extends AbstractControleur{



// UNCOMMENT THE FOLLOWING IF NEEDED TO SET THE INDEX PAGE :
/* @RenderMapping
	public String view(RenderRequest request, RenderResponse response) {
		return "index";
	}*/
//    @Controller
//    public class PrestationController {
    @Autowired
    PrestationService prestationService;
    //AFFICHAGE DU FORMULAIRE POUR LA CREATION D'UN PRESTATION : ==========================================================================
    //in the controller use Model as parameter and add new bean object into the model while loading

    @RequestMapping(value = "/newPrestation", method = RequestMethod.GET) // <===sans l'extension de la page
    public String newPrestation(Model model,
            @ModelAttribute("submit_ButtonState") String submit_ButtonState
    //             ,HttpServletRequest request,
    //             ,HttpServletResponse response
            ,HttpSession session
    ) {
        initCommon(session, model);
        
//        model.addAttribute("connectedOrNot", connectedOrNot);//<=pour l'onglet de connexion du menu bar
        model.addAttribute("consigne", "renseignez les détails de l'Inscription");
        model.addAttribute("prestation", new Prestation());

        model.addAttribute("submit_ButtonState", submit_ButtonState);

        return "/WEB-INF/jsp/newPrestation.jsp";
    }

    
    
    
    // CREATE PRESTATION
    @RequestMapping(value = "/newPrestation", method = RequestMethod.POST)
    public String addInscription(
            @Valid @ModelAttribute("prestation") Prestation prestation,
            BindingResult result, Model model,
            @RequestParam(name = "prestation_id", defaultValue = "0") long idPrestation,
            HttpServletResponse response,
            HttpSession session
    ) {
//        model.addAttribute("connectedOrNot", connectedOrNot);//<=pour l'onglet de connexion du menu bar
        initCommon(session, model);
        if (result.hasErrors()) {
            return "/WEB-INF/jsp/newPrestation.jsp";
        }
        if (prestation.getIdPrestation() == null) {
            prestationService.saveOrUpdatePrestation(prestation, null);//le 2ème paramètre "curs" doit être mis à null si l'on crée !

            model.addAttribute("consigne", "");
            model.addAttribute("message", "NEW PRESTATION ADDED - SUCCESS");
            model.addAttribute("prestation", new Prestation());
            model.addAttribute("submit_ButtonState", "hidden");//chacher le bouton submit pour éviter une seconde validation par inadvertance
            return "/WEB-INF/jsp/newPrestation.jsp";
        } /**
         * ************************************************************
         */
        else {//BLOC DEPLACABLE EN REEL FIN DE TRAITEMENT :
            // model.addAttribute("submit_ButtonState", "hidden");//chacher le bouton submit pour éviter une seconde validation par inadvertance
            //Si le client n'est pas enregistré :
            // model.addAttribute("consigne", "renseignez vos coordonnées");
            try {
                //return "/WEB-INF/jsp/newClient.jsp";
                //return new ModelAndView("redirect:/WEB-INF/jsp/newClient.jsp");
                response.sendRedirect("/web_lavage_auto/blankPage");
            } catch (IOException ex) {
                Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
        /**
         * **********************************************************
         */
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setLenient(false);
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);

    }

    @ModelAttribute("submit_ButtonState")
    String getSubmit_ButtonState() {
        return "visible"; //or however you create a default
    }

    @ModelAttribute("prestation")
    Prestation getPrestation() {
        return new Prestation(); //or however you create a default
    }
}
