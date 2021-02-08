<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	if(session.getAttribute("user")==null){
		session.setAttribute("redirect","AdminMod.jsp");
		response.sendRedirect("Login.jsp");
	}else{
		UserBean user=(UserBean)session.getAttribute("user");
		if(!user.isManager()) response.sendRedirect("Catalogo.jsp");
		else{
			Collection<?> products = (Collection<?>) request.getAttribute("products");
			if(products == null) {
				response.sendRedirect("./admin");	
				return;
			}
			ProductBean product = (ProductBean) request.getAttribute("product");
	
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.vinylzone.control.*,it.vinylzone.model.*"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="icon" href="./imgs/logo_vinyl.png">
	<title>VinylZone</title>
	<style>
	span{
		display:none;
		color:red;
	}
	</style>
</head>

<body>
	<script src="./scripts/orderfilter.js"></script>
	<script src="./scripts/AdminModValidator.js"></script>
	<jsp:include page="/Header.jsp"/>
	<%if(session.getAttribute("alertMsg")!=null){%>
		<h3><font color="red"><%=session.getAttribute("alertMsg")%></font></h3> 
		<%session.setAttribute("alertMsg",""); %>
	<%}%><br>
	<h2>Products</h2>
	<a href="admin">List</a>
	<table border="1">
		<tr>
			<th>Code</th>
			<th>Name </th>
			<th>Description</th>
			<th>Action</th>
			<th>Picture</th>
		</tr>
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProductBean bean = (ProductBean) it.next();
					if(!bean.isDeleted()){
		%>
		<tr>
			<td><%=bean.getCode()%></td>
			<td><%=bean.getName()%></td>
			<td><%=bean.getDesc()%></td>
			<td><a href="admin?action=delete&id=<%=bean.getCode()%>">Delete</a><br>
				<a href="admin?action=modify&id=<%=bean.getCode()%>">Modify</a><br>
				<a href="product?action=details&id=<%=bean.getCode()%>">Details</a></td>
				<%
				//System.out.println(bean.getName());
				request.setAttribute("imgbean",bean);%>
			<td><%if(bean.getPhoto()!=null){ %><img src="imgControl?id=<%=bean.getCode()%>" style="width:100px"><% }else{%><img src="./imgs/no_disc.png" style="width:100px"><%} %></td>
		</tr>
		<%
					}	
				}
			} else {
		%>
		<tr>
			<td colspan="6">No products available</td>
		</tr>
		<%
			}
		%> </table>
		<h2>Insert</h2>
		<form action="admin" method="post" enctype="multipart/form-data" onsubmit="return valinsert()">
		<input type="hidden" name="action" value="insert"> 
		
		<label for="name">Name:</label><br> 
		<input name="name" type="text" maxlength="30" id="nameins" required placeholder="enter name"><span id="spannameins">Minimo 4 caratteri, massimo 30</span><br> 
		<label for="artist">Artist:</label><br> 
		<input name="artist" type="text" maxlength="30"  id="artistins"required placeholder="enter artist"><span id="spanartistins">Minimo 4 caratteri, massimo 30</span><br> 
		<label for="size">Size:</label><br> 
		<input name="size" type="text" maxlength="20" id="sizeins" required placeholder="enter size"><span id="spansizeins">Minimo 2 caratteri, massimo 20</span><br>
		
		<label for="description">Description:</label><br>
		<input type="text" name="description" id="descins" required placeholder="enter description"><span id="spandescins">Minimo 4 caratteri</span><br>
		
		<label for="tracklist">Tracklist:</label><br>
		<input type="text" name="tracklist" required id="trackins" placeholder="enter tracklist"><span id="spantrackins">Minimo 6 caratteri</span><br>
		
		<label for="genre">Genre:</label><br> 
		<input name="genre" type="text" maxlength="20" id="genreins" required placeholder="enter genre"><span id="spangenreins">Minimo 2 caratteri, massimo 20</span><br>
		
		<label for="year">Year:</label><br> 
		<input name="year" type="number" maxlength="4" id="yearins" required placeholder="enter year"><span id="spanyearins">Anno valido</span><br>  
		
		<label for="price">Price:</label><br> 
		<input name="price" type="number" min="0" id="priceins" value="0" required><span id="spanpriceins">Non sotto lo zero</span><br>

		<label for="quantity">Quantity:</label><br> 
		<input name="quantity" type="number" min="1" value="1"  id="quantityins" required><span id="spanquantityins">Non sotto lo zero</span><br>
		<input class="file" type="file" name="talkPhoto" value="" maxlength="255"><br>
		<input type="submit" value="Add"><input type="reset" value="Reset">

	</form>
	<hr>
	<% if (request.getAttribute("mod")!=null){ 
		ProductBean mod=(ProductBean)request.getAttribute("mod");
		request.setAttribute("mod",mod);
	%>
		<h2>MODIFY PRODUCT <%=mod.getCode()%>  </h2>
		
		<form action="admin" method="post" enctype="multipart/form-data" onsubmit="return valmodify()">
		<input type="hidden" name="action" value="update"> 
		<input type="hidden" name="code" value="<%=mod.getCode()%>">
		<label for="name">Name:</label><br> 
		<input name="name" type="text" maxlength="30" id="namemod" required value="<%=mod.getName()%>"><span id="spannamemod">Minimo 4 caratteri, massimo 30</span><br> 
		<label for="artist">Artist:</label><br> 
		<input name="artist" type="text" maxlength="30" id="artistmod" required value="<%=mod.getArtist()%>"><span id="spanartistmod">Minimo 4 caratteri, massimo 30</span><br> 
		<label for="size">Size:</label><br> 
		<input name="size" type="text" maxlength="20" id="sizemod"required value="<%=mod.getSize()%>"><span id="spansizemod">Minimo 2 caratteri, massimo 20</span><br>
		
		<label for="description">Description:</label><br>
		<input type="text" name="description" id="descmod" required value="<%=mod.getDesc()%>"><span id="spandescmod">Minimo 4 caratteri</span><br>
		
		<label for="tracklist">Tracklist:</label><br>
		<input type="text" name="tracklist" id="trackmod" required value="<%=mod.getTracklist()%>"><span id="spantrackmod">Minimo 6 caratteri</span><br>
		
		<label for="genre">Genre:</label><br> 
		<input name="genre" type="text" maxlength="20" id="genremod" required value="<%=mod.getGenre()%>"><span id="spangenremod">Minimo 2 caratteri, massimo 20</span><br>
		
		<label for="year">Year:</label><br> 
		<input name="year" type="text" maxlength="4" id="yearmod" required value="<%=mod.getPubyear()%>"><span id="spanyearmod">Anno valido</span><br>  
		
		<label for="price">Price:</label><br> 
		<input name="price" type="number" min="0" id="pricemod" value="<%=mod.getPrice()%>" required><span id="spanpricemod">Non sotto lo zero</span><br>

		<label for="quantity">Quantity:</label><br> 
		<input name="quantity" type="number" min="0" id="quantitymod" value="<%=mod.getQuantity()%>" required><span id="spanquantitymod">Non sotto lo zero</span><br>
		
		<label for="photo">Inserisci una foto se vuoi modificare quella presente, altrimenti lascia vuoto questo campo</label>
		<input class="file" type="file" name="talkPhoto" value="" maxlength="255"><br>
		<input type="submit" value="Modifica"><input type="reset" value="Ripristina modifiche">

	</form>
	
	<hr>
	<%}}} %>
	
	Visualizzazione ordini:
	
	<a href="admin?action=showallorders"><button id="mostraordini" class="bottone">Mostra tutti gli ordini</button></a><br>
	<form action="admin" method="get" onsubmit="return valricercautente()">
		<input type="text" name="action" value="showordersbyuser" hidden>
		<label for="utenterichiesto">Ricerca per utente: </label><input type="text" placeholder="Inserisci l'utente" id="ricercaordinenome" name="utenterichiesto"><span id="spanutenterichiesto">Inserisci una mail corretta</span>
		<input type="submit" value="ricerca">
	</form><br>
	<form action="admin" method="get" onsubmit="return valricercakey()">
		<input type="text" name="action" value="showorderbykey" hidden>
		<label for="key">Ricerca per numero ordine: </label><input type="text" placeholder="Inserisci #ordine" id="key" name="key">
		<input type="submit" value="ricerca"><span id="spankey">Inserisci un valore numerico</span>
	</form><br>
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
					DATA: <%=ordini.get(i).getData() %><br>
					UTENTE: <%=ordini.get(i).getMail() %>
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
	
	<br><br>
	<jsp:include page="/Footer.jsp"/>
</body>
</html>