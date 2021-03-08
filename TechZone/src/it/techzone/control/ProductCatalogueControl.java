package it.techzone.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Product;
import it.techzone.model.managers.ProductManager;
//ricerca di un prodottos
public class ProductCatalogueControl extends HttpServlet{
	static ProductManager pm=new ProductManager();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		try { //se la richiesta nell'url non è regolare, allora vengono mostrati tutti i prodotti nella homepage
			if(request.getParameter("q")==null) {
				ArrayList<Product> prodotti=pm.getAllProducts("");
				request.setAttribute("prodotti", prodotti);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/HomePage.jsp");
				dispatcher.forward(request, response);
				}	else { 
						//se la ricerca è avvenuta per categoria, allora vengono presi tutti i prodotti 
						//di una data categoria dal database e mostrati nella pagina
						if(request.getParameter("by").equalsIgnoreCase("categoria")){
							ArrayList<Product> prodotti=pm.searchProductsByCat(request.getParameter("q"));
							request.setAttribute("prodotti", prodotti);
							RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Ricerca.jsp");
							dispatcher.forward(request, response);
						}
						// se la ricerca è avvenuta per nome, allora vengono ricercati i prodotti con il nome
						// inserito e mostrati nella pagina
						if(request.getParameter("by").equalsIgnoreCase("nome")){
							ArrayList<Product> prodotti=pm.searchProductsByName(request.getParameter("q"));
							request.setAttribute("prodotti", prodotti);
							RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Ricerca.jsp");
							dispatcher.forward(request, response);
						}
					}
				}
			
		
			catch(Exception e2) {
			
			session.setAttribute("alertMsg","Errore, ritorno alla Homepage");
			response.sendRedirect("./HomePage.jsp");	
			}
		}
}



