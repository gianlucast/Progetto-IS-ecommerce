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
				if(request.getParameter("idProd")!=null) {
					long idProd= Long.parseLong(request.getParameter("idProd"));
				
					
					if(pm.deleteProduct(idProd))
						session.setAttribute("alertMsg","Prodotto rimosso con successo");
					else
						session.setAttribute("alertMsg", "Errore nella rimozione del prodotto");
					response.sendRedirect("./AdminMod.jsp");
				}else {
					session.setAttribute("alertMsg", "Richiesta non valida");
					response.sendRedirect("./AdminMod.jsp");
				}
				
			}else {
				session.setAttribute("alertMsg","Azione non autorizzata");
				response.sendRedirect("./Homepage.jsp");
			
		}
		
	}
		catch (Exception e2) {e2.printStackTrace();}
	

}
}
