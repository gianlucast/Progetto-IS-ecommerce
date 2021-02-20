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

import it.techzone.control.OrderPlaceControl;
import it.techzone.control.UserOrderViewControl;
import it.techzone.model.beans.Cart;
import it.techzone.model.beans.Order;
import it.techzone.model.beans.Product;
import it.techzone.model.beans.ProductCart;
import it.techzone.model.beans.ProductOrder;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.managers.ProductManager;

public class OrderPlaceControlTest extends Mockito{
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
    public void testOrderPlace() {
    	requestMap=new HashMap<String, Object>();
    	sessioneMap=new HashMap<String, Object>();
    	boolean flag=false;
    	try {
    		UtenteRegistrato u=new UtenteRegistrato();
    		u.setEmail("g.simonini@gmail.com");
    		u.setId(1);
	    	sessioneMap.put("utente", u);
	    	ArrayList<Product> products = new ArrayList<Product>();
	    	ProductManager pm=new ProductManager();
			products.add(pm.retrieveProduct(1));
			products.add(pm.retrieveProduct(2));
			products.add(pm.retrieveProduct(3));
			ProductCart a = new ProductCart(1, products.get(0));
			ProductCart b = new ProductCart(1, products.get(1));
			ProductCart c = new ProductCart(1, products.get(2));
			ArrayList<ProductCart> productsC = new ArrayList<ProductCart>();
			productsC.add(a);
			productsC.add(b);
			productsC.add(c);
			Cart carrello = new Cart();
			carrello.setProductList(productsC);
		
			sessioneMap.put("cart", carrello);
			
	    	when(request.getSession()).thenReturn(session);
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	OrderPlaceControl servlet=new OrderPlaceControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	assertEquals(sessioneMap.get("alertMsg"),"Ordine effettuato con successo");
	    	assertEquals(sessioneMap.get("cart"), null);
	    	flag=true;
	    }catch(Exception e) {
			e.printStackTrace();
			fail("testOrderPlace() not passed!");
		}finally {
	        if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	    }
    }
    
    @Test
    public void testOrderPlaceFail() {
    	requestMap=new HashMap<String, Object>();
    	sessioneMap=new HashMap<String, Object>();
    	boolean flag1=false,flag2=false,flag3=false,flag4=false;
    	try {
    		
	    	sessioneMap.put("utente", null);
	    	ArrayList<Product> products = new ArrayList<Product>();
	    	ProductManager pm=new ProductManager();
			products.add(pm.retrieveProduct(1));
			products.add(pm.retrieveProduct(2));
			products.add(pm.retrieveProduct(3));
			ProductCart a = new ProductCart(1, products.get(0));
			ProductCart b = new ProductCart(1, products.get(1));
			ProductCart c = new ProductCart(1, products.get(2));
			ArrayList<ProductCart> productsC = new ArrayList<ProductCart>();
			productsC.add(a);
			productsC.add(b);
			productsC.add(c);
			Cart carrello = new Cart();
			carrello.setProductList(productsC);
		
			sessioneMap.put("cart", carrello);
			
	    	when(request.getSession()).thenReturn(session);
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	OrderPlaceControl servlet=new OrderPlaceControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	assertEquals(sessioneMap.get("alertMsg"),"Errore, utente non loggato");
	    	assertNotEquals(sessioneMap.get("cart"), null);
	    	flag1=true;
	    }catch(Exception e) {
			e.printStackTrace();
			fail("testOrderPlaceFail() (user not logged) not passed!");
	    }
    	
    	try {
    		UtenteRegistrato u=new UtenteRegistrato();
    		u.setEmail("g.simonini@gmail.com");
    		u.setId(1);
    		
	    	sessioneMap.put("utente", u);
	    	
		
			sessioneMap.put("cart", null);
			
	    	when(request.getSession()).thenReturn(session);
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	OrderPlaceControl servlet=new OrderPlaceControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	assertEquals(sessioneMap.get("alertMsg"),"Errore, carrello vuoto");
	    	assertEquals(sessioneMap.get("cart"), null);
	    	flag2=true;
	    }catch(Exception e) {
			e.printStackTrace();
			fail("testOrderPlaceFail() (null cart) not passed!");
	    }
		
    	try {
    		
    		UtenteRegistrato u=new UtenteRegistrato();
    		u.setEmail("g.simonini@gmail.com");
    		u.setId(1);
    		
	    	sessioneMap.put("utente", u);
	    	
			Cart carrello = new Cart();
		
			sessioneMap.put("cart", carrello);
			
	    	when(request.getSession()).thenReturn(session);
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	OrderPlaceControl servlet=new OrderPlaceControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	assertEquals(sessioneMap.get("alertMsg"),"Errore, carrello vuoto");
	    	assertNotEquals(sessioneMap.get("cart"), null);
	    	flag3=true;
	    }catch(Exception e) {
			e.printStackTrace();
			fail("testOrderPlaceFail() (empty cart) not passed!");
	    }

    	try {
    		UtenteRegistrato u=new UtenteRegistrato();
    		u.setEmail("g.simonini@gmail.com");
    		u.setId(1);
	    	sessioneMap.put("utente", u);
	    	ArrayList<Product> products = new ArrayList<Product>();
	    	ProductManager pm=new ProductManager();
			products.add(pm.retrieveProduct(1));
			products.add(pm.retrieveProduct(2));
			products.add(pm.retrieveProduct(3));
			ProductCart a = new ProductCart(99, products.get(0));
			ProductCart b = new ProductCart(1, products.get(1));
			ProductCart c = new ProductCart(1, products.get(2));
			ArrayList<ProductCart> productsC = new ArrayList<ProductCart>();
			productsC.add(a);
			productsC.add(b);
			productsC.add(c);
			Cart carrello = new Cart();
			carrello.setProductList(productsC);
		
			sessioneMap.put("cart", carrello);
			
	    	when(request.getSession()).thenReturn(session);
	    	when(sg.getServletContext()).thenReturn(context);	
	    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	    	
	    	OrderPlaceControl servlet=new OrderPlaceControl();
	    	servlet.init(sg);
	    	servlet.doGet(request, response);
	    	
	    	assertEquals(sessioneMap.get("alertMsg"),"Errore nell'ordine.");
	    	
	    	flag4=true;
	    }catch(Exception e) {
			e.printStackTrace();
			fail("testOrderPlace() (cart overflow product qty) not passed!");
		}
    	
	    if(flag1&&flag2&&flag3&&flag4) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }

}
