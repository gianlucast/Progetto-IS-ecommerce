/**
 * 
 */
function trim(indirizzo){
	document.writeln("Nome: "+indirizzo.substring(0, indirizzo.indexOf("_")))
	modifica=indirizzo.substring(indirizzo.indexOf("_")+1);
	document.writeln("<br>Cognome: "+modifica.substring(0,modifica.indexOf("_")));
	modifica=modifica.substring(modifica.indexOf("_")+1);
	document.writeln("<br>Via: "+modifica.substring(0,modifica.indexOf("_")));
	modifica=modifica.substring(modifica.indexOf("_")+1);
	document.writeln("<br>Numero Civico: "+modifica.substring(0,modifica.indexOf("_")));
	modifica=modifica.substring(modifica.indexOf("_")+1);
	document.writeln("<br>CAP: "+modifica.substring(0,modifica.indexOf("_")));
	modifica=modifica.substring(modifica.indexOf("_")+1);
	document.writeln("<br>Comune: "+modifica);
}

function showfatturazione(){
	var fatt=document.getElementById("indirizzofatturazione");
	if(document.getElementById("checkfatt").checked)
		fatt.style.display="none";
	else
		fatt.style.display="block";
}