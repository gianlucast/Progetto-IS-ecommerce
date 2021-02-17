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
	HttpSession session;
	public void cartExistsTest() {
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
	public void retrieveCartTest() {
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
	public void newCartTest() {
		boolean flag=false;
		Cart a;
		Cart b;
		try {
				session = new MockHttpSession();
				a=cm.newCart(session);
				assertNotNull(a);
				b=cm.retrieveCart(session);
				for(int i=0;b.getProductList().size()<i;i++) { // in teoria non ci entra proprio
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
		

	public void checkQuantityTest() {
		boolean flag= false;
		int idProdottoTest=1; 
		int quantità = 2; //facciamo che di questo prodotto test ce ne sia disponibile solo 1 nel database
		try {
				session=new MockHttpSession();
				cm.newCart(session);
				assertFalse(cm.checkQuantity(idProdottoTest, quantità, session));
				((MockHttpSession)session).clearAttributes();
				assertFalse(cm.checkQuantity(idProdottoTest, quantità, session));//secondo test, per valutare se viene creato il carrello
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
	
	public void modCartTest() {
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
				assertEquals(a.getProductList().get(0), b.getProductList().get(0));
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
	public void changeQuantityCartTest() {
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
				cm.changeQuantityCart(idProdottoTest, 1, session);
				a=(Cart) session.getAttribute("cart");
				assertEquals(a.getProductList().get(0), b.getProductList().get(0));
				assertEquals(a.getPrezzoTotale(),b.getPrezzoTotale()); 
				((MockHttpSession)session).clearAttributes();
				session=null;
				//caso 2, nel carrello c'è già il prodotto e lo si vuole rimuovere
				a.addToCart(pm.retrieveProduct(idProdottoTest), 1);
				session.setAttribute("cart", a);
				cm.changeQuantityCart(idProdottoTest, 0, session); //quantità 0 rimuobe prodotto
				a=(Cart) session.getAttribute("cart");
				assertEquals(a.isInCart(pm.retrieveProduct(1)),-1); // isInCart ritornerà -1 dato che il prodotto è stato rimosso
				assertEquals(a.getPrezzoTotale(),0);
				((MockHttpSession)session).clearAttributes();
				session=null;
				//caso 3, nel carrello c'è già il prodotto ma si vuole aumentare la quantità più di quanto sia permesso
				a.addToCart(pm.retrieveProduct(idProdottoTest), 1);
				b.addToCart(pm.retrieveProduct(idProdottoTest), 1);
				session = new MockHttpSession();
				session.setAttribute("cart", a);
				cm.changeQuantityCart(idProdottoTest, 4, session);
				a=(Cart) session.getAttribute("cart");
				assertEquals(a.getProductList().get(0), b.getProductList().get(0)); //sono rimasti uguali
				assertEquals(a.getPrezzoTotale(),b.getPrezzoTotale()); 
				((MockHttpSession)session).clearAttributes();
				session=null;
				//caso 4, nel carrello il prodotto non c'è, quindi modificare la quantità non è possibile
				session = new MockHttpSession();
				session.setAttribute("cart", a);
				cm.changeQuantityCart(idProdottoTest, 1, session);
				a=(Cart) session.getAttribute("cart");
				assertEquals(a.isInCart(pm.retrieveProduct(1)),-1); 
				assertEquals(a.getPrezzoTotale(),0);
				
				
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
	

}

