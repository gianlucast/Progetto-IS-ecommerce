package it.techzone.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.techzone.model.beans.Product;
import it.techzone.model.models.ProductManager;

public class ProductCatalogueControl extends HttpServlet{
	static ProductManager pm=new ProductManager();
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			if(request.getParameter("q")==null) {
				ArrayList<Product> prodotti=pm.getAllProducts("");
				request.setAttribute("prodotti", prodotti);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/HomePage.jsp");
				dispatcher.forward(request, response);
				}	else { 
						if(request.getParameter("by").equalsIgnoreCase("categoria")){
							ArrayList<Product> prodotti=pm.searchProductsByCat(request.getParameter("q"));
							request.setAttribute("prodotti", prodotti);
							RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/SearchPage.jsp");
							dispatcher.forward(request, response);
						}
						// essenzialmente non sono la stessa cosa?
						if(request.getParameter("by").equalsIgnoreCase("nome")){
							ArrayList<Product> prodotti=pm.searchProductsByName(request.getParameter("q"));
							request.setAttribute("prodotti", prodotti);
							RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/SearchPage.jsp");
							dispatcher.forward(request, response);
						}
					}
				}
			
		
			catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
		}
}



