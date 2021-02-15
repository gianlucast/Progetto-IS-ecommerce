package it.techzone.test.dao;


import it.techzone.model.beans.Order;
import it.techzone.model.beans.Product;

import static org.junit.Assert.assertNotEquals;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import it.techzone.model.dao.ProductDAO;
import junit.framework.TestCase;





public class ProductDAOTest extends TestCase {
	private static ProductDAO pd;
	private static PrintWriter pw;
	
	public void setUp () throws Exception{
		pd = new ProductDAO();
	}
	//aggiunta no 
	public void testRetrieveProductById() throws SQLException {
		boolean flag=false;
		Product oracolo = null, pi = null;
		try {
				
				oracolo=new Product();
				oracolo.setCodice(1);
				oracolo.setDescrizione("OnePlus Nord supporta il 5G. E' un prodotto della fascia media, ottimo per prestazioni e OS.");
				oracolo.setNomeProd("OnePlus Nord 5G");
				oracolo.setQuantita(15);
				oracolo.setCategoria("Smartphone");
				oracolo.setTipo("Android");
				oracolo.setCosto(398.99F);
				pi=pd.retrieveProductById(1);
				assertEquals(pi.getCodice(), oracolo.getCodice());
				assertEquals(pi.getDescrizione(), oracolo.getDescrizione());
				assertEquals(pi.getNomeProd(), oracolo.getNomeProd());
				assertEquals(pi.getQuantita(), oracolo.getQuantita());
				assertEquals(pi.getCategoria(), oracolo.getCategoria());
				assertEquals(pi.getTipo(), oracolo.getTipo());
				assertEquals(pi.getCosto(), oracolo.getCosto());
				
				flag=true;
		}
			
		catch(Exception e) {
			fail("testRetrieveProductbyId() not passed!");
				
		}finally {
				if(flag==true) {
					System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
				}
		}
	}
	

