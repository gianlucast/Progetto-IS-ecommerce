package it.techzone.control;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.techzone.model.beans.Product;
import it.techzone.model.managers.ProductManager;

public class DeleteProductControl extends HttpServlet {
	
	static ProductManager pm = new ProductManager();
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session= request.getSession();
		try {
			if(session.getAttribute("manager")!=null) {
				long idProd= (long) request.getAttribute("idProd");
			
				
				pm.deleteProduct(idProd);
				session.setAttribute("alertMsg","Prodotto rimosso con successo");
				RequestDispatcher requestD = getServletContext().getRequestDispatcher("./AdminMod.jsp");
				requestD.forward(request, response);
			}
				
			else {
				session.setAttribute("alertMsg","Azione non autorizzata");
				response.sendRedirect("./Homepage.jsp");
			
		}
		
	}
		catch (Exception e2) {System.out.println(e2);}
	

}
}
