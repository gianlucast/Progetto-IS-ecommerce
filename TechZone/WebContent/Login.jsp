<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% if(session.getAttribute("utente")!=null)
	response.sendRedirect("HomePage.jsp");
%>

<!DOCTYPE html>
<html>
    
<head>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
</head>

		

<body>
	<script src="./scripts/ValidateLogin.js"></script>
	<jsp:include page="/Header.jsp"/>
	<div class="container h-100">
		<div class="d-flex justify-content-center h-100">
			<div class="user_card">
				<div class="d-flex justify-content-center">
					<div class="brand_logo_container">
					   <img src="./img/logo_techzone.png" style="width:100%" class="brand_logo" alt="Logo">
					</div>
				</div>
				<div class="d-flex justify-content-center form_container">
				<%if(session.getAttribute("alertMsg")!=null){%>
						<h3><div class="alertMsg"><%=session.getAttribute("alertMsg")%></div></p></h3> 
						<%session.setAttribute("alertMsg",""); %>
						<%}%><br>
						
					<form action="logincontrol" method="get">
						<div class="input-group mb-3">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<label for="mail"><input type="text" name="email" id="mail" class="form-control input_user" value="" placeholder="inserisci la mail" required></label>
						</div>
						
						<div class="input-group mb-2">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<label for="password"><input type="password" name="password" id="password" class="form-control input_pass" value="" placeholder="inserisci la password" required></label>
					    	</div>			
		
						<div class="form-group">
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input" id="customControlInline">
								<label class="custom-control-label" for="customControlInline">Remember me</label>
							</div>
						</div>
					<div class="d-flex justify-content-center mt-3 login_container">
				 	<button type="submit" class="btn login_btn">Login</button>
				   </div>
				   </form>
				</div>
				
		
				<div class="mt-4">
					<div class="d-flex justify-content-center links">
						Non hai un account? <a href="Signup.jsp" class="ml-2">Registrati ora!</a>
						
					</div>
				</div>
				
			</div>
		</div>
	</div>
	<jsp:include page="/Footer.jsp"/>
</body>
</html>




  




