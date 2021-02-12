package it.techzone.test.dao;


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
	
	public void testRetrieveProductById() throws SQLException {
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
					fail("testRetrieveProductbyId() not passed!");
				}
			}
		finally {
				if(flag==true) {
					pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
				}
		}
	}

	public void  testDoDeleteProduct() throws SQLException {
		

		boolean flag = false;

		Product oracolo = null, pi = null;

		try {
				byte[] images = {(byte)0xe0, 0x4f, (byte)0xd0};
				oracolo=new Product(10213,490, images,"console next gen ","ps4",10,"videogiochi","console");
				pd.doUpdate(oracolo);
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

	public void doRetrieveAll() throws SQLException {
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

	public void testDoRetrieveByCat()   {
		ArrayList<Product> oracolo = new ArrayList<Product>();
		ArrayList<Product> pi = new ArrayList<Product>();
		
		boolean flag = true;
		try {	byte[] images = {(byte)0xe0, 0x4f, (byte)0xd0};
				oracolo.add(new Product(10213,490, images,"console next gen ","ps4",10,"pincopallo","console"));
				byte[] images1 ={(byte)0xe0, 0x4f, (byte)0xd0};
				oracolo.add(new Product(10451,300, images,"console past gen","ps3",15,"pincopallo","console"));
				byte[] images2 ={(byte)0xe0, 0x4f, (byte)0xd0};
				oracolo.add(new Product(10682,390, images,"console next gen","xbox one",15,"pincopallo", "console"));
				for(Product p : oracolo) {
					pd.doUpdate(p);
				}
				pi= pd.doRetrieveByCat("pincopallo");
					
				for(Product p : pi) {
					Product b = null;
					 for (Product c : oracolo) {
						if(p.getCodice()==c.getCodice()) {
							
							assertEquals(p.getCodice(),c.getCodice());
							assertEquals(p.getCosto(),c.getCosto());
							assertEquals(p.getTipo(),c.getTipo());
							assertEquals(p.getImmagine(),c.getImmagine());
							assertEquals(p.getDescrizione(),c.getDescrizione());
							assertEquals(p.getQuantita(),c.getQuantita());
							assertEquals(p.getNomeProd(),c.getNomeProd());
							assertEquals(p.getCategoria(),c.getCategoria());
						
						}	
					 }
					
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
	
	public void testDoRetrieveByName()   {
		ArrayList<Product> oracolo = new ArrayList<Product>();
		ArrayList<Product> pi = new ArrayList<Product>();
		
		boolean flag = true;
		try {	byte[] images = {(byte)0xe0, 0x4f, (byte)0xd0};
				oracolo.add(new Product(10213,490, images,"console next gen ","ps4",10,"videogiochi","console"));
				byte[] images1 ={(byte)0xe0, 0x4f, (byte)0xd0};
				oracolo.add(new Product(10451,300, images,"console past gen","ps4",15,"videogiochi","console"));
				byte[] images2 ={(byte)0xe0, 0x4f, (byte)0xd0};
				oracolo.add(new Product(10682,390, images,"console next gen","ps4",15,"videogiochi", "console"));
				for(Product p : oracolo) {
					pd.doUpdate(p);
				}
				pi= pd.doRetrieveByName("ps4");
					
				for(Product p : pi) {
					Product b = null;
					 for (Product c : oracolo) {
						if(p.getCodice()==c.getCodice()) {
							
							assertEquals(p.getCodice(),c.getCodice());
							assertEquals(p.getCosto(),c.getCosto());
							assertEquals(p.getTipo(),c.getTipo());
							assertEquals(p.getImmagine(),c.getImmagine());
							assertEquals(p.getDescrizione(),c.getDescrizione());
							assertEquals(p.getQuantita(),c.getQuantita());
							assertEquals(p.getNomeProd(),c.getNomeProd());
							assertEquals(p.getCategoria(),c.getCategoria());
						
						}	
					 }
					
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
}
