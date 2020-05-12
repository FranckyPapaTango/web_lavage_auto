/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgw.web_lavage_auto.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

/**
 *
 * @author BigWave
 */
@Component
public class PasswordValidator {

    private final Pattern pattern;
    private Matcher matcher;
    
    
/**
 * ^                 #start of string
 * (?=.*[0-9])       #password must contains a minimum of 1 numeric character
 * (?=.*[a-z])       #password must contains a minimum of 1 lower case letter
 * (?=.*[A-Z])       #password must contains a minimum of 1 upper case letter
 * (?=.*[@#$%^&*])   #password must contains a minimum of 1 special character
 * {8,}              #password must be at least 8 characters in lenght
 * $                 #end of string 
 */
    String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&*]).{8,}$";

    //constructor:
    public PasswordValidator() {
        this.pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    public boolean valid(String password) {
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
