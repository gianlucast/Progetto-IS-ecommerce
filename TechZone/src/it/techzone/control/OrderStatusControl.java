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
				
				try {
				if(session.getAttribute("manager")!=null) {
					long id = Long.parseLong((String) request.getParameter("orderId"));
					om.changeStatus(id, request.getParameter("changeStatus"));
					RequestDispatcher requestD = getServletContext().getRequestDispatcher("./OrderManagement.jsp");
					requestD.forward(request, response);
					
				
				}
				else {
					session.setAttribute("alertMsg","Accesso non autorizzato");
					response.sendRedirect("./HomePage.jsp");
					
				}
			}
				
			catch(Exception e2) {}
		}
		
}
		
