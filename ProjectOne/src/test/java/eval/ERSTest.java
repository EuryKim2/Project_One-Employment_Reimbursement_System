package eval;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;

import controller.ViewController;
import dao.DBConnection;
import dao.UserDao;
import service.UserService;
import servlets.ViewDispatcher;

public class ERSTest{
	
	@Mock
	DBConnection con = new DBConnection();
	
	@Mock
	UserDao uDao = new UserDao(con);
	
	@Mock
	UserService userServ = new UserService(uDao);
		
	@Test
	public void userVerifyTest() throws IOException, ServletException{	
		assertEquals("first", userServ.getUserVerify("employ", "password").getFirstName());
	}
	
	@Test
    public void processSuccessTest() throws IOException, ServletException {
		
		//HttpServletRequest req = mock(HttpServletRequest.class);
		MockHttpServletRequest req = new MockHttpServletRequest();

		req.setRequestURI("/ProjectOne/ticket_info.change");

        assertEquals("html/ticket.html", ViewDispatcher.process(req));
    }
	
	@Test
    public void processFailTest() throws IOException, ServletException {
		
		MockHttpServletRequest req = new MockHttpServletRequest();

		req.setRequestURI("doesnotexist");

        assertEquals("/html/unsuccessfullogin.html", ViewDispatcher.process(req));
    }
	
	@Test
    public void loginSuccessTest() throws IOException, ServletException {
		
		MockHttpServletRequest req = new MockHttpServletRequest();

		req.setMethod("POST");
		req.setParameter("username", "employee");
		req.setParameter("password", "123");

        assertEquals("html/employee_home.html", ViewController.login(req));
    }
	
	@Test
	public void loginFailTest() {
		MockHttpServletRequest req = new MockHttpServletRequest();

		req.setMethod("POST");
		req.setParameter("username", "aswfdxfe");
		req.setParameter("password", "sfghetkjsf");

        assertEquals("/ProjectOne/wrongcreds.change", ViewController.login(req));
	}
}
