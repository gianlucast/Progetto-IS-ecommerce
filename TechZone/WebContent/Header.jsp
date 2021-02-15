<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="./css/main.css"> 
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	
</head>

<body>

<div class="brand"><a href="HomePage.jsp"><img src="./img/logo_techzone.png"></a></div>

    <!-- Navigation -->
    <nav class="navbar navbar-default" role="navigation">
    
        <div class="container">
           
            
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
		
				<li><form action="search" method="get" class="search"  onsubmit="return validateSearch()">
	<!-- DA MODIFICARE -->		<input type="text" name="for" value="nameandartist" hidden>
						<div id="contenitore_search">
							<input type="text" name="q" list="ricerca-datalist" placeholder="Ricerca" onkeyup="ricerca(this.value)" id="s">
							<button type="submit" id="bottone_ricerca"><i class="fa fa-search" aria-hidden="true"></i></button>
						</div>
						<datalist id="ricerca-datalist"></datalist>
					</form></li>
				
				
				<% if(session.getAttribute("utente")==null&&session.getAttribute("manager")==null){%>
				<li><a href="Login.jsp">Login</a></li>
				<li><a href="Signup.jsp">Sign Up</a></li>
				<% }else{%>
				<li><a href="UserArea.jsp">Area utente</a>
				<li><a href="logoutcontrol">Logout</a>
				<%} %>
				<li><a href="CartView.jsp"><i class="fa fa-shopping-cart fa-2x" aria-hidden="true"></i></a></li>
		</ul>
		
            </div>
            <!-- /navbar-collapse -->
        </div>
        <!-- /container -->
    </nav>
    
    <!-- Intro text -->
    <div class="title bg-color-intro">
       <h1>Immergiti nel mondo della tecnologia!</h1>
       <h2>TechZone è alla portata di tutti.</h2>
       <div class="hr"></div>
    </div>
    <!-- /intro text -->

</body>
</html>



















