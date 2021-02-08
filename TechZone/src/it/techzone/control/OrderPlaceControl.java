package it.techzone.control;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Cart;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.models.OrderManager;
import it.techzone.model.models.UserManager;

public class OrderPlaceControl extends HttpServlet{
		static OrderManager om=new OrderManager(); 
		public void doGet(HttpServletRequest request, HttpServletResponse response) {
			HttpSession session=request.getSession();
			try {
				if(session.getAttribute("utente")==null) {
					session.setAttribute("alertMsg", "Errore, utente non loggato");
					response.sendRedirect("./Login.jsp");
				}else {
					if(session.getAttribute("cart")==null) {
						session.setAttribute("alertMsg", "Errore, carrello vuoto");
						response.sendRedirect("./Homepage.jsp");
					}else {
						Cart cart=(Cart) session.getAttribute("cart");
						if(cart.getProductList().size()==0) {
							session.setAttribute("alertMsg", "Errore, carrello vuoto");
							response.sendRedirect("./Homepage.jsp");
						}else {
							UtenteRegistrato user=(UtenteRegistrato) session.getAttribute("utente");
							if(om.placeOrder(user, cart)) {
								session.setAttribute("cart", null);
								session.setAttribute("alertMsg", "Ordine effettuato con successo");
								response.sendRedirect("./Homepage.jsp");
							}else {
								session.setAttribute("alertMsg", "Errore nell'ordine.");
								response.sendRedirect("./Homepage.jsp");
							}
						}
					}
				}
			}catch(Exception e2){System.out.println(e2);}
		}
}
