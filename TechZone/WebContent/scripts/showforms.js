/**
 * 
 */


function showformnewcard(div_id){
	var div=document.getElementById(div_id);
	/*
	if((div.style.display=="none")&&(div.innerHTML==""))
		div.innerHTML="<form action='user' method='post' name='formnuovacarta'><br><input type='hidden' name='action' value='addcarta'><br><label for='carta'>Carta:<input type='text' name='carta' id='carta'></label><br><input type='submit' name='mod' id='mod' value='Conferma'></form>";
	*/
	if(div.style.display=="none"){
		div.style.display="block";
		document.getElementById("bottonenew_card").innerHTML="Nascondi aggiunta";
	}
	else{
		div.style.display="none";
		document.getElementById("bottonenew_card").innerHTML="Aggiugni una nuova carta"
	}
}

function showformnewaddress(div_id){
	var div=document.getElementById(div_id);
	/*
	if((div.style.display=="none")&&(div.innerHTML==""))
		div.innerHTML="<form action='user' method='post' name='formnuovoindirizzo'><br><input type='hidden' name='action' value='addindirizzo'><br><label for='nome'>Nome:<input type='text' name='nome' id='nome'></label><br><label for='cognome'>Cognome:<input type='text' name='cognome' id='cognome'></label><br><label for='via'>Via:<input type='text' name='via' id='via'></label><br><label for='numero civico'>Numero Civico:<input type='text' name='num_civ' id='num_civ'></label><br><label for='cap'>Cap:<input type='text' name='cap' id='cap'></label><br><label for='comune'>Comune:<input type='text' name='comune' id='comune'></label><br><input type='submit' name='mod' id='mod' value='Conferma'></form>";
	*/
	if(div.style.display=="none"){
		div.style.display="block";
		document.getElementById("bottonenew_addr").innerHTML="Nascondi aggiunta";
	}
	else{
		div.style.display="none";
		document.getElementById("bottonenew_addr").innerHTML="Aggiugni un nuovo indirizzo"
	}
}

function showformuser(){
	if(document.getElementById("mod_info").style.display=="block"){
		document.getElementById("mod_info").style.display="none";
		document.getElementById("modifica_user").innerHTML="Mostra modifica"
	}
	else{
		document.getElementById("mod_info").style.display="block";
		document.getElementById("modifica_user").innerHTML="Nascondi modifica";
	}
}

function showformaddress(mod_addr){
	var form=document.getElementById(mod_addr);
	var bottone=document.getElementById("bottone"+mod_addr);
	if(form.style.display=="block"){
		form.style.display="none";
		bottone.innerHTML="Mostra modifica"
	}
	else{
		form.style.display="block";
		bottone.innerHTML="Nascondi modifica";
	}
}

