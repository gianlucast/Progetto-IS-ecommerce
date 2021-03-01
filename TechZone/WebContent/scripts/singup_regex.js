var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);


function ValidateSignup(){
	
	return(ValidateEmail()&&ValidateName()&&ValidateSurname()&&ValidatePassword()&&ValidateTelephone()&&ValidateAddress()&&ValidateCity()&&ValidateState()&&ValidateCAP()&&ValidatePayment()&&ValidateCode()&&ValidateDate()&&ValidateCvv());
}



function ValidateEmail() {
	
 var mail=document.getElementById("inputEmail4").value;
 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail)&&mail.length<64)
  {
	 return true;
  }

	$("#inputEmail4").focus(function(){
		 $("#spanmail").css("display", "inline")
	  });

	$("inputEmail4").blur(function(){
		 $("#spanmail").css("display", "none")
	  });
	
    $("#inputEmail4").focus();
    return (false);
 
}

function ValidatePassword() {
	
 var pass=document.getElementById("inputPassword4").value;
 if(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,18}$/.test(pass)){
	
	return true;
 
 }

	$("#inputPassword4").focus(function(){
	    $("#spanpassword").css("display", "inline")
	  });

	$("#inputPassword4").blur(function(){
	    $("#spanpassword").css("display", "none")
	  });

 $("#inputPassword4").focus();
 return (false);
 
}


function ValidateName(){
	
	var nome=document.getElementById("inputNome4").value;

	if(/^[A-Za-z]+$/.test(nome)){

		return true;
	}

	$("#inputNome4").focus(function(){
	    $("#spannome").css("display", "inline")
	  });

	$("#inputNome4").blur(function(){
	    $("#spannome").css("display", "none")
	  });

	$("#inputNome4").focus();
		return (false);

}

function ValidateSurname(){
	
	var cognome=document.getElementById("inputSurname4").value;

	if(/^(?:[A-Za-z]+)(?:[A-Za-z0-9 _]{1,64})$/.test(cognome)){
	
	return true;
	}
	
	$("#inputSurname4").focus(function(){
	    $("#spancognome").css("display", "inline")
	  });

	$("#inputSurname4").blur(function(){
	    $("#spancognome").css("display", "none")
	  });

	$("#inputSurname4").focus();
	return (false);
	
}


function ValidateDate(){
	$("#inputMonth").focus(function(){
	    $("#spanmonth").css("display", "inline")
	});
	
	$("#inputYear").focus(function(){
	    $("#spanyear").css("display", "inline")
	});
	
	$("#inputMonth").blur(function(){
	    $("#spanmonth").css("display", "none")
	});
	
	$("#inputYear").blur(function(){
	    $("#spanyear").css("display", "none")
	});
	
	var data= new Date();
	var scadenzamese = document.getElementById("inputMonth").value;
	var scadenzaanno = document.getElementById("inputYear").value;
	if(data.getFullYear()>scadenzaanno){
			 $("#inputMonth").focus();

   			 $("#inputYear").focus();

			  return false;
	}else{ 
		if(data.getFullYear()==scadenzaanno){

			if(data.getMonth()+1>=scadenzamese){
				$("#inputMonth").focus();
				$("#inputYear").focus();
				return false;
			}
		}
		return true;
	}
	
	
	
	
	$("#inputMonth").focus();
    $("#inputYear").focus();
    return (false);
	
}

function ValidateCAP(){

	var cap = document.getElementById("inputZip").value;
	if(/^\d{5}$/.test(cap)){
		
		return true;
	}
	
	$("#inputZip").focus(function(){
	    $("#spanzip").css("display", "inline")
	});
	
	$("#inputZip").blur(function(){
	    $("#spanzip").css("display", "none")
	});
	
	$("#inputZip").focus();
    return (false);
	
	
}

function ValidateTelephone(){

	var telephone = document.getElementById("inputTelephone").value;
	if(/^\d{10}$/.test(telephone)){
		
		return true;
	}
	
	$("#inputTelephone").focus(function(){
	    $("#spantelefono").css("display", "inline")
	});
	
	$("#inputTelephone").blur(function(){
	    $("#spantelefono").css("display", "none")
	});
	
	$("#inputTelephone").focus();
    return (false);
	
}

function ValidateCvv(){
	
	var cvv = document.getElementById("inputCvv").value;
	if(/^\d{3}$/.test(cvv)){
		
		return true;
	}
	
	$("#inputCvv").focus(function(){
	    $("#spancvv").css("display", "inline")
	});
	
	$("#inputCvv").blur(function(){
	    $("#spancvv").css("display", "none")
	});
	
	$("#inputCvv").focus();
    return (false);
	
}

function ValidateCode(){
	var code = document.getElementById("inputCode").value;
	if(/^\d{16}$/.test(code)){
		
		return true;
	}
	
	$("#inputCode").focus(function(){
	    $("#spancode").css("display", "inline")
	});
	
	$("#inputCode").blur(function(){
	    $("#spancode").css("display", "none")
	});
	
	$("#inputCode").focus();
    return (false);
	
}

function ValidateCity(){

	var city = document.getElementById("inputCity").value;
	
	if(/^(?:[A-Za-z]+)(?:[A-Za-z0-9 _]{5,64})$/.test(city)){
	
	return true;
	}
	
	$("#inputCity").focus(function(){
	    $("#spancity").css("display", "inline")
	});
	
	$("#inputCity").blur(function(){
	    $("#spancity").css("display", "none")
	});
	
	$("#inputCity").focus();
    return (false);
}

function ValidateAddress(){

	var address = document.getElementById("inputAddress").value;
	
	if(/^(?:[A-Za-z]+)(?:[A-Za-z0-9 _]{5,64})$/.test(address)){
		
		return true;
	}
	
	$("#inputAddress").focus(function(){
	    $("#spanindirizzo").css("display", "inline")
	});
	
	$("#inputAddress").blur(function(){
	    $("#spanindirizzo").css("display", "none")
	});
	
	$("#inputAddress").focus();
    return (false);
	
}

function ValidateState(){
	
	var state=document.getElementById("inputState").value;

	if(/^[A-Za-z]+$/.test(state)){

		return true;
	}

	$("#inputState").focus(function(){
	    $("#spanstate").css("display", "inline")
	  });

	$("#inputState").blur(function(){
	    $("#spanstate").css("display", "none")
	  });

	$("#inputState").focus();
		return (false);

}

function ValidatePayment(){
	
	var payment=document.getElementById("inputPayment").value;

	if(/^[A-Za-z]+$/.test(payment)){

		return true;
	}

	$("#inputPayment").focus(function(){
	    $("#spanpay").css("display", "inline")
	  });

	$("#inputPayment").blur(function(){
	    $("#spanpay").css("display", "none")
	  });

	$("#inputPayment").focus();
		return (false);

}


