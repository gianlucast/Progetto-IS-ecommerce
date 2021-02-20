package it.techzone.test.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import it.techzone.control.UserOrderSearchControl;
import it.techzone.control.UserOrderViewControl;
import it.techzone.model.beans.Order;
import it.techzone.model.beans.UtenteRegistrato;

public class UserOrderViewControlTest extends Mockito{
	@Mock
    HttpServletRequest request;
 
    @Mock
    HttpServletResponse response;
 
    @Mock
    HttpSession session;
    
    @Mock
    ServletConfig sg;
    @Mock
    ServletContext context;
    @Mock
    RequestDispatcher dispatcher;
    
    HashMap<String, Object> requestMap;
    HashMap<String, Object> sessioneMap;
    
    @Before
    public void setUp() throws Exception {
        request=mock(HttpServletRequest.class);
        response=mock(HttpServletResponse.class);
        session=mock(HttpSession.class);
        sg=mock(ServletConfig.class);
        context = mock(ServletContext .class);
        dispatcher=mock(RequestDispatcher.class);
        
        requestMap=new HashMap<String, Object>();
    	doAnswer(new Answer<Object>() {
    		@Override
    		public Object answer(InvocationOnMock invocation)throws Throwable{
    			String key= (String) invocation.getArguments()[0];
    			return requestMap.get(key);
    		}
    	}).when(request).getAttribute(anyString());
    	
    	Mockito.doAnswer(new Answer<Object>(){
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String key = (String) invocation.getArguments()[0];
                Object value = invocation.getArguments()[1];
                requestMap.put(key, value);
                return null;
            }
        }).when(request).setAttribute(anyString(), any());
    	
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
                requestMap.remove(key);
                return null;
            }
        }).when(request).removeAttribute(anyString());
    }

    @Test
    public void testUserOrderView() {
    	requestMap=new HashMap<String, Object>();
    	sessioneMap=new HashMap<String, Object>();
    	boolean flag=false;
    	try {
    		UtenteRegistrato u=new UtenteRegistrato();
    		u.setEmail("g.simonini@gmail.com");
	    	sessioneMap.put("utente", u);
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("idOrd")).thenReturn("1");
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	UserOrderViewControl servlet=new UserOrderViewControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	assertNotEquals(sessioneMap.get("ordine"),null);
	    	Order ordine=(Order)sessioneMap.get("ordine");
	    	assertEquals(1,ordine.getNumeroOrdine());
	    	flag=true;
	    }catch(Exception e) {
			e.printStackTrace();
			fail("testUserOrderView() not passed!");
		}finally {
	        if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	    }
    }

    @Test
    public void testUserOrderViewFail() {
    	requestMap=new HashMap<String, Object>();
    	sessioneMap=new HashMap<String, Object>();
    	boolean flag1=false, flag2=false, flag3=false, flag4=false;
    	try {
 
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("idOrd")).thenReturn("1");
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	UserOrderViewControl servlet=new UserOrderViewControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	assertEquals(sessioneMap.get("ordine"),null);
	    	assertEquals(sessioneMap.get("alertMsg"), "Richiesta non valida");
	    	flag1=true;
	    }catch(Exception e) {
			e.printStackTrace();
			fail("testUserOrderViewFail() (user not logged) not passed!");
		}
    	
    	try {
    		requestMap=new HashMap<String, Object>();
        	sessioneMap=new HashMap<String, Object>();
    		UtenteRegistrato u=new UtenteRegistrato();
    		u.setEmail("g.simonini@gmail.com");
	    	sessioneMap.put("utente", u);
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("idOrd")).thenReturn("-1");
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	UserOrderViewControl servlet=new UserOrderViewControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	assertEquals(sessioneMap.get("ordine"),null);
	    	assertEquals(sessioneMap.get("alertMsg"), "Richiesta non valida");
	    	flag2=true;
	    }catch(Exception e) {
			e.printStackTrace();
			fail("testUserOrderViewFail() (idOrd not valid) not passed!");
		}
    	
    	try {
    		requestMap=new HashMap<String, Object>();
        	sessioneMap=new HashMap<String, Object>();
    		UtenteRegistrato u=new UtenteRegistrato();
    		u.setEmail("g.simonini@gmail.com");
	    	sessioneMap.put("utente", u);
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("idOrd")).thenReturn(null);
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	UserOrderViewControl servlet=new UserOrderViewControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	assertEquals(sessioneMap.get("alertMsg"), "Errore nella richiesta");
	    	assertEquals(sessioneMap.get("ordine"),null);
	    	
	    	flag3=true;
	    }catch(Exception e) {
			e.printStackTrace();
			fail("testUserOrderViewFail() (idOrd null) not passed!");
		}
    	
    	try {
    		requestMap=new HashMap<String, Object>();
        	sessioneMap=new HashMap<String, Object>();
    		UtenteRegistrato u=new UtenteRegistrato();
    		u.setEmail("m.massara@gmail.com");
	    	sessioneMap.put("utente", u);
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("idOrd")).thenReturn("1");
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	UserOrderViewControl servlet=new UserOrderViewControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	session.setAttribute("alertMsg", "Richiesta non valida");
	    	assertEquals(sessioneMap.get("ordine"),null);
	    	
	    	flag4=true;
	    }catch(Exception e) {
			e.printStackTrace();
			fail("testUserOrderViewFail() (user don't own the order) not passed!");
		}
    	
	    if(flag1&&flag2&&flag3&&flag4) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }
}
