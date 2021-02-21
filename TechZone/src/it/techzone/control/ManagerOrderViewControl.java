package it.techzone.control;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Order;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.managers.OrderManager;

public class ManagerOrderViewControl extends HttpServlet{
	static OrderManager om=new OrderManager();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		
		try {
			if(session.getAttribute("manager")==null) {
				session.setAttribute("alertMsg", "Richiesta non valida");
				response.sendRedirect("./HomePage.jsp");
			}else {
				if(request.getParameter("idOrd")==null) {
					session.setAttribute("alertMsg", "Errore nella richiesta");
					response.sendRedirect("./HomePage.jsp");
				}else {
						long idOrd=Long.parseLong(request.getParameter("idOrd"));
						Order ordine=om.getOrderById(idOrd);
						session.setAttribute("ordine",ordine);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OrderManagerDetailPage.jsp");
						dispatcher.forward(request, response);
					}
				}
			}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
