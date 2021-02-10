<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.techzone.control.*,it.techzone.model.beans.*"%>
<html>
<% 
	if(request.getAttribute("prodotto")==null) response.sendRedirect("HomePage.jsp");
	else{
	Product prodotto=(Product)request.getAttribute("prodotto");
%>
<head><title><%=prodotto.getNomeProd() %>-TechZone</title><link rel="icon" href="./imgs/logo_vinyl.png">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="./css/itemview.css">
<link rel="stylesheet" href="./css/cat.css">
</head>
	<body>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
	<script src="./scripts/jqueryzoom.js"></script>
	     <div id="pagecontent">
		<jsp:include page="/Header.jsp"/>
		<jsp:include page="/lateral_menu.jsp"/>
		 <div id="contentwrap">
		 <center>
				<div class= "container1">
			
			<div class="img"><img src="imgControl?id=<%=prodotto.getCodice()%>" alt="Lights" class="w3-image" width="600" height="400" style="height:40%;width:45%"></div>
			
			<br><div class="paragraph"><%=prodotto.getNomeProd() %></div>
			<br><b>Tipo:</b> <%=prodotto.getTipo() %>
			<div class="containtext">
			<form action="addtocartcontrol" method="get">
				<input type="hidden" name="product" value="<%=prodotto.getCodice() %>">
				<%if(prodotto.getQuantita()>0){ %>
				<br><div class="ok"> Quantità 
				
				<select id="qta" name="qta">
					<% for(int i=1;i<=prodotto.getQuantita()&&i<10;i++){ %>
					<option value="<%=i%>"><%=i%></option>
					<%} %>
				</select>
				</div>
				<br><div class="ok"><div class="price"><b>Prezzo:</b> <%=prodotto.getCosto() %>€</div>
				<br><input type="submit" class="button" value="Aggiungi al carrello">
			</form>
				</div>
				</div>
				</div>
		<div class="container-box">
		<div class="box"><b>Descrizione:</b> <%=prodotto.getDescrizione() %>
		<br><b>Categoria:</b> <%=prodotto.getCategoria() %></div>
		
		</div>
		
			
		<%}else{ %>
			<h3> Prodotto al momento non disponibile</h3> <%} %>
			<% }%>
		</div>
		
		</div>
		</div>
		</center>
		</h2>
		<jsp:include page="Footer.jsp"/>
	</body>
</html>