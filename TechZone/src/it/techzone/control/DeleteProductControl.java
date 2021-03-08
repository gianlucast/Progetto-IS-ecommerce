package it.techzone.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.techzone.model.beans.Product;
import it.techzone.model.managers.ProductManager;
//rimozione di un prodotto
public class DeleteProductControl extends HttpServlet  {
	
	static ProductManager pm = new ProductManager();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		HttpSession session= request.getSession();
		try {
			//Solo i manager possono rimuovere prodotti dal sitos
			if(session.getAttribute("manager")!=null) {
				//viene controllato la richiesta contenca il parametro collegato all'id del prodotto
				//questo può avvenire se il manager prova a cancellare un prodotto tramite un
				//url scorretto, e non dalla sua pagina di amministrazione
				if(request.getParameter("idProd")!=null) {
					long idProd= Long.parseLong(request.getParameter("idProd"));
				
					//se l'operazione va a buon fine nel database, allora viene mostrato un messaggio di successo
					
					if(pm.deleteProduct(idProd))
						session.setAttribute("alertMsg","Prodotto rimosso con successo");
					else
						session.setAttribute("alertMsg", "Errore nella rimozione del prodotto");
					response.sendRedirect("./AdminMod.jsp");
				}else
					//Il manager torna nella sua pagina di amministrazione se l'id inserito è null
					{
					session.setAttribute("alertMsg", "Richiesta non valida");
					response.sendRedirect("./AdminMod.jsp");
				}
				//nel caso un utente provi ad utilizzare un url per rimuovere un prodotto, viene bloccato
			}else {
				session.setAttribute("alertMsg","Azione non autorizzata");
				response.sendRedirect("./HomePage.jsp");
			
		}
		
	}
		catch(Exception e2) {
			
			session.setAttribute("alertMsg","Errore, ritorno alla Homepage");
			response.sendRedirect("./HomePage.jsp");	
}
	

}
}
