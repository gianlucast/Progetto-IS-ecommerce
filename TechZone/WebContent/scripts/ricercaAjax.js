var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);


function ricerca(str) {
	//datalist è spazio per inserire suggerimenti
	var dataList = document.getElementById('ricerca-datalist');
	if (str.length == 0) {
		// rimuove elementi <option> (suggerimenti) esistenti
		dataList.innerHTML = '';
		return;
	}
	var xmlHttpReq = new XMLHttpRequest();
	xmlHttpReq.responseType = 'json';
	xmlHttpReq.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			// rimuove elementi <option> (suggerimenti) esistenti
			$("#ricerca-datalist").html("");
			//dataList.innerHTML = '';

			for ( var i in this.response) {
				//alert("AGGIUNGO RISPOSTA");
				// crea un elemento option
				var option = document.createElement('option');
				// setta il valore
				option.value = this.response[i];
				// aggiunge elemento <option> a datalist
				$("#ricerca-datalist").append(option);
				//dataList.appendChild(option);
			}
		}
	}
	
	xmlHttpReq.open("GET", "search?type=ajax&q=" + str, true);
	xmlHttpReq.send();
}