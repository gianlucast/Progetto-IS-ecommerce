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
        
        <script src="./scripts/ValidateModifyProduct.js"></script>
        <link rel="icon" href="./img/logo_techzone.png">

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
                
                <form action="updateproductcontrol" class="form-group col-md-12" method="get" onsubmit="return ValidateModifyProduct()">
                
                <input type="hidden" name="action" value="mod">
                <div class="form-group< col-md-5">
                <label for="inputZip">Nome prodotto</label>
                <input type="text" class="form-control" id="inputNameProd" name="nomeprod" value="<%= prodotto.getNomeProd() %>">
                <span id="spannameprod" style="display:none; font-family: 'Josefin Sans', sans-serif;"><i class="fa fa-info-circle" aria-hidden="true"></i> Minimo 5, massimo 64 (tra caratteri e numeri) </span>
                </div>
                <div class="form-group col-md-5">
                <label for="inputZip">Quantità</label>
                <input type="number" class="form-control" id="inputQuant" name="quantita" value="<%= prodotto.getQuantita() %>">
                <span id="spanquant" style="display:none; font-family: 'Josefin Sans', sans-serif;"><i class="fa fa-info-circle" aria-hidden="true"></i> Minimo 1, massimo 99 cifre(non sotto lo zero). Dopo la ,/. solo due cifre.</span>
                </div>
                <div class="form-group col-md-5">
                <label for="inputZip">Tipo</label>
                <input type="text" class="form-control" id="inputTipo" name="tipo" value="<%= prodotto.getTipo() %>" >
                <span id="spantipo" style="display:none; font-family: 'Josefin Sans', sans-serif;"><i class="fa fa-info-circle" aria-hidden="true"></i> Solo caratteri</span>
                </div>
                <div class="form-group col-md-5">
                <label for="inputZip">Costo</label>
                <input type="number" step="0.01" class="form-control" id="inputCosto" name="costo" value="<%= prodotto.getCosto() %>" >
                <span id="spancosto" style="display:none; font-family: 'Josefin Sans', sans-serif;"><i class="fa fa-info-circle" aria-hidden="true"></i>Minimo 1, non sotto lo zero</span>
                </div>
                <div class="form-group col-md-5">
                <label for="inputZip">Codice</label>
                <input type="number" class="form-control" id="inputCodice" name="codice" readonly value="<%= prodotto.getCodice() %>">
                </div>
                <div class="form-group col-md-5">
                <label for="inputZip">Categoria</label>
                <input type="text" class="form-control" id="inputCat" name="categoria" value="<%= prodotto.getCategoria() %>">
                <span id="spancat" style="display:none; font-family: 'Josefin Sans', sans-serif;"><i class="fa fa-info-circle" aria-hidden="true"></i> Solo caratteri</span>
                </div>
                 <div class="form-group col-md-8">
                <label for="inputZip">Descrizione</label>
                <textarea class="form-control" id="inputDescr" name="descrizione" style="height:250px"><%= prodotto.getDescrizione() %></textarea>
                <span id="spandescr" style="display:none; font-family: 'Josefin Sans', sans-serif;"><i class="fa fa-info-circle" aria-hidden="true"></i> Minimo 5 caratteri, massimo 1000 (fra caratteri, numeri, simboli..)</span>
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