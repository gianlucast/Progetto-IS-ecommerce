package it.techzone.test.dao;


import it.techzone.model.beans.Order;
import it.techzone.model.beans.Product;

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
	
	public void setup () throws Exception{
		pd = new ProductDAO();
	}
	//aggiunta no 
	public void testRetrieveProductById() throws SQLException {
		boolean flag=false;
		Product oracolo = null, pi = null;
		try {
				
				
				oracolo=pd.retrieveProductById(10213);
				assertEquals(10213, oracolo.getCodice());
				
				flag=true;
		}
			
		catch(Exception e) {
				if(flag==false) {
					fail("testRetrieveProductbyId() not passed!");
				}
			}
		finally {
				if(flag==true) {
					pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
				}
		}
	}

	public void testRetrieveProductByIdException() throws SQLException {
		boolean flag=false;
		Product oracolo = null, pi = null;
		try {
				
				
				oracolo=pd.retrieveProductById(-12312);
				fail("testRetrieveProductbyIdException() not passed!(irregular id format!)");
				
		}
		catch(Exception e) {

			pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
	
	}

	

	public void  testDoDeleteProduct() throws SQLException {
		

		boolean flag = false;

		Product oracolo = null, pi = null;

		try {
				
				pd.doDeleteProduct(10213);
				pi=pd.retrieveProductById(10213);
				flag = true;
		} 
		
		catch(Exception e){
				if(flag==false) {
					pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
					
				
			}
		}
		finally {
			if(flag==true) {
				fail("testDoDeletProduct() not passed!");
			}
			}
		
		
	}

	public void testDoDeleteProductException() throws SQLException {
		
		try {
				pd.doDeleteProduct(-12323);
				fail("testRetrieveProductbyIdException() not passed!(irregular id format!)");
				
		}
		catch(Exception e) {
			
		}
		
		try {
				pd.doDeleteProduct(213123312);
				fail("testRetrieveProductbyIdException() not passed!(product doesn't exist!)");
		}
		catch(Exception e) {
			pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
		
	}

/*	public void doRetrieveAll() throws SQLException {
		
		boolean flag = false;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Product> products = new ArrayList<Product>();

		String selectSQL = "SELECT * FROM " + PRODUCT_TABLE;

		if (ordine != null && !ordine.equals("")) {
			selectSQL += " ORDER BY " + ordine;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
	
			while(rs.next()) {
				Product p=new Product();
				p.setCodice(rs.getLong("codiceProdotto"));
				p.setCategoria(rs.getString("CATEGORIA"));
				p.setTipo(rs.getString("TIPO"));
				p.setDescrizione(rs.getString("DESCRIZIONE"));
				p.setNomeProd(rs.getString("nomeProd"));
				p.setQuantita(rs.getInt("QUANTITA"));
				p.setCosto(rs.getFloat("COSTO"));
				p.setImmagine(rs.getBytes("IMMAGINE"));
				products.add(p);
			}
			return products;
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
??*/
	public void testDoRetrieveByCat()   {
		ArrayList<Product> oracolo = new ArrayList<Product>();		
		boolean flag = true;
		try {
				oracolo= pd.doRetrieveByCat("videogiochi");
				
				 for (Product c : oracolo) {		
						assertEquals("videogiochi",c.getCategoria());
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
				pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
			}
	}
}

	public void testDoRetrieveByCatException() throws SQLException {
		ArrayList<Product> oracolo = new ArrayList<Product>();	
		try {
				oracolo=pd.doRetrieveByCat("123123");
				fail("testRetrieveProductbyIdException() not passed!(irregular format!)");
				
		}
		catch(Exception e) {
			
		}
		
		try {
				oracolo=pd.doRetrieveByCat("alimentari");
				fail("testRetrieveProductbyIdException() not passed!(category doesn't exist)");
		}
		catch(Exception e) {
			pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		}
		
}
	
	public void testDoRetrieveByName()   {
		ArrayList<Product> oracolo = new ArrayList<Product>();
		
		
		boolean flag = true;
		try {	
				oracolo= pd.doRetrieveByName("ps4");
					
					 for (Product c : oracolo) {
						
							assertEquals("ps4",c.getNomeProd());
					 }
					
				flag=true;
			}
			
		catch(Exception e) {
			if(flag==false) {
				fail("testDoRetrieveByName() not passed!");
			}
		}
	finally {
			if(flag==true) {
				pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
			}
		}
}


	
	public void testDoUpdate() throws SQLException{
		boolean flag=false;
		Product oracolo = null, pi = null;
		try {
				
				byte[] images = {(byte)0xe0, 0x4f, (byte)0xd0};
				oracolo=new Product(10213,490, images,"console next gen ","ps4",10,"videogiochi","console");
				pd.doUpdate(oracolo);
				pi=pd.retrieveProductById(10213);
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
					pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
				}
				
		}
}



public void testDoUpdateException() throws SQLException{
	Product aggiornamento;
	
	try {
		aggiornamento=null;
		pd.doUpdate(aggiornamento);
		fail("testDoUpdateOrderException() (product not valid!) not passed");
	}catch(Exception e) {
		
	}
	
	try {
		byte[] images = {(byte)0xe0, 0x4f, (byte)0xd0};
		aggiornamento= new Product(-10213,490, images,"console next gen ","ps4",10,"videogiochi","console");
		fail("testDoUpdateOrderException() (Product's id isn't valid) not passed");
	}catch(Exception e) {
		
	}
	
	try {
		byte[] images = {(byte)0xe0, 0x4f, (byte)0xd0};
		aggiornamento= new Product(10213,490, images,"console next gen ","ps4",10,"pincopallo","console");
		fail("testDoUpdateOrderException() (Product's category isn't valid) not passed");
	}catch(Exception e) {
		
	}
	
	pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
}

}
