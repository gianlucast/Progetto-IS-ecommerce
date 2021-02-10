/**
 * 
 */
var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

function ajaxordini(){
	if(valdateres()){
		var d1=document.getElementById("data1").value;
		var d2=document.getElementById("data2").value;
		var d1split=d1.split("-");
		var d2split=d2.split("-");
		var giorno2=parseInt(d2split[2])+1;
		$("#ordinifiltrati").html("");
		$.get('order', {"action": 'orderfilterbydate', "anno1": d1split[0] , "mese1": d1split[1], "giorno1": d1split[2], "anno2": d2split[0] , "mese2": d2split[1], "giorno2": giorno2},
				function(resp){
						var box=document.getElementById("ordinifiltrati");
						box.innerHTML="";
						var dainserire="";
						for(var i=0;i<resp.length;i++){
							dainserire+="<hr>NUMERO ORDINE: "+resp[i].code;
							dainserire+="<table border=1><th>NOME PRODOTTO</th><th>QUANTITA</th><th>PREZZO TOTALE</th>";
							var prodotti=resp[i].orderline;
							for(var j=0;j<prodotti.length;j++){
								dainserire+="<tr><td>"+prodotti[j].prod.name+"</td><td>"+prodotti[j].num+"</td><td>"+(prodotti[j].num*prodotti[j].prod.price)+"</td></tr>";
							}
							
							dainserire+="</table>"
							dainserire+="<a href='order?action=generatefattura&idorder="+resp[i].code+"'><button type='button' class='bottone'>Stampa fattura</button></a>";
						}
						$("#ordinifiltrati").html(dainserire);
						$("#ordinifiltrati").css("display","block");
						/*
						box.innerHTML=dainserire;
						box.style.display="block";
						*/
				}
		
		);
	}

}