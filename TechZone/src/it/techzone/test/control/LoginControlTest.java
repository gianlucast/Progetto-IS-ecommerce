package it.techzone.test.control;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import it.techzone.control.LoginControl;
import it.techzone.model.beans.Manager;
import it.techzone.model.beans.UtenteRegistrato;
import junit.framework.TestCase;
public class LoginControlTest extends Mockito{
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
    public void testLogin() {
    	sessioneMap=new HashMap<String, Object>();
    	boolean flag=false;
    	try {
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("email")).thenReturn("g.simonini@gmail.com");
	    	when(request.getParameter("password")).thenReturn("Testpassword1");
	    	when(session.getAttribute("utente")).thenReturn(null);
	    	when(session.getAttribute("manager")).thenReturn(null);
	    	
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
	    	
	    	
	    	ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
	        
	    	new LoginControl().doGet(request,response);
	    	
	    	verify(response).sendRedirect(captor.capture());
	        assertEquals("./HomePage.jsp", captor.getValue());
	        
	    	assertEquals("Login avvenuto con successo", sessioneMap.get("alertMsg"));
	    	assertNotEquals(sessioneMap.get("utente"),null);
	    	assertEquals(sessioneMap.get("manager"),null);
	    
	    	
	    	flag=true;
	    
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testLogin() not passed!");
		}finally {
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    }
    	
    @Test
    public void testLoginManager() {
    	sessioneMap=new HashMap<String, Object>();
    	boolean flag=false;
    	try {
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("email")).thenReturn("giuseppe@techzone.it");
	    	when(request.getParameter("password")).thenReturn("Amministratore1");
	    	when(session.getAttribute("utente")).thenReturn(null);
	    	when(session.getAttribute("manager")).thenReturn(null);
	    	
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
	    	
	    	
	    	ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
	        
	    	new LoginControl().doGet(request,response);
	    	
	    	verify(response).sendRedirect(captor.capture());
	        assertEquals("./HomePage.jsp", captor.getValue());
	        
	    	assertEquals("Login avvenuto con successo", sessioneMap.get("alertMsg"));
	    	
	    	
	    	assertNotEquals(sessioneMap.get("manager"),null);
	    	assertEquals(sessioneMap.get("utente"),null);
	    	
	    	flag=true;
	    
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testLoginManager() not passed!");
		}finally {
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    }
    
    @Test
    public void testLoginFail() {
    	boolean flag1=false;
    	boolean flag2=false;
    	boolean flag3=false;
    	try {
    		sessioneMap=new HashMap<String, Object>();
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("email")).thenReturn("giuseppe@techzone.it");
	    	when(request.getParameter("password")).thenReturn("Amministratore1");
	    	sessioneMap.put("utente", new UtenteRegistrato());
	    	when(session.getAttribute("utente")).thenReturn(null);
	    	when(session.getAttribute("manager")).thenReturn(null);
	    	
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
	    	
	    	
	    	ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
	        
	    	new LoginControl().doGet(request,response);
	    	
	    	verify(response).sendRedirect(captor.capture());
	    	assertEquals("Hai già effettuato il login", sessioneMap.get("alertMsg"));
	    	assertEquals("./HomePage.jsp", captor.getValue());
	        assertEquals(null, sessioneMap.get("manager"));
	        flag1=true;
	    	
	    	
	    	
	    
    	}catch(Exception e) {
    		fail("testLoginFail() (utente already logged) not passed!");
			
		}
    	
    	try {
    		sessioneMap=new HashMap<String, Object>();
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("email")).thenReturn("giuseppe@techzone.it");
	    	when(request.getParameter("password")).thenReturn("Amministratore1");
	    	sessioneMap.put("manager", new Manager());
	    	when(session.getAttribute("utente")).thenReturn(null);
	    	when(session.getAttribute("manager")).thenReturn(null);
	    	
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
	    	
	    	
	    	ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
	        
	    	new LoginControl().doGet(request,response);
	    	
	    	assertEquals("Hai già effettuato il login", sessioneMap.get("alertMsg"));
	        assertEquals(null, sessioneMap.get("utente"));
	        flag2=true;
	    	
	    	
	    	
	    
    	}catch(Exception e) {
    		fail("testLoginFail() (manager already logged) not passed!");
			
		}
    	
    	try {
    		sessioneMap=new HashMap<String, Object>();
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("email")).thenReturn("giuseppe@techzone.it");
	    	when(request.getParameter("password")).thenReturn("NonGiustaPassword");
	    	when(session.getAttribute("utente")).thenReturn(null);
	    	when(session.getAttribute("manager")).thenReturn(null);
	    	
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
	    	
	    	
	    	ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
	        
	    	new LoginControl().doGet(request,response);
	    	
	    	assertEquals(null,sessioneMap.get("utente"));
	    	assertEquals(null,sessioneMap.get("manager"));
	    	assertEquals("Corrispondenza non trovata", sessioneMap.get("alertMsg"));
	        
	    	flag3=true;
	    	
	    	
	    
    	}catch(Exception e) {
    		fail("testLoginFail() (manager already logged) not passed!");
			
		}
    	if(flag1&&flag2&&flag3) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }
}
