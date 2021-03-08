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
//visualizzazione di un ordine cercato dal manager
public class ManagerOrderViewControl extends HttpServlet{
	static OrderManager om=new OrderManager();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		
		try {
			//view degli ordini riservata ai manager, riguarda la visualizzazione di uno degli ordini
			//con uno dei due possibili modi di ricerca
			if(session.getAttribute("manager")==null) {
				session.setAttribute("alertMsg", "Richiesta non valida");
				response.sendRedirect("./HomePage.jsp");
			}else {
				//se nella request non c'è il parametro collegato all'id dell'ordine, allora errore
				if(request.getParameter("idOrd")==null) {
					session.setAttribute("alertMsg", "Errore nella richiesta");
					response.sendRedirect("./HomePage.jsp");
				}else {
					// vengono mostrati le informazioni dell'ordine nella pagina
						long idOrd=Long.parseLong(request.getParameter("idOrd"));
						Order ordine=om.getOrderById(idOrd);
						session.setAttribute("ordine",ordine);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OrderManagerDetailPage.jsp");
						dispatcher.forward(request, response);
					}
				}
			}
		catch(Exception e2) {
			
			session.setAttribute("alertMsg","Errore, ritorno alla Homepage");
			response.sendRedirect("./HomePage.jsp");	
			}
	}

}
