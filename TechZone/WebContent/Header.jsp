<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="./css/head-css.css"> 
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
	<body>
	<script src="./scripts/ricercaAjax.js"></script>
	<script src="./scripts/ValidateSearch.js"></script>
	<div id="container">
		<div id="header">
		<ul>
		<li><div class="dis"><a href="HomePage.jsp"><img src="./img/logo_techzone.png" style="width:100; height:100px;"></a></div>
		<li><h2 style="color:white">Benvenuti su TechZone</h2>
		<li style="padding-left:260px;">
					<form action="search" method="get" class="search"  onsubmit="return validateSearch()">
	<!-- DA MODIFICARE -->			<input type="text" name="for" value="nameandartist" hidden>
						<div id="contenitore_search">
							<input type="text" name="q" list="ricerca-datalist" placeholder="Ricerca" onkeyup="ricerca(this.value)" id="s">
							<button type="submit" id="bottone_ricerca"><i class="fa fa-search"></i></button>
						</div>
						<datalist id="ricerca-datalist"></datalist>
					</form>
				
				</li>
				<li style="float:right;"><div class="dis"><a href="CartView.jsp"><img src="./imgs/boh.png.png" style="width:50px; height:50px;"></a></div>
				<% if(session.getAttribute("utente")==null&&session.getAttribute("manager")==null){%>
				<li style="float:right;padding:10px;"><a href="Login.jsp">Login</a>/<a href="Signup.jsp">Sign Up</a></li>
				<% }else{%>
				<li style="float:right;padding:10px;"><a href="UserArea.jsp">Area utente</a>
				<li style="float:right;padding:10px;"><a href="user?action=logout">Logout</a>
				<%} %>
		</ul>
		</div>
	</div>
	</body>
</html>