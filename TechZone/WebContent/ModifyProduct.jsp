<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.techzone.control.*,it.techzone.model.beans.*"%>

<html>
<% 
	if(request.getAttribute("prodotto")==null)response.sendRedirect("HomePage.jsp");
	else{
	Product prodotto=(Product)request.getAttribute("prodotto");
%>
    <head>
        <title><%=prodotto.getNomeProd() %>-TechZone</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="css/bootstrap.min.css"> 

        <link rel="stylesheet" href="css/touchTouch.css">

        <link rel="stylesheet" href="css/main.css">
        

        <!-- Google fonts -->
        <link href='http://fonts.googleapis.com/css?family=Open%20Sans:400,700,600' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Josefin+Sans:400,600,700' rel='stylesheet' type='text/css'>        
    </head>
    
    <body>
    <tbody>
    <tr>
    <td>
    <jsp:include page="/Header.jsp"/> 
    

		<div class="section padding-inner">
	    	<div class="container">
				<div class="row">
					<!-- Image Column -->
					<div class="col-sm-6">
						<div class="portfolio-item">
							<div class="portfolio-image">
								<a href="#"><img src="imgControl?id=<%=prodotto.getCodice()%>" alt="Project Name"></a>
							</div>
						</div>
					</div>
					<!-- End Image Column -->
					
                    <!-- Project Info Column -->
					<div class="portfolio-item-description col-sm-6">
						<h3><%=prodotto.getNomeProd() %></h3>
						<p>
							<%=prodotto.getDescrizione() %>
						</p>
						
						<ul class="list-group">
							<li class="list-group-item"><b>Tipo:</b> <%=prodotto.getTipo() %></li>
							<li class="list-group-item"><b>Categoria:</b> <%=prodotto.getCategoria() %></li>
							
							<form action="addtocartcontrol" method="get">
								<input type="hidden" name="product" value="<%=prodotto.getCodice() %>">
								<li class="list-group-item"><b>Prezzo: </b><%= prodotto.getCosto() %>€</li>
							
							</form>
	
						</ul>
						
					
                        	
					</div>
					<!-- End Project Info Column -->
				</div>
                <!-- End Row -->
                </td>
                <br><br><br>
                <center>
                <h1 style="text-align:center; font-family: 'Josefin Sans', sans-serif">Modifica prodotto:</h1>
                <br><br><br>
                <td>
                <div class="container h-100" style="position:center">
                <form action="updateproductcontrol" class="form-group col-md-12" method="get" onSubmit="validateMod()">
                <input type="hidden" name="action" value="mod">
                <div class="form-group< col-md-5">
                <label for="inputZip">Nome prodotto</label>
                <input type="text" class="form-control" id="inputZip" name="nomeprod" value="<%= prodotto.getNomeProd() %>">
                </div>
                <div class="form-group col-md-5">
                <label for="inputZip">Quantità</label>
                <input type="number" class="form-control" id="inputZip" name="quantita" value="<%= prodotto.getQuantita() %>" >
                </div>
                <div class="form-group col-md-5">
                <label for="inputZip">Tipo</label>
                <input type="text" class="form-control" id="inputZip" name="tipo" value="<%= prodotto.getTipo() %>" >
                </div>
                <div class="form-group col-md-5">
                <label for="inputZip">Costo</label>
                <input type="number" class="form-control" id="inputZip" name="costo" value="<%= prodotto.getCosto() %>" >
                </div>
                <div class="form-group col-md-5">
                <label for="inputZip">Codice</label>
                <input type="number" class="form-control" id="inputZip" name="codice" readonly value="<%= prodotto.getCodice() %>">
                </div>
                <div class="form-group col-md-5">
                <label for="inputZip">Categoria</label>
                <input type="text" class="form-control" id="inputZip" name="categoria" value="<%= prodotto.getCategoria() %>">
                </div>
                 <div class="form-group col-md-8">
                <label for="inputZip">Descrizione</label>
                <textarea class="form-control" id="inputZip" name="descrizione" style="height:250px"><%= prodotto.getDescrizione() %></textarea>
                </div>
                <br><br><br>
                <input type="submit" class="btn-primary" value="Conferma modifiche">
                <br><br><br>
                <input type="reset" class="btn-primary" value="Reset">
                </form>
                </div>
                </td>
                </center>
                </tr>
                </tbody>
                <br><br><br>
                <% } %>
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