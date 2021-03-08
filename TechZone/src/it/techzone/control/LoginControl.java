package it.techzone.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Manager;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.managers.UserManager;

//servlet di login
public class LoginControl extends HttpServlet{
	static UserManager um=new UserManager(); 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//vengono presi i dati dal form di login
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		HttpSession session=request.getSession();
		
		try {
			//se in sessione non risulta che l'utente/manager sia loggato, allora la servlet va avanti
			if(session.getAttribute("utente")!=null||session.getAttribute("manager")!=null) {
				session.setAttribute("alertMsg", "Hai già effettuato il login");
				response.sendRedirect("./HomePage.jsp");
			}else {
				//Viene istanziato un bean con i dati dell'utente che ha effettuato il login
				UtenteRegistrato u=um.authentication(email,password);
				if(u==null) {
					//se non è un utente, è un manager
					Manager m=um.authenticationManager(email, password);
					if(m==null) {   
						//se non è nessuno dei due, allora le credenziali inserite non sono corrette
						session.setAttribute("alertMsg", "Corrispondenza non trovata");
						response.sendRedirect("./Login.jsp");
					}
					else {
						//si aggiorna la sessione con il manager
						session.setAttribute("manager", m);
						session.setAttribute("alertMsg", "Login avvenuto con successo");
						response.sendRedirect("./HomePage.jsp");
					}
				}else {
					//si aggiorna la sessione con l'utente
					session.setAttribute("utente", u);
					session.setAttribute("alertMsg", "Login avvenuto con successo");
					response.sendRedirect("./HomePage.jsp");
				}
				
				
			}
		  
			
		}catch(Exception e2) {
			
			session.setAttribute("alertMsg","Errore, ritorno alla Homepage");
			response.sendRedirect("./HomePage.jsp");	
			}
	}
}
