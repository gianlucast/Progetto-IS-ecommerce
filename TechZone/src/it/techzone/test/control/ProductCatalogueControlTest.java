//ProductCatalogueControlTest

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

import it.techzone.control.ProductCatalogueControl;
import it.techzone.control.ProductViewControl;
import it.techzone.model.beans.Product;

public class ProductCatalogueControlTest extends Mockito{
		@Mock
	    HttpServletRequest request;
	 
	    @Mock
	    HttpServletResponse response;
	    
	    @Mock
	    ServletConfig sg;
	    @Mock
	    ServletContext context;
	    @Mock
	    RequestDispatcher dispatcher;
	    
	    HashMap<String, Object> requestMap;
	    
	    @Before
	    public void setUp() throws Exception {
	        request=mock(HttpServletRequest.class);
	        response=mock(HttpServletResponse.class);
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
	    public void testGetAllProducts() {
	    	requestMap=new HashMap<String, Object>();
	    	boolean flag=false;
	    	try {
		    	when(request.getParameter("b")).thenReturn(null);
		    	when(sg.getServletContext()).thenReturn(context);	
		    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
		    	
		    	ProductCatalogueControl servlet=new ProductCatalogueControl();
		    	servlet.init(sg);
		    	servlet.doGet(request, response);
		    	
		    	
		    	assertNotEquals(requestMap.get("prodotti"),null);
		    	flag=true;
	    	}catch(Exception e) {
				e.printStackTrace();
				fail("testGetAllProducts() not passed!");
			}finally {
	            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	        }
	    }
	    
	    @Test
	    public void testSearchByName() {
	    	requestMap=new HashMap<String, Object>();
	    	boolean flag=false;
	    	try {
		    	when(request.getParameter("by")).thenReturn("nome");
		    	when(request.getParameter("q")).thenReturn("oneplus");
		    	when(sg.getServletContext()).thenReturn(context);	
		    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
		    	requestMap.put("by", "nome");
		    	requestMap.put("q", "oneplus");
		    	
		    	ProductCatalogueControl servlet=new ProductCatalogueControl();
		    	servlet.init(sg);
		    	servlet.doGet(request, response);
		    	
		    	
		    	assertNotEquals(requestMap.get("prodotti"),null);
		    	ArrayList<Product> prd=(ArrayList<Product>)requestMap.get("prodotti");
		    	assertEquals(prd.size(),1);
		    	assertEquals(prd.get(0).getCodice(),1L);


		    	requestMap=new HashMap<String, Object>();
		    	
		    	requestMap.put("by", "nome");
		    	requestMap.put("q", "Nome non esistente");
		    	when(request.getParameter("q")).thenReturn("Nome non esistente");
		    	servlet=new ProductCatalogueControl();
		    	servlet.init(sg);
		    	servlet.doGet(request, response);
		    	assertNotEquals(requestMap.get("prodotti"),null);
		    	prd=(ArrayList<Product>)requestMap.get("prodotti");
		    	assertEquals(prd.size(),0);
		    	flag=true;
	    	}catch(Exception e) {
				e.printStackTrace();
				fail("testSearchByName() not passed!");
			}finally {
	            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	        }
	    }
	    
	    @Test
	    public void testSearchByCategoria() {
	    	requestMap=new HashMap<String, Object>();
	    	boolean flag=false;
	    	try {
		    	when(request.getParameter("by")).thenReturn("categoria");
		    	when(request.getParameter("q")).thenReturn("Televisori e monitor");
		    	when(sg.getServletContext()).thenReturn(context);	
		    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
		    	requestMap.put("by", "nome");
		    	requestMap.put("q", "Televisori e monitor");
		    	
		    	ProductCatalogueControl servlet=new ProductCatalogueControl();
		    	servlet.init(sg);
		    	servlet.doGet(request, response);
		    	
		    	
		    	assertNotEquals(requestMap.get("prodotti"),null);
		    	ArrayList<Product> prd=(ArrayList<Product>)requestMap.get("prodotti");
		    	assertEquals(prd.size(),1);
		    	assertEquals(prd.get(0).getCodice(),3L);
		    	
		    	requestMap=new HashMap<String, Object>();
		    	
		    	requestMap.put("by", "categoria");
		    	requestMap.put("q", "Categoria non esistente");
		    	when(request.getParameter("q")).thenReturn("Categoria non esistente");
		    	servlet=new ProductCatalogueControl();
		    	servlet.init(sg);
		    	servlet.doGet(request, response);
		    	assertNotEquals(requestMap.get("prodotti"),null);
		    	prd=(ArrayList<Product>)requestMap.get("prodotti");
		    	assertEquals(prd.size(),0);
		    	flag=true;
	    	}catch(Exception e) {
				e.printStackTrace();
				fail("testSearchByCategoria() not passed!");
			}finally {
	            if(flag) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	        }
	    }
	    
	    
}
