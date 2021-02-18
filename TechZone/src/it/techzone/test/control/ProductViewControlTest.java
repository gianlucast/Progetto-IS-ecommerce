package it.techzone.test.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

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

import it.techzone.control.LogoutControl;
import it.techzone.control.ProductViewControl;
import javax.servlet.RequestDispatcher;

public class ProductViewControlTest extends Mockito{
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
    public void testProductViewControlNoIdProd(){
    	requestMap=new HashMap<String, Object>();
    	boolean flag=false;
    	try {
    		
	    	
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("idProd")).thenReturn(null);
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	ProductViewControl servlet=new ProductViewControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	
	    	
	    	assertNotEquals(requestMap.get("prodotti"),null);
	    	flag=true;
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testLogout() not passed!");
		}finally {
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    	
    }

    @Test
    public void testProductViewControl(){
    	requestMap=new HashMap<String, Object>();
    	boolean flag=false;
    	try {
    		
	    	
	    	when(sg.getServletContext()).thenReturn(context);
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("idProd")).thenReturn("1");
	    	
	    	ProductViewControl servlet=new ProductViewControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	assertNotEquals(requestMap.get("prodotto"),null);
	    	flag=true;
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testLogout() not passed!");
		}finally {
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    }

}
