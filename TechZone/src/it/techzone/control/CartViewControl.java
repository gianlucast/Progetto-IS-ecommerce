package it.techzone.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.managers.CartManager;
import it.techzone.model.managers.ProductManager;

public class CartViewControl extends HttpServlet{
	static CartManager cm=new CartManager();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		try {
			if(session.getAttribute("manager")==null) {
				if(!cm.cartExists(session)) cm.newCart(session);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CartPage.jsp");
				dispatcher.forward(request, response);
			}else {
				session.setAttribute("alertMsg", "Non puoi accedere al carrello");
				response.sendRedirect("Homepage.jsp");
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
