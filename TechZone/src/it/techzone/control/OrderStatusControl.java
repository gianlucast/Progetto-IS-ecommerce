package it.techzone.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Cart;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.managers.OrderManager;
import it.techzone.model.managers.UserManager;
//modifica stato ordines
public class OrderStatusControl extends HttpServlet {
	static OrderManager om = new OrderManager();
		public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
			HttpSession session = request.getSession();
				try {
					//solo il manager può modificare lo stato di un ordine
				if(session.getAttribute("manager")!=null) {
					//viene verificato che la request nell'url sia regolare
					if(request.getParameter("orderId")!=null) {
						
						long id = Long.parseLong(request.getParameter("orderId"));
						//una volta localizzato l'ordine, se la modifica dello stato è compatibile
						//con lo stato precedente, allora essa va a buon fine
						if(om.changeStatus(id, request.getParameter("changeStatus"))) {
							session.setAttribute("alertMsg", "Modifica avvenuta con successo");
							response.sendRedirect("./OrdersManagerPage.jsp");
						}else {
							//altrimenti, si ritorna alla pagina di amministrazione
							session.setAttribute("alertMsg", "Modifica fallita");
							
							response.sendRedirect("./OrdersManagerPage.jsp");
						}
					}else {
						
						session.setAttribute("alertMsg", "Richiesta non valida");
						
						response.sendRedirect("./OrdersManagerPage.jsp");
					}
				
				}
				else {
					session.setAttribute("alertMsg","Accesso non autorizzato");
					response.sendRedirect("./HomePage.jsp");
					
				}
			}
				
				catch(Exception e2) {
					
					session.setAttribute("alertMsg","Errore, ritorno alla Homepage");
					response.sendRedirect("./HomePage.jsp");	
					}
		}
		
}
		
