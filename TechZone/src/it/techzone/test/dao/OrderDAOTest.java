package it.techzone.test.dao;

import static org.junit.Assert.assertNotEquals;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import it.techzone.model.beans.Order;
import it.techzone.model.beans.ProductOrder;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.dao.OrderDao;
import it.techzone.model.dao.ProductDAO;
import it.techzone.model.dao.UserDAO;
import junit.framework.TestCase;

public class OrderDAOTest extends TestCase{
	
	private static PrintWriter pw;
	private static OrderDao od;
	private static UserDAO ud;
	private static ProductDAO pd;
	
	protected void setUp() throws Exception{
        od = new OrderDao();
        ud=new UserDAO();
        pd=new ProductDAO();
    }
	
	public void testDoSaveOrder() throws SQLException{
		Order oracolo=null, salvato=null;
		boolean flag=false;
		try {
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
			
			od.doSaveOrder(oracolo);
			//FATTO TRE VOLTE PER PROBLEMA SYNC DB
			salvato= od.retrieveOrderById(3);
			
			assertEquals(oracolo.getStato(),salvato.getStato());
			assertEquals(oracolo.getNumeroOrdine(),salvato.getNumeroOrdine());
			//assertEquals(o.getDataInvio(),salvato.getDataInvio());
			assertEquals(oracolo.getTotale(),salvato.getTotale());
			assertEquals(oracolo.getUtente().getId(),salvato.getUtente().getId());
			
			assertEquals(salvato.getProdotti().size(),1);
		
			ProductOrder a, oracoloProd;
			a=salvato.getProdotti().get(0);
			oracoloProd=oracolo.getProdotti().get(0);
			assertEquals(a.getCosto(),oracoloProd.getCosto());
			assertEquals(a.getQuantita(),oracoloProd.getQuantita());
			assertEquals(a.getProdotto().getCodice(),oracoloProd.getProdotto().getCodice());
		
			flag=true;
		}catch(Exception e) {
			e.printStackTrace();
			fail("testdoSaveOrder() not passed!");
		}finally {
            //DOVE LI VOGLIAMO SALVARE???
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
	}
	
	public void testDoSaveOrderException() throws SQLException{
		try {
			Order o=null;
			od.doSaveOrder(o);
			fail("testDoSaveOrderException() (null Order) not passed");
		}catch(Exception e) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
	
	public void testRetrieveOrderById() throws SQLException{
		long id=1L;
		Order oracolo=new Order();
		boolean flag=false;
		try {
			oracolo.setNumeroOrdine(id);
			oracolo.setDataArrivo(null);
			oracolo.setDataInvio(new Timestamp(1613086483000L));
			oracolo.setStato("In preparazione");
			oracolo.setTotale(398.99F);
			oracolo.setUtente(ud.doRetrieveByMail("g.simonini@gmail.com"));
			ArrayList<ProductOrder> prodottiOrdinati=new ArrayList<ProductOrder>();
			prodottiOrdinati.add(new ProductOrder(pd.retrieveProductById(1),398.99F,1));
			oracolo.setProdotti(prodottiOrdinati);
			Order retrieved=od.retrieveOrderById(1L);
			
			assertEquals(oracolo.getStato(),retrieved.getStato());
			
			assertEquals(oracolo.getNumeroOrdine(),retrieved.getNumeroOrdine());
			
			/*assertEquals(oracolo.getDataInvio(),retrieved.getDataInvio());
			System.out.println("Passato invio");
			*/
			assertEquals(oracolo.getTotale(),retrieved.getTotale());
			
			assertEquals(oracolo.getUtente().getId(),retrieved.getUtente().getId());
	
			assertEquals(retrieved.getProdotti().size(),1);
		
			ProductOrder a, oracoloProd;
			a=retrieved.getProdotti().get(0);
			oracoloProd=oracolo.getProdotti().get(0);
			assertEquals(a.getCosto(),oracoloProd.getCosto());
			assertEquals(a.getQuantita(),oracoloProd.getQuantita());
			assertEquals(a.getProdotto().getCodice(),oracoloProd.getProdotto().getCodice());
		
			flag=true;
		}catch(Exception e) {
			e.printStackTrace();
			fail("testRetrieveOrderById() not passed!");
		}finally {
            //DOVE LI VOGLIAMO SALVARE???
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
		
	}
	
	public void testRetrieveOrderByIdException() throws SQLException{
		try {
			long id=-1;
			Order o=od.retrieveOrderById(id);
			assertNotEquals(o,null);
			fail("testRetrieveOrderByIdException() (wrond Id) not passed");
		}catch(Throwable t) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}

	public void testRetrieveOrdersByMail() throws SQLException{
		boolean flag=false;
		String email="g.simonini@gmail.com";
		ArrayList<Order> oracolo=new ArrayList<Order>();
		Order o1=od.retrieveOrderById(1L);
		Order o2=od.retrieveOrderById(2L);
		oracolo.add(o1);
		oracolo.add(o2);
		try {
			ArrayList<Order> retrieved=od.retrieveOrdersByMail(email);
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
	
	public void testRetrieveOrderByMailException() throws SQLException{
		ArrayList<Order> retrieved;
		try {
			String mail=null;
			retrieved=od.retrieveOrdersByMail(mail);
			fail("testRetrieveOrderByMailException() (null mail) not passed");
		}catch(Exception e) {
			
		}
		
		try {
			String mail="Mailnonpresente@test.com";
			retrieved=od.retrieveOrdersByMail(mail);
			boolean result=retrieved.size()>0;
			assertEquals(result,true);
			fail("testRetrieveOrderByMailException() (mail without orders) not passed");
		}catch(Throwable t) {
			
		}
		
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
	public void testDoUpdateOrder() throws SQLException{
		boolean flag=false;
		long id=2l;
		Order oracolo=od.retrieveOrderById(id);
		oracolo.setStato("Spedito");
		try {
			od.doUpdateOrder(oracolo);
			//FATTO 3 volte per problema sync db
			Order modificato=od.retrieveOrderById(2l);
			
				assertEquals(oracolo.getStato(),modificato.getStato());
				assertEquals(oracolo.getNumeroOrdine(),modificato.getNumeroOrdine());
				assertEquals(oracolo.getDataInvio(),modificato.getDataInvio());
				assertEquals(oracolo.getTotale(),modificato.getTotale());
				assertEquals(oracolo.getUtente().getId(),modificato.getUtente().getId());
				
				assertEquals(modificato.getProdotti().size(),1);
			
				ProductOrder a, oracoloProd;
				a=modificato.getProdotti().get(0);
				oracoloProd=oracolo.getProdotti().get(0);
				assertEquals(a.getCosto(),oracoloProd.getCosto());
				assertEquals(a.getQuantita(),oracoloProd.getQuantita());
				assertEquals(a.getProdotto().getCodice(),oracoloProd.getProdotto().getCodice());
			
				flag=true;
		}catch(Exception e) {
			fail("testDoUpdateOrder() not passed!");
		}finally {
            //DOVE LI VOGLIAMO SALVARE???
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
	}
	
	public void testDoUpdateOrderException() throws SQLException{
		Order daAggiornare;
		Boolean result;
		try {
			daAggiornare=null;
			result=od.doUpdateOrder(daAggiornare);
			fail("testDoUpdateOrderException() (null order) not passed");
		}catch(Exception e) {
			
		}
		
		try {
			daAggiornare=od.retrieveOrderById(1L);
			daAggiornare.setNumeroOrdine(-1L);
			result=od.doUpdateOrder(daAggiornare);
			fail("testDoUpdateOrderException() (no Order with wrong Id) not passed");
		}catch(Exception e) {
			
		}
		
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
}
