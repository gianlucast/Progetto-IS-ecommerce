//UpdateProductControlTest

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

import it.techzone.control.ProductViewControl;
import it.techzone.control.UpdateProductControl;
import it.techzone.model.beans.Manager;
import it.techzone.model.beans.Product;

public class UpdateProductControlTest extends Mockito{
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
    public void testUpdateProductSuccess(){
    	requestMap=new HashMap<String, Object>();
    	sessioneMap=new HashMap<String, Object>();
    	sessioneMap.put("manager", new Manager());
    	boolean flag=false;
    	try {
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("categoria")).thenReturn("Televisori e monitor");
	    	when(request.getParameter("codice")).thenReturn("3");
	    	when(request.getParameter("costo")).thenReturn("1452.89");
	    	when(request.getParameter("descrizione")).thenReturn("Televisore Sony 4k UHD 120Hz 1ms");
	    	when(request.getParameter("nomeprod")).thenReturn("Sony DP2.1");
	    	when(request.getParameter("quantita")).thenReturn("9");
	    	when(request.getParameter("tipo")).thenReturn("Televisore");
	    	when(request.getParameter("action")).thenReturn("mod");
	    	
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	UpdateProductControl servlet=new UpdateProductControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	assertEquals(sessioneMap.get("alertMsg"),"Modifica effettuata");
	    	flag=true;
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testUpdateProductSuccess() not passed!");
		}finally {
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    }
    
    @Test
    public void testGetToUpdateProductPage(){
    	requestMap=new HashMap<String, Object>();
    	sessioneMap=new HashMap<String, Object>();
    	sessioneMap.put("manager", new Manager());
    	boolean flag=false;
    	try {
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("idProd")).thenReturn("3");
	    	when(request.getParameter("action")).thenReturn(null);
	    	
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	UpdateProductControl servlet=new UpdateProductControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	assertNotEquals(requestMap.get("prodotto"), null);
	    	Product p=(Product)requestMap.get("prodotto");
	    	assertEquals(3,p.getCodice());
	    	flag=true;
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testGetToUpdateProductPage() not passed!");
		}finally {
            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    }
    
    @Test
    public void testGetToUpdateProductPageFail(){
    	requestMap=new HashMap<String, Object>();
    	sessioneMap=new HashMap<String, Object>();
    	sessioneMap.put("manager", new Manager());
    	boolean flag1=false;
    	boolean flag2=false;
    	try {
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("idProd")).thenReturn(null);
	    	when(request.getParameter("action")).thenReturn(null);
	    	
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	UpdateProductControl servlet=new UpdateProductControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	assertEquals(requestMap.get("prodotto"), null);
	    	assertEquals(sessioneMap.get("alertMsg"),"Errore nella richiesta");
	    	flag1=true;
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testGetToUpdateProductPageFail() (null idPrdo) not passed!");
		}
    	
    	try {
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("idProd")).thenReturn("3");
	    	when(request.getParameter("action")).thenReturn(null);
	    	sessioneMap.remove("manager");
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	UpdateProductControl servlet=new UpdateProductControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	assertEquals(requestMap.get("prodotto"), null);
	    	assertEquals(sessioneMap.get("alertMsg"),"Accesso non autorizzato");
	    	flag2=true;
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testGetToUpdateProductPageFail() (not a manager) not passed!");
		}
    	
    	
    	
    	if(flag1&&flag2) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        
    }
    
    
    @Test
    public void testUpdateProductFail(){
    	requestMap=new HashMap<String, Object>();
    	sessioneMap=new HashMap<String, Object>();
    	sessioneMap.put("manager", new Manager());
    	boolean flag2=false, flag1=false;
    	try {
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("categoria")).thenReturn("Televisori e monitor");
	    	when(request.getParameter("codice")).thenReturn("3");
	    	when(request.getParameter("costo")).thenReturn("1452.89");
	    	when(request.getParameter("descrizione")).thenReturn("Televisore Sony 4k UHD 120Hz 1ms");
	    	when(request.getParameter("nomeprod")).thenReturn("");
	    	when(request.getParameter("quantita")).thenReturn("9");
	    	when(request.getParameter("tipo")).thenReturn("Televisore");
	    	when(request.getParameter("action")).thenReturn("mod");
	    	
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	UpdateProductControl servlet=new UpdateProductControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	assertEquals(sessioneMap.get("alertMsg"),"Errore nella modifica");
	    	flag1=true;
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testUpdateProductFail() (error in fields) not passed!");
		}
            
    	try {
    		sessioneMap.remove("manager");
	    	when(request.getSession()).thenReturn(session);
	    	when(request.getParameter("categoria")).thenReturn("Televisori e monitor");
	    	when(request.getParameter("codice")).thenReturn("3");
	    	when(request.getParameter("costo")).thenReturn("1452.89");
	    	when(request.getParameter("descrizione")).thenReturn("Televisore Sony 4k UHD 120Hz 1ms");
	    	when(request.getParameter("nomeprod")).thenReturn("Sony DP2.1");
	    	when(request.getParameter("quantita")).thenReturn("9");
	    	when(request.getParameter("tipo")).thenReturn("Televisore");
	    	when(request.getParameter("action")).thenReturn("mod");
	    	
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	UpdateProductControl servlet=new UpdateProductControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	assertEquals(sessioneMap.get("alertMsg"),"Accesso non autorizzato");
	    	flag2=true;
    	}catch(Exception e) {
			e.printStackTrace();
			fail("testUpdateProductFail() (not a manager) not passed!");
		}
    	
    	
    	if(flag1&&flag2) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        
    }
    	
    

}
