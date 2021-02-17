<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,it.techzone.control.*,it.techzone.model.beans.*"%>
    
<!DOCTYPE html>
<html>

<% 
	if(session.getAttribute("ordine")==null) response.sendRedirect("OrderManagerPage.jsp");
	else{
		Order order = (Order)session.getAttribute("ordine");
%>
<head>
		
 		<meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <!-- Boostrap Core CSS-->
        <link rel="stylesheet" href="css/bootstrap.min.css"> 
        
        <link rel="icon" href="./imgs/logo_techzone.png">
        <link rel="stylesheet" href="./css/main.css">
        
        

        <!-- Google fonts -->
        <link href='http://fonts.googleapis.com/css?family=Open%20Sans:400,700,600' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Josefin+Sans:400,600,700' rel='stylesheet' type='text/css'>      
 <title>Dettagli ordine</title>
</head>

<body>
<jsp:include page="/Header.jsp"/> 

	<div class="section padding-inner">
	    	<div class="container">
	    			<font size="5"><b>Numero dell'ordine:</b> <%=order.getNumeroOrdine() %></font><br/>
                    <font size="5"><b>Stato:</b> <%=order.getStato() %></font><br/> 
                   	 <form action="orderstatuscontrol">
                   	 	<input type="hidden" name="orderId" value="<%= order.getNumeroOrdine() %>">
                    	<select name="changeStatus">
                    		<option value="In Preparazione">In Preparazione</option>
                    		<option value="Spedito">Spedito</option>
                    		<option value="In Consegna">In consegna</option>
                    		<option value="Spedizione in ritardo">Spedizione in ritardo</option>
                    		<option value="Pacco smarrito">Pacco smarrito</option>
                    		<option value="Consegnato">Consegnato</option>
                    	</select>
                    	<input type="submit" value="Cambia Stato">
                   	 </form>
    				<font size="5"><b>Prezzo totale:</b> <%=order.getTotale() %></font><br/>
	    	<br><br><br>
				<!--  <div class="row"> -->
				
				<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col"></th>
      <th scope="col">Nome</th>
      <th scope="col">Quantità</th>
      <th scope="col">Costo</th>
    </tr>
  </thead>
  <tbody>
  <%  for(int i=0; i<order.getProdotti().size(); i++){ %>
    <div class="row"> 
   	 <tr>
     	 <th scope="row"><%= i+1 %></th>
     	 <td><%=order.getProdotti().get(i).getProdotto().getNomeProd() %></td>
    	  <td><%=order.getProdotti().get(i).getQuantita() %></td>
     	 <td><%=order.getProdotti().get(i).getCosto() %></td>
   	   <td><a href="productviewcontrol?idProd=<%=order.getProdotti().get(i).getProdotto().getCodice()%>"><img src="imgControl?id=<%=order.getProdotti().get(i).getProdotto().getCodice() %>" alt="Project Name" width=100px height=100px></a></td>
     </tr>
    </div>
    
    
 
		<%
		
			}
			
		}
		    
		
			%>	
					
					 </tbody>
</table>
					
					
					
                        	
					</div>
					
				</div>
              
                
        </div>
					
					
					
           
                
                <br><br><br>
                 <script src="js/jquery-1.11.0.js"></script>
     
	 <script src="js/bootstrap.min.js"></script>
     
     <!-- Smooth scroll JS -->
     <script src="js/smoothscroll.js"></script>
     
    <!-- Masonry JavaScript -->
    <script src="js/masonry.js"></script>
    
    <!-- Gallery JavaScript -->
	<script src="js/touchTouch.jquery.js"></script>
	<script src="js/script.js"></script>

<jsp:include page="Footer.jsp"/>


</body>
</html>