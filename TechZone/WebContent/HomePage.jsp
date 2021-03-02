<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Collection<?> products = (Collection<?>) request.getAttribute("prodotti");
	if(products == null) {
		response.sendRedirect("./productviewcontrol");	
		return;
	}
	Product product = (Product) request.getAttribute("prodotto");
%>



<!DOCTYPE html>
<html>

<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.techzone.control.*,it.techzone.model.beans.*"%>

    <head>
        <title>TechZone-HomePage</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <!-- Boostrap Core CSS-->
        <link rel="stylesheet" href="css/bootstrap.min.css"> 
        
        <link rel="icon" href="./imgs/logo_techzone.png">
        <link rel="stylesheet" href="./css/main.css">
        
        <link rel="icon" href="./img/logo_techzone.png">

        <!-- Google fonts -->
        <link href='http://fonts.googleapis.com/css?family=Open%20Sans:400,700,600' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Josefin+Sans:400,600,700' rel='stylesheet' type='text/css'>        
    </head>
    
    <body>
    <jsp:include page="/Header.jsp"/> 
    <jsp:include page="/lateral_menu.jsp"/><br><br><br><br>
    
     <!-- Portfolio -->

    <div class="container port-top">
    	<%if(session.getAttribute("alertMsg")!=null){%>
		<h3><font color="red"><%=session.getAttribute("alertMsg")%></font></h3> 
		<%session.setAttribute("alertMsg",""); 
		session.setAttribute("redirect",null);
		%>
		<%}%>

		<div class="row">
				    
				<%
					if(products==null) %> Products null
				<%  if(products.size()==0)  %> Products size 0
				<%
					if (products != null && products.size() != 0) {
						Iterator<?> it = products.iterator();
						while (it.hasNext()) {
							Product bean = (Product) it.next();
							if(bean.getQuantita()>0){
			%>
			
			<div class="col-md-4 col-sm-6">
				
				<div class="portfolio-item">
				
				
					<div class="portfolio-image">
						<div class="foto"><%if(bean.getImmagine()!=null){ %>
						<img src="imgControl?id=<%=bean.getCodice()%>" style="width:250px ; height:250px">
						
						<% }else{%>
						<img src="./imgs/no_disc.png" style="width:250px">
						<%} %>
					</div>	
						
					</div>
				
			        <div class="portfolio-info-fade">
			        <ul>
                        
           			<li class="name"><%=bean.getNomeProd()%></li>
					<li class="category"><%= bean.getCategoria()%></li>
					<br><br>
				    <li class="acquista"><a href="productviewcontrol?idProd=<%=bean.getCodice()%>" class="portfolio-btn">ACQUISTA</a></li>
					
						<%request.setAttribute("imgbean",bean);%>
					
			
				
					</ul>
					</div>
					
					
					 
					
				</div>	
				</div>
				
					
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
				
		    </div>
	</div>
	
	<br><br><br>
		    
	<jsp:include page="/Footer.jsp"/>	
	 <!-- jQuery Version 1.11.0 -->
     <script src="js/jquery-1.11.0.js"></script>
     
     <!-- Boostrap JS -->
	 <script src="js/bootstrap.min.js"></script>
     
     <!-- Smooth scroll JS -->
     <script src="js/smoothscroll.js"></script>
	
	
	   
	 </body>
    </html>	    



















