package it.techzone.control;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Order;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.managers.OrderManager;

public class UserOrderSearchControl extends HttpServlet{
	static OrderManager om=new OrderManager();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		try {
				if(session.getAttribute("utente")!=null) {
					UtenteRegistrato u=(UtenteRegistrato)session.getAttribute("utente");
					
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
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
}
