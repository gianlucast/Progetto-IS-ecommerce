package it.techzone.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.techzone.model.beans.Product;
import it.techzone.model.managers.ProductManager;
//modifica di un prodotto
public class UpdateProductControl extends HttpServlet{

 static ProductManager pm = new ProductManager();
 
 	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
 		HttpSession session = request.getSession();
 		try {
 			//solo i manager possono modificare i prodotti
 			if(session.getAttribute("manager")!=null) {
 				//controllo della validità della request
 				if(request.getParameter("action")!=null){
 					//si prendono i parametri dal form
	 				String categoria= request.getParameter("categoria");
	 				String codice= request.getParameter("codice");
	 				long code = Long.parseLong(codice);
	 				String costo= request.getParameter("costo");
	 				float cost= Float.parseFloat(costo);
	 				String descrizione = request.getParameter("descrizione");
	 				String nomeProd = request.getParameter("nomeprod");
	 				String quantita = request.getParameter("quantita");
	 				int quantity = Integer.parseInt(quantita);
	 				String tipo= request.getParameter("tipo");
	 				//in base al booleano di risposta del metodo di salvataggio del prodotto, si
	 				//può capire l'esito dell'operazione
	 				if(pm.updateProduct(code, descrizione, nomeProd, quantity, categoria, tipo, cost))
	 					session.setAttribute("alertMsg", "Modifica effettuata");
	 				else
	 					session.setAttribute("alertMsg", "Errore nella modifica");
	 				response.sendRedirect("./ModifyProduct.jsp");
 				}else {
 					//se l'action è null ma la request dell'idprod no, viene riaperta la pagina col prodotto
 					if(request.getParameter("idProd")!=null) {
 						long idProd=Long.parseLong(request.getParameter("idProd"));
 						Product prodotto=pm.retrieveProduct(idProd);
 						request.setAttribute("prodotto", prodotto);
 						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ModifyProduct.jsp");
 						dispatcher.forward(request, response);

 					}else {
 						session.setAttribute("alertMsg", "Errore nella richiesta");
 		 				response.sendRedirect("./AdminMod.jsp");
 					}
 				}
 			}
 			else {
 				session.setAttribute("alertMsg", "Accesso non autorizzato");
 				response.sendRedirect("./Homepage.jsp");
 			}
 		}
 		
 		
 		catch(Exception e2) {
			
			session.setAttribute("alertMsg","Errore, ritorno alla Homepage");
			response.sendRedirect("./HomePage.jsp");	
			}
}
}
