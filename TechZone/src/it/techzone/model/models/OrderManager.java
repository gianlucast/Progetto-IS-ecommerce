package it.techzone.model.models;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Result;

import it.techzone.model.beans.Cart;
import it.techzone.model.beans.Order;
import it.techzone.model.beans.ProductCart;
import it.techzone.model.beans.ProductOrder;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.dao.OrderDao;

//fatto da gianniggio


public class OrderManager {
	
	private OrderDao orderdao;

	
	public boolean placeOrder(UtenteRegistrato user,  Cart cart) throws SQLException{
		
		orderdao = new OrderDao();
		ArrayList<ProductOrder> ordinati= new ArrayList<ProductOrder>();
		    for(ProductCart c : cart.getProductList()) ordinati.add(new ProductOrder(c));
		    Order order = new Order();
		    order.setUtente(user);
		    order.setProdotti(ordinati);
		    order.setTotale(cart.getPrezzoTotale());
		    order.setStato("In preparazione");
		    
		    return orderdao.doSaveOrder(order);
		
	}
	
	public ArrayList<Order> searchOrders(String email) throws SQLException{
		
		orderdao = new OrderDao();
		return orderdao.retrieveOrdersByMail(email);
		
	}
	
	public Order getOrderById(long id) throws SQLException{
		
		orderdao = new OrderDao();
		return orderdao.retrieveOrderById(id);
		
	}
	
	public boolean checkStatus(long idOrder, String status) throws SQLException{
		
		orderdao = new OrderDao();
		Order order = orderdao.retrieveOrderById(idOrder);
		
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
	
	public boolean changeStatus(long idOrder, String status) throws SQLException{
		
		orderdao = new OrderDao();
		
		if(checkStatus(idOrder,status)==false)
			return false;
		
		Order order = orderdao.retrieveOrderById(idOrder);
		order.setStato(status);
		
		return orderdao.doUpdateOrder(order);
		
		
	}
	
	
}
