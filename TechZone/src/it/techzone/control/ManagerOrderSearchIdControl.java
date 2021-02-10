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

public class ManagerOrderSearchIdControl extends HttpServlet{
	static OrderManager om=new OrderManager();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		try {
				if(session.getAttribute("manager")!=null) {
					
					if(request.getParameter("idOrd")==null) {
						session.setAttribute("alertMsg", "Operazione non valida");
						response.sendRedirect("Homepage.jsp");
					}
					else {
						 
							Order o=om.getOrderById(Long.parseLong(request.getParameter("idOrd")));
							if(o==null) {
								session.setAttribute("alertMsg", "Ordine non trovato");
								response.sendRedirect("OrdersPage.jsp");
							}else {
								session.setAttribute("ordine", o);
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
			
		}
	}
	
}
