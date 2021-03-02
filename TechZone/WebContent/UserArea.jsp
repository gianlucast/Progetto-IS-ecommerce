<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,it.techzone.control.*,it.techzone.model.beans.*" %>

<% 
	
	if(session.getAttribute("utente")==null){
		
		%>
		
          <p>USER AREA HA TROVATO USER VUOTO</p>
          
          <% 
		response.sendRedirect("./Login.jsp");
	}
	else{
		UtenteRegistrato utente= (UtenteRegistrato) session.getAttribute("utente"); 
		
%>


<!DOCTYPE html>
<html>
    <head>
        <title>Area utente</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <!-- Boostrap Core CSS-->
        <link rel="stylesheet" href="css/bootstrap.min.css"> 
        
        <!-- Main CSS -->
        <link rel="stylesheet" href="css/main.css">
        
		<link rel="icon" href="./img/logo_techzone.png">
        <!-- Google fonts -->
        <link href='http://fonts.googleapis.com/css?family=Open%20Sans:400,700,600' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Josefin+Sans:400,600,700' rel='stylesheet' type='text/css'>        
    </head>
    <body>
    
	<jsp:include page="/Header.jsp"/>
		<br><br>
		
		<%if(session.getAttribute("alertMsg")!=null){%>
		<h3><font color="red"><%=session.getAttribute("alertMsg")%></font></h3> 
		<%session.setAttribute("alertMsg",""); 
		session.setAttribute("redirect","UserArea.jsp");%><br>
	<%}%>

	<div class="container">
      <div class="row padding-inner"> 
             
             <!-- Column1 -->
             <div class="col-md-6 col-sm-12">
             	
                  
                  <p class="block">Le tue informazioni</p>
                  
                  <div class="thumbnail">
                  
                  <p><b>Nome:</b> <%=utente.getNome() %></p>
				  <p><b>Cognome:</b> <%=utente.getCognome() %></p>
				  <p><b>Telefono:</b> <%=utente.getTelefono() %></p>
				  <p><b>Email:</b> <%=utente.getEmail() %></p>    
                 
            	 </div>
            </div>
            
            
            <div class="col-md-6 col-sm-12">
            
            <p class="block">I tuoi ordini</p>
            <div class="thumbnail">
            
            
	
	<% if(session.getAttribute("ordini")==null){%>
		
		<a href="userordersearchcontrol"><button class="button">Mostra ordini</button></a>
		
		
	<% 	}
		else{
			ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("ordini");
		    if(orders.size()==0){%>
		    
		    <p> Non ci sono ordini </p>
		    	
		<%  } else {
			
			for(Order o : orders){
		
		%>
		
				 <p><b>Numero dell'ordine:</b> <%=o.getNumeroOrdine() %></p>
				  <p><b>Data di invio:</b> <%=o.getDataInvio() %></p>
				  <p><b>Stato:</b> <%=o.getStato() %></p>
				  <br>
				  <a href="userorderviewcontrol?idOrd=<%=o.getNumeroOrdine()%>"><button class="button">Mostra dettagli</button></a>
				  <br><br>
		
		<%
		
			}
			
		}
		    
		
			%>
		
	
             
            
 		</div>
 		
            
    </div>
    
    <%
    	}
	}

	session.setAttribute("ordini", null);
	
		%>
		
		
   </div>
   
   
	<br><br>
	<br><br><br><br><br><br>
	<jsp:include page="/Footer.jsp"/>
	
	
	</body>	
	
	 

</html>	


			




















