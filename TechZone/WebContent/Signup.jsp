<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <% if(session.getAttribute("user")!=null)
	response.sendRedirect("Homepage.jsp");
%>
<!DOCTYPE html>
<html>
<head><title>Login-TechZone</title><link rel="icon" href="./imgs/logo_vinyl.png">
<link rel="stylesheet" href="./css/signup.css">
<style>
			span{
				display:none;
			}
		</style>
</head>
	<body>
		
		<script src="./scripts/singup_regex.js"></script>
		<jsp:include page="/Header.jsp"/>
		<%if(session.getAttribute("alertMsg")!=null){%>
		<h3><font color="red"><%=session.getAttribute("alertMsg")%></font></h3> 
		<%session.setAttribute("alertMsg",""); %>
	<%}%><br>
		<form action="user" method="post" name="registrazione" onSubmit="return ValidateSignup()">
		<table>
			<tr><td><input type="hidden" name="action" value="signup"><td></tr>
			<tr><td><label for="email"><input type="text" id="mail" name="mail" placeholder="inserisci la mail" required></label><span id="spanmail">Inserisci una mail valida!</span></td></tr>
			<tr><td><label for="password"><input type="password" id="password" name="password" placeholder="inserisci la password" required></label><span id="spanpassword">Inserisci una password valida (6 caratteri o più, ci teniamo alla tua privacy!)! </span></td></tr>
			<tr><td><label for="nome"><input type="text" name="nome" id="nome" placeholder="inserisci il nome" required></label><span id="spannome">Inserisci un nome valido (solo lettere, almeno 3)!</span></td></tr>
			<tr><td><label for="cognome"><input type="text" name="cognome" id="cognome" placeholder="inserisci il cognome" required></label><span id="spancognome">Inserisci un cognome valido(solo lettere, almeno 3)!</span></td></tr>
			<tr><td><label for="telefono(Opzionale)"><input type="text" id="telefono" name="telefono" placeholder="inserisci il telefono(opzionale)"></label><span id="spantelefono">Inserisci una numero di telefono valido (solo numeri, minimo 8, massimo 14)!</span></td></tr>
			<tr><td><label for="submit"><input type="submit" value="Registrati"></label></td></tr>
			</table>
		</form>
		<label>Hai già un account? Effettua il <a href="Login.jsp">login</a></label>
		<jsp:include page="/Footer.jsp"/>
	</body>
</html>
