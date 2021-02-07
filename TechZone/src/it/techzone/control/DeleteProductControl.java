package it.techzone.control;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.techzone.model.beans.Product;
import it.techzone.model.models.ProductManager;

public class DeleteProductControl extends HttpServlet {
	
	static ProductManager pm = new ProductManager();
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session= request.getSession();
		try {
			if(session.getAttribute("man")!=null) {
				Product prod= null;
				if((prod= (Product) session.getAttribute("product"))!=null) {
				
					pm.deleteProduct(prod.getCodice());
					session.setAttribute("alertMsg","Prodotto rimosso con successo");
					response.sendRedirect("./Productmanagement.jsp");
				}
				else {
					session.setAttribute("alertMsg","Prodotto già rimosso");
					response.sendRedirect("./Productmanagement.jsp");
				}
			}
			else {
				session.setAttribute("alertMsg","Azione non autorizzata");
				response.sendRedirect("./Homepage.jsp");
			
		}
		
	}
		catch (Exception e2) {System.out.println(e2);}
	

}
}
