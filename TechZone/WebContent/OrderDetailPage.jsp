<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.techzone.control.*,it.techzone.model.beans.*"%>
    
<!DOCTYPE html>
<html>

<% 
	if(request.getAttribute("ordine")==null) response.sendRedirect("UserArea.jsp");
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
				<div class="row">
					
					
					
                    <!-- Project Info Column -->
					<div class="portfolio-item-description col-sm-6">
					
					<p><b>Numero dell'ordine:</b> <%=order.getNumeroOrdine() %></p>
                    <p><b>Stato:</b> <%=order.getStato() %></p>	
    				<p><b>Prezzo totale:</b> <%=order.getTotale() %></p>
    				
				<%  for(int i=0; i<order.getProdotti().size(); i++){ %>
		
				 	<p><b>Nome:</b> <%=order.getProdotti().get(i).getProdotto().getNomeProd() %></p>
				 	<p><b>Tipo:</b> <%=order.getProdotti().get(i).getProdotto().getTipo() %></p>
				 	<p><b>Categoria:</b> <%=order.getProdotti().get(i).getProdotto().getCategoria() %></p>
				 	<p><b>Quantita':</b> <%=order.getProdotti().get(i).getProdotto().getQuantita() %></p>
				 	<p><b>Costo:</b> <%=order.getProdotti().get(i).getProdotto().getCosto() %></p>
				 	
				 	</div>
				 	
				 	<div class="col-sm-6">
						<div class="portfolio-item">
							<div class="portfolio-image">
								<a href="#"><img src="imgControl?id=<%=order.getProdotti().get(i).getProdotto().getImmagine() %>" alt="Project Name"></a>
							</div>
						</div>
					</div>
		
		
				<%
		
			}
			
		}
		    
		
			%>	
					
					
					
					
                        	
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