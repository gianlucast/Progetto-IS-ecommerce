package it.techzone.control;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Cart;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.managers.OrderManager;
import it.techzone.model.managers.UserManager;
//acquisto prodotto/i
public class OrderPlaceControl extends HttpServlet{
		static OrderManager om=new OrderManager(); 
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
			HttpSession session=request.getSession();
			try {
				//solo gli utenti possono effettuare acquistis
				if(session.getAttribute("utente")==null) {
					session.setAttribute("alertMsg", "Errore, utente non loggato");
					response.sendRedirect("./Login.jsp");
				}else {
					//non si possono effettuare acquisti se il carrello non esiste in sessione
					if(session.getAttribute("cart")==null) {
						session.setAttribute("alertMsg", "Errore, carrello vuoto");
						response.sendRedirect("./HomePage.jsp");
					}else {
						//se il carrello è vuoto, non si possono effettuare acquisti
						Cart cart=(Cart) session.getAttribute("cart");
						if(cart.getProductList().size()==0) {
							session.setAttribute("alertMsg", "Errore, carrello vuoto");
							response.sendRedirect("./HomePage.jsp");
						}else {
							UtenteRegistrato user=(UtenteRegistrato) session.getAttribute("utente");
							//viene effettuato acquisto e viene aggiunto un ordine alla lista ordini dell'utente
							if(om.placeOrder(user, cart)) {
								session.setAttribute("cart", null);
								session.setAttribute("alertMsg", "Ordine effettuato con successo");
								response.sendRedirect("./HomePage.jsp");
							}else {
								//se c'è un errore, si ritorna alla homepage
								session.setAttribute("alertMsg", "Errore nell'ordine.");
								response.sendRedirect("./HomePage.jsp");
							}
						}
					}
				}
			}catch(Exception e2) {
		
				session.setAttribute("alertMsg","Errore, ritorno alla Homepage");
				response.sendRedirect("./HomePage.jsp");	
				}
		}
}
