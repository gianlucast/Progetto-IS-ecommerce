package it.techzone.control;

import java.io.*;  
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.http.*;
import it.techzone.model.models.UserManager;
import it.techzone.model.beans.UtenteRegistrato;
  
public class RegisterControl extends HttpServlet {  
	static UserManager um = new UserManager();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {  
				
				 
				HttpSession session=request.getSession();
				if(session.getAttribute("utente")==null&& session.getAttribute("manager")==null) {
					String nome=request.getParameter("userName");  
					String cognome=request.getParameter("userSurname");
					String psw=request.getParameter("userPass");  
					String email=request.getParameter("userEmail");  
					String tel=request.getParameter("userPhone"); 
					long tele= Integer.parseInt(tel);
					String addressC=request.getParameter("userCountry");
					String addressCi=request.getParameter("userCity");
					String addressCa=request.getParameter("userCap");
					String addressSt=request.getParameter("userStreet");
					String address= addressC + addressCi + addressCa + addressSt;
					
					String payment=request.getParameter("userPayment");
				
          
					try{  
						UtenteRegistrato u=um.saveUser(email, nome, cognome, psw, tele, address, payment);
							if( u==null) {
								session.setAttribute("alertMsg", "Registrazione non effettuata, l'email inserita è già stata utilizzata");
								response.sendRedirect("./RegisterPage.jsp");
								
								
							}
							else {
								session.setAttribute("alertMsg", "Registrazione effettuata con successo");
								session.setAttribute("utente", u);
								RequestDispatcher requestD = getServletContext().getRequestDispatcher("./HomePage.jsp");
								requestD.forward(request, response);
								
							}
          
					}catch (Exception e2) {System.out.println(e2);}
          
				}
				
				else response.sendRedirect("./Homepage.jsp");
						

  
}  
}