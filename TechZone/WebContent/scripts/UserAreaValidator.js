/**
 * 
 */

function valmodinfo(){
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
	return (valmodnome()&&valmodcognome()&&valmodtelefono()&&valmodpassword());
}

function valmodnome(){
	var name=document.getElementById("nome").value;
	 if (/^([^0-9]*)$/.test(name)&&name.length>3&&name.length<25)
	  {
		 return true;
	  }
	    $("#nome").focus();
	    return (false)
}

function valmodcognome(){
	var cognome=document.getElementById("cognome").value;
	 if (/^([^0-9]*)$/.test(cognome)&&cognome.length>3&&cognome.length<20)
	  {
		 return true;
	  }
	    $("#cognome").focus();
	    return (false)
}

function valmodtelefono(){
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

function valmodpassword(){
	 var pass=document.getElementById("password").value;
	 if(pass.length==0)
		 return true;
	 if (pass.length>5)
	  {
		 return true;
	  }
	    $("#password").focus();
	    return (false)
}







function valnuovoind(){
	$("#nomeind").focus(function(){
	    $("#span1nome").css("display", "inline")
	  });
	$("#cognomeind").focus(function(){
	    $("#span1cognome").css("display", "inline")
	  });

	$("#viaind").focus(function(){
	    $("#span1via").css("display", "inline")
	  });

	$("#num_civind").focus(function(){
	    $("#span1num_civ").css("display", "inline")
	});
	
	$("#comuneind").focus(function(){
	    $("#span1comune").css("display", "inline")
	  });

	$("#capind").focus(function(){
	    $("#span1cap").css("display", "inline")
	});
	
	$("#nomeind").blur(function(){
	    $("#span1nome").css("display", "none")
	  });
	$("#cognomeind").blur(function(){
	    $("#span1cognome").css("display", "none")
	  });

	$("#viaind").blur(function(){
	    $("#span1via").css("display", "none")
	  });

	$("#num_civind").blur(function(){
	    $("#span1num_civ").css("display", "none")
	});
	
	$("#comuneind").blur(function(){
	    $("#span1comune").css("display", "none")
	  });

	$("#capind").blur(function(){
	    $("#span1cap").css("display", "none")
	});
	
	return(valnuovoindnome()&&valnuovoindcognome()&&valnuovoindvia()&&valnuovoindnum_civ()&&valnuovoindcap()&&valnuovoindcomune())
}

function valnuovoindnome(){
	var name=document.getElementById("nomeind").value;
	 if (/^([^0-9]*)$/.test(name)&&name.length>2&&name.length<25)
	  {
		 return true;
	  }
	    $("#nomeind").focus();
	    return (false)
}

function valnuovoindcognome(){
	var name=document.getElementById("cognomeind").value;
	 if (/^([^0-9]*)$/.test(name)&&name.length>2&&name.length<25)
	  {
		 return true;
	  }
	    $("#cognomeind").focus();
	    return (false)
}

function valnuovoindvia(){
	var name=document.getElementById("viaind").value;
	 if (/^([^0-9]*)$/.test(name)&&name.length>3&&name.length<40)
	  {
		 return true;
	  }
	    $("#viaind").focus();
	    return (false)
}

function valnuovoindnum_civ(){
	var name=document.getElementById("num_civind").value;
	 if (name.length>0&&name.length<6)
	  {
		 return true;
	  }
	    $("#num_civind").focus();
	    return (false)
}

function valnuovoindcomune(){
	var name=document.getElementById("comuneind").value;
	 if (/^([^0-9]*)$/.test(name)&&name.length>2&&name.length<40)
	  {
		 return true;
	  }
	    $("#comuneind").focus();
	    return (false)
}

function valnuovoindcap(){
	var cap=document.getElementById("capind").value; 
	if (/^\d+$/.test(cap)&&cap.length==5)
	  {
		 return true;
	  }
	    $("#capind").focus();
	    return (false)
}




function valmodind(){
	$("#nomeindmod").focus(function(){
	    $("#span2nome").css("display", "inline")
	  });
	$("#cognomeindmod").focus(function(){
	    $("#span2cognome").css("display", "inline")
	  });

	$("#viaindmod").focus(function(){
	    $("#span2via").css("display", "inline")
	  });

	$("#num_civindmod").focus(function(){
	    $("#span2num_civ").css("display", "inline")
	});
	
	$("#comuneindmod").focus(function(){
	    $("#span2comune").css("display", "inline")
	  });

	$("#capindmod").focus(function(){
	    $("#span2cap").css("display", "inline")
	});
	
	$("#nomeindmod").blur(function(){
	    $("#span2nome").css("display", "none")
	  });
	$("#cognomeindmod").blur(function(){
	    $("#span2cognome").css("display", "none")
	  });

	$("#viaindmod").blur(function(){
	    $("#span2via").css("display", "none")
	  });

	$("#num_civindmod").blur(function(){
	    $("#span2num_civ").css("display", "none")
	});
	
	$("#comuneindmod").blur(function(){
	    $("#span2comune").css("display", "none")
	  });

	$("#capindmod").blur(function(){
	    $("#span2cap").css("display", "none")
	});
	
	return(valmodindnome()&&valmodindcognome()&&valmodindvia()&&valmodindnum_civ()&&valmodindcap()&&valmodindcomune())
}

function valmodindnome(){
	var name=document.getElementById("nomeindmod").value;
	 if (/^([^0-9]*)$/.test(name)&&name.length>2&&name.length<25)
	  {
		 return true;
	  }
	    $("#nomeindmod").focus();
	    return (false)
}

function valmodindcognome(){
	var name=document.getElementById("cognomeindmod").value;
	 if (/^([^0-9]*)$/.test(name)&&name.length>2&&name.length<25)
	  {
		 return true;
	  }
	    $("#cognomeindmod").focus();
	    return (false)
}

function valmodindvia(){
	var name=document.getElementById("viaindmod").value;
	 if (/^([^0-9]*)$/.test(name)&&name.length>3&&name.length<40)
	  {
		 return true;
	  }
	    $("#viaindmod").focus();
	    return (false)
}

function valmodindnum_civ(){
	var name=document.getElementById("num_civindmod").value;
	 if (name.length>0&&name.length<6)
	  {
		 return true;
	  }
	    $("#num_civindmod").focus();
	    return (false)
}

function valmodindcomune(){
	var name=document.getElementById("comuneindmod").value;
	 if (/^([^0-9]*)$/.test(name)&&name.length>2&&name.length<40)
	  {
		 return true;
	  }
	    $("#comuneindmod").focus();
	    return (false)
}

function valmodindcap(){
	var cap=document.getElementById("capindmod").value; 
	if (/^\d+$/.test(cap)&&cap.length==5)
	  {
		 return true;
	  }
	    $("#capindmod").focus();
	    return (false)
}









function validatecarta(){
	$("#codicecarta").focus(function(){
	    $("#spancarta").css("display", "inline")
	  });
	$("#cvv").focus(function(){
	    $("#spancvv").css("display", "inline")
	  });
	$("#codicecarta").blur(function(){
	    $("#spancarta").css("display", "none")
	  });
	$("#cvv").blur(function(){
	    $("#spancvv").css("display", "none")
	  });
	return valcodicecarta()&&valcvv()&&valdata_scad();
}

function valcodicecarta(){
	var telefono=document.getElementById("codicecarta").value;
	if (/^\d+$/.test(telefono)&&telefono.length==16)
	  {
		 return true;
	  }
	  $("#codicecarta").focus();
	 return (false)
}
function valcvv(){
	var telefono=document.getElementById("cvv").value;
	if (/^\d+$/.test(telefono)&&telefono.length==3)
	  {
		 return true;
	  }
	  $("#cvv").focus();
	 return (false)
}
function valdata_scad(){
	var mese=document.getElementById("mese_scad").value;
	if (/^\d+$/.test(mese)&&mese>0&&mese<13)
	  {
		var anno=document.getElementById("anno_scad").value;
		 var d = new Date();
		 var n = d.getFullYear();
		 if (/^\d+$/.test(anno)&&anno>=n)
			 return true;
		 else
			 $("#anno_scad").focus();
	  }else{
		  $("#mese_scad").focus();
	  }
	 return (false)
}





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
