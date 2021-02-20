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

import it.techzone.control.DeleteProductControl;
import it.techzone.control.ProductViewControl;
import it.techzone.model.beans.Manager;

public class DeleteProductControlTest extends Mockito{
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
    public void testDeleteProduct() {
    	sessioneMap=new HashMap<String,Object>();
    	sessioneMap.put("manager", new Manager());
    	requestMap=new HashMap<String, Object>();
    	boolean flag=false;
    	try {
    		when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("idProd")).thenReturn("2");
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	DeleteProductControl servlet=new DeleteProductControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	
	    	assertEquals(sessioneMap.get("alertMsg"),"Prodotto rimosso con successo");
	    	flag=true;
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		fail("testDeleteProduct() not passed");
    	}finally {
    		if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    	}
    }
    
    @Test
    public void testDeleteProductFail() {
    	sessioneMap=new HashMap<String,Object>();
    	sessioneMap.put("manager", new Manager());
    	requestMap=new HashMap<String, Object>();
    	boolean flag1=false;
    	boolean flag2=false;
    	boolean flag3=false;
    	try {
    		when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("idProd")).thenReturn("-12");
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	DeleteProductControl servlet=new DeleteProductControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	
	    	assertEquals(sessioneMap.get("alertMsg"),"Errore nella rimozione del prodotto");
	    	flag1=true;
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		fail("testDeleteProductFail() (idProduct not valid) not passed");
    	}
    	
    	try {
    		when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("idProd")).thenReturn(null);
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	DeleteProductControl servlet=new DeleteProductControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	
	    	assertEquals(sessioneMap.get("alertMsg"),"Richiesta non valida");
	    	flag2=true;
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		fail("testDeleteProductFail() (idProduct null) not passed");
    	}
    	
    	try {
    		sessioneMap.remove("manager");
    		when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("idProd")).thenReturn("3");
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	DeleteProductControl servlet=new DeleteProductControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	
	    	assertEquals(sessioneMap.get("alertMsg"),"Azione non autorizzata");
	    	flag3=true;
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		fail("testDeleteProductFail() (idProduct null) not passed");
    	}
    	
    	if(flag1&&flag2&&flag3) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    	
    }

}
