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
//modifica quantità di un prodotto nel carrello
public class ModInCartControl extends HttpServlet {
static CartManager cm= new CartManager();
static ProductManager pm= new ProductManager();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session= request.getSession();
		try {
			//solo gli utenti possono avere un carrellos
			if(session.getAttribute("manager")==null) {
						//viene modificata la quantità di un prodotto nel carrello e viene ricaricata la pagina
						int changeQ = Integer.parseInt(request.getParameter("change"));
						long id= Long.parseLong(request.getParameter("idProd"));
						//Il manager comunica col DAO, quindi la modifica della quantità verrà gestita in base
						//ai dati nel database
						cm.changeQuantityCart(id, changeQ, session);
						response.sendRedirect("./CartView.jsp");
						
						
				}
				
			
			else {
				session.setAttribute("alertMsg","Accesso non autorizzato");
				response.sendRedirect("./HomePage.jsp");
				
			}
		}
		catch(Exception e2) {
			
		
			}
	}
	
}
