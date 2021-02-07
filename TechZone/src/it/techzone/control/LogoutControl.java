package it.techzone.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Manager;
import it.techzone.model.beans.UtenteRegistrato;

public class LogoutControl {

public void doGet(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session=request.getSession();
		
		try { 
			if(session.getAttribute("utente")==null&&session.getAttribute("manager")==null) {
				session.setAttribute("alertMsg", "Errore, utente non loggato");
				response.sendRedirect("./Homepage.jsp");
			}else {
				session.removeAttribute("utente");
				session.removeAttribute("manger");
				session.setAttribute("alertMsg", "Logout effettuato con successo.");
				response.sendRedirect("./Homepage.jsp");
			}
		}catch (Exception e2) {System.out.println(e2);} 
	}
}

