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

import it.techzone.control.LogoutControl;
import it.techzone.control.RegisterControl;
import it.techzone.model.beans.Manager;
import it.techzone.model.beans.UtenteRegistrato;

public class LogoutControlTest extends Mockito{
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
    public void testLogout(){
    	sessioneMap=new HashMap<String, Object>();
    	boolean flag=false;
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
	    	
	    	Mockito.doAnswer(new Answer<Object>(){
	            @Override
	            public Object answer(InvocationOnMock invocation) throws Throwable {
	                String key = (String) invocation.getArguments()[0];
	                sessioneMap.remove(key);
	                return null;
	            }
	        }).when(session).removeAttribute(anyString());
	    	
	    	
	    	when(request.getSession()).thenReturn(session);
	    	sessioneMap.put("utente", new UtenteRegistrato());
	    	
	    	ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
	    	
	    	new LogoutControl().doGet(request,response);
	    	verify(response).sendRedirect(captor.capture());
	        assertEquals("./HomePage.jsp", captor.getValue());
	        
	        assertEquals("Logout effettuato con successo.", sessioneMap.get("alertMsg"));
	    	assertEquals(sessioneMap.get("utente"),null);
	    	assertEquals(sessioneMap.get("manager"),null);
	    	
	    	flag=true;
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testLogout() not passed!");
		}finally {
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    	
    }
    
    @Test
    public void testLogoutManager(){
    	sessioneMap=new HashMap<String, Object>();
    	boolean flag=false;
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
	    	
	    	Mockito.doAnswer(new Answer<Object>(){
	            @Override
	            public Object answer(InvocationOnMock invocation) throws Throwable {
	                String key = (String) invocation.getArguments()[0];
	                sessioneMap.remove(key);
	                return null;
	            }
	        }).when(session).removeAttribute(anyString());
	    	
	    	
	    	when(request.getSession()).thenReturn(session);
	    	sessioneMap.put("manager", new Manager());
	    	
	    	ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
	    	
	    	new LogoutControl().doGet(request,response);
	    	verify(response).sendRedirect(captor.capture());
	        assertEquals("./HomePage.jsp", captor.getValue());
	        
	        assertEquals("Logout effettuato con successo.", sessioneMap.get("alertMsg"));
	    	assertEquals(sessioneMap.get("utente"),null);
	    	assertEquals(sessioneMap.get("manager"),null);
	    	
	    	flag=true;
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testLogoutManager() not passed!");
		}finally {
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    	
    }
    
    @Test
    public void testLogoutFail(){
    	sessioneMap=new HashMap<String, Object>();
    	boolean flag=false;
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
	    	
	    	ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
	    	
	    	new LogoutControl().doGet(request,response);
	    	verify(response).sendRedirect(captor.capture());
	        assertEquals("./HomePage.jsp", captor.getValue());
	        
	        assertEquals("Errore, utente non loggato", sessioneMap.get("alertMsg"));
	    	assertEquals(sessioneMap.get("utente"),null);
	    	assertEquals(sessioneMap.get("manager"),null);
	    	
	    	flag=true;
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testLogoutFail() not passed!");
		}finally {
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    	
    }

}
