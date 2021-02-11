package it.techzone.control;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Cart;
import it.techzone.model.beans.Product;
import it.techzone.model.managers.CartManager;
import it.techzone.model.managers.ProductManager;

public class AddToCartControl extends HttpServlet {
static CartManager cm= new CartManager();
static ProductManager pm= new ProductManager();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session= request.getSession();
		try {
			if(session.getAttribute("manager")==null) {
				
				
						if (!cm.cartExists(session))cm.newCart(session);
						else cm.retrieveCart(session);
						Long idProd=Long.parseLong(request.getParameter("product"));
						cm.modCart(idProd,1, session);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("./CartView.jsp");
						dispatcher.forward(request, response);
					
					
				
				}
				
			
			else {
				session.setAttribute("alertMsg","Accesso non autorizzato");
				response.sendRedirect("./HomePage.jsp");
				
			}
		}
		catch(Exception e2) {}
	}
	
}
