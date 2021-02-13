package it.techzone.test.dao;

import static org.junit.Assert.assertNotEquals;

import java.io.PrintWriter;
import java.sql.SQLException;

import it.techzone.model.beans.Manager;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.dao.UserDAO;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert.*;

public class UserDAOTest extends TestCase{
	
	private static PrintWriter pw;
	private static UserDAO ud;
	
	
	protected void setUp() throws Exception{
        ud = new UserDAO();
    }
	
	public static Test suite(PrintWriter writer){
        pw = writer;
        return new TestSuite(UserDAOTest.class);

    }
	
	public void testDoSaveUser() throws SQLException{
		UtenteRegistrato u=null, utenteinserito=null;
		boolean flag=false;
		
		try {
			u=new UtenteRegistrato(3477542769l, "Marcello", "Massara", "Mmassara1", "m.massara@gmail.com", "Mastercard, 9999888877776666, 06/2025, 777", "Italia, Novara, 28100, Via Roma 71");
			ud.doSaveUser(u);
			utenteinserito=ud.doRetrieveByMail("m.massara@gmail.com");
			assertEquals(u.getCognome(),utenteinserito.getCognome());
			assertEquals(u.getNome(),utenteinserito.getNome());
			assertEquals(u.getTelefono(),utenteinserito.getTelefono());
			assertEquals(u.getEmail(),utenteinserito.getEmail());
			assertEquals(u.getIndirizzo(),utenteinserito.getIndirizzo());
			assertEquals(u.getPagamento(),utenteinserito.getPagamento());
			flag=true;
		}catch(Exception e) {
			fail("testdoSaveUser() not passed!");
		}finally {
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
	}
	
	public void testDoSaveUserException() throws SQLException{
		try {
			UtenteRegistrato user=null;
			ud.doSaveUser(user);
			fail("testDoSaveUserException() (null UtenteRegistrato) not passed");
		}catch(Exception e) {
			
		}
		
		try {
			UtenteRegistrato user2=new UtenteRegistrato(1,3270876971L, "Giovanni", "Simonini", "a4abe91512306f1e4f1f8e06d0b6684ac4cd5b66", "g.simonini@gmail.com", "Mastercard, 1111222233334444, 04/2026, 777", "Italia, Avellino, 83100, Viale Italia 12");
			ud.doSaveUser(user2);
			fail("testDoSaveUserException() (mail already used for UtenteRegistrato) not passed");
		}catch(Exception e) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
	public void testAuthenticate() throws SQLException{
		UtenteRegistrato oracolo=null, utenteAutenticato=null;
		boolean flag=false;
		
		try {
			oracolo=new UtenteRegistrato(1,3270876971L, "Giovanni", "Simonini", "a4abe91512306f1e4f1f8e06d0b6684ac4cd5b66", "g.simonini@gmail.com", "Mastercard, 1111222233334444, 04/2026, 777", "Italia, Avellino, 83100, Viale Italia 12");
			utenteAutenticato=ud.authenticate("g.simonini@gmail.com", "Testpassword1");
			assertEquals(oracolo.getId(),utenteAutenticato.getId());
			assertEquals(oracolo.getCognome(),utenteAutenticato.getCognome());
			assertEquals(oracolo.getNome(),utenteAutenticato.getNome());
			assertEquals(oracolo.getTelefono(),utenteAutenticato.getTelefono());
			assertEquals(oracolo.getEmail(),utenteAutenticato.getEmail());
			assertEquals(oracolo.getIndirizzo(),utenteAutenticato.getIndirizzo());
			assertEquals(oracolo.getPagamento(),utenteAutenticato.getPagamento());
			flag=true;
		}catch(Exception e) {
			fail("testAuthenticate() not passed!");
		}finally {
            //DOVE LI VOGLIAMO SALVARE???
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
	}
	
	public void testAuthenticateException() throws SQLException{
		try {
			String mail, password;
			mail=null;
			password=null;
			UtenteRegistrato u=ud.authenticate(mail,password);
			fail("testAuthenticateException() (null mail e password) not passed");
		}catch(Exception e) {
			
		}
		try {
			String mail, password;
			mail="";
			password="";
			UtenteRegistrato u=ud.authenticate(mail,password);
			fail("testAuthenticateException() (empty mail e password) not passed");
		}catch(Exception e) {
			
		}
		
		try {
			String mail, password;
			mail="testmail@test.test";
			password="testPassword11";
			UtenteRegistrato u=ud.authenticate(mail,password);

			assertNotEquals(String.valueOf(u),String.valueOf(null));
			fail("testAuthenticateException() (no UtenteRegistrato found in DB) not passed");
		}catch(Exception e) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
	public void testAuthenticateManager() throws SQLException{
		Manager oracolo=null, managerAutenticato=null;
		boolean flag=false;
		
		try {
			oracolo=new Manager(1l,3477669134l, "Giuseppe", "Amministratore", "Amministratore1", "giuseppe@techzone.it","assistenza-g@techzone.it");
			managerAutenticato=ud.authenticateManager("giuseppe@techzone.it", "Amministratore1");
			assertEquals(oracolo.getId(),managerAutenticato.getId());
			assertEquals(oracolo.getCognome(),managerAutenticato.getCognome());
			assertEquals(oracolo.getNome(),managerAutenticato.getNome());
			assertEquals(oracolo.getTelefono(),managerAutenticato.getTelefono());
			assertEquals(oracolo.getEmail(),managerAutenticato.getEmail());
			assertEquals(oracolo.getSupportEmail(),managerAutenticato.getSupportEmail());
			flag=true;
		}catch(Exception e) {
			fail("testAuthenticate() not passed!");
		}finally {
            //DOVE LI VOGLIAMO SALVARE???
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
	}
	
	public void testAuthenticateManagerException() throws SQLException{
		try {
			String mail, password;
			mail=null;
			password=null;
			Manager m=ud.authenticateManager(mail,password);
			fail("testAuthenticateManagerException() (null mail e password) not passed");
		}catch(Exception e) {
			
		}
		try {
			String mail, password;
			mail="";
			password="";
			Manager m=ud.authenticateManager(mail,password);
			assertNotEquals(String.valueOf(m),String.valueOf(null));
			fail("testAuthenticateManagerException() (empty mail e password) not passed");
		}catch(Exception e) {
			
		}
		
		try {
			String mail, password;
			mail="testmail@test.test";
			password="testPassword11";
			Manager m=ud.authenticateManager(mail,password);
			assertNotEquals(String.valueOf(m),String.valueOf(null));
			fail("testAuthenticateManagerException() (no Manager found in DB) not passed");
		}catch(Exception e) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
	public void testDoRetrieveByEmail() throws SQLException{
		UtenteRegistrato oracolo=null, utentedb=null;
		boolean flag=false;
		
		try {
			oracolo=new UtenteRegistrato(1,3270876971L, "Giovanni", "Simonini", "a4abe91512306f1e4f1f8e06d0b6684ac4cd5b66", "g.simonini@gmail.com", "Mastercard, 1111222233334444, 04/2026, 777", "Italia, Avellino, 83100, Viale Italia 12");
			utentedb=ud.doRetrieveByMail("g.simonini@gmail.com");
			assertEquals(oracolo.getId(),utentedb.getId());
			assertEquals(oracolo.getCognome(),utentedb.getCognome());
			assertEquals(oracolo.getNome(),utentedb.getNome());
			assertEquals(oracolo.getTelefono(),utentedb.getTelefono());
			assertEquals(oracolo.getEmail(),utentedb.getEmail());
			assertEquals(oracolo.getIndirizzo(),utentedb.getIndirizzo());
			assertEquals(oracolo.getPagamento(),utentedb.getPagamento());
			flag=true;
		}catch(Exception e) {
			fail("testDoRetrieveByEmail() not passed!");
		}finally {
            //DOVE LI VOGLIAMO SALVARE???
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
	}
	
	public void testDoRetrieveByMailException() throws SQLException{
		try {
			String mail;
			mail=null;
			UtenteRegistrato u=ud.doRetrieveByMail(mail);
			assertNotEquals(String.valueOf(u),String.valueOf(null));
			fail("testDoRetrieveByMailException() (null mail e password) not passed");
		}catch(Exception e) {
			
		}
		try {
			String mail;
			mail="";
			UtenteRegistrato u=ud.doRetrieveByMail(mail);
			assertNotEquals(String.valueOf(u),String.valueOf(null));
			fail("testDoRetrieveByMailException() (empty mail) not passed");
		}catch(Exception e) {
			
		}
		
		try {
			String mail;
			mail="testmail@test.test";
			UtenteRegistrato u=ud.doRetrieveByMail(mail);
			assertNotEquals(String.valueOf(u),String.valueOf(null));
			fail("testDoRetrieveByMailException() (no UtenteRegistrato found in DB) not passed");
		}catch(Exception e) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
	public void testDoRetrieveById() throws SQLException{
		UtenteRegistrato oracolo=null, utentedb=null;
		boolean flag=false;
		
		try {
			oracolo=new UtenteRegistrato(1,3270876971L, "Giovanni", "Simonini", "a4abe91512306f1e4f1f8e06d0b6684ac4cd5b66", "g.simonini@gmail.com", "Mastercard, 1111222233334444, 04/2026, 777", "Italia, Avellino, 83100, Viale Italia 12");
			utentedb=ud.doRetrieveById(1);
			assertEquals(oracolo.getId(),utentedb.getId());
			assertEquals(oracolo.getCognome(),utentedb.getCognome());
			assertEquals(oracolo.getNome(),utentedb.getNome());
			assertEquals(oracolo.getTelefono(),utentedb.getTelefono());
			assertEquals(oracolo.getEmail(),utentedb.getEmail());
			assertEquals(oracolo.getIndirizzo(),utentedb.getIndirizzo());
			assertEquals(oracolo.getPagamento(),utentedb.getPagamento());
			flag=true;
		}catch(Exception e) {
			fail("testDoRetrieveById() not passed!");
		}finally {
            //DOVE LI VOGLIAMO SALVARE???
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
	}
	
	public void testDoRetrieveByIdException() throws SQLException{

		try {
			Long id;
			id=-1L;
			UtenteRegistrato u=ud.doRetrieveById(id);
			assertNotEquals(String.valueOf(u),String.valueOf(null));
			fail("testDoRetrieveByIdException() (no UtenteRegistrato found in DB) not passed");
		}catch(Exception e) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
	public void testCheckEmailUsed() throws SQLException{
		boolean flag=false;
		boolean oracolo=true;
		try {
			boolean test1=ud.checkEmailUsed("g.simonini@gmail.com");
			assertEquals(oracolo,test1);
			boolean test2=ud.checkEmailUsed("testrandomMail@mailrandom.com");
			oracolo=false;
			assertEquals(oracolo,test2);
			flag=true;
		}catch(Exception e) {
			fail("testCheckEmailUsed() not passed!");
		}finally {
            //DOVE LI VOGLIAMO SALVARE???
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
		
	}
	
	public void testCheckEmailUsedException() throws SQLException{
		
		try {
			boolean test1=ud.checkEmailUsed(null);
			assertEquals(test1,true);
			fail("testCheckEmailUsedException() (null Email) not passed");
		}catch(Exception e) {

		}finally {
            //DOVE LI VOGLIAMO SALVARE???
            System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
		
	}
	
}
