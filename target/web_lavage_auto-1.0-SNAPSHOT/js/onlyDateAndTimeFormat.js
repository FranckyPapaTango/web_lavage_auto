/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function onlyDateAndTimeFormat(ComponentID) {
    var myVar= document.getElementById(ComponentID).value;
    // La 1ère étape consiste à définir l'expression régulière d'un prix
    var regDateAndTime = new RegExp("^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4} (2[0-3]|[01]?[0-9]):([0-5]?[0-9]):([0-5]?[0-9])$");
    if (regDateAndTime .test(myVar)=== false)
        alert("Veuillez saisir une Date valide. Please provide valid date in format dd/mm/yyyy hh:mm:ss, including leading zero. Example: 01-12-2015 03:04:59");    
    //return regPrice.test(myVar);
}
