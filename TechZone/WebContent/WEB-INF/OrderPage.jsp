<%@ page language="java" %>
     <%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.techzone.control.*,it.techzone.model.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order-Page TechZone</title>
</head>
<body>

<hr>
		<div id="ordini" class="box">
			<h2 align="left">I TUOI ORDINI</h2>
			
			<input type="date" id="data1"><input type="date" id="data2" class="bottone"> <button id="filtraordini" class="bottone" onClick="ajaxordini()">Filtra per data</button><span id="spandata">Inserisci delle date valide</span>
			<br><br>
			<%if(request.getAttribute("ordini")==null){ %>
				<div id="ordinifiltrati" style="display:none">
			<%}else{ %>
				<div id="ordinifiltrati">
			<%} %>
			<br><br>
			<%
				
				ArrayList<Order> ordini=(ArrayList<Order>)request.getAttribute("ordini");
				for(int i=0;i<ordini.size();i++) {
					%>
					<hr>
					NUMERO ORDINE: <%= ordini.get(i).getNumeroOrdine()%><br>
					DATA: <%= ordini.get(i).getDataArrivo() %>
					<table>
						<!--  <th>CODICE PRODOTTO-->
						<th>NOME PRODOTTO
						<th>QUANTITA'
						<th>PREZZO TOTALE
					<%
				
					ArrayList<ProductOrder> elementi=ordini.get(i).getProdotti();
					for(int j=0;j<elementi.size();j++) {
					%>
						<tr>
						<!-- 	<td><%= elementi.get(j).getProdotto().getCodice() %>-->
							<td><%=elementi.get(j).getProdotto().getNomeProd() %>
							<td> <%=elementi.get(j).getQuantita()%>
							<td> <%=elementi.get(j).getCosto()%> 
						</tr>
					<% }%>
					</table>
					<br><br>
				<% }%>
				
				</div>
			<%request.setAttribute("ordini",null); %>
			
	</div>
	</div>

</body>
</html>