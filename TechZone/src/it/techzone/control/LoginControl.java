package it.techzone.control;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Manager;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.models.UserManager;

public class LoginControl extends HttpServlet{
	static UserManager um=new UserManager(); 
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		HttpSession session=request.getSession();
		
		try {
			if(session.getAttribute("utente")!=null||session.getAttribute("manager")!=null) {
				session.setAttribute("alertMsg", "Hai già effettuato il login");
				response.sendRedirect("./Homepage.jsp");
			}else {
				UtenteRegistrato u=um.authentication(email,password);
				if(u==null) {
					Manager m=um.authenticationManager(email, password);
					if(m==null) {   
						session.setAttribute("alertMsg", "Corrispondenza non trovata");
						response.sendRedirect("./Login.jsp");
					}
					else {
						session.setAttribute("manager", m);
					}
				}else 
					session.setAttribute("utente", u);
				session.setAttribute("alertMsg", "Login avvenuto con successo");
				response.sendRedirect("./Homepage.jsp");
			}
			
		}catch (Exception e2) {System.out.println(e2);} 
	}
}
