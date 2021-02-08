<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Collection<?> products = (Collection<?>) request.getAttribute("productsearch");
	if(products == null) {
		response.sendRedirect("./product");	
		return;
	}
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.vinylzone.control.*,it.vinylzone.model.*"%>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="icon" href="./imgs/logo_vinyl.png">
	<link rel="stylesheet" href="./css/cat.css">
	<link rel="stylesheet" href="./css/slideshow.css">
	<title>VinylZone</title>
</head>

<body>
	
	
	<div id="pagecontent">
	<jsp:include page="/Header.jsp"/>
			
	

	
	<jsp:include page="/lateral_menu.jsp"/>
		<div id="contentwrap">
		<h3>Risultati della ricerca: </h3>
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
					if(products==null) %>
				<%  if(products.size()==0)  %>La ricerca non ha prodotto risultati
				<%
					if (products != null && products.size() != 0) {
						Iterator<?> it = products.iterator();
						while (it.hasNext()) {
							ProductBean bean = (ProductBean) it.next();
							if(!bean.isDeleted()){
				%>
				
				
					<!-- <td><%=bean.getCode()%></td>-->
					
					<!--<td><%=bean.getDesc()%></td>-->
					<td>
					<div class="cont">
					<div class="foto"><%if(bean.getPhoto()!=null){ %><img src="imgControl?id=<%=bean.getCode()%>" style="width:100px"><% }else{%><img src="./imgs/no_disc.png" style="width:100px"><%} %></div>
					<div class="name"><%=bean.getName()%></div>
					<div class="artist"><%= bean.getArtist()%></div>
				    <a href="product?action=details&id=<%=bean.getCode()%>"><button class="button">Acquista</button></a></td>
					</div>
					</div>
						<%request.setAttribute("imgbean",bean);%>
					
					
				
				
				<%
							}	
						}
					} %>
				</tr>
			</table>
			</div>
		<br><br><br><br>
	<jsp:include page="/Footer.jsp"/>
	</div>
	
</body>
</html>