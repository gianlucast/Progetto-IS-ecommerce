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
	
	public void setUp() {
		om=new OrderManager();
		pd=new ProductDAO();
		ud=new UserDAO();
		od=new OrderDao();
	}
	
	public void testPlaceOrder() throws SQLException{
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
	
			Cart carrello = new Cart();
			carrello.setProductList(productsC);
			UtenteRegistrato utente = ud.doRetrieveByMail("g.simonini@gmail.com");
	
			boolean result = om.placeOrder(utente, carrello);
			assertTrue(result);
			Order d = od.retrieveOrderById(3);
			ProductOrder prodottoOrdineSalvato;
			ProductOrder prodottoOrdineOracolo;
			for(int i=0;i<d.getProdotti().size();i++) {
				prodottoOrdineSalvato=d.getProdotti().get(i);
				prodottoOrdineOracolo=productsO.get(i);
				assertEquals(prodottoOrdineSalvato.getProdotto().getCodice(),prodottoOrdineOracolo.getProdotto().getCodice());
				assertEquals(prodottoOrdineSalvato.getCosto(),prodottoOrdineOracolo.getCosto());
				assertEquals(prodottoOrdineSalvato.getQuantita(),prodottoOrdineOracolo.getQuantita());
			}
			assertEquals(d.getStato(),"In preparazione");
			assertEquals(d.getTotale(),carrello.getPrezzoTotale());
			assertEquals(d.getUtente().getId(),utente.getId());
			assertEquals(d.getNumeroOrdine(),3L);
			
			flag =true;
		}
		catch(Exception e){
			e.printStackTrace();
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
	
	public void placeOrderTestException() {
		
		try {
			Product a=pd.retrieveProductById(1);
			ProductCart b = new ProductCart(1, a);
			ArrayList<ProductCart> productList=new ArrayList<ProductCart>();
			productList.add(b);
			Cart c=new Cart();
			c.setProductList(productList);
			boolean res=om.placeOrder(null, c);
			assertEquals(res,true);
			fail("searchOrderTestException() not passed (null user)");
		}catch(Throwable t) {
			
		}
		try {
			UtenteRegistrato u=ud.doRetrieveById(1);
			boolean res=om.placeOrder(u, new Cart());
			assertEquals(res,true);
			fail("searchOrderTestException() not passed (empty cart)");
		}catch(Throwable t) {
			
		}
		try {
			UtenteRegistrato u=ud.doRetrieveById(1);
			boolean res=om.placeOrder(u, null);
			assertEquals(res,true);
			fail("searchOrderTestException() not passed (null cart)");
		}catch(Throwable t) {
			
		}
	}
	

	public void testSearchOrders() {
		boolean flag=false;
		String email="g.simonini@gmail.com";
		ArrayList<Order> oracolo=new ArrayList<Order>();
		
		try {
			Order o1=om.getOrderById(1L);
			Order o2=om.getOrderById(2L);
			oracolo.add(o1);
			oracolo.add(o2);
			ArrayList<Order> retrieved=om.searchOrders(email);
			Order ordineOracolo;
			Order ordineRicevuto;
			for(int i=0;i<=1;i++) {
				ordineOracolo=oracolo.get(i);
				ordineRicevuto=retrieved.get(i);
				assertEquals(ordineOracolo.getStato(),ordineRicevuto.getStato());
				assertEquals(ordineOracolo.getNumeroOrdine(),ordineRicevuto.getNumeroOrdine());
				assertEquals(ordineOracolo.getDataInvio(),ordineRicevuto.getDataInvio());
				assertEquals(ordineOracolo.getTotale(),ordineRicevuto.getTotale());
				assertEquals(ordineOracolo.getUtente().getId(),ordineRicevuto.getUtente().getId());
				assertEquals(ordineRicevuto.getProdotti().size(),1);
				ProductOrder a, oracoloProd;
				a=ordineRicevuto.getProdotti().get(0);
				oracoloProd=ordineOracolo.getProdotti().get(0);
				assertEquals(a.getCosto(),oracoloProd.getCosto());
				assertEquals(a.getQuantita(),oracoloProd.getQuantita());
				assertEquals(a.getProdotto().getCodice(),oracoloProd.getProdotto().getCodice());
			
			}
			flag=true;
		}catch(Exception e) {
			e.printStackTrace();
			fail("testRetrieveOrderById() not passed!");
		}finally {
	        //DOVE LI VOGLIAMO SALVARE???
	        if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	    }
	}
	
	
	public void testSearchOrdersException() {
		try {
			boolean res = false;
			ArrayList<Order> a = od.retrieveOrdersByMail("pimco@gmail.com");
			if(a==null) {
				res = true;
			}
			assertEquals(false,res);
			fail("searchOrderTestException() not passed (email not in database)");
		}catch(Throwable t) {
			
		}
		try {
			boolean res = false;
			ArrayList<Order> a = od.retrieveOrdersByMail(null);
			if(a==null) {
				res = true;
			}
			assertEquals(false,res);
			fail("searchOrderTestException() not passed (null email)");
		}
		catch(Throwable t) {
			
		}
		try {
			boolean res = false;
			ArrayList<Order> a = od.retrieveOrdersByMail("");
			if(a==null) {
				res = true;
			}
			assertEquals(false,res);
			fail("searchOrderTestException() not passed (empty email)");
		}
		catch(Throwable t) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}

	
	public void testGetOrderById() {	
		boolean flag=false;
		Order oracolo =null;
		
			try{
				oracolo=new Order();
				oracolo.setNumeroOrdine(1L);
				oracolo.setDataArrivo(null);
				oracolo.setDataInvio(new Timestamp(1613086483317L));
				oracolo.setStato("In preparazione");
				oracolo.setTotale(398.99F);
				oracolo.setUtente(ud.doRetrieveByMail("g.simonini@gmail.com"));
				ArrayList<ProductOrder> prodottiOrdinati=new ArrayList<ProductOrder>();
				prodottiOrdinati.add(new ProductOrder(pd.retrieveProductById(1),398.99F,1));
				oracolo.setProdotti(prodottiOrdinati);
				Order retrieved = od.retrieveOrderById(1L); 
				
				
				assertEquals(retrieved.getProdotti().size(),1);
				
				ProductOrder a, oracoloProd;
				a=retrieved.getProdotti().get(0);
				oracoloProd=oracolo.getProdotti().get(0);
				assertEquals(a.getCosto(),oracoloProd.getCosto());
				assertEquals(a.getQuantita(),oracoloProd.getQuantita());
				assertEquals(a.getProdotto().getCodice(),oracoloProd.getProdotto().getCodice());
				assertEquals(retrieved.getStato(),oracolo.getStato());
				assertEquals(retrieved.getTotale(),oracolo.getTotale());
				assertEquals(retrieved.getUtente().getId(),oracolo.getUtente().getId());
				assertEquals(retrieved.getNumeroOrdine(),oracolo.getNumeroOrdine());
				
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

	public void testGetOrderByIdException() {
		try {
			boolean res = false;
			ArrayList<Order> a = od.retrieveOrdersByMail("-213");
			if(a==null) {
				res = true;
			}
			assertEquals(false,res);
			fail("searchOrderTestException() not passed (id not in database)");
		}
		
		catch(Throwable t) {
			System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
	}
	
	
	public void testCheckStatus() {
		boolean flag=false;
		
		
		try {
			//troppe cose da mettere nel db, così dovrebbe bastare
				
			boolean res;
			res=om.checkStatus(1, "Spedito");
			assertTrue(res);
			res=om.checkStatus(1, "In consegna");
			assertFalse(res);
			res=om.checkStatus(1, "Spedizione in ritardo");
			assertFalse(res);
			res=om.checkStatus(1, "Spedizione in ritardo");
			assertFalse(res);
			res=om.checkStatus(1, "Consegnato");
			assertFalse(res);
			res=om.checkStatus(1, "Spedito");
			assertTrue(res);
			res=om.checkStatus(1, "Pacco Smarrito");
			assertFalse(res);
			res=om.checkStatus(1, "");
			assertFalse(res);
			res=om.checkStatus(1, "");
			assertFalse(res);
			flag=true;	
		
		}
		catch(Exception e) {
			fail("checkStatusTest() not passed!");
		}
		finally{
			if(flag)System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
	}

	public void testChangeStatus() {
		Order oracolo;
		boolean flag=false;
		try {
			boolean res;
			oracolo=new Order();
			oracolo.setNumeroOrdine(2L);
			oracolo.setDataArrivo(null);
			oracolo.setDataInvio(new Timestamp(1613086483317L));
			oracolo.setStato("Spedito"); //questo sarà lo stato dopo averlo cambiato
			oracolo.setTotale(398.99F);
			oracolo.setUtente(ud.doRetrieveByMail("g.simonini@gmail.com"));
			
			res=om.changeStatus(oracolo.getNumeroOrdine(), "Spedito");
			assertTrue(res);
			
			//Già so che quello che cambia il DAO è giusto, l'abbiamo già testato
			
		}
		catch(Exception e) {
			fail("changeStatusTest() not passed!");
			
		}
		finally {
			if(flag)System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
	}
	
	public void testChangeStatusException() {
		boolean res;
		try {
			res=om.changeStatus(2L, "butta il pacco da un ponte" );
			assertTrue(res);
			fail("testChangeStatusException (status not valid) failed");
		}catch(Throwable t) {
			
		}
		try {

			res=om.changeStatus(-1, "In consegna" );
			assertTrue(res);
			fail("testChangeStatusException (idOrder not found) failed");
		}catch(Throwable t) {
			
		}
		try {

			res=om.changeStatus(2L, "");
			assertTrue(res);
			fail("testChangeStatusException (empty status) failed");
		}catch(Throwable t) {
			
		}
		try {

			res=om.changeStatus(2L, null);
			assertTrue(res);
			fail("testChangeStatusException (null status) failed");
		}catch(Throwable t) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
	
}
