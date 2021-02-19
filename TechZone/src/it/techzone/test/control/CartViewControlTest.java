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
import it.techzone.control.CartViewControl;
import it.techzone.model.beans.Cart;
import it.techzone.model.beans.Manager;
import it.techzone.model.beans.UtenteRegistrato;

public class CartViewControlTest extends Mockito {

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

	
    public void testViewNotManager() {
    	boolean flag = false;
    	try {
    			when(request.getSession()).thenReturn(session);
    			when(sg.getServletContext()).thenReturn(context);	
    			when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    			assertEquals(sessioneMap.get("manager"),null);
    			CartViewControl servlet = new CartViewControl();
    			servlet.init(sg);
    	    	servlet.doGet(request, response);
    	    	assertNotEquals(sessioneMap.get("cart"),null);
    			flag=true;
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("testViewNotManager() not passed!");
    	}
    
    finally {
        if(flag==true) System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }
    }
    public void testViewManager() {
    	boolean flag=false;
    	
    	try {
    		sessioneMap=new HashMap<String, Object>();
   			sessioneMap.put("manager", new Manager());
	   		when(request.getSession()).thenReturn(session);
	   		assertEquals(sessioneMap.get("utente"),null);
	    	assertNotEquals(sessioneMap.get("manager"),null);
	    	CartViewControl servlet = new CartViewControl();
	    	servlet.doGet(request, response);
	    	ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    		verify(response).sendRedirect(captor.capture());
    		assertEquals("./HomePage.jsp", captor.getValue());
    		assertEquals("Non puoi accedere al carrello", sessioneMap.get("alertMsg"));
    		flag=true;
    		
   }
   catch(Exception e ) {
	   e.printStackTrace();
	   fail("testViewManager() not passed!");
   }
   finally {
	   if(flag==true) {
		   System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
	   }
   }
    }
    
}

