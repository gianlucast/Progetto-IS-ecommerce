<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	if(session.getAttribute("manager")==null){
		response.sendRedirect("/HomePage.jsp");
	}
		
		Collection<?> products = (Collection<?>) request.getAttribute("prodotti");
			if(products == null) {
				response.sendRedirect("./productviewcontrol");	
				return;
			}
			Product product = (Product) request.getAttribute("product"); 
	
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.techzone.control.*,it.techzone.model.beans.*"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="icon" href="./imgs/logo_vinyl.png">
	<title>TechZone</title>
	
	 <link rel="stylesheet" href="css/bootstrap.min.css"> 
        
        <link rel="icon" href="./imgs/logo_techzone.png">
        <link rel="stylesheet" href="./css/main.css">
        
        

        <!-- Google fonts -->
        <link href='http://fonts.googleapis.com/css?family=Open%20Sans:400,700,600' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Josefin+Sans:400,600,700' rel='stylesheet' type='text/css'> 
	
	
	
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
	<p class="block" font-size="40px">Prodotti</p>
	
	<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Codice</th>
      <th scope="col">Nome</th>
      <th scope="col">Descrizione</th>
      <th scope="col">Operazione</th>
      <th scope="col">Foto</th>
    </tr>
  </thead>

	<tbody>

		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					Product prod = (Product) it.next();
					
		%>
		<div class="row"> 
		<tr>
			<td><%=prod.getCodice() %></td>
			<td><%=prod.getNomeProd() %></td>
			<td><%=prod.getDescrizione() %></td>
			<td><a href="deleteproductcontrol?idProd=<%=prod.getCodice() %>">Elimina</a><br>
				<a href="updateproductcontrol?idProd=<%=prod.getCodice()%>">Modifica</a><br>
				<a href="productviewcontrol?idProd=<%=prod.getCodice()%>">Dettagli</a></td>
				<%
				request.setAttribute("imgbean", prod);%>
			<td><%if(prod.getImmagine()!=null){ %><img src="imgControl?id=<%=prod.getCodice()%>" style="width:100px"><% }else{%><img src="./imgs/no_disc.png" style="width:100px"><%} %></td>
		</tr>
		<%
			  }	
				
			} else {
		%>
		<tr>
			<td colspan="6">No products available</td>
		</tr>
		<%
			}
		%> 
		</table>

	<hr>
	<% if (request.getAttribute("mod")!=null){ 
		Product mod=(Product)request.getAttribute("mod");
		request.setAttribute("mod",mod);
	   } %>
	
	
	<br><br>
	<jsp:include page="/Footer.jsp"/>
</body>
</html>