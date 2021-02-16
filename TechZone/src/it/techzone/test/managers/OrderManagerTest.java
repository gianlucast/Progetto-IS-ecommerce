package it.techzone.test.managers;
import static org.junit.Assert.assertNotEquals;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import it.techzone.model.beans.Cart;
import it.techzone.model.beans.Manager;
import it.techzone.model.beans.Order;
import it.techzone.model.beans.Product;
import it.techzone.model.beans.ProductCart;
import it.techzone.model.beans.ProductOrder;
import it.techzone.model.beans.User;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.dao.OrderDao;
import it.techzone.model.dao.ProductDAO;
import it.techzone.model.dao.UserDAO;
import it.techzone.model.managers.OrderManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert.*;

public class OrderManagerTest extends TestCase {
	
	private static OrderManager om;
	private static ProductDAO pd;
	private static UserDAO ud;
	private static OrderDao od;
	public void placeOrderTest() throws SQLException{
		boolean flag=false;
		try {		
				ArrayList<Product> products = new ArrayList<Product>();
				products.add(pd.retrieveProductById(1));
				products.add(pd.retrieveProductById(2));
				products.add(pd.retrieveProductById(3));
				ProductCart a = new ProductCart(1, products.get(0));
				ProductCart b = new ProductCart(1, products.get(1));
				ProductCart c = new ProductCart(1, products.get(2));
				ProductOrder e = new ProductOrder (a);
				ProductOrder f = new ProductOrder (b);
				ProductOrder g = new ProductOrder (c);
				ArrayList<ProductCart> productsC = new ArrayList<ProductCart>();
				productsC.add(a);
				productsC.add(b);
				productsC.add(c);
				ArrayList<ProductOrder> productsO = new ArrayList<ProductOrder>();
				productsO.add(e);
				productsO.add(f);
				productsO.add(g);
		
				Cart carrello = new Cart(productsC,78);
				UtenteRegistrato utente = ud.doRetrieveByMail("m.massara@gmail.com");
		
		
				boolean result = om.placeOrder(utente, carrello);
				assertTrue(result);
				ArrayList<Order> ordine = od.retrieveOrdersByMail("m.massara@gmail.com");
				Order d = ordine.get(0);
				assertEquals(d.getProdotti(), productsC);
				assertEquals(d.getStato(),"in preparazione");
				assertEquals(d.getTotale(),78);
				assertEquals(d.getUtente().getId(),utente.getId());
				//anche order id e data?
		
				flag =true;
			}
		catch(Exception e){
			if(flag==false) {
				fail("placeOrderTest() not passed!");
			}
		
		
		}
		finally {
			if(flag==true) {
				System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
			}
		}
	}
	/*
	public void placeOrderTestException() {
		
		try
	}
	*/

	public void searchOrdersTest() {
	boolean flag=false;
	Order oracolo =null;
	
		try{
			oracolo=new Order();
			oracolo.setNumeroOrdine(3L);
			oracolo.setDataArrivo(null);
			oracolo.setDataInvio(new Timestamp(1613086483317L));
			oracolo.setStato("In preparazione");
			oracolo.setTotale(398.99F);
			oracolo.setUtente(ud.doRetrieveByMail("g.simonini@gmail.com"));
			ArrayList<ProductOrder> prodottiOrdinati=new ArrayList<ProductOrder>();
			prodottiOrdinati.add(new ProductOrder(pd.retrieveProductById(1),398.99F,1));
			oracolo.setProdotti(prodottiOrdinati);
			ArrayList<Order> b = od.retrieveOrdersByMail("g.simonini@gmail.com"); //ha fatto un solo ordine
			Order c = b.get(0);
			
			assertEquals(c.getProdotti(), oracolo.getProdotti());
			assertEquals(c.getStato(),"in preparazione");
			assertEquals(c.getTotale(),oracolo.getTotale());
			assertEquals(c.getUtente().getId(),oracolo.getUtente().getId());
			assertEquals(c.getNumeroOrdine(),oracolo.getNumeroOrdine());
			
			flag=true;
			
		}
		catch(Exception e) {
			if(flag==false) {
			fail("searchOrderTest() not passed!");
			}
		}
		finally {
			if(flag==true) {
				System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
			}
		}
	}
	public void searchOrdersTestException() {
		try {
			boolean res = false;
			ArrayList<Order> a = od.retrieveOrdersByMail("pimco@gmail.com");
			if(a==null) {
				res = true;
			}
			assertEquals(false,res);
			fail("searchOrderTestException() not passed (email not in database)");
		}
		
		catch(Exception e) {
			System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
	}
	
public void getOrderById() {
		
		
		boolean flag=false;
		Order oracolo =null;
		
			try{
				oracolo=new Order();
				oracolo.setNumeroOrdine(3L);
				oracolo.setDataArrivo(null);
				oracolo.setDataInvio(new Timestamp(1613086483317L));
				oracolo.setStato("In preparazione");
				oracolo.setTotale(398.99F);
				oracolo.setUtente(ud.doRetrieveByMail("g.simonini@gmail.com"));
				ArrayList<ProductOrder> prodottiOrdinati=new ArrayList<ProductOrder>();
				prodottiOrdinati.add(new ProductOrder(pd.retrieveProductById(1),398.99F,1));
				oracolo.setProdotti(prodottiOrdinati);
				Order c = od.retrieveOrderById(3L); 
				
				
				assertEquals(c.getProdotti(), oracolo.getProdotti());
				assertEquals(c.getStato(),"in preparazione");
				assertEquals(c.getTotale(),oracolo.getTotale());
				assertEquals(c.getUtente().getId(),oracolo.getUtente().getId());
				assertEquals(c.getNumeroOrdine(),oracolo.getNumeroOrdine());
				
				flag=true;
				
			}
			catch(Exception e) {
				if(flag==false) {
				fail("getOrderById() not passed!");
				}
			}
			finally {
				if(flag==true) {
					System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
				}
			}
		}

public void getOrderByIdTestException() {
	try {
		boolean res = false;
		ArrayList<Order> a = od.retrieveOrdersByMail("-213");
		if(a==null) {
			res = true;
		}
		assertEquals(false,res);
		fail("searchOrderTestException() not passed (id not in database)");
	}
	
	catch(Exception e) {
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
}
public void checkStatusTest() {
	boolean flag=false;
	Order oracolo1= od.retrieveOrderById(1);
	Order oracolo2=od.retrieveOrderById(2);
	Order oracolo3=od.retrieveOrderById(3);
	Order oracolo4=od.retrieveOrderById(4);
	Order oracolo5=od.retrieveOrderById(5);
	Order oracolo6=od.retrieveOrderById(6);
	//ogni ordine ha uno stato differente per controllare i diversi casi di test
	try {
			boolean res;
			res=om.checkStatus(1, "Spedito");
			assertTrue(res);
			res=om.checkStatus(2, "In consegna");
			assertTrue(res);
			res=om.checkStatus(2, "Spedizione in ritardo");
			assertTrue(res);
			res=om.checkStatus(3, "Spedizione in ritardo");
			assertTrue(res);
			res=om.checkStatus(3, "Consegnato");
			assertTrue(res);
			res=om.checkStatus(4, "Spedito");
			assertTrue(res);
			res=om.checkStatus(4, "Pacco Smarrito");
			assertTrue(res);
			res=om.checkStatus(5, "");
			assertFalse(res);
			res=om.checkStatus(6, "");
			assertFalse(res);
			
			flag=true;
			
	
	}
	catch(Exception e) {
		fail("checkStatusTest() not passed!");
	}
	finally{
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
}

}
