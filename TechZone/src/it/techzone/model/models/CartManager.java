package it.techzone.model.models;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import it.techzone.model.beans.*;
import it.techzone.model.dao.*;

public class CartManager {
	private ProductDAO productdao;
	
	public boolean cartExists(HttpSession session) {
		if(session.getAttribute("cart")==null)return false;
		return true;
	}
	
	public Cart retrieveCart(HttpSession session) {
		if(cartExists(session)==false)return null;
		else return (Cart)session.getAttribute("cart");
	}
	
	public Cart newCart(HttpSession session) {
		Cart c=new Cart();
		session.setAttribute("cart", c);
		return c;
	}
	
	public boolean checkQuantity(long idProd, int quantita, HttpSession session) throws SQLException {
		productdao=new ProductDAO();
		Product p=productdao.retrieveProductById(idProd);
		if(session.getAttribute("cart")==null) newCart(session);
		Cart c=(Cart)session.getAttribute("cart");
		int pos=c.isInCart(p);
		int quantitadesiderata=quantita;
		if(pos>0) quantitadesiderata+=c.getProductList().get(pos).getQuantita();
		
		if(quantitadesiderata>p.getQuantita()) return false;
		return true;
	}
	
	public boolean modCart(long idProd, int quantita, HttpSession sessione) throws SQLException {
		if(!checkQuantity(idProd,quantita,sessione)) return false;
		Cart c=(Cart)sessione.getAttribute("cart");
		productdao=new ProductDAO();
		Product p=productdao.retrieveProductById(idProd);
		c.addToCart(p, quantita);
		return true;
	}
	public boolean changeQuantityCart(long idProd, int quantita, HttpSession sessione) throws SQLException {
		if(!checkQuantity(idProd,quantita,sessione)) return false;
		Cart c=(Cart)sessione.getAttribute("cart");
		productdao=new ProductDAO();
		Product p=productdao.retrieveProductById(idProd);
		c.setQuantityInCart(p, quantita);
		return true;
	}
}
