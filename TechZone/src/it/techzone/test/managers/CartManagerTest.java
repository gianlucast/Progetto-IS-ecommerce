//DEVO ANCORA AGGIUNGERE ECCEZIONI
package it.techzone.test.managers;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.mock.web.MockHttpSession;

import it.techzone.model.beans.Cart;
import it.techzone.model.beans.Product;
import it.techzone.model.dao.ProductDAO;
import it.techzone.model.managers.CartManager;
import it.techzone.model.managers.ProductManager;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class CartManagerTest extends TestCase{
	private static CartManager cm = new CartManager();
	private static ProductManager pm= new ProductManager();
	private static ProductDAO pd = new ProductDAO();
	HttpSession session;
	public void testCartExists() {
		boolean flag=false;
		try {
				session = new MockHttpSession();
				cm.newCart(session);
				assertTrue(cm.cartExists(session));
				((MockHttpSession)session).clearAttributes();
				session=null;
				flag=true;
		}
		
		catch(Exception e){
			if(flag==false) {
				fail("CartExistsTest() not passed!");
			}
		}
		finally {
			System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
	  }
	
	
	public void testRetrieveCart() {
		boolean flag=false;
		Cart a = new Cart();
		Cart b;
		try {
				session = new MockHttpSession();
				session.setAttribute("cart", a);
				b= cm.retrieveCart(session);
				a = (Cart) session.getAttribute("cart");
				for(int i=0;b.getProductList().size()<i;i++) { // in teoria non ci entra proprio
					assertEquals(a.getProductList().get(i), b.getProductList().get(i));
					
				}
				((MockHttpSession)session).clearAttributes();
				session=null;
				
				//secondo test, verifica se ritorna null
				session = new MockHttpSession();
				assertNull(cm.retrieveCart(session));
				((MockHttpSession)session).clearAttributes();
				session=null;
				flag=true;
		}
		
		catch(Exception e){
			if(flag==false) {
				fail("retrieveCartTest() not passed!");
			}
		}
		finally {
			System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
	  }
	public void testNewCart() {
		boolean flag=false;
		Cart a;
		Cart b;
		try {
				session = new MockHttpSession();
				a=cm.newCart(session);
				assertNotNull(a);
				b=cm.retrieveCart(session);
				for(int i=0;b.getProductList().size()<i;i++) { 
					assertEquals(a.getProductList().get(i), b.getProductList().get(i));
					
				}
				((MockHttpSession)session).clearAttributes();
				session=null;
				
		}
		catch(Exception e){
			if(flag==false) {
				fail("newCartTest() not passed!");
			}
		}
		finally {
			System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
	  }
		

	public void testCheckQuantity() {
		boolean flag= false;
		int idProdottoTest=1; 
		try {
				session=new MockHttpSession();
				cm.newCart(session);
				assertFalse(cm.checkQuantity(idProdottoTest, 20, session));//troppi
				((MockHttpSession)session).clearAttributes();
				session= null;
				session=new MockHttpSession();
				cm.newCart(session);
				cm.modCart(idProdottoTest, 10, session);
				assertFalse(cm.checkQuantity(idProdottoTest, 40, session));//troppi
				((MockHttpSession)session).clearAttributes();
				session= null;
				session=new MockHttpSession();
				cm.newCart(session);
				assertTrue(cm.checkQuantity(idProdottoTest, 13, session));//ok
				((MockHttpSession)session).clearAttributes();
				session= null;
				session=new MockHttpSession();
				cm.newCart(session);
				cm.modCart(idProdottoTest, 10, session);
				assertTrue(cm.checkQuantity(idProdottoTest, 3, session));//ok
				((MockHttpSession)session).clearAttributes();
				session= null;
				session=new MockHttpSession();
				assertFalse(cm.checkQuantity(idProdottoTest, 18, session)); //per vedere se viene creato il carrello
				((MockHttpSession)session).clearAttributes();
				session=null;
				flag=true;
				
		}
		
		catch(Exception e) {
			if(flag==false) {
			fail("CartExistsTest() not passed!");
			}
		}
		finally {
			System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
	}
	
	public void testCheckQuantityException() {
		try {
			 session = new MockHttpSession();
			 cm.newCart(session);
			 assertFalse(cm.checkQuantity(-12, 0, session));
			 fail ("testCheckQuantityException() not passed!(id not in database)");
			 
		}
		catch (Exception e) {
			System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
		
	}
	public void testModCar() {
		boolean flag=false;
		int idProdottoTest=1;
		int quantità=2;
		Cart a = new Cart();
		Cart b;
		try {
				a.addToCart(pm.retrieveProduct(idProdottoTest), quantità);
				session = new MockHttpSession();
				cm.newCart(session);
				assertTrue(cm.modCart(idProdottoTest, quantità, session));
				b= (Cart) session.getAttribute("cart");
				for(int i=0;b.getProductList().size()<i;i++) { // in teoria non ci entra proprio
					assertEquals(a.getProductList().get(i), b.getProductList().get(i));
					
				}
				assertEquals(a.getPrezzoTotale(),b.getPrezzoTotale());
				((MockHttpSession)session).clearAttributes();
				session=null;
				flag=true;
		}
		catch(Exception e) {
			if(flag==false) {
			fail("modCartTest() not passed!");
			}
		}
		finally {
			System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
	}
	public void testChangeQuantityCart() {
		boolean flag=false;
		int idProdottoTest=1;
		Cart a = new Cart();
		Cart b = new Cart();
		try {	//caso 1, in partenza è già presente il prodotto nel carrello, la quantità
				//viene modificata nei limiti della disponibilità
				a.addToCart(pm.retrieveProduct(idProdottoTest), 1);
				b.addToCart(pm.retrieveProduct(idProdottoTest), 2);
				session = new MockHttpSession();
				session.setAttribute("cart", a);
				cm.changeQuantityCart(idProdottoTest, 2, session);
				
				a=(Cart) session.getAttribute("cart");
				assertEquals(a.getProductList().size(),b.getProductList().size());
				for(int i=0;b.getProductList().size()<i;i++) { 
					assertEquals(a.getProductList().get(i), b.getProductList().get(i));
					
				}
				assertEquals(a.getPrezzoTotale(),b.getPrezzoTotale(),0); 
				((MockHttpSession)session).clearAttributes();
				session=null;
				a=new Cart();
				b=new Cart();
				//caso 2, nel carrello c'è già il prodotto e lo si vuole rimuovere
				session = new MockHttpSession();
				a.addToCart(pm.retrieveProduct(idProdottoTest), 1);
				session.setAttribute("cart", a);
				cm.changeQuantityCart(idProdottoTest, 0, session); //quantità 0 rimuove prodotto
				a=(Cart) session.getAttribute("cart");
				assertEquals(a.isInCart(pm.retrieveProduct(1)),-1);// isInCart ritornerà -1 dato che il prodotto è stato rimosso
				assertEquals(a.getPrezzoTotale(),0,0);
				((MockHttpSession)session).clearAttributes();
				session=null;
				a=new Cart();
				//caso 3, nel carrello c'è già il prodotto ma si vuole aumentare la quantità più di quanto sia permesso
				a.addToCart(pm.retrieveProduct(idProdottoTest), 1);
				b.addToCart(pm.retrieveProduct(idProdottoTest), 1);
				session = new MockHttpSession();
				session.setAttribute("cart", a);
				cm.changeQuantityCart(idProdottoTest, 17, session);
				a=(Cart) session.getAttribute("cart");
				for(int i=0;b.getProductList().size()<i;i++) { 
					assertEquals(a.getProductList().get(i), b.getProductList().get(i));
					
				}//sono rimasti uguali
				assertEquals(a.getPrezzoTotale(),b.getPrezzoTotale(),0); 
				((MockHttpSession)session).clearAttributes();
				session=null;
				a=new Cart();
				//caso 4, nel carrello il prodotto non c'è, quindi modificare la quantità non è possibile
				session = new MockHttpSession();
				session.setAttribute("cart", a);
				cm.changeQuantityCart(idProdottoTest, 1, session);
				a=(Cart) session.getAttribute("cart");
				assertEquals(a.isInCart(pm.retrieveProduct(1)),-1); 
				assertEquals(a.getPrezzoTotale(),0,0);
				
				
				flag=true;
				
		}
		catch(Exception e) {
			if(flag==false) {
			fail("changeQuantityCartTest() not passed!");
			}
		}
		finally {
			System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
	}
	
	public void testChangeQuantityCartException() {
		try {
			 session = new MockHttpSession();
			 cm.newCart(session);
			 assertFalse(cm.changeQuantityCart(-12, 0, session));
			 fail ("testCheckQuantityException() not passed!(id not in database)");
			 
		}
		catch (Exception e) {
			System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
		
	}
}

