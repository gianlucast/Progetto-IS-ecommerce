package it.techzone.control;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Cart;
import it.techzone.model.beans.Product;
import it.techzone.model.models.CartManager;
import it.techzone.model.models.ProductManager;

public class ModInCartControl extends HttpServlet {
static CartManager cm= new CartManager();
static ProductManager pm= new ProductManager();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session= request.getSession();
		try {
			if(session.getAttribute("manager")==null) {
				
						int changeQ = Integer.parseInt(request.getParameter("change"));
						long id= Long.parseLong(request.getParameter("idProd"));
						cm.changeQuantityCart(id, changeQ, session);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("./CartPage.jsp");
						dispatcher.forward(request, response);
						
						
				}
				
			
			else {
				session.setAttribute("alertMsg","Accesso non autorizzato");
				response.sendRedirect("./HomePage");
				
			}
		}
		catch(Exception e2) {}
	}
	
}
