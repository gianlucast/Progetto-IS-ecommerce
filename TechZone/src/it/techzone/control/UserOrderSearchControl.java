package it.techzone.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Order;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.managers.OrderManager;
//visualizzazione della lista ordini effettuati dall'utente loggato
public class UserOrderSearchControl extends HttpServlet{
	static OrderManager om=new OrderManager();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		try {	//solo gli utenti possono vedere i propri ordini effettuati, perché i manager non possono
				//effettuare ordini
				if(session.getAttribute("utente")!=null) {
						UtenteRegistrato u=(UtenteRegistrato)session.getAttribute("utente");
						//vengono ricavati gli ordini dal database grazie all'email dell'utente,e mostrati nella pagina
						ArrayList<Order> ordini=om.searchOrders(u.getEmail());
						session.setAttribute("ordini",ordini);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserArea.jsp");
						dispatcher.forward(request, response);
					}
				else {
					session.setAttribute("alertMsg", "Pagina non disponibile");
					response.sendRedirect("./HomePage.jsp");
				}
	    }
		catch(Exception e2) {
			
			session.setAttribute("alertMsg","Errore, ritorno alla Homepage");
			response.sendRedirect("./HomePage.jsp");	
			}
	}
	
}
