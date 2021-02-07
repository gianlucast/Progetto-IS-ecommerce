<% if(session.getAttribute("user")!=null)
	response.sendRedirect("Catalogo.jsp");
%>
<html>
	<head><title>Login-TechZone</title><link rel="icon" href="./imgs/logo_vinyl.png">
	<link rel="stylesheet" href="./css/login.css">

	<style>
		span{
			display:none;
		}
	
	</style>
	</head>
	<body background="./imgs/sfondo.jpg">
		<script src="./scripts/ValidateLogin.js"></script>
		<jsp:include page="/Header.jsp"/>
	
		<%if(session.getAttribute("alertMsg")!=null){%>
		<h3><font color="red"><%=session.getAttribute("alertMsg")%></font></h3> 
		<%session.setAttribute("alertMsg",""); %>
	<%}%><br>
		<form action="logincontrol" method="get">
			<label for="mail"><input type="text" name="email" id="mail" placeholder="inserisci la mail" required></label><span id="spanmail">Questa non è una mail, perché farmi controllare?</span>
			<br><label for="password"><input type="password" name="password" id="password" placeholder="inserisci la password" required></label><span id="spanpassword">Questa password non può essere valida, perché farmi controllare?</span>
			<br><label for="submit"><input type="submit" value="Accedi"></label>
		</form>
		<label>Non hai un account?<a href="Signup.jsp"> Registrati ora!</a></label>
		 <jsp:include page="/Footer.jsp"/>
	</body>
	
</html>