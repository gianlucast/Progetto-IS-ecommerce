package it.techzone.control;

import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;
import it.techzone.model.models.UserManager;  
  
public class RegisterControl extends HttpServlet {  
	public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
				UserManager um = null;
				response.setContentType("text/html");  
				PrintWriter out = response.getWriter();  
          
				String nome=request.getParameter("userName");  
				String cognome=request.getParameter("userSurname");
				String psw=request.getParameter("userPass");  
				String email=request.getParameter("userEmail");  
				String tel=request.getParameter("userPhone"); 
				long tele= Integer.parseInt(tel);
				String address=request.getParameter("userAddress");
				String payment=request.getParameter("userPayment");
				
          
					try{  
							
							
							um.saveUser(email, nome, cognome, psw, tele, address, payment);
          
					}catch (Exception e2) {System.out.println(e2);}  
          
				out.println("Registrazione completata");
out.close();  
}  
  
}  
