package it.techzone.test.dao;

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
    }
	
	public void testDoSaveOrder() throws SQLException{
		Order o=null, salvato=null;
		boolean flag=false;
		try {
			o=new Order();
			o.setNumeroOrdine(1L);
			o.setDataArrivo(null);
			o.setDataInvio(new Timestamp(1613086483317L));
			o.setStato("In preparazione");
			o.setTotale(55);
			o.setUtente(ud.doRetrieveByMail("g.simonini@gmail.com"));
			ArrayList<ProductOrder> prodottiOrdinati=new ArrayList<ProductOrder>();
			prodottiOrdinati.add(new ProductOrder(pd.retrieveProductById(1),55L,1));
			o.setProdotti(prodottiOrdinati);
			
			od.doSaveOrder(o);
			salvato= od.retrieveOrderById(1);
			assertEquals(o.getStato(),salvato.getStato());
			assertEquals(o.getNumeroOrdine(),salvato.getNumeroOrdine());
			assertEquals(o.getDataInvio(),salvato.getDataInvio());
			assertEquals(o.getTotale(),salvato.getTotale());
			assertEquals(o.getUtente(),salvato.getUtente());
			assertEquals(o.getProdotti(),salvato.getProdotti());
			flag=true;
		}catch(Exception e) {
			fail("testdoSaveOrder() not passed!");
		}finally {
            //DOVE LI VOGLIAMO SALVARE???
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
	}
	
	public void testDoSaveOrderException() throws SQLException{
		try {
			Order o=null;
			od.doSaveOrder(o);
			fail("testDoSaveOrderException() (null Order) not passed");
		}catch(Exception e) {
			
		}
		pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
	
	public void testRetrieveOrderById() throws SQLException{
		long id=1L;
		Order oracolo=new Order();
		boolean flag=false;
		try {
			oracolo.setNumeroOrdine(id);
			oracolo.setDataArrivo(null);
			oracolo.setDataInvio(new Timestamp(1613086483317L));
			oracolo.setStato("In preparazione");
			oracolo.setTotale(55);
			oracolo.setUtente(ud.doRetrieveByMail("g.simonini@gmail.com"));
			ArrayList<ProductOrder> prodottiOrdinati=new ArrayList<ProductOrder>();
			prodottiOrdinati.add(new ProductOrder(pd.retrieveProductById(1),55L,1));
			oracolo.setProdotti(prodottiOrdinati);
			Order retrieved=od.retrieveOrderById(1L);
			assertEquals(oracolo.getStato(),retrieved.getStato());
			assertEquals(oracolo.getNumeroOrdine(),retrieved.getNumeroOrdine());
			assertEquals(oracolo.getDataInvio(),retrieved.getDataInvio());
			assertEquals(oracolo.getTotale(),retrieved.getTotale());
			assertEquals(oracolo.getUtente(),retrieved.getUtente());
			assertEquals(oracolo.getProdotti(),retrieved.getProdotti());
			flag=true;
		}catch(Exception e) {
			fail("testRetrieveOrderById() not passed!");
		}finally {
            //DOVE LI VOGLIAMO SALVARE???
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
		
	}
	
	public void testRetrieveOrderByIdException() throws SQLException{
		try {
			long id=-1;
			od.retrieveOrderById(id);
			fail("testRetrieveOrderByIdException() (wrond Id) not passed");
		}catch(Exception e) {
			
		}
		pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
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
			for(int i=1;i<=2;i++) {
				ordineOracolo=oracolo.get(i);
				ordineRicevuto=retrieved.get(i);
				assertEquals(ordineOracolo.getStato(),ordineRicevuto.getStato());
				assertEquals(ordineOracolo.getNumeroOrdine(),ordineRicevuto.getNumeroOrdine());
				assertEquals(ordineOracolo.getDataInvio(),ordineRicevuto.getDataInvio());
				assertEquals(ordineOracolo.getTotale(),ordineRicevuto.getTotale());
				assertEquals(ordineOracolo.getUtente(),ordineRicevuto.getUtente());
				assertEquals(ordineOracolo.getProdotti(),ordineRicevuto .getProdotti());
				flag=true;
			}
		}catch(Exception e) {
			fail("testRetrieveOrderById() not passed!");
		}finally {
            //DOVE LI VOGLIAMO SALVARE???
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
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
			fail("testRetrieveOrderByMailException() (mail without orders) not passed");
		}catch(Exception e) {
			
		}
		
		pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
	public void testDoUpdateOrder() throws SQLException{
		boolean flag=false;
		long id=1l;
		Order oracolo=od.retrieveOrderById(id);
		oracolo.setStato("Spedito");
		try {
			od.doUpdateOrder(oracolo);
			Order modificato=od.retrieveOrderById(1l);
			
				assertEquals(oracolo.getStato(),modificato.getStato());
				assertEquals(oracolo.getNumeroOrdine(),modificato.getNumeroOrdine());
				assertEquals(oracolo.getDataInvio(),modificato.getDataInvio());
				assertEquals(oracolo.getTotale(),modificato.getTotale());
				assertEquals(oracolo.getUtente(),modificato.getUtente());
				assertEquals(oracolo.getProdotti(),modificato.getProdotti());
				flag=true;
		}catch(Exception e) {
			fail("testDoUpdateOrder() not passed!");
		}finally {
            //DOVE LI VOGLIAMO SALVARE???
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
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
		
		pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
}
