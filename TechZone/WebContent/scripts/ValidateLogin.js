var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

function validatelog(){
	$("#mail").focus(function(){
		 $("#spanmail").css("display", "inline")
	  });
	
	$("#password").focus(function(){
	    $("#spanpassword").css("display", "inline")
	  });
	
	$("#mail").blur(function(){
		 $("#spanmail").css("display", "none")
	  });
	
	$("#password").blur(function(){
	    $("#spanpassword").css("display", "none")
	  });
	
	 var mail=document.getElementById("mail").value;
	 var pass=document.getElementById("password").value;
	 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail)&&mail.length<64)
	  {
		 if (/^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,18}$/.test(pass))
			 
		  {
			 return true;
		  }
		 $("#password").focus();
		 return false;
	  }
	 $("#mail").focus();
	 return false;
	
}