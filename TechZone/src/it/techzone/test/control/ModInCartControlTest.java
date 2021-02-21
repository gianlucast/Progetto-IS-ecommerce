package it.techzone.test.control;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
import org.springframework.mock.web.MockHttpSession;

import it.techzone.control.AddToCartControl;
import it.techzone.control.ModInCartControl;
import it.techzone.model.beans.Cart;
import it.techzone.model.beans.Manager;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.managers.CartManager;
import it.techzone.model.managers.ProductManager;

public class ModInCartControlTest extends Mockito {
	private static CartManager cm = new CartManager();
	private static ProductManager pm = new ProductManager();
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
    
    

    HashMap<String, Object> sessioneMap;
    
    @Before
    public void setUp() throws Exception {
        request=mock(HttpServletRequest.class);
        response=mock(HttpServletResponse.class);
        session=mock(HttpSession.class);
        sg=mock(ServletConfig.class);
        context = mock(ServletContext .class);
        dispatcher=mock(RequestDispatcher.class);
    
    
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
    }
    
    @Test
    public void testModInCartNManager() throws IOException {
    	Cart a= new Cart();
    	Cart b= new Cart();
    	sessioneMap=new HashMap<String, Object>();
    	boolean flag1 = false;
    	try {
    			
    			a.addToCart(pm.retrieveProduct(1), 3);
    			b.addToCart(pm.retrieveProduct(1), 1);
    			sessioneMap.put("cart", b);
    			session.setAttribute("cart", b);
    			when(request.getSession()).thenReturn(session);
    			when(request.getParameter("change")).thenReturn("3");
    			when(request.getParameter("idProd")).thenReturn("1");
    			when(sg.getServletContext()).thenReturn(context);	
		    	when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    			
    			assertEquals(sessioneMap.get("manager"),null);
    			ModInCartControl servlet = new ModInCartControl();
    			servlet.init(sg);
    			servlet.doGet(request, response);
    			b= (Cart) sessioneMap.get("cart");
    			assertEquals(a.getProductList().size(),b.getProductList().size());
    			assertEquals(a.getProductList().get(0).getProdotto().getCodice(),b.getProductList().get(0).getProdotto().getCodice());
    			assertEquals(a.getProductList().get(0).getQuantita(),b.getProductList().get(0).getQuantita());
    			assertEquals(a.getPrezzoTotale(),b.getPrezzoTotale(),1);
    			flag1= true;
    	}
    	
    	catch(Exception e) {
    				e.printStackTrace();
    				fail("testModInCartNManager() not passed!");
    	}
    	
    
    finally {
    	if(flag1==true ) {
    		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    	}
    		
    	}
    }
    
    @Test
   public void testModInCartYManager()  {
	   boolean flag= false;
	   try {
	   			sessioneMap=new HashMap<String, Object>();
	   			sessioneMap.put("manager", new Manager());
		   		when(request.getSession()).thenReturn(session);
		   		assertEquals(sessioneMap.get("utente"),null);
		    	assertNotEquals(sessioneMap.get("manager"),null);
		   		AddToCartControl servlet = new AddToCartControl();
        		servlet.doGet(request, response);
        		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        		verify(response).sendRedirect(captor.capture());
        		assertEquals("./HomePage.jsp", captor.getValue());
        		assertEquals("Accesso non autorizzato", sessioneMap.get("alertMsg"));
        		flag=true;
        		
	   }
	   catch(Exception e ) {
		   e.printStackTrace();
		   fail("testYAddToCartFail() (manager asks for the update) not passed!");
	   }
	   finally {
		   if(flag==true) {
			   System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		   }
	   }
   }
}
