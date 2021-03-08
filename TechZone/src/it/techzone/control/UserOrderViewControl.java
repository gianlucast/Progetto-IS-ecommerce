package it.techzone.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Order;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.managers.OrderManager;

//visualizzazione di un ordine effettuato dall'utente loggatos
public class UserOrderViewControl extends HttpServlet{
	static OrderManager om=new OrderManager();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		
		try {
			//solo l'utente può visualizzare il proprio ordine effettuato, in quanto i manager non possono effettuare ordini
			if(session.getAttribute("utente")==null) {
				session.setAttribute("alertMsg", "Richiesta non valida");
				response.sendRedirect("HomePage.jsp");
			}else {
				//se la request nell'url non è valida, errore
				if(request.getParameter("idOrd")==null) {
					session.setAttribute("alertMsg", "Errore nella richiesta");
					response.sendRedirect("HomePage.jsp");
				}else {
					//viene preso l'ordine, ma se il valore nella request non corrisponde ad un ordine esistente nel database, errore
					long idOrd=Long.parseLong(request.getParameter("idOrd"));
					Order ordine=om.getOrderById(idOrd);
					if(ordine==null) {
						session.setAttribute("alertMsg", "Richiesta non valida");
						response.sendRedirect("HomePage.jsp");
					}else {
						//si deve verificare che l'ordine visualizzato sia effettivamente dell'utente che sta cercando di visualizzarlo
						//quindi si prendono tutti gli ordini da lui effettuati e si fa il confronto
						//se ha riscontro positivo viene mostrato l'ordine nella pagina, altrimenti no
						UtenteRegistrato u=(UtenteRegistrato)session.getAttribute("utente");
						ArrayList<Order> ordini=om.searchOrders(u.getEmail());
						boolean flag=false;
						for(int i=0;i<ordini.size();i++) {
							if(ordini.get(i).getNumeroOrdine()==ordine.getNumeroOrdine()) flag=true;
						}
						if(!flag) {
							session.setAttribute("alertMsg", "Richiesta non valida");
							response.sendRedirect("HomePage.jsp");
						}else {
							session.setAttribute("ordine",ordine);
							RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OrderDetailPage.jsp");
							dispatcher.forward(request, response);
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
