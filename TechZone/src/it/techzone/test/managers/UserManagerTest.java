package it.techzone.test.managers;
import static org.junit.Assert.assertNotEquals;

import java.io.PrintWriter;
import java.sql.SQLException;

import it.techzone.model.beans.Manager;
import it.techzone.model.beans.UtenteRegistrato;

import it.techzone.model.managers.UserManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert.*;
public class UserManagerTest extends TestCase{
	private static PrintWriter pw;
	private static UserManager um;
	
	
	protected void setUp() throws Exception{
        um = new UserManager();
    }
	
	public void testSaveUser() {
		UtenteRegistrato u=null, utenteinserito=null;
		boolean flag=false;
		
		try {
			u=new UtenteRegistrato(3477542769l, "Marcello", "Massara", "Mmassara1", "m.massara@gmail.com", "Mastercard, 9999888877776666, 06/2025, 777", "Italia, Novara, 28100, Via Roma 71");
			um.saveUser("m.massara@gmail.com","Marcello", "Massara", "Mmassara1",3477542769l,"Italia, Novara, 28100, Via Roma 71","Mastercard, 9999888877776666, 06/2025, 777");
			utenteinserito=um.authentication("m.massara@gmail.com", "Mmassara1");
			assertEquals(u.getCognome(),utenteinserito.getCognome());
			assertEquals(u.getNome(),utenteinserito.getNome());
			assertEquals(u.getTelefono(),utenteinserito.getTelefono());
			assertEquals(u.getEmail(),utenteinserito.getEmail());
			assertEquals(u.getIndirizzo(),utenteinserito.getIndirizzo());
			assertEquals(u.getPagamento(),utenteinserito.getPagamento());
			flag=true;
		}catch(Exception e) {
			e.printStackTrace();
			fail("testSaveUser() not passed!");
			
		}finally {
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
	}
	
	public void testSaveUserException() {
		UtenteRegistrato u;
		boolean res;
		try {
			u=um.saveUser("g.simonini@gmail.com","Alfonso", "Massara", "Mmassara1",3477542769l,"Italia, Novara, 28100, Via Roma 71","Mastercard, 9999888877776666, 06/2025, 777");
			res=u==null;
			assertEquals(false,res);
			fail("testSaveUserException() (mail already used) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			u=um.saveUser("","Alfonso", "Massara", "Mmassara1",3477542769l,"Italia, Novara, 28100, Via Roma 71","Mastercard, 9999888877776666, 06/2025, 777");
			res=u==null;
			assertEquals(false,res);
			fail("testSaveUserException() (empty mail) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			u=um.saveUser(null,"Alfonso", "Massara", "Mmassara1",3477542769l,"Italia, Novara, 28100, Via Roma 71","Mastercard, 9999888877776666, 06/2025, 777");
			res=u==null;
			assertEquals(false,res);
			fail("testSaveUserException() (null mail) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			u=um.saveUser("Stringatest","Alfonso", "Massara", "Mmassara1",3477542769l,"Italia, Novara, 28100, Via Roma 71","Mastercard, 9999888877776666, 06/2025, 777");
			res=u==null;
			assertEquals(false,res);
			fail("testSaveUserException() (not valid mail) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			u=um.saveUser("a.massara@gmail.com","", "Massara", "Mmassara1",3477542769l,"Italia, Novara, 28100, Via Roma 71","Mastercard, 9999888877776666, 06/2025, 777");
			res=u==null;
			assertEquals(false,res);
			fail("testSaveUserException() (empty name) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			u=um.saveUser("a.massara@gmail.com",null, "Massara", "Mmassara1",3477542769l,"Italia, Novara, 28100, Via Roma 71","Mastercard, 9999888877776666, 06/2025, 777");
			assertNotEquals(String.valueOf(u),String.valueOf(null));
			fail("testSaveUserException() (null name) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			u=um.saveUser("a.massara@gmail.com","Alfonso", "", "Mmassara1",3477542769l,"Italia, Novara, 28100, Via Roma 71","Mastercard, 9999888877776666, 06/2025, 777");
			res=u==null;
			assertEquals(false,res);
			fail("testSaveUserException() (empty secondname) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			u=um.saveUser("a.massara@gmail.com","Alfonso", null, "Mmassara1",3477542769l,"Italia, Novara, 28100, Via Roma 71","Mastercard, 9999888877776666, 06/2025, 777");
			res=u==null;
			assertEquals(false,res);
			fail("testSaveUserException() (null secondname) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			u=um.saveUser("a.massara@gmail.com","Alfonso", "Massara", "",3477542769l,"Italia, Novara, 28100, Via Roma 71","Mastercard, 9999888877776666, 06/2025, 777");
			res=u==null;
			assertEquals(false,res);
			fail("testSaveUserException() (empty password) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			u=um.saveUser("a.massara@gmail.com","Alfonso", "Massara", "a",3477542769l,"Italia, Novara, 28100, Via Roma 71","Mastercard, 9999888877776666, 06/2025, 777");
			res=u==null;
			assertEquals(false,res);
			fail("testSaveUserException() (not valid password) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			u=um.saveUser("a.massara@gmail.com","Alfonso", "Massara", null,3477542769l,"Italia, Novara, 28100, Via Roma 71","Mastercard, 9999888877776666, 06/2025, 777");
			res=u==null;
			assertEquals(false,res);
			fail("testSaveUserException() (null password) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			u=um.saveUser("a.massara@gmail.com","Alfonso", "Massara", "Mmassara1",34769l,"Italia, Novara, 28100, Via Roma 71","Mastercard, 9999888877776666, 06/2025, 777");
			res=u==null;
			assertEquals(false,res);
			fail("testSaveUserException() (not valid phone) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			u=um.saveUser("a.massara@gmail.com","Alfonso", "Massara", "Mmassara1",3477542769l,"","Mastercard, 9999888877776666, 06/2025, 777");
			res=u==null;
			assertEquals(false,res);
			fail("testSaveUserException() (empty address) not passed!");
		}catch(Throwable t) {
			
		} 
		try {
			u=um.saveUser("a.massara@gmail.com","Alfonso", "Massara", "Mmassara1",3477542769l,null,"Mastercard, 9999888877776666, 06/2025, 777");
			res=u==null;
			assertEquals(false,res);
			fail("testSaveUserException() (null address) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			u=um.saveUser("a.massara@gmail.com","Alfonso", "Massara", "Mmassara1",3477542769l,"Italia, Novara, 28100, Via Roma 71","");
			res=u==null;
			assertEquals(false,res);
			fail("testSaveUserException() (empty payment) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			u=um.saveUser("a.massara@gmail.com","Alfonso", "Massara", "Mmassara1",3477542769l,"Italia, Novara, 28100, Via Roma 71",null);
			res=u==null;
			assertEquals(false,res);
			fail("testSaveUserException() (empty mail) not passed!");
		}catch(Throwable t) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
	public void testEmailAlreadyUsed() {
		boolean flag=false;
		try {
			assertEquals(false,um.emailAlreadyUsed("TESTMAILNONPRESENTE@gmail.com"));
			assertEquals(true,um.emailAlreadyUsed("g.simonini@gmail.com"));
			flag=true;
		}catch(Exception e) {
			fail("testEmailAlreadyUsed() not passed!");
		}finally {
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
	}
	
	public void testAuthentication() {
		UtenteRegistrato oracolo=null, utenteAutenticato=null;
		boolean flag=false;
		
		try {
			oracolo=new UtenteRegistrato(1,3270876971L, "Giovanni", "Simonini", "a4abe91512306f1e4f1f8e06d0b6684ac4cd5b66", "g.simonini@gmail.com", "Mastercard, 1111222233334444, 04/2026, 777", "Italia, Avellino, 83100, Viale Italia 12");
			utenteAutenticato=um.authentication("g.simonini@gmail.com", "Testpassword1");
			assertEquals(oracolo.getId(),utenteAutenticato.getId());
			assertEquals(oracolo.getCognome(),utenteAutenticato.getCognome());
			assertEquals(oracolo.getNome(),utenteAutenticato.getNome());
			assertEquals(oracolo.getTelefono(),utenteAutenticato.getTelefono());
			assertEquals(oracolo.getEmail(),utenteAutenticato.getEmail());
			assertEquals(oracolo.getIndirizzo(),utenteAutenticato.getIndirizzo());
			assertEquals(oracolo.getPagamento(),utenteAutenticato.getPagamento());
			flag=true;
		}catch(Exception e) {
			fail("testAuthentication() not passed!");
		}finally {
            //DOVE LI VOGLIAMO SALVARE???
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
	}
	
	public void testAuthenticationException() {
		UtenteRegistrato u;
		boolean res;
		try {
			u=um.authentication("", "Password1");
			res=u==null;
			assertEquals(false,res);
			fail("testAuthenticationException() (empty mail) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			u=um.authentication(null, "Password1");
			res=u==null;
			assertEquals(false,res);
			fail("testAuthenticationException() (null mail) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			u=um.authentication("g.simonini@gmail.com", "");
			res=u==null;
			assertEquals(false,res);
			fail("testAuthenticationException() (empty pass) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			u=um.authentication("g.simonini@gmail.com", null);
			res=u==null;
			assertEquals(false,res);
			fail("testAuthenticationException() (null pass) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			u=um.authentication("g.simonini@gmail.com", "PASSWORDtest2");
			res=u==null;
			assertEquals(false,res);
			fail("testAuthenticationException() (combination not found) not passed!");
		}catch(Throwable t) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
	public void testAuthenticationManager() {
		Manager oracolo=null, managerAutenticato=null;
		boolean flag=false;
		try {
			oracolo=new Manager(1l,3477669134l, "Giuseppe", "Amministratore", "Amministratore1", "giuseppe@techzone.it","assistenza-g@techzone.it");
			managerAutenticato=um.authenticationManager("giuseppe@techzone.it", "Amministratore1");
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
	
	public void testAuthenticationManagerException() {
		Manager m;
		boolean res;
		try {
			m=um.authenticationManager("", "Password1");
			res=m==null;
			assertEquals(false,res);
			fail("testAuthenticationException() (empty mail) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			m=um.authenticationManager(null, "Password1");
			res=m==null;
			assertEquals(false,res);
			fail("testAuthenticationException() (null mail) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			m=um.authenticationManager("g.simonini@gmail.com", "");
			res=m==null;
			assertEquals(false,res);
			fail("testAuthenticationException() (empty pass) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			m=um.authenticationManager("g.simonini@gmail.com", null);
			res=m==null;
			assertEquals(false,res);
			fail("testAuthenticationException() (null pass) not passed!");
		}catch(Throwable t) {
			
		}
		try {
			m=um.authenticationManager("g.simonini@gmail.com", "PASSWORDtest2");
			res=m==null;
			assertEquals(false,res);
			fail("testAuthenticationException() (combination not found) not passed!");
		}catch(Throwable t) {
			
		}
		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	}
	
}
