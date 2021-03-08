package it.techzone.control;
import java.io.IOException;

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
//aggiunta di un prodotto al carrello
public class AddToCartControl extends HttpServlet {
static CartManager cm= new CartManager();
static ProductManager pm= new ProductManager();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		HttpSession session= request.getSession();
		try {
			//i manager non possono avere un carrello
			if(session.getAttribute("manager")==null) {
				
						//se al momento dell'aggiunta del prodotto non è stato ancora creato un carrello,
						//ne viene creato uno vuoto
						if (!cm.cartExists(session))cm.newCart(session);
						else cm.retrieveCart(session);
						//viene preso dalla request l'id del prodotto e con la modcart avviene 
						//la comunicazione con il DAO per aggiungerlo al carrello prendendolo dal database
						Long idProd=Long.parseLong(request.getParameter("product"));
						cm.modCart(idProd,1, session);
						//viene aperta automaticamente la pagina del carrello
						response.sendRedirect("./CartView.jsp");
					
				}
				
			
			else {
				//I manager ritornano alla home page quando provano ad aggiungere un prodotto al carrello
				session.setAttribute("alertMsg","Accesso non autorizzato");
				response.sendRedirect("HomePage.jsp");
				
			}
		}
		catch(Exception e2) {
		
				session.setAttribute("alertMsg","Errore, ritorno alla Homepage");
				response.sendRedirect("./HomePage.jsp");	
}
}
	
}
