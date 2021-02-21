var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

function ValidateOrderSearchByIdOrd(){	

return ValidateIdOrd();
}

function ValidateOrderSearchByEmail(){

return ValidateEmail();
}

	
	
function ValidateEmail() {
	
 	var mail=document.getElementById("inputEmail").value;

 	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail)&&mail.length<64)
 		 {
		  return true;
 		 }

	$("#inputEmail").focus(function(){
		 $("#spanmailord").css("display", "inline")
	  });

	$("#inputEmail").blur(function(){
		 $("#spanmailord").css("display", "none")
	  });
	
   	 $("#inputEmail").focus();
  	 return (false);
}

function ValidateIdOrd(){
	
	var idord=document.getElementById("inputOrdine").value;
	if (/^[0-9]*$/.test(idord))
	{
		return true;
	}
	
	$("#inputOrdine").focus(function(){
		 $("#spanidord").css("display", "inline")
	  });

	$("#inputOrdine").blur(function(){
		 $("#spanidord").css("display", "none")
	  });
	
	$("#inputOrdine").focus();
  	 return (false);
		
}

