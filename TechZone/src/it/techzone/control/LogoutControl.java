package it.techzone.control;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Manager;
import it.techzone.model.beans.UtenteRegistrato;

public class LogoutControl extends HttpServlet{

public void doGet(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session=request.getSession();
		
		try { 
			//se l'utente non è loggato, allora non può svolgere operazione di logout
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
		}catch (Exception e2) {System.out.println(e2);} 
	}
}

