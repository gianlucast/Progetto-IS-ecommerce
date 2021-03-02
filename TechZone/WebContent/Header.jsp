<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="./css/main.css"> 
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="css/bootstrap.min.css"> 
        <link rel="icon" href="./imgs/logo_techzone.png">
        <link rel="stylesheet" href="./css/main.css">

        <!-- Google fonts -->
        <link href='http://fonts.googleapis.com/css?family=Open%20Sans:400,700,600' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Josefin+Sans:400,600,700' rel='stylesheet' type='text/css'>     
		<link rel="icon" href="./img/logo_techzone.png">
</head>

<body>

<div class="brand"><a href="HomePage.jsp"><img src="./img/logo_techzone.png"></a></div>

    <!-- Navigation -->
    <nav class="navbar navbar-default" role="navigation">
    
        <div class="container">
           
            
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
				
				<li><form action="productcataloguecontrol" method="get" class="search"  onsubmit="return validateSearch()">
	        		<input type="text" name="by" value="nome" hidden>
						<div id="contenitore_search">
							<input type="text" name="q" list="ricerca-datalist" placeholder="Ricerca" onkeyup="ricerca(this.value)" id="s">
							<button type="submit" id="bottone_ricerca"><i class="fa fa-search" aria-hidden="true"></i></button>
						</div>
						
						<datalist id="ricerca-datalist"></datalist>
					</form></li>
				
				
				<% if(session.getAttribute("utente")==null&&session.getAttribute("manager")==null){%>
				
				<li><a href="Login.jsp">Login</a></li>
				<li><a href="Signup.jsp">Sign Up</a></li>
				<% }if(session.getAttribute("utente")!=null){%>
				<li><a href="UserArea.jsp">Area utente</a>
				<li><a href="logoutcontrol">Logout</a>
				<%} else if(session.getAttribute("manager")!=null){%>
				<li><a href="OrdersManagerPage.jsp">Area Manager</a>
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


  <script src="js/jquery-1.11.0.js"></script>
     
     <!-- Boostrap JS -->
	 <script src="js/bootstrap.min.js"></script>
     
     <!-- Smooth scroll JS -->
     <script src="js/smoothscroll.js"></script>
</body>
</html>



















