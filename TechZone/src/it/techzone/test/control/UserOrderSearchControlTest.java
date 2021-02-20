package it.techzone.test.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
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

import it.techzone.control.ProductViewControl;
import it.techzone.control.UserOrderSearchControl;
import it.techzone.model.beans.Order;
import it.techzone.model.beans.UtenteRegistrato;

public class UserOrderSearchControlTest extends Mockito{
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
    public void testUserOrderSearch(){
    	requestMap=new HashMap<String, Object>();
    	sessioneMap=new HashMap<String, Object>();
    	boolean flag=false;
    	try {
    		UtenteRegistrato u=new UtenteRegistrato();
    		u.setEmail("g.simonini@gmail.com");
	    	sessioneMap.put("utente", u);
	    	when(request.getSession()).thenReturn(session);
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	UserOrderSearchControl servlet=new UserOrderSearchControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	
	    	
	    	assertNotEquals(sessioneMap.get("ordini"),null);
	    	ArrayList<Order> ordini=(ArrayList<Order>)sessioneMap.get("ordini");
	    	assertEquals(2,ordini.size());
	    	assertEquals(1,ordini.get(0).getNumeroOrdine());
	    	assertEquals(2,ordini.get(1).getNumeroOrdine());
	    	flag=true;
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testUserOrderSearch() not passed!");
		}finally {
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    	
    }
    
    @Test
    public void testUserOrderSearchFail (){
    	requestMap=new HashMap<String, Object>();
    	sessioneMap=new HashMap<String, Object>();
    	boolean flag=false;
    	try {
    		
	    	sessioneMap.put("utente", null);
	    	when(request.getSession()).thenReturn(session);
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	UserOrderSearchControl servlet=new UserOrderSearchControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	
	    	
	    	assertEquals(sessioneMap.get("ordini"),null);
	    	assertEquals(sessioneMap.get("alertMsg"),"Pagina non disponibile");
	    	flag=true;
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testUserOrderSearchFail() (user not logged) not passed!");
		}finally {
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    	
    }


}
