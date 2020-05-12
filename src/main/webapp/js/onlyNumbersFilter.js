/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function onlyNumbersFilter(ComponentID){
var donnee = document.getElementById(ComponentID).value;
//alert(codepostale);
if (isNaN(donnee) && donnee.length>0) {
	//alert("!");
var t=donnee.substring(0, donnee.length-1);
//innertHtml
document.getElementById(ComponentID).value=t;
}
}
