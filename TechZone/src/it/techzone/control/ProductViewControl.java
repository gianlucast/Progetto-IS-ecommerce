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
//visualizzazione di un prodottos
public class ProductViewControl extends HttpServlet{
	static ProductManager pm=new ProductManager();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		try {
			//viene controllato che la request nell'url sia regolare, e vengono mostrati nella pagina di
			//amministrazione nel caso dell'admin, nella homepage nel caso dell'utente
			if(request.getParameter("idProd")==null) {
				ArrayList<Product> prodotti=pm.getAllProducts("");
				request.setAttribute("prodotti", prodotti);
				if(session.getAttribute("manager")!=null) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminMod.jsp");
					dispatcher.forward(request, response);
				}else {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/HomePage.jsp");
					dispatcher.forward(request, response);
				}
			}else {
				// se la request è regolare, allora viene mostrato il prodotto nella pagina
				if(request.getParameter("idProd")!=null) {
					Long idProd=Long.parseLong(request.getParameter("idProd"));
					Product prodotto=pm.retrieveProduct(idProd);
					request.setAttribute("prodotto", prodotto);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
					dispatcher.forward(request, response);
				
				}
			}
		
		} catch(Exception e2) {
			
			session.setAttribute("alertMsg","Errore, ritorno alla Homepage");
			response.sendRedirect("./HomePage.jsp");	
			}
		}
}
