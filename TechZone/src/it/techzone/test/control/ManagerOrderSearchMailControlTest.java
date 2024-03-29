package it.techzone.test.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
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
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.mock.web.MockHttpSession;

import it.techzone.control.ManagerOrderSearchIdControl;
import it.techzone.control.ManagerOrderSearchMailControl;
import it.techzone.model.beans.Order;
import it.techzone.model.beans.Product;
import it.techzone.model.beans.ProductOrder;
import it.techzone.model.beans.Manager;
import it.techzone.model.beans.UtenteRegistrato;
import it.techzone.model.managers.OrderManager;
import it.techzone.model.managers.ProductManager;

public class ManagerOrderSearchMailControlTest extends Mockito {
	private static OrderManager om = new OrderManager();
	
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
    public void testYManagerYMail() throws IOException {
    	Order a;
    	boolean flag1 = false, flag2=false;
    	try {
    			sessioneMap=new HashMap<String, Object>();
    			sessioneMap.put("manager", new Manager());
    			when(request.getSession()).thenReturn(session);
    			when(request.getParameter("mailOrd")).thenReturn("g.simonini@gmail.com");
    			assertNotEquals(sessioneMap.get("manager"),null);
    			when(sg.getServletContext()).thenReturn(context);	
    			when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    	
    			ManagerOrderSearchMailControl servlet = new ManagerOrderSearchMailControl();
    			servlet.init(sg);
    	    	servlet.doGet(request, response);
    	    	
    			assertNotNull(sessioneMap.get("ordini"));
    			
    			a = om.getOrderById(1);//da aggiungere
    			ArrayList<Order> list = (ArrayList<Order>)sessioneMap.get("ordini");
    			for (Order b :list) {
    				if(a.getNumeroOrdine()==b.getNumeroOrdine()) {
    					assertEquals(a.getDataArrivo(),b.getDataArrivo());
    					assertEquals(a.getDataInvio(),b.getDataInvio());
    					assertEquals(a.getStato(),b.getStato());
    					assertEquals(a.getTotale(),b.getTotale(),0);
    					assertEquals(a.getProdotti().size(),b.getProdotti().size());
    					for(int i=0;a.getProdotti().size()<i;i++) {
    							ProductOrder ap = a.getProdotti().get(i);
    							ProductOrder bp=b.getProdotti().get(i);
    							assertEquals(ap.getCosto(),bp.getCosto(),0);
    							assertEquals(ap.getProdotto().getCodice(),bp.getProdotto().getCodice());//ai fini del productOrder basta valutare solo il codice del prodotto stesso
    							assertEquals(ap.getQuantita(),bp.getQuantita());
    							
    					}
    					
    				}
    			}
    		
    			flag1= true;
    			
    			
    	}
    	
    	catch(Exception e) {
    				e.printStackTrace();
    				fail("testYManagerYMail() not passed!");
    	}
    	
    	try {
    			sessioneMap=new HashMap<String, Object>();
    			sessioneMap.put("manager", new Manager());
    			when(request.getSession()).thenReturn(session);
    			when(request.getParameter("mailOrd")).thenReturn("g.fantozzi@gmail.com");
    			ManagerOrderSearchMailControl servlet = new ManagerOrderSearchMailControl();
    			servlet.doGet(request, response);
    			assertEquals(sessioneMap.get("alertMsg"),"Nessun ordine trovato per la mail: g.fantozzi@gmail.com");
    			flag2=true;
        		
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		fail("testYManagerNMail() not passed!");
        	}
    
    
    finally {
    	if(flag1==true && flag2==true) {
    		System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    	}
    		
    	}
    }
    
    @Test
   public void testNManager()  {
	   boolean flag= false;
	   try {
		   
	   			sessioneMap=new HashMap<String, Object>();
		    	
		    	when(request.getSession()).thenReturn(session);
		    	assertEquals(sessioneMap.get("manager"),null);
		    	ManagerOrderSearchIdControl servlet = new ManagerOrderSearchIdControl();

    			ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    			servlet.doGet(request, response);
    			verify(response).sendRedirect(captor.capture());
    			assertEquals("./HomePage.jsp", captor.getValue());
    			assertEquals("Operazione non autorizzata",sessioneMap.get("alertMsg"));
        		flag=true;
        		
	   }
	   catch(Exception e ) {
		   e.printStackTrace();
		   fail("testNManager() (not a manager) not passed!");
	   }
	   finally {
		   if(flag==true) {
			   System.out.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
		   }
	   }
   }
}
