package it.techzone.test.control;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import junit.framework.TestCase;
public class LoginControlTest extends TestCase{
	@Mock
    HttpServletRequest request;
 
    @Mock
    HttpServletResponse response;
 
    @Mock
    HttpSession sessione;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
}
