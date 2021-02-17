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
        
        <link rel="stylesheet" href="./css/main.css">

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
		session.setAttribute("redirect","OrdersManagerPage.jsp");%><br>
	<%}%>

	<div class="container">
      <div class="row padding-inner"> 
      <div class="form-group col-md-5">
      <label for="inputState">Id Ordine</label>
      <form action="managerordersearchidcontrol">
     	 <input type="text" class="form-control" id="inputCity" name="idOrd" placeholder="Inserisci id ordine">
     	 <br>
         <input type="submit" class="btn-primary" value="Ricerca">
      </form>
      </div>
      <div class="form-group col-md-5">
      <label for="inputState">Email</label>
      <form action="managerordersearchmailcontrol">
     	 <input type="text" class="form-control" id="inputCity" name="mailOrd" placeholder="Inserisci mail utente">
     	 <br>
         <input type="submit" class="btn-primary" value="Ricerca">
      </form>
      </div>
            
            <div class="col-md-6 col-sm-12">
            
            <p class="block">Ricerca ordini</p>
            <div class="thumbnail">
            
            
	
	<% if(session.getAttribute("ordini")==null){%>
		
	
	<% 	}
		else{
			ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("ordini");
		    if(orders.size()==0){ %>
		    
		    <p> Non sono stati trovati ordini </p>
		    	
		<%  } else {
			
			for(Order o : orders){
		
		%>
				 <p><b>Mail utente:</b><%= o.getUtente().getEmail() %>
				 <p><b>Numero dell'ordine:</b> <%=o.getNumeroOrdine() %></p>
				  <p><b>Data di invio:</b> <%=o.getDataInvio() %></p>
				  <p><b>Stato:</b> <%=o.getStato() %></p>
				  <br>
				  <a href="managerorderviewcontrol?idOrd=<%=o.getNumeroOrdine()%>"><button class="button">Mostra dettagli / Cambia stato</button></a>
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