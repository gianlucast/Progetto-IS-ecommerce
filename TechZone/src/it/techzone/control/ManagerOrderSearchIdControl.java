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
//ricerca ordine con id ordine
public class ManagerOrderSearchIdControl extends HttpServlet{
	static OrderManager om=new OrderManager();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession();
		try {
				//Solo i manager possono effettuare operazioni di ricerca degli ordiniss
				if(session.getAttribute("manager")!=null) {
					//viene controllato che la richiesta contenga il parametro collegato all'id 
					if(request.getParameter("idOrd")==null) {
						session.setAttribute("alertMsg", "Operazione non valida");
						response.sendRedirect("./HomePage.jsp");
					}
					else {
						 	
							Order o=om.getOrderById(Long.parseLong(request.getParameter("idOrd")));
							//se non esistono ordini collegati all'id inserito, allora si ritorna alla pagina 
							// di amministrazione
							if(o==null) {
								session.setAttribute("alertMsg", "Ordine non trovato");
								response.sendRedirect("./OrdersManagerPage.jsp");
								
							}else {
								//altrimenti, viene mostrato il risultato della ricerca nella pagina
								ArrayList<Order> array=new ArrayList<Order>();
								array.add(o);
								session.setAttribute("ordini", array);
								RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OrdersManagerPage.jsp");
								dispatcher.forward(request, response);
							}
						}
					}
				//gli utenti normali vengono reindirizzati alla homepage
				else {
						
						session.setAttribute("alertMsg", "Operazione non autorizzata");
						response.sendRedirect("./HomePage.jsp");
				}
			
		}catch(Exception e2) {
			
			session.setAttribute("alertMsg","Errore, ritorno alla Homepage");
			response.sendRedirect("./HomePage.jsp");	
			}
	}
	
}
