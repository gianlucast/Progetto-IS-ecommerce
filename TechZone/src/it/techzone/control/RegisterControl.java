package it.techzone.control;

import java.io.*;  
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.http.*;

import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.managers.UserManager;
  
public class RegisterControl extends HttpServlet {  
	static UserManager um = new UserManager();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {  
				
				 
				HttpSession session=request.getSession();
				if(session.getAttribute("utente")==null&& session.getAttribute("manager")==null) {
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
          
					}catch (Exception e2) {System.out.println(e2);}
          
				}
				
				else response.sendRedirect("./HomePage.jsp");
						

  
}  
}