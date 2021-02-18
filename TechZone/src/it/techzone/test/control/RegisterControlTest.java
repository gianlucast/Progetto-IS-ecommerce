package it.techzone.test.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import it.techzone.control.LoginControl;
import it.techzone.control.RegisterControl;
import it.techzone.model.beans.Manager;
import it.techzone.model.beans.UtenteRegistrato;

public class RegisterControlTest extends Mockito{
	@Mock
    HttpServletRequest request;
 
    @Mock
    HttpServletResponse response;
 
    @Mock
    HttpSession session;
    HashMap<String, Object> sessioneMap;
    
    @Before
    public void setUp() throws Exception {
        request=mock(HttpServletRequest.class);
        response=mock(HttpServletResponse.class);
        session=mock(HttpSession.class);
    }
    
    @Test
    public void testRegistration(){
    	sessioneMap=new HashMap<String, Object>();
    	boolean flag=false;
    	try {
	    	doAnswer(new Answer<Object>() {
	    		@Override
	    		public Object answer(InvocationOnMock invocation)throws Throwable{
	    			String key= (String) invocation.getArguments()[0];
	    			return sessioneMap.get(key);
	    		}
	    	}).when(session).getAttribute(anyString());
	    	
	    	Mockito.doAnswer(new Answer<Object>(){
	            @Override
	            public Object answer(InvocationOnMock invocation) throws Throwable {
	                String key = (String) invocation.getArguments()[0];
	                Object value = invocation.getArguments()[1];
	                sessioneMap.put(key, value);
	                return null;
	            }
	        }).when(session).setAttribute(anyString(), any());
	    	
	    	
	    	when(request.getSession()).thenReturn(session);
	    	when(session.getAttribute("utente")).thenReturn(null);
	    	when(session.getAttribute("manager")).thenReturn(null);
	    	
	    	UtenteRegistrato u=new UtenteRegistrato(3477542769l, "Marcello", "Massara", "Mmassara1", "m.massara@gmail.com", "Mastercard, 9999888877776666, 06/2025, 777", "Italia, Novara, 28100, Via Roma 71");
	    	when(request.getParameter("userEmail")).thenReturn("m.massara@gmail.com");
	    	when(request.getParameter("userPass")).thenReturn("Mmassara1");
	    	when(request.getParameter("userName")).thenReturn("Marcello");
	    	when(request.getParameter("userSurname")).thenReturn("Massara");
	    	when(request.getParameter("userPhone")).thenReturn("3477542769");
	    	when(request.getParameter("userCountry")).thenReturn("Italia");
	    	when(request.getParameter("userCity")).thenReturn("Novara");
	    	when(request.getParameter("userCap")).thenReturn("28100");
	    	when(request.getParameter("userStreet")).thenReturn("Via Roma 71");
	    	when(request.getParameter("userPaymentInst")).thenReturn("Mastercard");
	    	when(request.getParameter("userPaymentCode")).thenReturn("9999888877776666");
	    	when(request.getParameter("userPaymentExpMonth")).thenReturn("06");
	    	when(request.getParameter("userPaymentExpYear")).thenReturn("2025");
	    	when(request.getParameter("userPaymentCvv")).thenReturn("777");
	    	
	    	ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
	        
	    	new RegisterControl().doPost(request,response);
	    	
	    	verify(response).sendRedirect(captor.capture());
	        assertEquals("./HomePage.jsp", captor.getValue());
	    	
	        assertEquals("Registrazione effettuata con successo", sessioneMap.get("alertMsg"));
	        UtenteRegistrato inSessione=(UtenteRegistrato)sessioneMap.get("utente");
	    	assertEquals(inSessione.getEmail(),u.getEmail());
	    	assertEquals(sessioneMap.get("manager"),null);
	    	
	    	flag=true;
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testRegistration() not passed!");
		}finally {
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    }

    @Test
    public void testRegistrationFail(){
    	sessioneMap=new HashMap<String, Object>();
    	boolean flag1=false;
    	boolean flag2=false;
    	boolean flag3=false;
    	try {
    		sessioneMap=new HashMap<String, Object>();
	    	doAnswer(new Answer<Object>() {
	    		@Override
	    		public Object answer(InvocationOnMock invocation)throws Throwable{
	    			String key= (String) invocation.getArguments()[0];
	    			return sessioneMap.get(key);
	    		}
	    	}).when(session).getAttribute(anyString());
	    	
	    	Mockito.doAnswer(new Answer<Object>(){
	            @Override
	            public Object answer(InvocationOnMock invocation) throws Throwable {
	                String key = (String) invocation.getArguments()[0];
	                Object value = invocation.getArguments()[1];
	                sessioneMap.put(key, value);
	                return null;
	            }
	        }).when(session).setAttribute(anyString(), any());
	    	
	    	
	    	when(request.getSession()).thenReturn(session);
	    	when(session.getAttribute("utente")).thenReturn(null);
	    	when(session.getAttribute("manager")).thenReturn(null);
	    	
	    	
	    	when(request.getParameter("userEmail")).thenReturn("g.simonini@gmail.com");
	    	when(request.getParameter("userPass")).thenReturn("Mmassara1");
	    	when(request.getParameter("userName")).thenReturn("Marcello");
	    	when(request.getParameter("userSurname")).thenReturn("Massara");
	    	when(request.getParameter("userPhone")).thenReturn("3477542769");
	    	when(request.getParameter("userCountry")).thenReturn("Italia");
	    	when(request.getParameter("userCity")).thenReturn("Novara");
	    	when(request.getParameter("userCap")).thenReturn("28100");
	    	when(request.getParameter("userStreet")).thenReturn("Via Roma 71");
	    	when(request.getParameter("userPaymentInst")).thenReturn("Mastercard");
	    	when(request.getParameter("userPaymentCode")).thenReturn("9999888877776666");
	    	when(request.getParameter("userPaymentExpMonth")).thenReturn("06");
	    	when(request.getParameter("userPaymentExpYear")).thenReturn("2025");
	    	when(request.getParameter("userPaymentCvv")).thenReturn("777");
	    	
	    	
	    	new RegisterControl().doPost(request,response);
	    	
	        assertEquals("Registrazione fallita.", sessioneMap.get("alertMsg"));
	    	assertEquals(sessioneMap.get("utente"),null);
	    	assertEquals(sessioneMap.get("manager"),null);
	    	
	    	flag1=true;
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testRegistrationFail() (email already used) not passed!");
		}
            
    	
        
    	try {
    		sessioneMap=new HashMap<String, Object>();
	    	doAnswer(new Answer<Object>() {
	    		@Override
	    		public Object answer(InvocationOnMock invocation)throws Throwable{
	    			String key= (String) invocation.getArguments()[0];
	    			return sessioneMap.get(key);
	    		}
	    	}).when(session).getAttribute(anyString());
	    	
	    	Mockito.doAnswer(new Answer<Object>(){
	            @Override
	            public Object answer(InvocationOnMock invocation) throws Throwable {
	                String key = (String) invocation.getArguments()[0];
	                Object value = invocation.getArguments()[1];
	                sessioneMap.put(key, value);
	                return null;
	            }
	        }).when(session).setAttribute(anyString(), any());
	    	
	    	
	    	when(request.getSession()).thenReturn(session);
	    	sessioneMap.put("utente", new UtenteRegistrato());
	    	
	    	
	    	when(request.getParameter("userEmail")).thenReturn("newmail@gmail.com");
	    	when(request.getParameter("userPass")).thenReturn("Mmassara1");
	    	when(request.getParameter("userName")).thenReturn("Marcello");
	    	when(request.getParameter("userSurname")).thenReturn("Massara");
	    	when(request.getParameter("userPhone")).thenReturn("3477542769");
	    	when(request.getParameter("userCountry")).thenReturn("Italia");
	    	when(request.getParameter("userCity")).thenReturn("Novara");
	    	when(request.getParameter("userCap")).thenReturn("28100");
	    	when(request.getParameter("userStreet")).thenReturn("Via Roma 71");
	    	when(request.getParameter("userPaymentInst")).thenReturn("Mastercard");
	    	when(request.getParameter("userPaymentCode")).thenReturn("9999888877776666");
	    	when(request.getParameter("userPaymentExpMonth")).thenReturn("06");
	    	when(request.getParameter("userPaymentExpYear")).thenReturn("2025");
	    	when(request.getParameter("userPaymentCvv")).thenReturn("777");
	    	
	    	new RegisterControl().doPost(request,response);
	    	
	        assertEquals("Sei già loggato.", sessioneMap.get("alertMsg"));
	    	assertNotEquals(sessioneMap.get("utente"),null);
	    	assertEquals(sessioneMap.get("manager"),null);
	    	
	    	flag2=true;
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testRegistrationFail() (UtenteRegistrato already logged) not passed!");
		}
    	
    	try {
    		sessioneMap=new HashMap<String, Object>();
	    	doAnswer(new Answer<Object>() {
	    		@Override
	    		public Object answer(InvocationOnMock invocation)throws Throwable{
	    			String key= (String) invocation.getArguments()[0];
	    			return sessioneMap.get(key);
	    		}
	    	}).when(session).getAttribute(anyString());
	    	
	    	Mockito.doAnswer(new Answer<Object>(){
	            @Override
	            public Object answer(InvocationOnMock invocation) throws Throwable {
	                String key = (String) invocation.getArguments()[0];
	                Object value = invocation.getArguments()[1];
	                sessioneMap.put(key, value);
	                return null;
	            }
	        }).when(session).setAttribute(anyString(), any());
	    	
	    	
	    	when(request.getSession()).thenReturn(session);
	    	
	    	sessioneMap.put("manager", new Manager());
	    	
	    	
	    	when(request.getParameter("userEmail")).thenReturn("newmail@gmail.com");
	    	when(request.getParameter("userPass")).thenReturn("Mmassara1");
	    	when(request.getParameter("userName")).thenReturn("Marcello");
	    	when(request.getParameter("userSurname")).thenReturn("Massara");
	    	when(request.getParameter("userPhone")).thenReturn("3477542769");
	    	when(request.getParameter("userCountry")).thenReturn("Italia");
	    	when(request.getParameter("userCity")).thenReturn("Novara");
	    	when(request.getParameter("userCap")).thenReturn("28100");
	    	when(request.getParameter("userStreet")).thenReturn("Via Roma 71");
	    	when(request.getParameter("userPaymentInst")).thenReturn("Mastercard");
	    	when(request.getParameter("userPaymentCode")).thenReturn("9999888877776666");
	    	when(request.getParameter("userPaymentExpMonth")).thenReturn("06");
	    	when(request.getParameter("userPaymentExpYear")).thenReturn("2025");
	    	when(request.getParameter("userPaymentCvv")).thenReturn("777");
	    	
	    	new RegisterControl().doPost(request,response);
	    	
	        assertEquals("Sei già loggato.", sessioneMap.get("alertMsg"));
	    	assertEquals(sessioneMap.get("utente"),null);
	    	assertNotEquals(sessioneMap.get("manager"),null);
	    	
	    	flag3=true;
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testRegistrationFail() (Manger already logged) not passed!");
		}
    	
    	if(flag1&&flag2&&flag3) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }

}
