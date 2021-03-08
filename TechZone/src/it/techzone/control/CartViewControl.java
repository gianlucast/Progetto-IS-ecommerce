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
			//i manager non possono avere un carrello
			if(session.getAttribute("manager")==null) {
				//se non esiste un carrello in sessione, ne viene creato uno vuoto
				if(!cm.cartExists(session)) cm.newCart(session);
				//viene aperta la pagina con il carrello, che sia vuoto o meno
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CartView.jsp");
				dispatcher.forward(request, response);
			}else {
				//I manager ritornano alla homepage se provano a visualizzare un carrello
				session.setAttribute("alertMsg", "Non puoi accedere al carrello");
				response.sendRedirect("HomePage.jsp");
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
