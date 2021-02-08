package it.techzone.control;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.techzone.model.beans.Order;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.models.OrderManager;

public class OrderSearchControl extends HttpServlet{
	static OrderManager om=new OrderManager();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		try {
			if(session.getAttribute("utente")==null&&session.getAttribute("manager")==null) {
				session.setAttribute("alertMsg", "Non puoi accedere a questa pagina");
				response.sendRedirect("Homepage.jsp");
			}else {
				if(session.getAttribute("utente")!=null) {
					UtenteRegistrato u=(UtenteRegistrato)session.getAttribute("utente");
					if(request.getParameter("idOrd")!=null) {
						session.setAttribute("alertMsg", "Non puoi accedere a questa pagina");
						response.sendRedirect("Homepage.jsp");
					}else {
						ArrayList<Order> ordini=om.searchOrders(u.getEmail());
						session.setAttribute("ordini",ordini);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OrdersPage.jsp");
						dispatcher.forward(request, response);
					}
				}else {
					if(request.getParameter("idOrd")==null&&request.getParameter("mailOrd")==null) {
						session.setAttribute("alertMsg", "Operazione non valida");
						response.sendRedirect("Homepage.jsp");
					}else {
						if(request.getParameter("idOrd")!=null) {
							Order o=om.getOrderById(Long.parseLong(request.getParameter("idOrd")));
							if(o==null) {
								session.setAttribute("alertMsg", "Ordine non trovato");
								response.sendRedirect("OrdersPage.jsp");
							}else {
								session.setAttribute("ordine", o);
								RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OrdersPage.jsp");
								dispatcher.forward(request, response);
							}
						}else {
							ArrayList<Order> ordini=om.searchOrders(request.getParameter("mailOrd"));
							if(ordini.size()==0) {
								session.setAttribute("alertMsg", "Nessun ordine trovato per la mail: "+request.getParameter("mailOrd"));
								response.sendRedirect("OrdersPage.jsp");
							}else {
								session.setAttribute("ordini",ordini);
								RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OrdersPage.jsp");
								dispatcher.forward(request, response);
							}
						}
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
