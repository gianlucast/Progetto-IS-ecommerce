package it.techzone.test.managers;
import static org.junit.Assert.assertNotEquals;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import it.techzone.model.beans.Manager;
import it.techzone.model.beans.Product;
import it.techzone.model.beans.UtenteRegistrato;

import it.techzone.model.managers.ProductManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert.*;

public class ProductManagerTest extends TestCase{
private static ProductManager pm;
	
	
	protected void setUp() throws Exception{
        pm = new ProductManager();
    }
	
	public void testRetrieveProduct() {
		Product oracolo=new Product(2,1285.99F,null,"Frigorifero Samsung smart, tecnologico e stupefacente.","Samsung FrigoT21",12,"Elettrodomestici","Frigo");
		Product pi;
		boolean flag=false;
		try {
			pi=pm.retrieveProduct(2);
			assertEquals(pi.getCodice(), oracolo.getCodice());
			assertEquals(pi.getDescrizione(), oracolo.getDescrizione());
			assertEquals(pi.getNomeProd(), oracolo.getNomeProd());
			assertEquals(pi.getQuantita(), oracolo.getQuantita());
			assertEquals(pi.getCategoria(), oracolo.getCategoria());
			assertEquals(pi.getTipo(), oracolo.getTipo());
			assertEquals(pi.getCosto(), oracolo.getCosto());
			
			flag=true;
		}catch(Exception e) {
			fail("testRetrieveProduct() not passed!");
				
		}finally {
				if(flag==true) {
					System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
				}
		}
	}
	
	public void testRetrieveProductException() throws SQLException {
		boolean flag=false;
		Product pi = null;
		boolean res;
		try {
			pi=pm.retrieveProduct(-12312);
			res=pi==null;
			assertEquals(false,res);
			fail("testRetrieveProductException() not passed!(Id not found!)");
				
		}
		catch(Throwable t) {

			System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
	
	}
	
	public void testSearchProductsByName() {
		ArrayList<Product> oracolo = new ArrayList<Product>();	
		ArrayList<Product> retrieved;
		
		
		
		boolean flag = false;
		try {
			oracolo.add(pm.retrieveProduct(1));
			retrieved= pm.searchProductsByName("OnePlus Nord");
				
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
				fail("testSearchProductsByName() not passed!");
			}
		}
		finally {
			if(flag==true) {
				System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
			}
		}
	}
	
	public void testSearchProductsByNameException() throws SQLException {
		ArrayList<Product> retrieved = new ArrayList<Product>();	
		boolean res;
		try {
			
			retrieved=pm.searchProductsByName(null);
			res=retrieved==null;
			assertEquals(false,res);
			fail("testsearchProductsByNameException() not passed!(null name)");
				
		}
		catch(Throwable t) {
			
		}
		try {
			
			retrieved=pm.searchProductsByName("");
			res=retrieved==null;
			assertEquals(false,res);
			fail("testsearchProductsByNameException() not passed!(empty name)");
				
		}
		catch(Throwable t) {
			
		}
		
		try {
			retrieved=pm.searchProductsByName("TestNonPresente");
			res=retrieved.size()>0;
			assertTrue(res);
			fail("testsearchProductsByNameException() not passed!(no match)");
		}
		catch(Throwable t) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}

