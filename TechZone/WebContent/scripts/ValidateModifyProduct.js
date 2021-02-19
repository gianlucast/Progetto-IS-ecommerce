var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

function ValidateModifyProduct(){	

return (ValidateNameProd()&&ValidateQuantity()&&ValidateTipo()&&ValidateCosto()&&ValidateCategoria()&&ValidateDescrizione());

}

function ValidateNameProd(){
	
	var nameProd = document.getElementById("inputNameProd").value;
	
	if(/^(?:[A-Za-z]+)(?:[A-Za-z0-9 _]{5,64})$/.test(nameProd)){
		
		return true;
	}
	
	$("#inputNameProd").focus(function(){
	    $("#spannameprod").css("display", "inline")
	});
	
	$("#inputNameProd").blur(function(){
	    $("#spannameprod").css("display", "none")
	});
	
	$("#inputNameProd").focus();
	
	
    return (false);
}

function ValidateQuantity(){
	
	var quantity = document.getElementById("inputQuant").value;
	
	if(/^\d{1,99}$/.test(quantity)){
		
	
		return true;
	}
	
	$("#inputQuant").focus(function(){
	    $("#spanquant").css("display", "inline")
	});
	
	$("#inputQuant").blur(function(){
	    $("#spanquant").css("display", "none")
	});
	
	$("#inputQuant").focus();
	
	
    return (false);

}

function ValidateTipo(){
	
	var tipo = document.getElementById("inputTipo").value;
	
	if(/^[A-Za-z]+$/.test(tipo)){
		
		
		return true;
	}
	
	$("#inputTipo").focus(function(){
	    $("#spantipo").css("display", "inline")
	});
	
	$("#inputTipo").blur(function(){
	    $("#spantipo").css("display", "none")
	});
	
	$("#inputTipo").focus();
	
	
    return (false);
	
}

function ValidateCosto(){
	
	var costo = document.getElementById("inputCosto").value;
	
	if(/^\d+(.\d{1,2})?$/.test(costo)){
		
		if(costo>0){
		
			
			return true;
		
		}	
		
	}
	
	$("#inputCosto").focus(function(){
	    $("#spancosto").css("display", "inline")
	});
	
	$("#inputCosto").blur(function(){
	    $("#spancosto").css("display", "none")
	});
	
	$("#inputCosto").focus();
	
	
    return (false);
	
}

function ValidateCategoria(){
	
	var categoria = document.getElementById("inputCat").value;
	
	if(/^[A-Za-z]+$/.test(categoria)){
		
	
		return true;
	}
	
	$("#inputCat").focus(function(){
	    $("#spancat").css("display", "inline")
	});
	
	$("#inputCat").blur(function(){
	    $("#spancat").css("display", "none")
	});
	
	$("#inputCat").focus();
	
	
    return (false);
	
}

function ValidateDescrizione(){
	
	var descr = document.getElementById("inputDescr").value;
	
	if(descr.length<1000&&descr.length>5){
		
		
		return true;
	}
	
	$("#inputDescr").focus(function(){
	    $("#spandescr").css("display", "inline")
	});
	
	$("#inputDescr").blur(function(){
	    $("#spandescr").css("display", "none")
	});
	
	$("#inputDescr").focus();
	
	
    return (false);
	
}