package it.techzone.control;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Order;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.models.OrderManager;

public class ManagerOrderSearchMailControl extends HttpServlet{
	static OrderManager om=new OrderManager();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		try {
				if(session.getAttribute("manager")!=null) {
					
					if(request.getParameter("mailOrd")==null) {
						session.setAttribute("alertMsg", "Operazione non valida");
						response.sendRedirect("Homepage.jsp");
					}
					else {
						
							ArrayList<Order> ordini=om.searchOrders(request.getParameter("mailOrd"));
							if(ordini.size()==0) {
								session.setAttribute("alertMsg", "Nessun ordine trovato per la mail: "+request.getParameter("mailOrd"));
								response.sendRedirect("OrdersPage.jsp");
							}else {
								session.setAttribute("ordini",ordini);
								RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OrdersPage.jsp");
								dispatcher.forward(request, response);
							}
						}
					}
				
				else {
					session.setAttribute("alertMsg", "Operazione non autorizzata");
					response.sendRedirect("./HomePage");
			}
		
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("");
		}
	}
	
}
