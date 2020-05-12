/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgw.web_lavage_auto.validator;

import mgw.domaine_lavage_auto.entities.Userpojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author BigWave
 */
@Component
public class UserpojoValidator implements Validator {

    @Autowired
    PasswordValidator passwordValidator;

    @Override
    public boolean supports(Class<?> type) {
        return Userpojo.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        
 
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nom", "notEmpty.nom");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prenom", "notEmpty.prenom");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "statut", "notEmpty.statut");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginEmail", "notEmpty.loginEmail");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "notEmpty.password");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, errorCode);
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, errorCode);
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numVoieTypeVoieLibelleVoie", "notEmpty.numVoieTypeVoieLibelleVoie");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, errorCode);
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codePostale", "notEmpty.codePostale");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ville", "notEmpty.ville");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pays", "notEmpty.pays");

//    implémentation de la logique correspondant à : @Size(min = 1, max = 30) sur l'attribut "nom" de l'entité:
        Userpojo userpojo = (Userpojo) o;
        if (userpojo.getLoginEmail().trim().length() < 1 || userpojo.getLoginEmail().trim().length() > 30) {
            errors.rejectValue("loginEmail", "valid.loginEmail");
        }

//    implémentation de la logique correspondant au regex de password:
        if (!passwordValidator.valid(userpojo.getPassword()) ) {
            errors.rejectValue("password", "valid.password");
        }

    }

}

