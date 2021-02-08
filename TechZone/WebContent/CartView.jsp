<%@ page language="java" %>
    <%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.vinylzone.control.*,it.vinylzone.model.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Carrello-VinylZone</title>
	<link rel="icon" href="./imgs/logo_vinyl.png">
	<link rel="stylesheet" href="./css/cartview.css">
</head>
<body>
	<script src="./scripts/cart_scripts.js"></script>
	<script src="./scripts/showforms.js"></script>
	<jsp:include page="Header.jsp"/>
	<% HttpSession sessione=request.getSession(); 
		if(sessione.getAttribute("cart")==null) sessione.setAttribute("cart", new ShoppingCart());
		ShoppingCart cart=(ShoppingCart)sessione.getAttribute("cart");
		ArrayList<CartItem> lista=cart.getList();
	%>
	<%
	request.setAttribute("redirect","CartView.jsp");
	if(session.getAttribute("alertMsg")!=null){%>
		<h3><font color="red"><%=session.getAttribute("alertMsg")%></font></h3> 
		<%session.setAttribute("alertMsg","");
		session.setAttribute("redirect","CartView.jsp");%>
	<%}%><br>
	<h1>Il tuo carrello:</h1><br>
	<% if(cart.isEmpty()){%>
	<h2>Vuoto, aggiungi qualcosa!</h2>
	<%
	}else{%> <table ><tr><th>FOTO<th>NOME<th>QTA'<th>PREZZO</th></tr><%
		for(int i=0;i<lista.size();i++){
			if(lista.get(i).isDeleted()) response.sendRedirect("./product?action=changequantity&qt=0&id="+lista.get(i).getCode());	
			%>
			<tr>
				<td><img src="imgControl?id=<%=lista.get(i).getCode()%>" style="width:100px"></td>
				<td><%=lista.get(i).getName()%><td align="center"><%=lista.get(i).getNum()%><td align="center"><%=lista.get(i).getTotalPrice()%>
				<td><a href="product?action=changequantity&qt=<%=lista.get(i).getNum()+1%>&id=<%=lista.get(i).getCode()%>"><button class="button">Aggiungi</button></a>
				<td><a href="product?action=changequantity&qt=<%=lista.get(i).getNum()-1%>&id=<%=lista.get(i).getCode()%>"><button class="button">Rimuovi</button></a>
			</tr><%
		}
		%>
		</table>
		<hr>
		<p align="right"> <%=cart.getTotal()%></p>
		<%if(session.getAttribute("user")!=null){ 
			UserBean user=(UserBean) session.getAttribute("user");
			ArrayList<Indirizzo> indirizzi=user.getIndirizzi();
			ArrayList<Carta> carte=user.getMetodi_pag();
		%>
		<div id="new_addr" style="display:none">
			<form action='address' method='post' name='formnuovoindirizzo'><br><input type='hidden' name='action' value='addindirizzo'><br><label for='nome'>Nome:<input type='text' name='nome' id='nome'></label><br><label for='cognome'>Cognome:<input type='text' name='cognome' id='cognome'></label><br><label for='via'>Via:<input type='text' name='via' id='via'></label><br><label for='numero civico'>Numero Civico:<input type='text' name='num_civ' id='num_civ'></label><br><label for='cap'>Cap:<input type='text' name='cap' id='cap'></label><br><label for='comune'>Comune:<input type='text' name='comune' id='comune'></label><br><input type='submit' name='mod' id='mod' value='Conferma'></form>
		</div>
		<div id="new_card" style="display:none">
			<form action="user" method="post">
				<input type="hidden" name="action" value="addcarta"><br><label for="codice">Codice:<input type="text" name="codice" placeholder="Codice a 16 cifre"></label><br><label for="cvv">CVV:<input type="text" name="cvv" placeholder="Codice a 3 cifre"></label><br><label for="mese/anno">Mese/Anno scadenza<input type="number" name="mese" min="1" max="12">/<input type="number" name="anno" min="<%=new java.text.SimpleDateFormat("yyyy").format(new java.util.Date()) %>" max="<%=new java.text.SimpleDateFormat("yyyy").format(new java.util.Date())+10 %>"></label><br><input type="submit" value="Conferma inserimento carta">
			</form>
		</div>
		
		
		<form action="order" method="post" name="placeorder">
			<input type="hidden" name="action" value="place">
			<div align="left" id="indirizzospedizione">
				<h3 align="left">Seleziona l'indirizzo di consegna</h3>
				<table border="5">
				<%
					Indirizzo indirizzo;
					for(int i=0;i<indirizzi.size();i++){
						indirizzo=indirizzi.get(i);
				%>
				<tr><td><input type="radio" name="indirizzo_cons_scelto" id="indirizzo<%=i %>" value="<%=indirizzo.getIdindirizzo()%>" checked></td><td><label for="NomeCognome"><%=indirizzo.getNome()%> <%=indirizzo.getCognome()%></label><br><label for="Via"><%=indirizzo.getVia()%></label><br><label for="num_civ"><%=indirizzo.getNumero_civico()%></label><br><label for="cap"><%=indirizzo.getCap()%></label><br><label for="Comune"><%=indirizzo.getComune()%></label></td></tr>
				<%} %>
				</table>
				<button type="button" name="bottonenew_addr" id="bottonenew_addr" onclick="showformnewaddress('new_addr')">Aggiungi un nuovo indirizzo</button>
				
			</div>
			<label for="fattsped">Usa lo stesso indirizzo per la fatturazione</label><input type="checkbox" name="fattsped" id="checkfatt" onclick="showfatturazione()" checked>
			<div align="left" id="indirizzofatturazione" style="display: none">
				<h3 align="left">Seleziona l'indirizzo di fatturazione</h3>
				<table border="10">
				<%for(int i=0;i<indirizzi.size();i++){	
					indirizzo=indirizzi.get(i);
				%>
				<tr><td><input type="radio" name="indirizzo_fatt_scelto" id="indirizzo<%=i %>" value="<%=indirizzo.getIdindirizzo()%>" checked></td><td><label for="NomeCognome"><%=indirizzo.getNome()%> <%=indirizzo.getCognome()%></label><br><label for="Via"><%=indirizzo.getVia()%></label><br><label for="num_civ"><%=indirizzo.getNumero_civico()%></label><br><label for="cap"><%=indirizzo.getCap()%></label><br><label for="Comune"><%=indirizzo.getComune()%></label></td></tr>
				<%} %>
				</table>
			</div>
			
			<div align="left" id="cartadiv">
				<h3 align="left">Seleziona la carta</h3>
				<table border="5">
				<%for(int i=0;i<carte.size();i++){	%>
				<tr><td><input type="radio" name="carta_scelta" id="carta<%=i %>" value="<%=carte.get(i).getCodice()%>" checked></td><td><label for="carta"><%=carte.get(i).getCodice()%></label></td></tr>
				<%} %>
				</table>
				<button type="button" name="bottonenew_card" id="bottonenew_card" onclick="showformnewcard('new_card')">Aggiungi una nuova carta</button>
				
			</div>
			<input type="submit" value="Checkout"><br><br><br><br>
		</form>	
				
		<%}else{ %>
			
		<br><br><a href="order?action=place"><input type="button" value="CheckOut"></a>
		<br><br>
		
		<%}
	}	
	%>
	
	<jsp:include page="Footer.jsp"/>
</body>
</html>