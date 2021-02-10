<% if(session.getAttribute("user")!=null)
	response.sendRedirect("Homepage.jsp");
%>
<html>
	<head><title>Login-TechZone</title><link rel="icon" href="./imgs/logo_vinyl.png">
	<!--  <link rel="stylesheet" href="./css/login.css"> -->
	<head><title>Login-TechZone</title><link rel="icon" href="./imgs/logo_vinyl.png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

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