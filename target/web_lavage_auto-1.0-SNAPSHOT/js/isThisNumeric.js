/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function isThisNumeric(ComponentID) {
    var myVar= document.getElementById(ComponentID).value;
    // La 1ère étape consiste à définir l'expression régulière d'un prix
    //var regPrice = new RegExp(/^[0-9]{1,}(\.|,)[0-9]{0,2}$/);
    var regPrice = new RegExp(/^[0-9]{1,16}([.,][0-9]{2})?$/);
    if (regPrice .test(myVar)=== false)
        alert("Veuillez saisir une Valeur valide (0000... ou 00,00");    
    //return regPrice.test(myVar);
}

