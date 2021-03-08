package it.techzone.control;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Manager;
import it.techzone.model.beans.UtenteRegistrato;
//servlet di logout
public class LogoutControl extends HttpServlet{

public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session=request.getSession();
		
		try { 
			//se l'utente non è loggato, allora non può svolgere operazione di logouts
			if(session.getAttribute("utente")==null&&session.getAttribute("manager")==null) {
				session.setAttribute("alertMsg", "Errore, utente non loggato");
				response.sendRedirect("./HomePage.jsp");
			}else {
				//la sessione viene svuotata dalle informazioni di utente o manager e quindi il logout avviene
				//con successo
				session.removeAttribute("utente");
				session.removeAttribute("manager");
				session.setAttribute("alertMsg", "Logout effettuato con successo.");
				response.sendRedirect("./HomePage.jsp");
			}
		}catch(Exception e2) {
			
			session.setAttribute("alertMsg","Errore, ritorno alla Homepage");
			response.sendRedirect("./HomePage.jsp");	
			}
	}
}

