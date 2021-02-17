package it.techzone.control;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Cart;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.managers.OrderManager;
import it.techzone.model.managers.UserManager;

public class OrderStatusControl extends HttpServlet {
	static OrderManager om = new OrderManager();
		public void doGet (HttpServletRequest request, HttpServletResponse response) {
			HttpSession session = request.getSession();
				System.out.println("Entrato nel metodo");
				try {
				if(session.getAttribute("manager")!=null) {
					System.out.println("Ehy, sono manager");
					long id = Long.parseLong(request.getParameter("orderId"));
					System.out.println("prima di om.changeStatus");
					if(om.changeStatus(id, request.getParameter("changeStatus"))) {
						System.out.println("Successo");
						session.setAttribute("alertMsg", "Modifica avvenuta con successo");
						response.sendRedirect("./OrdersManagerPage.jsp");
					}else {
						System.out.println("Insuccesso");
						session.setAttribute("alertMsg", "Modifica fallita");
						
						response.sendRedirect("./OrdersManagerPage.jsp");
					}
				
				}
				else {
					session.setAttribute("alertMsg","Accesso non autorizzato");
					response.sendRedirect("./HomePage.jsp");
					
				}
			}
				
			catch(Exception e2) {}
		}
		
}
		
