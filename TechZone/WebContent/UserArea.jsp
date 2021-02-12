<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,it.techzone.control.*,it.techzone.model.beans.*" %>

<% 
	
	if(session.getAttribute("utente")==null){
		System.out.println("USER AREA HA TROVATO USER VUOTO");
		response.sendRedirect("./Login.jsp");
	}
	else{
		UtenteRegistrato utente= (UtenteRegistrato) session.getAttribute("utente"); 
		
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
			
			</div>
			<br>
			</div>
		<hr>
		
		<hr>
		  
				
				
				<%	
				
			}
			%>
		</div> 
		
	
	<br><br>
	<jsp:include page="/Footer.jsp"/>
	</body>	

</html>