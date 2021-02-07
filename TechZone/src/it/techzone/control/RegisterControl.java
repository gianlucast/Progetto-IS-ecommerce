package it.techzone.control;

import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;
import it.techzone.model.models.UserManager;
import it.techzone.model.beans.UtenteRegistrato;
  
public class RegisterControl extends HttpServlet {  
	static UserManager um = new UserManager();
	LoginControl log= new LoginControl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {  
				
				 
				HttpSession session=request.getSession();
				if(session.getAttribute("user")==null) {
					String nome=request.getParameter("userName");  
					String cognome=request.getParameter("userSurname");
					String psw=request.getParameter("userPass");  
					String email=request.getParameter("userEmail");  
					String tel=request.getParameter("userPhone"); 
					long tele= Integer.parseInt(tel);
					String address=request.getParameter("userAddress");
					String payment=request.getParameter("userPayment");
				
          
					try{  
							
							if( um.saveUser(email, nome, cognome, psw, tele, address, payment)==null) {
								session.setAttribute("alertMsg", "Registrazione non effettuata, l'email inserita è già stata utilizzata");
							}
							else {
								session.setAttribute("alertMsg", "Registrazione effettuata con successo");
								UtenteRegistrato u= um.authentication(email, psw);
								response.sendRedirect("./Homepage.jsp");
								
								
							}
          
					}catch (Exception e2) {System.out.println(e2);}
          
				}
				
				else response.sendRedirect("./Homepage.jsp");
						

  
}  
}