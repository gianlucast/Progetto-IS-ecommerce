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
import it.techzone.model.beans.Cart;
import it.techzone.model.beans.Manager;
import it.techzone.model.beans.UtenteRegistrato;

public class AddToCartControlTest extends Mockito {
	
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
    
    public void testNotManager() throws IOException {
    	Cart a;
    	boolean flag1 = false, flag2=false;
    	try {
    			sessioneMap=new HashMap<String, Object>();
    	
    			when(request.getSession()).thenReturn(session);
    			when(request.getParameter("idProd")).thenReturn("1");
    			assertEquals(sessioneMap.get("utente"),null);
    	
    			AddToCartControl servlet = new AddToCartControl();
    			servlet.doGet(request, response);
    			ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    	
    			verify(response).sendRedirect(captor.capture());
    			assertEquals("./CartView.jsp", captor.getValue());
    			assertNotNull(sessioneMap.get("cart"));
    			a= (Cart) sessioneMap.get("cart");
    			assertEquals(a.getProductList().get(0).getProdotto().getCodice(),1);
    			assertEquals(a.getProductList().get(0).getQuantita(),1);
    			assertEquals(a.getPrezzoTotale(),398.99,0);
    		
    		
    			flag1= true;
    	}
    	
    	catch(Exception e) {
    				e.printStackTrace();
    				fail("testNotManager() not passed!");
    	}
    	
    	try {
    			session = new MockHttpSession();
    			a=new Cart();
    			session.setAttribute("cart", a);
    			sessioneMap=new HashMap<String, Object>();
    	    	
        		when(request.getSession()).thenReturn(session);
        		when(request.getParameter("idProd")).thenReturn("1");
        		assertEquals(sessioneMap.get("utente"),null);
        		AddToCartControl servlet = new AddToCartControl();
        		servlet.doGet(request, response);
        		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        	
        		verify(response).sendRedirect(captor.capture());
        		assertEquals("./CartView.jsp", captor.getValue());
        		a= (Cart) sessioneMap.get("cart");
        		assertEquals(a.getProductList().get(0).getProdotto().getCodice(),1);
        		assertEquals(a.getProductList().get(0).getQuantita(),1);
        		assertEquals(a.getPrezzoTotale(),398.99,0);
        		
        		
        		flag1= true;
        		
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("testNotManager() not passed!");
        	}
    
    
    finally {
    	if(flag1==true && flag2==true) {
    		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    	}
    		
    	}
    }
   public void testYesManager()  {
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
		   fail("testYesManager() not passed!");
	   }
	   finally {
		   if(flag==true) {
			   System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		   }
	   }
   }
}
