package servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import controller.ViewController;
import model.User;

public class JSONDispatcherServlet {
	
	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		switch(req.getRequestURI()) {
		
		 case "/ProjectOne/getsessionuser.json":
			 ViewController.getSessionUser(req, res);
			 break;
		 case "/ProjectOne/getusertickets.json":
			 ViewController.getUserTickets(req, res);
			 break;
		 case "/ProjectOne/getalltickets.json":
			 ViewController.getAllTickets(req, res);
			 break;
		default:
			res.getWriter().write(new ObjectMapper().writeValueAsString(new User()));
		
		}
	}

}
