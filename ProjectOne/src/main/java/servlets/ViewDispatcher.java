package servlets;

import javax.servlet.http.HttpServletRequest;

import controller.ViewController;

public class ViewDispatcher {
	
	public static String process(HttpServletRequest req) {
		switch(req.getRequestURI()) {
		case"/ProjectOne/login.change":
			return ViewController.login(req);
		case"/ProjectOne/wrongcreds.change":
			return "unsuccessfullogin.html";
		case"/ProjectOne/ticket_info.change":
			return "html/ticket.html";
		case"/ProjectOne/add_ticket.change":
			ViewController.addTicket(req);
			return "html/employee_home.html";
		case"/ProjectOne/update_ticket.change":
			ViewController.updateTicket(req);
			return "html/fm_home.html";
		default:
			return "/html/unsuccessfullogin.html";
		}
	}
	

}
