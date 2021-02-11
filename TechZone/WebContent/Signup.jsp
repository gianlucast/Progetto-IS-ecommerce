<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <% if(session.getAttribute("utente")!=null||session.getAttribute("manager")!=null)
	response.sendRedirect("HomePage.jsp");
%>
<!DOCTYPE html>
<html>
<head><title>Signup-TechZone</title><link rel="icon" href="./imgs/logo_vinyl.png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
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
	    <div class="form-row">
    <div class="form-group col-md-5">
      <label for="inputNome4">Nome</label>
      <input type="text" class="form-control" id="inputNome4" placeholder="Inserisci nome" required name="userName">
      <span id="spannome">Inserisci un nome valido (solo lettere, almeno 3)!</span>
    </div>
    <div class="form-group col-md-5">
      <label for="inputSurname4">Cognome</label>
      <input type="text" class="form-control" id="inputSurname4" placeholder="Inserisci cognome" required name="userSurname">
      <span id="spancognome">Inserisci un cognome valido(solo lettere, almeno 3)!</span>
      </div>
    <div class="form-group col-md-5">
      <label for="inputEmail4">Email</label>
      <input type="email" class="form-control" id="inputEmail4" placeholder="Email" name="userEmail">
      <span id="spanmail">Inserisci una mail valida!</span>
    </div>
    <div class="form-group col-md-5">
      <label for="inputPassword4">Password</label>
      <input type="password" class="form-control" id="inputPassword4" placeholder="Password" name="userPass">
    </div>    
    </div>
    <div class="form-row">
    <div class="form-group col-md-5">
    <label for="inputTelephone">Telefono</label>
    <input type="text" class="form-control" id="inputTelephone" placeholder="Numero di telefono" name="userPhone">
  </div>
  	<div class="form-group col-md-5">
    <label for="inputAddress">Address</label>
    <input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St" name="userStreet">
  </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-5">
      <label for="inputCity">Città</label>
      <input type="text" class="form-control" id="inputCity" name="userCity">
    </div>
    <div class="form-group col-md-4">
      <label for="inputState">Paese</label>
      <select id="inputState" class="form-control" name="userCountry">
        <option selected>Choose...</option>
        <option>Italy</option>
        <option>USA</option>
      </select>
    </div>
    <div class="form-group col-md-1">
      <label for="inputZip">CAP</label>
      <input type="text" class="form-control" id="inputZip" name="userCap">
    </div>
  </div>
   <div class="form-row">
    <div class="form-group col-md-5">
      <label for="inputCity">Metodo di pagamento</label>
      <input type="text" class="form-control" id="inputCity" name="userPaymentInst">
    </div>
    <div class="form-group col-md-5">
      <label for="inputState">Codice</label>
        <input type="text" class="form-control" id="inputCity" name="userPaymentCode">
    </div>
    <div class="form-group col-md-4">
      <label for="inputZip" >EXP MONTH</label>
      <input type="number" class="form-control" id="inputZip" min="1" max="12"  name="userPaymentExpMonth">
    </div>
    <div class="form-group col-md-4">
      <label for="inputZip" >EXP YEAR</label>
      <input type="number" class="form-control" id="inputZip" min="2021" name="userPaymentExpYear">
    </div>
    <div class="form-group col-md-1">
      <label for="inputZip">CVV</label>
      <input type="number" class="form-control" id="inputZip" name="userPaymentCvv" maxlength="3" minlength="3">
    </div>
  </div>

  
  <button type="submit" class="btn btn-primary">Sign in</button>
		<label>Hai già un account? Effettua il <a href="Login.jsp">Login</a></label>
		
		</form>
		<br><br><br>
		<jsp:include page="/Footer.jsp"/>
	</body>
</html>


<!--  <form action="user" method="post" name="registrazione" onSubmit="return ValidateSignup()">
		<table>
			<tr><td><input type="hidden" name="action" value="signup"><td></tr>
			<tr><td><label for="userEmail"><input type="text" id="userEmail" name="userEmail" placeholder="inserisci la mail" required></label><span id="spanmail">Inserisci una mail valida!</span></td></tr>
			<tr><td><label for="userPass"><input type="password" id="userPass" name="userPass" placeholder="inserisci la password" required></label><span id="spanpassword">Inserisci una password valida (6 caratteri o più, ci teniamo alla tua privacy!)! </span></td></tr>
			<tr><td><label for="userName"><input type="text" name="userName" id="userName" placeholder="inserisci il nome" required></label><span id="spannome">Inserisci un nome valido (solo lettere, almeno 3)!</span></td></tr>
			<tr><td><label for="userSurname"><input type="text" name="userSurname" id="userSurname" placeholder="inserisci il cognome" required></label><span id="spancognome">Inserisci un cognome valido(solo lettere, almeno 3)!</span></td></tr>
			<tr><td><label for="userPhone"><input type="text" id="userPhone" name="userPhone" placeholder="inserisci il telefono(opzionale)"></label><span id="spantelefono">Inserisci una numero di telefono valido (solo numeri, minimo 8, massimo 14)!</span></td></tr>
			<tr><td><label for="submit"><input type="submit" value="Registrati"></label></td></tr>
			</table>
		</form> -->
