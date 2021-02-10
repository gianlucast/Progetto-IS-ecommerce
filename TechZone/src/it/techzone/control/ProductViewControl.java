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
import it.techzone.model.managers.ProductManager;

public class ProductViewControl extends HttpServlet{
	static ProductManager pm=new ProductManager();
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			if(request.getParameter("idProd")==null) {
				ArrayList<Product> prodotti=pm.getAllProducts("");
				request.setAttribute("prodotti", prodotti);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/HomePage.jsp");
				dispatcher.forward(request, response);
			}else {
				if(request.getParameter("idProd")!=null) {
					Long idProd=Long.parseLong(request.getParameter("idProd"));
					Product prodotto=pm.retrieveProduct(idProd);
					request.setAttribute("prodotto", prodotto);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
					dispatcher.forward(request, response);
				
				}
			}
		
		} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
		}
}
