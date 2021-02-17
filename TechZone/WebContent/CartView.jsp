<%@ page language="java" %>
    <%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.techzone.control.*,it.techzone.model.beans.*"%>
    
    
<!DOCTYPE html>
<html>
<head>
	 <head>
        <title>Carrello-TechZone</title>

         <meta charset="ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <!-- Boostrap Core CSS-->
        <link rel="stylesheet" href="css/bootstrap.min.css"> 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        
        <link rel="icon" href="./imgs/logo_techzone.png">
        <link rel="stylesheet" href="./css/main.css">
     
        <link href='http://fonts.googleapis.com/css?family=Open%20Sans:400,700,600' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Josefin+Sans:400,600,700' rel='stylesheet' type='text/css'>        
    </head>

<body>
	
	<jsp:include page="/Header.jsp"/>
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
	<p class="block" style="font-size:40px">Il tuo carrello:</p><br>
	<% if(cart.getProductList().size()==0){%>
	<h2>Vuoto, aggiungi qualcosa!</h2>
	<%
	}else{%> 
	
	
					<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Foto</th>
      <th scope="col">Nome</th>
      <th scope="col">Quantità</th>
      <th scope="col">Prezzo</th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
 <%  for(int i=0;i<lista.size();i++){ %>
  
  			<div class="row"> 
		<%	if(lista.get(i).getQuantita()==0) response.sendRedirect("./product?action=changequantity&qt=0&id="+lista.get(i).getProdotto().getCodice());	
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
		
		
	  	<form action="orderplacecontrol" method="post" name="placeorder">
		<input type="submit" value="Checkout"><br><br><br><br>
		</form>	
				
		<%}else{ %>
			
		<br><br><a href="orderplacecontrol"><input type="button" class="button" value="CheckOut"></a>
		<br><br>
		
		<%}
		
	}%>
	<br><br><br>
	<jsp:include page="Footer.jsp"/>
	
	<script src="js/jquery-1.11.0.js"></script>
     
     <!-- Boostrap JS -->
	 <script src="js/bootstrap.min.js"></script>
     
     <!-- Smooth scroll JS -->
     <script src="js/smoothscroll.js"></script>
	
	
	
</body>
</html>