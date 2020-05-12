/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function isThisAPassword(ComponentID) {
    var myVar= document.getElementById(ComponentID).value;
    // La 1ère étape consiste à définir l'expression régulière d'un prix
    //var regPrice = new RegExp(/^[0-9]{1,}(\.|,)[0-9]{0,2}$/);
    var regPwd = new RegExp(/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/);
    if (regPwd .test(myVar)=== false)
        alert("Veuillez saisir un Mot de Passe présentant au moins: \n Une majuscule \n Une minuscule \n un des cacharactères spéciaux @#$% \n Un chiffre \n et de longueur d'au moins 8 caractères.");    
    return regPwd.test(myVar);
}

