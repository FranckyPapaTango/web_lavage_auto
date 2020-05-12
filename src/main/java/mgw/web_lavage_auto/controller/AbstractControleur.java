/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgw.web_lavage_auto.controller;

import javax.servlet.http.HttpSession;
import mgw.domaine_lavage_auto.entities.Client;
import mgw.domaine_lavage_auto.entities.Userpojo;
//import mgw.persistence_lavage_auto.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

/**
 *
 * @author BigWave
 */
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//
//@EnableJpaRepositories(basePackages = {"mgw"})
//@EntityScan(basePackages = {"mgw"})
public abstract class AbstractControleur {

    String link;
    String link2;
    String connectedOrNot;
    String logout_ButtonState;
 //   @Autowired
 //   private ClientService clientService;

    public void initCommon(HttpSession session, Model model) {
        // si user non connecté
        if (session.getAttribute("connectedOrNot") == null
                || session.getAttribute("connectedOrNot") == "CONNEXION") {
            link = "./connexion.html";
            link2 = "./newClient.html?operation=commande";
            connectedOrNot = "CONNEXION";
            logout_ButtonState = "hidden";
        } else {//sinon:
            link = "";
            link2 = "./newCommande.html";//<== Retour en mode "NORMAL"
            connectedOrNot = "CONNECTED as";
            logout_ButtonState = "visible";

// DE PLUS (POUR EVITER DE REMPLIR LE FORMULAIRE CLIENT) ET FAIRE PASSER DIRECTEMENT L'UTILISATEUR CONNECTE
// LE FORMULAIRE DE COMMANDE (QUI DU COUP SE PREREMPLI), FAIRE :
//session.getAttribute("user") == session.getAttribute("client_in_session")
//            Userpojo user = (Userpojo) session.getAttribute("client_in_session");//<= CECI EST ENCORE UN POJO DONC SANS id
//            Client client_in_session = clientService.findClientByLoginEmailAndPassword(user.getLoginEmail(), user.getPassword()).get(0);
//            
//            Client client_in_session = (Client) session.getAttribute("client_in_session");  //TEST          
//            System.out.println("From AbstractControleur => client_in_session ="+client_in_session.getIdClient());//TEST
            
        }

        model.addAttribute("link", link);
        model.addAttribute("link2", link2);
        model.addAttribute("connectedOrNot", connectedOrNot);//<=rendu sur la vue menu bar        
        session.setAttribute("connectedOrNot", connectedOrNot);
        model.addAttribute("logout_ButtonState", logout_ButtonState);//<=rendu sur la vue menu bar        
//        session.setAttribute("logout_ButtonState", logout_ButtonState);

    }
}
