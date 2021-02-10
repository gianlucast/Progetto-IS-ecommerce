var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);



//VALIDAZIONE DELLA RICERCA PER DATA
function valdateres(){
	$("#data1").focus(function(){
	    $("#spandata").css("display", "inline")
	  });
	
	$("#data2").focus(function(){
	    $("#spandata").css("display", "inline")
	  });
	
	$("#data1").blur(function(){
	    $("#spandata").css("display", "none")
	  });
	
	$("#data2").blur(function(){
	    $("#spandata").css("display", "none")
	  });
	
	var d1=document.getElementById("data1").value;
	var d2=document.getElementById("data2").value;
	var d1split=d1.split("-");
	var d2split=d2.split("-");
	var data1 = new Date(d1split[0], d1split[1]-1, d1split[2], 0, 0, 0, 0);
	var data2 = new Date(d2split[0], d2split[1]-1, d2split[2], 23, 59, 0, 0);
	
	var ora = new Date();
	ora.setDate(ora.getDate()+1);
	if(data1.getTime()<=ora.getTime()&&data2.getTime()<=ora.getTime()&&data1.getTime()<data2.getTime()){
		return true;
	}
	$("#data1").focus();
	$("#data2").focus();
}

//RICERCA PER UTENTE
function valricercautente(){
	var prendi=document.getElementById('ricercaordinenome').value;
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(prendi)&&prendi.length<255) return true;
	$('#ricercaordinenome').focus(function(){
		$('#spanutenterichiesto').css("display","inline");
	});
	$('#ricercaordinenome').blur(function(){
		$('#spanutenterichiesto').css("display","none");
	});
	$('#ricercaordinenome').focus();
	return false;
}
//RICERCA PER CODICE ORDINE
function valricercakey(){
	var prendi=document.getElementById('key').value;
	if (/^\d+$/.test(prendi)&&parseInt(prendi)>=0) return true;
	$('#key').focus(function(){
		$('#spankey').css("display","inline");
	});
	$('#key').blur(function(){
		$('#spankey').css("display","none");
	});
	$('#key').focus();
	return false;
}





//VALIDAZIONE MODIFICA E INSERIMENTO PRODOTTI

function valmodify(){
	return(valname('mod')&&valartist('mod')&&valsize('mod')&&valdesc('mod')&&valtracklist('mod')&&valgenre('mod')&&valyear('mod')&&valprice('mod')&&valquantity('mod'));
}

function valinsert(){
	return(valname('ins')&&valartist('ins')&&valsize('ins')&&valdesc('ins')&&valtracklist('ins')&&valgenre('ins')&&valyear('ins')&&valprice('ins')&&valquantity('ins'));
}

function valname(tipo){
	var prendi=document.getElementById('name'+tipo).value;
	if(prendi.length>3&&prendi.length<30) return true;
	$('#name'+tipo).focus(function(){
		$('#spanname'+tipo).css("display","inline");
	});
	$('#name'+tipo).blur(function(){
		$('#spanname'+tipo).css("display","none");
	});
	$('#name'+tipo).focus();
	return false;
	
}

function valartist(tipo){
	var prendi=document.getElementById('artist'+tipo).value;
	if(prendi.length>3&&prendi.length<30) return true;
	$('#artist'+tipo).focus(function(){
		$('#spanartist'+tipo).css("display","inline");
	});
	$('#artist'+tipo).blur(function(){
		$('#spanartist'+tipo).css("display","none");
	});
	$('#artist'+tipo).focus();
	return false;
}

function valsize(tipo){
	var prendi=document.getElementById('size'+tipo).value;
	if(prendi.length>1&&prendi.length<20) return true;
	$('#size'+tipo).focus(function(){
		$('#spansize'+tipo).css("display","inline");
	});
	$('#size'+tipo).blur(function(){
		$('#spansize'+tipo).css("display","none");
	});
	$('#size'+tipo).focus();
	return false;
}

function valdesc(tipo){
	var prendi=document.getElementById('desc'+tipo).value;
	if(prendi.length>3) return true;
	$('#desc'+tipo).focus(function(){
		$('#spandesc'+tipo).css("display","inline");
	});
	$('#desc'+tipo).blur(function(){
		$('#spandesc'+tipo).css("display","none");
	});
	$('#desc'+tipo).focus();
	return false;
}

function valtracklist(tipo){
	var prendi=document.getElementById('track'+tipo).value;
	if(prendi.length>5) return true;
	$('#track'+tipo).focus(function(){
		$('#spantrack'+tipo).css("display","inline");
	});
	$('#track'+tipo).blur(function(){
		$('#spantrack'+tipo).css("display","none");
	});
	$('#track'+tipo).focus();
	return false;
}

function valgenre(tipo){
	var prendi=document.getElementById('genre'+tipo).value;
	if(prendi.length>2&&prendi.length<20) return true;
	$('#genre'+tipo).focus(function(){
		$('#spangenre'+tipo).css("display","inline");
	});
	$('#genre'+tipo).blur(function(){
		$('#spangenre'+tipo).css("display","none");
	});
	$('#genre'+tipo).focus();
	return false;
}

function valyear(tipo){
	var prendi=document.getElementById('year'+tipo).value;
	var annocorrente=new Date().getFullYear()+1
	if(prendi.length==4&&/^\d+$/.test(prendi)&&parseInt(prendi)<=annocorrente) return true;
	$('#year'+tipo).focus(function(){
		$('#spanyear'+tipo).css("display","inline");
	});
	$('#year'+tipo).blur(function(){
		$('#spanyear'+tipo).css("display","none");
	});
	$('#year'+tipo).focus();
	return false;
}

function valprice(tipo){
	var prendi=document.getElementById('price'+tipo).value;
	if(/^\d+$/.test(prendi)&&parseInt(prendi)>0) return true;
	$('#price'+tipo).focus(function(){
		$('#spanprice'+tipo).css("display","inline");
	});
	$('#price'+tipo).blur(function(){
		$('#spanprice'+tipo).css("display","none");
	});
	$('#price'+tipo).focus();
	return false;
}

function valquantity(tipo){
	var prendi=document.getElementById('quantity'+tipo).value;
	if(/^\d+$/.test(prendi)&&parseInt(prendi)>=0) return true;
	$('#quantity'+tipo).focus(function(){
		$('#spanquantity'+tipo).css("display","inline");
	});
	$('#quantity'+tipo).blur(function(){
		$('#spanquantity'+tipo).css("display","none");
	});
	$('#quantity'+tipo).focus();
	return false;
}