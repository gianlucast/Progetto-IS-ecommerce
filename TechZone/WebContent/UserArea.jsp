<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,it.techzone.control.*,it.techzone.model.beans.*" %>

<% 
	
	if(session.getAttribute("user")==null){
		System.out.println("USER AREA HA TROVATO USER VUOTO");
		response.sendRedirect("./Login.jsp");
	}
	else{
		UtenteRegistrato utente= (UtenteRegistrato) session.getAttribute("user"); 
		
%>


<!DOCTYPE html>
<html>
	<head>
	<link rel="icon" href="./imgs/logo_vinyl.png">
		<meta charset="ISO-8859-1">
		<title>User Area-TechZone</title>
		<link rel="stylesheet" href="./css/userarea.css">
	<style>
		span{
			display:none;
			color:red
		}
	</style>
	</head>
	<body>
	<script src="./scripts/showforms.js"></script>
	<script src="./scripts/orderfilter.js"></script>
	<script src="./scripts/UserAreaValidator.js"></script>
	<jsp:include page="/Header.jsp"/>
	<%if(session.getAttribute("alertMsg")!=null){%>
		<h3><font color="red"><%=session.getAttribute("alertMsg")%></font></h3> 
		<%session.setAttribute("alertMsg",""); 
		session.setAttribute("redirect","UserArea.jsp");%><br>
	<%}%>
	<div id="informazioni" class="box">
			<h2 align="left">Le tue informazioni</h2>
			<div id="info">
			<br><b>Nome:</b> <%=utente.getNome() %>
			<br><b>Cognome:</b> <%=utente.getCognome() %>
			<br><b>Telefono:</b> <%=utente.getTelefono() %>
			<br><b>Email:</b> <%=utente.getEmail() %>
			<br><button name="modifica_user" id="modifica_user" class="bottone" onclick="showformuser()">Mostra modifica </button>
			</div>
			<br>
			<div id="mod_info" style="display:none">
			<form action="user" method="post" onsubmit="return valmodinfo()">
				<br><input type="hidden" name="action" value="updateuser">
				<br><input type="hidden" name="mail" id="mail" value="<%=utente.getEmail() %>">
				<br><label for="nome">Nome:<input type="text" name="name" id="nome" value="<%=utente.getNome() %>" required></label><span id="spannome">Inserisci un nome valido (solo lettere, almeno 3)!</span>
				<br><label for="cognome">Cognome:<input type="text" name="cognome" id="cognome"  value="<%=utente.getCognome() %>"required></label><span id="spancognome">Inserisci un cognome valido(solo lettere, almeno 3)!</span>
				<br><label for="telefono">Telefono:<input type="text" name="telefono" id="telefono" value="<%=utente.getTelefono() %>"></label><span id="spantelefono">Inserisci una numero di telefono valido (solo numeri, minimo 8, massimo 14)!</span>
				<br><label for="password">Password:<input type="password" name="password" id="password" placeholder="Inserisci una nuova password o lascia vuoto per confermare la tua vecchia password"></label><span id="spanpassword">Inserisci una password valida (6 caratteri o più, ci teniamo alla tua privacy!)! </span>
				<br><input type="submit" name="mod" id="mod" value="Conferma">
			</form>
			</div>
		</div>
		<hr>
		
		<hr>
		  
				%>
				
				<%	
				
			}
			%>
		</div> 
		<hr>
		<div id="ordini" class="box">
			<h2 align="left">I TUOI ORDINI</h2>
			<a href="order?action=showorders"><button id="mostraordini" class="bottone">Mostra tutti gli ordini</button></a><br>
			<input type="date" id="data1"><input type="date" id="data2" class="bottone"> <button id="filtraordini" class="bottone" onClick="ajaxordini()">Filtra per data</button><span id="spandata">Inserisci delle date valide</span>
			<br><br>
			<%if(request.getAttribute("ordini")==null){ %>
				<div id="ordinifiltrati" style="display:none">
			<%}else{ %>
				<div id="ordinifiltrati">
			<%} %>
			<br><br>
			<%if(request.getAttribute("ordini")==null){ %>
			
			
			<%}else{
				%><%
				
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
						   <td><%= elementi.get(j).getCodice%>
							<td><%=elementi.get(j).getNomeProd()%>
							<td> <%=elementi.get(j).getQuantita()%>
							<td> <%=elementi.get(j).getCosto()%> 
						</tr>
					<% }%>
					</table>
					
					<br><br>
				<% }%>
				
				</div>
			<%}request.setAttribute("ordini",null); %>
			
	</div>
	
	<br><br>
	<jsp:include page="/Footer.jsp"/>
	
	</body>
	
	<%}%>	

</html>