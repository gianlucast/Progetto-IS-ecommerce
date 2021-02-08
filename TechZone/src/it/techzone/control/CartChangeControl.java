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

public class CartChangeControl extends HttpServlet {
static CartManager cm= new CartManager();
static ProductManager pm= new ProductManager();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session= request.getSession();
		try {
		if(session.getAttribute("manager")==null) {
			if(session.getAttribute("product")!=null && !cm.cartExists(session) ) {
				//aggiunta di un prodotto dalla sua pagina 
					if (!cm.cartExists(session))cm.newCart(session);
					else cm.retrieveCart(session);
					Product p=(Product) session.getAttribute("product");
					cm.modCart(p.getCodice(),1, session);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("./Cart");
					dispatcher.forward(request, response);
				}
				
			else if (cm.cartExists(session) && session.getAttribute("product")==null && request.getAttribute("change")!=null) {
						int changeQ = Integer.parseInt((String) request.getAttribute("change"));
						long id= Long.parseLong((String) request.getAttribute("idProd"));
						//creato nuovo metodo in cartManager per la modifica del carrello, quello già presente è collegato
						//ad addcart che non prevede la rimozione. Si potrebbe anche modificare il metodo già esistente, però 
						// vediamo insieme
						cm.changeQuantityCart(id, changeQ, session);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("./Cart");
						dispatcher.forward(request, response);
					
					
			}
			
		}
		}
		catch(Exception e2) {}
	}
	
}
