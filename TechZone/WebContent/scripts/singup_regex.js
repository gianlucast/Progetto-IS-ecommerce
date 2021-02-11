var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);


function ValidateSignup(){
	$("#mail").focus(function(){
		 $("#spanmail").css("display", "inline")
	  });
	$("#nome").focus(function(){
	    $("#spannome").css("display", "inline")
	  });
	$("#cognome").focus(function(){
	    $("#spancognome").css("display", "inline")
	  });

	$("#password").focus(function(){
	    $("#spanpassword").css("display", "inline")
	  });

	$("#telefono").focus(function(){
	    $("#spantelefono").css("display", "inline")
	});
	
	$("#mail").blur(function(){
		 $("#spanmail").css("display", "none")
	  });
	$("#nome").blur(function(){
	    $("#spannome").css("display", "none")
	  });
	$("#cognome").blur(function(){
	    $("#spancognome").css("display", "none")
	  });

	$("#password").blur(function(){
	    $("#spanpassword").css("display", "none")
	  });

	$("#telefono").blur(function(){
	    $("#spantelefono").css("display", "none")
	});
	return(ValidateEmail()&&ValidateName()&&ValidateSurname()&&ValidatePassword()&&ValidateTelefono());
}



function ValidateEmail() 
{
 var mail=document.getElementById("mail").value;
 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail)&&mail.length<255)
  {
	 return true;
  }
    $("#mail").focus();
    return (false)
 
}

function ValidateName() 
{
 var name=document.getElementById("nome").value;
 if (/^([^0-9]*)$/.test(name)&&name.length>3&&name.length<25)
  {
	 return true;
  }
    $("#nome").focus();
    return (false)
 
}

function ValidateSurname() 
{
 var cognome=document.getElementById("cognome").value;
 if (/^([^0-9]*)$/.test(cognome)&&cognome.length>3&&cognome.length<20)
  {
	 return true;
  }
    $("#cognome").focus();
    return (false)
 
}

function ValidatePassword() 
{
 var pass=document.getElementById("password").value;
 if (pass.length>5)
  {
	 return true;
  }
    $("#password").focus();
    return (false)
 
}

function ValidateTelefono() 
{
 var telefono=document.getElementById("telefono").value;
 if(telefono.length==0)
	 return true;
 if (/^\d+$/.test(telefono)&&telefono.length>7&&telefono.length<15)
  {
	 return true;
  }
    $("#telefono").focus();
    return (false)
 
}

function ValidateDate(){
	var data= new Date();
	var scadenzamese = document.getElementById("inputZip").value;
	var scandenzaanno = document.getElementById("inputZip").value;
	if(data.getFullYear()>scadenzaanno.getFullYear())
		{
			return false;
		}
	else if(data.getFullYear()>scadenzaanno.getFullYear()&&data.getMonth()+1>scadenzamese.getMonth()+1)
		{
			return false;
		}
	else 
	{
		return true;
	}
	$("#scadenzamese").focus();
    return (false)
    $("#scadenzaanno").focus();
    return (false)
	
}