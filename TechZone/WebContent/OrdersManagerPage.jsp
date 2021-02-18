<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,it.techzone.control.*,it.techzone.model.beans.*" %>

<% 
	
	if(session.getAttribute("manager")==null){
		
		%>
		
          <p>USER AREA HA TROVATO USER VUOTO</p>
          
          <% 
		response.sendRedirect("./Login.jsp");
	}
	else{
		Manager manager= (Manager) session.getAttribute("manager"); 
		
%>


<!DOCTYPE html>
<html>
    <head>
        <title>Area Manager</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <!-- Boostrap Core CSS-->
        <link rel="stylesheet" href="css/bootstrap.min.css"> 
        
        <!-- Main CSS -->
        <link rel="stylesheet" href="css/main.css">
        

        <!-- Google fonts -->
        <link href='http://fonts.googleapis.com/css?family=Open%20Sans:400,700,600' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Josefin+Sans:400,600,700' rel='stylesheet' type='text/css'>        
    </head>
    <body>
    
	<jsp:include page="/Header.jsp"/>
		<br><br>
		
		<h1 style="font-family:'Josefin Sans', sans-serif; text-align:center">Ricerca ordini</h1>
		
		
		<%if(session.getAttribute("alertMsg")!=null){%>
		<h3><font color="red"><%=session.getAttribute("alertMsg")%></font></h3> 
		<%session.setAttribute("alertMsg",""); 
		session.setAttribute("redirect","OrdersManagerPage.jsp");%><br>
	<%}%>
	<center>
	<div class="container">
      <div class="row padding-inner"> 
      <form action="managerordersearchidcontrol">
     	 <input type="text" class="casella" name="idOrd" placeholder="inserisci id ordine">
         <input type="submit" class="button" value="Ricerca">
      </form>
      <form action="managerordersearchmailcontrol">
     	 <input type="text" class="casella" name="mailOrd" placeholder="inserisci mail utente">
         <input type="submit" class="button"value="Ricerca">
      </form>
            
            <br><br><br>
            <p class="block">Risultati:</p>
            
            <div class="thumbnail">
            
            
	
	<% if(session.getAttribute("ordini")==null){%>
		
	
	<% 	}
		else{
			ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("ordini");
		    if(orders.size()==0){ %>
		    
		    <p style="font-family:'Josefin Sans', sans-serif"> Non sono stati trovati ordini </p>
		    	
		<%  } else {
			
			for(Order o : orders){
		
		%>
				 <p style="font-family:'Josefin Sans', sans-serif"><b>Mail utente:</b> <%= o.getUtente().getEmail() %>
				 <p style="font-family:'Josefin Sans', sans-serif"><b>Numero dell'ordine:</b> <%=o.getNumeroOrdine() %></p>
				  <p style="font-family:'Josefin Sans', sans-serif"><b>Data di invio:</b> <%=o.getDataInvio() %></p>
				  <p style="font-family:'Josefin Sans', sans-serif"><b>Stato:</b> <%=o.getStato() %></p>
				  <br>
				  <a href="managerorderviewcontrol?idOrd=<%=o.getNumeroOrdine()%>"><button class="button">Mostra dettagli / Cambia stato</button></a>
				  <br><br>
				  
		
		<%
		
			}
			
		}
		    
		
			%>
		
	
             
            
 		</div>
 		
            
    
    
    <%
    	}
	}

	session.setAttribute("ordini", null);
	
		%>
		
   </div>
   </center>
   
   
	<br><br>
	<br><br><br><br><br><br>
	<jsp:include page="/Footer.jsp"/>
	
	
	</body>	
	
	 

</html>	