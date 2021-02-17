package it.techzone.control;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.techzone.model.beans.Product;
import it.techzone.model.managers.ProductManager;

public class UpdateProductControl extends HttpServlet{
 static ProductManager pm = new ProductManager();
 
 	public void doGet(HttpServletRequest request, HttpServletResponse response) {
 		HttpSession session = request.getSession();
 		try {
 			if(session.getAttribute("manager")!=null) {
 				if(request.getParameter("action").equalsIgnoreCase("mod")){
	 				String categoria= request.getParameter("categoria");
	 				String codice= request.getParameter("codice");
	 				long code = Long.parseLong(codice);
	 				String costo= request.getParameter("costo");
	 				float cost= Float.parseFloat(costo);
	 				String descrizione = request.getParameter("descrizione");
	 				String nomeProd = request.getParameter("nomeprod");
	 				String quantità = request.getParameter("quantità");
	 				int quantity = Integer.parseInt(quantità);
	 				String tipo= request.getParameter("tipo");
	 			
	 				pm.updateProduct(code, descrizione, nomeProd, quantity, categoria, tipo, cost);
	 				session.setAttribute("alertMsg", "Modifica effettuata");
	 				response.sendRedirect("./AdminMod.jsp");
 				}else {
 					if(request.getParameter("idProd")!=null) {
 						long idProd=Long.parseLong(request.getParameter("idProd"));
 						request.setAttribute("prodotto", pm.retrieveProduct(idProd));
 	 					response.sendRedirect("./ModifyProduct.jsp");
 					}else {
 						session.setAttribute("alertMsg", "Errore nella richiesta");
 		 				response.sendRedirect("./AdminMod.jsp");
 					}
 				}
 			}
 			else {
 				session.setAttribute("alertMsg", "Accesso non autorizzato");
 				response.sendRedirect("./HomePage.jsp");
 			}
 		}
 		
 		catch(Exception e2) {System.out.println(e2);}
 		
 	
 	}
}
