<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./product");	
		return;
	}
	Product product = (Product) request.getAttribute("product");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.techzone.control.*,it.techzone.model.beans.*"%>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="icon" href="./imgs/logo_vinyl.png">
	<link rel="stylesheet" href="./css/cat.css">
	<link rel="stylesheet" href="./css/slideshow.css">
	<title>TechZone</title>
</head>

<body onload="showSlides(1)">
	
	
	<div id="pagecontent">
	<jsp:include page="/Header.jsp"/>
	<!-- SLIDESHOW START -->
			<script src="./scripts/slideshow.js"></script>
		
		    <div class="slideshow-container">
		
		  <!-- Full-width images with number and caption text -->
		  <div class="mySlides fade">
		    <div class="numbertext">1 / 3</div>
		    <img src="./imgs/logovinyll.png" style="width:100%">
		    <div class="text"></div>
		  </div>
		
		  <div class="mySlides fade">
		    <div class="numbertext">2 / 3</div>
		    <img src="./imgs/vinilebn.jpg" style="width:100%">
		    <div class="text"></div>
		  </div>
		  
		  <div class="mySlides fade">
		    <div class="numbertext">3 / 3</div>
		    <img src="./imgs/nuoveuscite.png" style="width:100%">
		    <div class="text"></div>
		  </div>
		
		<!-- Next and previous buttons -->
		  <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
		  <a class="next" onclick="plusSlides(1)">&#10095;</a>
		
		</div>
		<br>

<!-- The dots/circles -->
<div style="text-align:center">
  <span class="dot" onclick="currentSlide(1)"></span>
  <span class="dot" onclick="currentSlide(2)"></span>
  <span class="dot" onclick="currentSlide(3)"></span>
</div>
	<!-- SLIDESHOW END -->
	
	<jsp:include page="/lateral_menu.jsp"/>
		<div id="contentwrap">
		<%if(session.getAttribute("alertMsg")!=null){%>
		<h3><font color="red"><%=session.getAttribute("alertMsg")%></font></h3> 
		<%session.setAttribute("alertMsg",""); 
		session.setAttribute("redirect",null);
		%>
	<%}%>
			<table>
			<tr>
			<div class="productbox">
					<!--  <th>Code <a href="product?sort=code">Sort</a></th>
					<th>Name <a href="product?sort=name">Sort</a></th>
					<th>Description <a href="product?sort=description">Sort</a></th>
					<th>Action</th>
					<th>Picture</th>-->
				
				<%
					if(products==null) %>Products null
				<%  if(products.size()==0)  %>Products size 0
				<%
					if (products != null && products.size() != 0) {
						Iterator<?> it = products.iterator();
						while (it.hasNext()) {
							Product bean = (Product) it.next();
							if(bean.getQuantita()>0){
				%>
				
				
					<!-- <td><%=bean.getCodice()%></td>-->
					
					<!--<td><%=bean.getDescrizione()%></td>-->
					<td>
					<div class="cont">
					<div class="foto"><%if(bean.getImmagine()!=null){ %><img src="imgControl?id=<%=bean.getCodice()%>" style="width:100px"><% }else{%><img src="./imgs/no_disc.png" style="width:100px"><%} %></div>
					<div class="name"><%=bean.getNomeProd()%></div>
					<div class="category"><%= bean.getCategoria()%></div>
				    <a href="product?action=details&id=<%=bean.getCodice()%>"><button class="button">Acquista</button></a></td>
					</div>
					</div>
						<%request.setAttribute("imgbean",bean);%>
					
					
				
				
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
				%>
				</tr>
			</table>
			</div>
		<br><br>
	<jsp:include page="/Footer.jsp"/>
	</div>
	
</body>
</html>