	public void testSearchProductsByCat() {
		ArrayList<Product> oracolo = new ArrayList<Product>();	
		ArrayList<Product> retrieved;
		
		
		
		boolean flag = false;
		try {
			oracolo.add(pm.retrieveProduct(2));
			retrieved= pm.searchProductsByCat("Elettrodomestici");
				
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
	
	
	public void testSearchProductsByCatException() throws SQLException {
		ArrayList<Product> retrieved = new ArrayList<Product>();	
		boolean res;
		try {
			
			retrieved=pm.searchProductsByCat(null);
			res=retrieved==null;
			assertEquals(false,res);
			fail("testSearchProductsByCatException() not passed!(null cat)");
				
		}
		catch(Throwable t) {
			
		}
		try {
			
			retrieved=pm.searchProductsByCat("");
			res=retrieved==null;
			assertEquals(false,res);
			fail("testSearchProductsByCatException() not passed!(empty cat)");
				
		}
		catch(Throwable t) {
			
		}
		
		try {
			retrieved=pm.searchProductsByCat("TestNonPresente");
			res=retrieved.size()>0;
			assertTrue(res);
			fail("testSearchProductsByCatException() not passed!(no match)");
		}
		catch(Throwable t) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
	public void testDeleteProduct() {
		boolean flag = false;

		Product oracolo = null, pi = null;
		boolean result;
		try {
			result=pm.deleteProduct(2);
			assertTrue(result);
			flag = true;
		}catch(Exception e){
			fail("testDoDeletProduct() not passed!");
		}
		finally {
			if(flag)System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
	}
	
	public void testDeleteProductException() throws SQLException {
		
		try {
				boolean result=pm.deleteProduct(-12323);
				assertTrue(result);
				fail("testDeleteProductException() not passed!(Id not found)");
		}
		catch(Throwable t) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
	public void testUpdateProduct() {
		boolean flag=false;
		Product oracolo = null, pi = null;
		boolean risultato;
		try {
			
			oracolo=pm.retrieveProduct(3);
			oracolo.setQuantita(9);
			
			risultato=pm.updateProduct(oracolo.getCodice(),oracolo.getDescrizione(),oracolo.getNomeProd(),oracolo.getQuantita(),oracolo.getCategoria(),oracolo.getTipo(),oracolo.getCosto());
			assertEquals(risultato,true);
			pi=pm.retrieveProduct(3);
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
	
	
	public void testUpdateProductException() {
	
		Product p;
		boolean res;
		/*
		try {
			res=pm.updateProduct(3,"Televisore Sony 4k UHD 120Hz 1ms", "Sony DP2.1", 9,"Televisori e monitor","Televisore",1452.89f);
			assertEquals(true,res);
			fail("testUpdateProductException() (id not found) not passed!");
		}catch(Throwable t) {
			
		}
		
		*/
		try {
			res=pm.updateProduct(-1,"Televisore Sony 4k UHD 120Hz 1ms", "Sony DP2.1", 9,"Televisori e monitor","Televisore",1452.89f);
			assertEquals(true,res);
			fail("testUpdateProductException() (id not found) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			res=pm.updateProduct(3,"", "Sony DP2.1", 9,"Televisori e monitor","Televisore",1452.89f);
			assertEquals(true,res);
			fail("testUpdateProductException() (empty desc) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			res=pm.updateProduct(-1,null, "Sony DP2.1", 9,"Televisori e monitor","Televisore",1452.89f);
			assertEquals(true,res);
			fail("testUpdateProductException() (null desc) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			res=pm.updateProduct(-1,"Televisore Sony 4k UHD 120Hz 1ms", "", 9,"Televisori e monitor","Televisore",1452.89f);
			assertEquals(true,res);
			fail("testUpdateProductException() (empty name) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			res=pm.updateProduct(-1,"Televisore Sony 4k UHD 120Hz 1ms", null, 9,"Televisori e monitor","Televisore",1452.89f);
			assertEquals(true,res);
			fail("testUpdateProductException() (null name) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			res=pm.updateProduct(3,"Televisore Sony 4k UHD 120Hz 1ms", "Sony DP2.1", -1,"Televisori e monitor","Televisore",1452.89f);
			assertEquals(true,res);
			fail("testUpdateProductException() (negative quantity) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			res=pm.updateProduct(3,"Televisore Sony 4k UHD 120Hz 1ms", "Sony DP2.1", 9,"","Televisore",1452.89f);
			assertEquals(true,res);
			fail("testUpdateProductException() (empty categoria) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			res=pm.updateProduct(3,"Televisore Sony 4k UHD 120Hz 1ms", "Sony DP2.1", 9,null,"Televisore",1452.89f);
			assertEquals(true,res);
			fail("testUpdateProductException() (null categoria) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			res=pm.updateProduct(3,"Televisore Sony 4k UHD 120Hz 1ms", "Sony DP2.1", 9,"Televisori e monitor","",1452.89f);
			assertEquals(true,res);
			fail("testUpdateProductException() (empty tipo) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			res=pm.updateProduct(3,"Televisore Sony 4k UHD 120Hz 1ms", "Sony DP2.1", 9,"Televisori e monitor",null,1452.89f);
			assertEquals(true,res);
			fail("testUpdateProductException() (null tipo) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			res=pm.updateProduct(3,"Televisore Sony 4k UHD 120Hz 1ms", "Sony DP2.1", 9,"Televisori e monitor","Televisore",-12f);
			assertEquals(true,res);
			fail("testUpdateProductException() (negative prezzo) not passed!");
		}catch(Throwable t) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
	public void testGetAllProducts() throws SQLException {
		ArrayList<Product> oracolo = new ArrayList<Product>();	
		ArrayList<Product> retrieved;
		
		boolean flag = false;
		try {
			oracolo.add(pm.retrieveProduct(1));
			oracolo.add(pm.retrieveProduct(2));
			oracolo.add(pm.retrieveProduct(3));
			retrieved= pm.getAllProducts("");
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
			e.printStackTrace();
			if(flag==false) {
				fail("testGetAllProducts() not passed!");
			}
		}
		finally {
			if(flag==true) {
				System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
			}
		}
	}
	
	public void testGetAllProductsException() throws SQLException {
		
		ArrayList<Product> retrieved = new ArrayList<Product>();	
		boolean res;
		try {
			
			retrieved=pm.getAllProducts(null);
			res=retrieved==null;
			assertEquals(false,res);
			fail("testGetAllProductsException not passed!(null ord)");
				
		}
		catch(Throwable t) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
}
