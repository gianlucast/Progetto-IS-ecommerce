package it.techzone.control;

import java.io.*;  
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.http.*;

import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.managers.UserManager;
//servlet della registrazione utente 
public class RegisterControl extends HttpServlet {  
	static UserManager um = new UserManager();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {  
				
				 
				HttpSession session=request.getSession();
				//se la sessione non vede nè un utente nè un manager loggato, allora la registrazione
				// può essere effettuata
				if(session.getAttribute("utente")==null&& session.getAttribute("manager")==null) {
					//vengono presi i campi dal form
					String nome=request.getParameter("userName");  
					String cognome=request.getParameter("userSurname");
					String psw=request.getParameter("userPass");  
					String email=request.getParameter("userEmail"); 
					String tel=request.getParameter("userPhone"); 
					long tele= Long.parseLong(tel);
					String addressC=request.getParameter("userCountry");
					String addressCi=request.getParameter("userCity");
					String addressCa=request.getParameter("userCap");
					String addressSt=request.getParameter("userStreet");
					String address= addressC + ", "+addressCi + ", "+addressCa + ", "+addressSt;
					
					String paymentInst=request.getParameter("userPaymentInst");
					
					String paymentCo=request.getParameter("userPaymentCode");
					String paymentExpMonth=request.getParameter("userPaymentExpMonth");
					String paymentExpyear=request.getParameter("userPaymentExpYear");
					String paymentCvv=request.getParameter("userPaymentCvv");
					String payment=paymentInst+", "+paymentCo+", "+paymentExpMonth+"/"+paymentExpyear+", "+paymentCvv;
				
          
					try{  
						//con la saveuser si può salvare un utente nel database tramite il DAO
						// se essa restituisce un oggetto UtenteRegistrato allora vuol dire
						// che la registrazione è andata a buon fine, altrimenti no
						UtenteRegistrato u=um.saveUser(email, nome, cognome, psw, tele, address, payment);
							if( u==null) {
								session.setAttribute("alertMsg", "Registrazione fallita.");
								response.sendRedirect("./Signup.jsp");
								
								
							}
							else {
								session.setAttribute("alertMsg", "Registrazione effettuata con successo");
								session.setAttribute("utente", u);
								response.sendRedirect("./HomePage.jsp");
								
							}
          
					}catch(Exception e2) {
						
						session.setAttribute("alertMsg","Errore, ritorno alla Homepage");
						response.sendRedirect("./HomePage.jsp");	
						}
          
				}else {
					session.setAttribute("alertMsg", "Sei già loggato.");
					response.sendRedirect("./HomePage.jsp");
				}
						

  
}  
}