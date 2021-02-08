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
		<!--  <div id="indirizzi" class="box">
			<h2 align="left">I tuoi indirizzi</h2>
			<button name="bottonenew_addr" id="bottonenew_addr" class="bottone" onclick="showformnewaddress('new_addr')">Aggiungi un nuovo indirizzo</button>
			<div id="new_addr" style="display:none">
			
				<form action='address' method='post' name='formnuovoindirizzo' onsubmit="return valnuovoind()"><br><input type='hidden' name='action' value='addindirizzo'><br><label for='nome'>Nome:<input type='text' name='nome' id='nomeind'></label><span id="span1nome">Inserisci un nome valido (solo lettere, almeno 3)!</span><br><label for='cognome'>Cognome:<input type='text' name='cognome' id='cognomeind'></label><span id="span1cognome">Inserisci un cognome valido(solo lettere, almeno 3)!</span><br><label for='via'>Via:<input type='text' name='via' id='viaind'></label><span id="span1via">Inserisci una via valida(solo lettere, almeno 4)!</span><br><label for='numero civico'>Numero Civico:<input type='text' name='num_civ' id='num_civind'></label><span id="span1num_civ">Inserisci un numero civico valido(almeno 1 carattere, massimo 6)!</span><br><label for='cap'>Cap:<input type='text' name='cap' id='capind'></label><span id="span1cap">Inserisci un CAP(5 numeri)!</span><br><label for='comune'>Comune:<input type='text' name='comune' id='comuneind'></label><span id="span1comune">Inserisci un comune valido(solo lettere, almeno 2)!</span><br><input type='submit' name='mod' id='mod' value='Conferma'></form>
			
			</div>
			<%
			if(utente.getIndirizzo().equalsIgnoreCase("")){
			%>
			Non hai indirizzi.
			<%}else{
				
			%>
			<br><b>INDIRIZZO <%= utente.getIndirizzo() %></b>
			<br><b>NOME</b>: <%=indirizzo.getNome() %>
			 <br><b>COGNOME</b>: <%=indirizzo.getCognome() %>
			<br><b>VIA</b>: <%=indirizzo.getVia() %>
			<br><b>NUMERO CIVICO</b>: <%=indirizzo.getNumero_civico() %>
			<br><b>CAP</b>: <%=indirizzo.getCap() %>
			<br><b>COMUNE</b>: <%=indirizzo.getComune() %>
			<button name="bottonemod_addr<%=i%>" id="bottonemod_addr <%=i%>" class="bottone" onclick="showformaddress('mod_addr<%=i%>')">Mostra modifica </button>
			<a href="address?action=deleteaddress&indirizzo=<%= utente.getIndirizzo() %>"><button name="elimina" class="bottone">Elimina</button></a>
			 <div id="mod_addr<%=i%>" style="display:none">
			<form action="address" method="post" onsubmit="return valmodind()">
				<br><input type="hidden" name="action" value="updateindirizzo">
				<br><input type="hidden" name="idindirizzo" id="idindirizzo" value="<%=indirizzo.getIdindirizzo() %>">
				<br><label for="nome">Nome:<input type="text" name="nome" id="nomeindmod" value="<%=indirizzo.getNome() %>"></label><span id="span2nome">Inserisci un nome valido (solo lettere, almeno 3)!</span>
				<br><label for="cognome">Cognome:<input type="text" name="cognome" id="cognomeindmod"  value="<%=indirizzo.getCognome()%>"></label><span id="span2cognome">Inserisci un cognome valido(solo lettere, almeno 3)!</span>
				<br><label for="via">Via:<input type="text" name="via" id="viaindmod" value="<%=indirizzo.getVia() %>"></label><span id="span2via">Inserisci una via valida(solo lettere, almeno 4)!</span>
				<br><label for="numero civico">Numero Civico:<input type="text" name="num_civ" id="num_civindmod" value="<%=indirizzo.getNumero_civico() %>"></label><span id="span2num_civ">Inserisci un numero civico valido(almeno 1 carattere, massimo 6)!</span>
				<br><label for="cap">Cap:<input type="text" name="cap" id="capindmod" value="<%=indirizzo.getCap() %>"></label><span id="span2cap">Inserisci un CAP(5 numeri)!</span>
				<br><label for="comune">Comune:<input type="text" name="comune" id="comuneindmod" value="<%=indirizzo.getComune() %>"></label><span id="span2comune">Inserisci un comune valido(solo lettere, almeno 2)!</span>
				<br><input type="submit" name="mod" id="mod" value="Conferma">
			</form>
			</div> 
		
		
		
		<%
			}
		}
		%>
		</div>-->
		<hr>
		<!--  <div id="carte" class="box">
			<h2 align="left">Le tue carte</h2>
			<button name="bottonenew_card" id="bottonenew_card" class="bottone" onclick="showformnewcard('new_card')">Aggiungi una nuova carta</button>
			<div id="new_card" style="display:none">
			<form action="user" method="post" onsubmit="return validatecarta()">
				<input type="hidden" name="action" value="addcarta"><br><label for="codice">Codice:<input type="text" name="codice" id="codicecarta" placeholder="Codice a 16 cifre"></label><span id="spancarta">Il codice deve essere a 16 cifre!</span><br><label for="cvv">CVV:<input type="text" id="cvv" name="cvv" placeholder="Codice a 3 cifre"></label><span id="spancvv">Il cvv deve essere a 3 cifre!</span><br><label for="mese/anno">Mese/Anno scadenza<input type="number" name="mese" id="mese_scad" min="1" max="12">/<input type="number" id="anno_scad" name="anno" min="<%=new java.text.SimpleDateFormat("yyyy").format(new java.util.Date()) %>" max="<%=new java.text.SimpleDateFormat("yyyy").format(new java.util.Date())+10 %>"></label><br><input type="submit" value="Conferma inserimento carta">
				</form>
			</div>
			<%
			if(utente.getPagamento){
			%> Non hai carte salvate!<% 	
			}else{
				%>
					<br><b>CARTA <%=i+1 %>: <%=carta.getCodice()%></b> <a href="user?action=deletecarta&carta=<%=carta.getCodice() %>"><button name="elimina" class="bottone">Elimina</button></a>
				<%	
				}
			}
			%>
		</div> -->
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
					NUMERO ORDINE: <%= ordini.get(i).getCode()%><br>
					DATA: <%=ordini.get(i).getData() %>
					<table>
						<!--  <th>CODICE PRODOTTO-->
						<th>NOME PRODOTTO
						<th>QUANTITA'
						<th>PREZZO TOTALE
					<%
					ArrayList<CartItem> elementi=ordini.get(i).getOrderline();
					for(int j=0;j<elementi.size();j++) {
					%>
						<tr>
						<!-- 	<td><%= elementi.get(j).getCode()%>-->
							<td><%=elementi.get(j).getName()%>
							<td> <%=elementi.get(j).getNum()%>
							<td> <%=elementi.get(j).getTotalPrice()%> 
						</tr>
					<% }%>
					</table>
					<a href="order?action=generatefattura&idorder=<%=ordini.get(i).getCode()%>"><button type="button" class="bottone">Stampa fattura</button></a>
					<br><br>
				<% }%>
				
				</div>
			<%}request.setAttribute("ordini",null); %>
			
	</div>
	<br><br>
	<jsp:include page="/Footer.jsp"/>
	</body>
		
	
	<%} %>
</html>