/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *      
 */
function isThisAnEmail(ComponentID) {
    var myVar= document.getElementById(ComponentID).value;
    // La 1ère étape consiste à définir l'expression régulière d'une adresse email
    var regEmail = new RegExp('^[0-9a-z._-]+@{1}[0-9a-z.-]{2,}[.]{1}[a-z]{2,5}$', 'i');
    if (regEmail.test(myVar)=== false)
        alert("Veuillez saisir une adresse email valide .");    
    return regEmail.test(myVar);
}

// var emails = new Array('adressemail@gmail','adresse@mel.fr','adr@fr.com.eu');

//   for(var e = 0; e < emails.length; e++){
//       // Affiche:
//   // Test sur adressemail@gmail : false
//   // Test sur adresse@mel.fr : true
//   // Test sur adr@fr.com.eu : true 
//     document.write('Test sur '+ emails[e] +' : '+ isThisAnEmail(emails[e]) +' < br /> ');
//   }