	public void testRetrieveProductByIdException() throws SQLException {
		boolean flag=false;
		Product pi = null;
		try {
				pi=pd.retrieveProductById(-12312);
				assertNotEquals(String.valueOf(pi),String.valueOf(null));
				fail("testRetrieveProductbyIdException() not passed!(Id not found!)");
				
		}
		catch(Exception e) {

			System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
	
	}

	

	public void  testDoDeleteProduct() throws SQLException {
		

		boolean flag = false;

		Product oracolo = null, pi = null;
		boolean result;
		try {
			result=pd.doDeleteProduct(2);
			assertTrue(result);
			flag = true;
		}catch(Exception e){
			fail("testDoDeletProduct() not passed!");
		}
		finally {
			if(flag)System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
			
	}
		

	public void testDoDeleteProductException() throws SQLException {
		
		try {
				boolean result=pd.doDeleteProduct(-12323);
				assertTrue(result);
				fail("testRetrieveProductbyIdException() not passed!(Id not found)");
				
		}
		
		catch(Throwable t) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}

	public void testdoRetrieveAll() throws SQLException {
		
		ArrayList<Product> oracolo = new ArrayList<Product>();	
		ArrayList<Product> retrieved;
		oracolo.add(pd.retrieveProductById(1));
		oracolo.add(pd.retrieveProductById(2));
		oracolo.add(pd.retrieveProductById(3));
		boolean flag = false;
		try {
				retrieved= pd.doRetrieveAll("");
				Product retrProd, oracleProd;
				for(int i=0;i<retrieved.size();i++) {
					retrProd=retrieved.get(i);
					oracleProd=oracolo.get(i);
					assertEquals(retrProd.getCodice(), oracleProd.getCodice());
					assertEquals(retrProd.getDescrizione(), oracleProd.getDescrizione());
					assertEquals(retrProd.getNomeProd(), oracleProd.getNomeProd());
					assertEquals(retrProd.getQuantita(), oracleProd.getQuantita());
					assertEquals(retrProd.getCategoria(), oracleProd.getCategoria());
					assertEquals(retrProd.getTipo(), oracleProd.getTipo());
					assertEquals(retrProd.getCosto(), oracleProd.getCosto());
					
				}
				flag=true;
			}
			
		catch(Exception e) {
			if(flag==false) {
				fail("testDoRetrieveAll() not passed!");
			}
		}
		finally {
			if(flag==true) {
				System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
			}
		}
	}
	
	
	public void testDoRetrieveByCat() throws SQLException   {
		ArrayList<Product> oracolo = new ArrayList<Product>();	
		ArrayList<Product> retrieved;
		Product articolo=pd.retrieveProductById(1);
		oracolo.add(articolo);
		boolean flag = false;
		try {
				retrieved= pd.doRetrieveByCat("Smartphone");
				Product retrProd, oracleProd;
				for(int i=0;i<retrieved.size();i++) {
					retrProd=retrieved.get(i);
					oracleProd=oracolo.get(i);
					assertEquals(retrProd.getCodice(), oracleProd.getCodice());
					assertEquals(retrProd.getDescrizione(), oracleProd.getDescrizione());
					assertEquals(retrProd.getNomeProd(), oracleProd.getNomeProd());
					assertEquals(retrProd.getQuantita(), oracleProd.getQuantita());
					assertEquals(retrProd.getCategoria(), oracleProd.getCategoria());
					assertEquals(retrProd.getTipo(), oracleProd.getTipo());
					assertEquals(retrProd.getCosto(), oracleProd.getCosto());
					
				}
				flag=true;
			}
			
		catch(Exception e) {
			if(flag==false) {
				fail("testDoRetrieveByCat() not passed!");
			}
		}
		finally {
			if(flag==true) {
				System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
			}
		}
	}

	public void testDoRetrieveByCatException() throws SQLException {
		ArrayList<Product> retrieved = new ArrayList<Product>();	
		try {
				retrieved=pd.doRetrieveByCat(null);
				fail("testRetrieveProductbyCatException() not passed!(irregular format!)");
				
		}
		catch(Exception e) {
			
		}
		
		try {
				retrieved=pd.doRetrieveByCat("alimentari");
				boolean res=retrieved.size()>0;
				assertTrue(res);
				fail("testRetrieveProductbyCatException() not passed!(category doesn't exist)");
		}
		catch(Throwable t) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
	public void testDoRetrieveByName() throws SQLException{
		ArrayList<Product> oracolo = new ArrayList<Product>();	
		ArrayList<Product> retrieved;
		oracolo.add(pd.retrieveProductById(1));
		
		
		boolean flag = false;
		try {	
				retrieved= pd.doRetrieveByName("OnePlus Nord");
					
				Product retrProd, oracleProd;
				for(int i=0;i<retrieved.size();i++) {
					retrProd=retrieved.get(i);
					oracleProd=oracolo.get(i);
					assertEquals(retrProd.getCodice(), oracleProd.getCodice());
					assertEquals(retrProd.getDescrizione(), oracleProd.getDescrizione());
					assertEquals(retrProd.getNomeProd(), oracleProd.getNomeProd());
					assertEquals(retrProd.getQuantita(), oracleProd.getQuantita());
					assertEquals(retrProd.getCategoria(), oracleProd.getCategoria());
					assertEquals(retrProd.getTipo(), oracleProd.getTipo());
					assertEquals(retrProd.getCosto(), oracleProd.getCosto());
					
				}
				flag=true;
			
		}catch(Exception e) {
			if(flag==false) {
				fail("testDoRetrieveByName() not passed!");
			}
		}
		finally {
			if(flag==true) {
				System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
			}
		}
	}

	public void testDoRetrieveByNameException() throws SQLException {
		ArrayList<Product> retrieved = new ArrayList<Product>();	
		try {
				retrieved=pd.doRetrieveByName(null);
				fail("testRetrieveProductbyNameException() not passed!(irregular format!)");
				
		}
		catch(Exception e) {
			
		}
		
		try {
				retrieved=pd.doRetrieveByName("TestNonPresente");
				boolean res=retrieved.size()>0;
				assertTrue(res);
				fail("testRetrieveProductbyCatName() not passed!(category doesn't exist)");
		}
		catch(Throwable t) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}

	
	public void testDoUpdate() throws SQLException{
		boolean flag=false;
		Product oracolo = null, pi = null;
		boolean risultato;
		try {
			
			oracolo=pd.retrieveProductById(3);
			oracolo.setQuantita(9);
			
			risultato=pd.doUpdate(oracolo);
			assertEquals(risultato,true);
			pi=pd.retrieveProductById(3);
			assertEquals(pi.getCodice(), oracolo.getCodice());
			assertEquals(pi.getCosto(), oracolo.getCosto());
			assertEquals(pi.getTipo(), oracolo.getTipo());
			assertEquals(pi.getImmagine(),oracolo.getImmagine());
			assertEquals(pi.getDescrizione(),oracolo.getDescrizione());
			assertEquals(pi.getQuantita(),oracolo.getQuantita());
			assertEquals(pi.getNomeProd(),oracolo.getNomeProd());
			assertEquals(pi.getCategoria(),oracolo.getCategoria());
			flag=true;
		}
			
		catch(Exception e) {
				if(flag==false) {
					fail("testDoUpdate() not passed!");
				}
			}
		finally {
				if(flag==true) {
					System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
				}
				
		}
	}



	public void testDoUpdateException() throws SQLException{
		Product aggiornamento;
		boolean risultato;
		try {
			aggiornamento=null;
			risultato=pd.doUpdate(aggiornamento);
			assertNotEquals(String.valueOf(risultato),String.valueOf(0));
			fail("testDoUpdateOrderException() (product not valid!) not passed");
		}catch(Exception e) {
			
		}
		
		try {
			byte[] images = {(byte)0xe0, 0x4f, (byte)0xd0};
			aggiornamento= new Product(-10213,490, images,"console next gen ","ps4",10,"videogiochi","console");
			risultato=pd.doUpdate(aggiornamento);
			assertNotEquals(String.valueOf(risultato),true);
			fail("testDoUpdateOrderException() (Product's id isn't valid) not passed");
		}catch(Throwable t) {
			
		}
		
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}

}
