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
//ricerca ordini con mail
public class ManagerOrderSearchMailControl extends HttpServlet{
	static OrderManager om=new OrderManager();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		try {
				//solo i manager possono effettuare la ricerca degli ordiniss
				if(session.getAttribute("manager")!=null) {
					session.setAttribute("mailTest",request.getParameter("mailOrd"));
					//viene controllato che la richiesta contenga il parametro collegato all'email
					//questo può avvenire se il manager prova a cancellare un prodotto tramite un
					//url scorretto, e non dalla sua pagina di amministrazione
					if(request.getParameter("mailOrd")==null) {
						session.setAttribute("alertMsg", "Operazione non valida");
						response.sendRedirect("./HomePage.jsp");
					}
					else {
						
							//la classe manager comunica col dao che prende gli ordini legati all'email dal database
							ArrayList<Order> ordini=om.searchOrders(request.getParameter("mailOrd"));
							if(ordini==null||ordini.size()==0) {
								//se nessun ordine viene trovato, errore
								session.setAttribute("alertMsg", "Nessun ordine trovato per la mail: "+request.getParameter("mailOrd"));
								response.sendRedirect("./OrdersManagerPage.jsp");
							}else {
								//altrimenti vengono mostrati gli ordini nella pagina
								session.setAttribute("ordini",ordini);
								RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OrdersManagerPage.jsp");
								dispatcher.forward(request, response);
							}
						}
					}
				
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
