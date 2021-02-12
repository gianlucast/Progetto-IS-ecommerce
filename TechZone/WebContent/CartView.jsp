<%@ page language="java" %>
    <%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.techzone.control.*,it.techzone.model.beans.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Carrello-TechZone</title>
	<link rel="icon" href="">
	<link rel="stylesheet" href="./css/cartview.css">
</head>
<body>
	<script src="./scripts/cart_scripts.js"></script>
	<script src="./scripts/showforms.js"></script>
	<jsp:include page="Header.jsp"/>
	<% HttpSession sessione=request.getSession(); 
		if(sessione.getAttribute("cart")==null) sessione.setAttribute("cart", new Cart());
		Cart cart=(Cart)sessione.getAttribute("cart");
		ArrayList<ProductCart> lista=cart.getProductList();
	%>
	<%
	request.setAttribute("redirect","CartView.jsp");
	if(session.getAttribute("alertMsg")!=null){%>
		<h3><font color="red"><%=session.getAttribute("alertMsg")%></font></h3> 
		<%session.setAttribute("alertMsg","");
		session.setAttribute("redirect","CartView.jsp");%>
	<%}%><br>
	<h1>Il tuo carrello:</h1><br>
	<% if(cart.getProductList().size()==0){%>
	<h2>Vuoto, aggiungi qualcosa!</h2>
	<%
	}else{%> <table ><tr><th>FOTO<th>NOME<th>QTA'<th>PREZZO</th></tr><%
		for(int i=0;i<lista.size();i++){
			if(lista.get(i).getQuantita()==0) response.sendRedirect("./product?action=changequantity&qt=0&id="+lista.get(i).getProdotto().getCodice());	
			%>
			<tr>
				<td><img src="imgControl?id=<%=lista.get(i).getProdotto().getCodice()%>" style="width:100px"></td>
				<td><%=lista.get(i).getProdotto().getNomeProd() %><td align="center"><%=lista.get(i).getQuantita()%><td align="center"><%=lista.get(i).getProdotto().getCosto() %>
				<td><a href="modincartcontrol?change=<%=lista.get(i).getQuantita()+1%>&idProd=<%=lista.get(i).getProdotto().getCodice()%>"><button class="button">Aggiungi</button></a>
				<td><a href="modincartcontrol?change=<%=lista.get(i).getQuantita()-1%>&idProd=<%=lista.get(i).getProdotto().getCodice()%>"><button class="button">Rimuovi</button></a>
			</tr><%
		} %>
		</table>
		<hr>
		<p align="right"> <%=cart.getPrezzoTotale()%></p>
		<%if(session.getAttribute("user")!=null){ 
			UtenteRegistrato user=(UtenteRegistrato) session.getAttribute("user");
			String indirizzo=user.getIndirizzo();
			String carta=user.getPagamento();
		%>
		
		
	  	<form action="order" method="post" name="placeorder">
		<input type="hidden" name="action" value="place">
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