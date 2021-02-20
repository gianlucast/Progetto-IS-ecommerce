package it.techzone.test.control;

import static org.junit.Assert.assertEquals;
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

import it.techzone.control.OrderStatusControl;
import it.techzone.model.beans.Manager;

public class OrderStatusControlTest extends Mockito{
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
    public void testOrderStatusChange() {
    	requestMap=new HashMap<String, Object>();
    	sessioneMap=new HashMap<String,Object>();
    	sessioneMap.put("manager", new Manager());
    	boolean flag=false;
    	try {
    		
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("orderId")).thenReturn("2");
	    	when(request.getParameter("changeStatus")).thenReturn("Spedito");
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	OrderStatusControl servlet=new OrderStatusControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	assertEquals(sessioneMap.get("alertMsg"),"Modifica avvenuta con successo");
	    	flag=true;
    	}catch(Exception e) {
    		e.printStackTrace();
    		fail("testOrderStatusChange() not passed!");
		}finally {
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    	
    }
    
    @Test
    public void testOrderStatusChangeFail() {
    	requestMap=new HashMap<String, Object>();
    	sessioneMap=new HashMap<String,Object>();
    	sessioneMap.put("manager", new Manager());
    	boolean flag1=false;
    	boolean flag2=false;
    	boolean flag3=false;
    	boolean flag4=false;
    	try {
    		
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("orderId")).thenReturn("2");
	    	when(request.getParameter("changeStatus")).thenReturn("Non valido");
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	OrderStatusControl servlet=new OrderStatusControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	assertEquals(sessioneMap.get("alertMsg"),"Modifica fallita");
	    	flag1=true;
    	}catch(Exception e) {
    		e.printStackTrace();
    		fail("testOrderStatusChangeFail() (status not valid) not passed!");
		}
    	
    	try {
    		
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("orderId")).thenReturn("-1");
	    	when(request.getParameter("changeStatus")).thenReturn("Spedito");
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	OrderStatusControl servlet=new OrderStatusControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	assertEquals(sessioneMap.get("alertMsg"),"Modifica fallita");
	    	flag2=true;
    	}catch(Exception e) {
    		e.printStackTrace();
    		fail("testOrderStatusChangeFail() (idOrder not valid) not passed!");
		}
    	
    	try {
    		
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("orderId")).thenReturn(null);
	    	when(request.getParameter("changeStatus")).thenReturn("Spedito");
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	OrderStatusControl servlet=new OrderStatusControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	assertEquals(sessioneMap.get("alertMsg"),"Richiesta non valida");
	    	flag3=true;
    	}catch(Exception e) {
    		e.printStackTrace();
    		fail("testOrderStatusChangeFail() (idOrder null) not passed!");
		}
    	
    	try {
    		
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("orderId")).thenReturn("-1");
	    	when(request.getParameter("changeStatus")).thenReturn("Spedito");
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	sessioneMap.remove("manager");
	    	OrderStatusControl servlet=new OrderStatusControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	assertEquals(sessioneMap.get("alertMsg"),"Accesso non autorizzato");
	    	flag4=true;
    	}catch(Exception e) {
    		e.printStackTrace();
    		fail("testOrderStatusChangeFail() (not a manager) not passed!");
		}
            
    	
    	
    	if(flag1&&flag2&&flag3&&flag4) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    	
    }
}
