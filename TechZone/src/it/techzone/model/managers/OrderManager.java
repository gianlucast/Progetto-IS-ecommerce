package it.techzone.model.managers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.validator.routines.EmailValidator;

import com.mysql.cj.xdevapi.Result;

import it.techzone.model.beans.Cart;
import it.techzone.model.beans.Order;
import it.techzone.model.beans.ProductCart;
import it.techzone.model.beans.ProductOrder;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.dao.OrderDao;
import it.techzone.model.dao.ProductDAO;


public class OrderManager {
	
	private OrderDao orderdao;
	private ProductDAO productdao;
	private EmailValidator emailVal=EmailValidator.getInstance();

	
	public boolean placeOrder(UtenteRegistrato user,  Cart cart) throws SQLException{
		//se user o cart non esistono, l'operazione non può andare a buon fine
		if(user==null||cart==null||cart.getProductList().size()==0) return false;
		orderdao = new OrderDao();
		productdao=new ProductDAO();
		ArrayList<ProductOrder> ordinati= new ArrayList<ProductOrder>();
		//caricamento dei prodotti ordinati
		    for(ProductCart c : cart.getProductList()) {
		    	long codiceProd=c.getProdotto().getCodice();
		    	if(productdao.retrieveProductById(codiceProd)==null) return false;
		    	if(productdao.retrieveProductById(codiceProd).getQuantita()<c.getQuantita()) return false;
		    	ordinati.add(new ProductOrder(c));
		    }
		    Order order = new Order();
		    order.setUtente(user);
		    order.setProdotti(ordinati);
		    order.setTotale(cart.getPrezzoTotale());
		    //lo stato, appena ricevuto un ordine, è di default il seguente.
		    order.setStato("In preparazione");
		    
		    return orderdao.doSaveOrder(order); //ritorna l'esito dell'operazione di salvataggio dell'ordine nel database
		
	}
	
	public ArrayList<Order> searchOrders(String email) throws SQLException{
		if(email==""||email==null||!emailVal.isValid(email)) return null;
		orderdao = new OrderDao();
		return orderdao.retrieveOrdersByMail(email); //ritorna la lista di ordini effettuati dall'utente avente la mail passata come parametro
		
	}
	
	public Order getOrderById(long id) throws SQLException{
		orderdao = new OrderDao();
		return orderdao.retrieveOrderById(id);//ritorna l'ordine con l'id passato come parametro.
		
	}
	
	//ritorna true se il cambio di stato è valido, false altrimenti
	public boolean checkStatus(long idOrder, String status) throws SQLException{
		orderdao = new OrderDao();
		Order order = orderdao.retrieveOrderById(idOrder);
		if(order==null) return false;
		if(status==null||status=="") return false;
		//Basato sullo statechart presente nel RAD, un ordine può passare solo ad alcuni stati a partire da alcuni stati.
		if (order.getStato().equalsIgnoreCase("In preparazione")) {
				   if(status.equalsIgnoreCase("Spedito")) 
				      return true;
				   else
				     return false;
		}
		else if(order.getStato().equalsIgnoreCase("Spedito")) {
				   if(status.equalsIgnoreCase("In consegna")|| status.equalsIgnoreCase("Spedizione in ritardo"))
				      return true;
				   else
				      return false;
		}
		else if(order.getStato().equalsIgnoreCase("In consegna")){
				   if(status.equalsIgnoreCase("Spedizione in ritardo") || status.equalsIgnoreCase("Consegnato"))
				    return true;
				else
				    return false;
		}
		else if(order.getStato().equalsIgnoreCase("Spedizione in ritardo")){
				    if(status.equalsIgnoreCase("Spedito") || status.equalsIgnoreCase("Pacco smarrito"))
				    return true;
				else
				    return false;
		}
		else if(order.getStato().equalsIgnoreCase("Consegnato"))
				    return false;
		
		else if(order.getStato().equalsIgnoreCase("Pacco smarrito"))
				    return false; 
		
		return false;
	}
	
	//esegue il cambio di stato, a patto che checkStatus ritorni true
	public boolean changeStatus(long idOrder, String status) throws SQLException{
		
		orderdao = new OrderDao();
		
		if(checkStatus(idOrder,status)==false)
			return false;
		
		Order order = orderdao.retrieveOrderById(idOrder);
		order.setStato(status);
		
		return orderdao.doUpdateOrder(order); //ritorna l'esito del cambio di stato sul db.
		
		
	}
	
	
}
