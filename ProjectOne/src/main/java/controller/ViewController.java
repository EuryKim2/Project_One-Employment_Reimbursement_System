package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DBConnection;
import dao.ReimbDao;
import dao.UserDao;
import model.Reimb;
import model.User;
import service.ReimbService;
import service.UserService;

public class ViewController {
	
	public static DBConnection con = new DBConnection();
	static ReimbDao rDao = new ReimbDao(con);
	static UserService userServ = new UserService(new UserDao(con));
	static ReimbService reimbServ = new ReimbService(rDao);
	private static Logger log = Logger.getLogger(ViewController.class);
	
	public static String login(HttpServletRequest req) {
		if(!req.getMethod().equals("POST")) {
			return "html/index.html";
		}
		
		User u = userServ.getUserVerify(req.getParameter("username"), req.getParameter("password"));
		
		if(u == null) {
			return "/ProjectOne/wrongcreds.change";
		}
		req.getSession().setAttribute("currentUser", u);
		if(u.getRole() == 1){
			return "html/fm_home.html";
		}else{
			return "html/employee_home.html";
		}
		
	}
	
	public static void addTicket(HttpServletRequest req) {
		if(!req.getMethod().equals("POST")) {
			return;
		}
		
		User u = (User)req.getSession().getAttribute("currentUser");
		Reimb r = new Reimb(Double.valueOf(req.getParameter("amount")), req.getParameter("descr")
				, u.getUserId(), 1, Integer.valueOf(req.getParameter("type")));
		log.info("Inserted ticket with amount: " + r.getAmount() + " and type: " + r.getType() + ".");
		rDao.insert(r);
	}
	
	public static void updateTicket(HttpServletRequest req) {
		if(!req.getMethod().equals("POST")) {
			return;
		}
		
		User u = (User)req.getSession().getAttribute("currentUser");
		Reimb r = rDao.getByName(Integer.valueOf(req.getParameter("id")));
		r.setResolved(Timestamp.valueOf(LocalDateTime.now()));
		r.setStatus(Integer.valueOf(req.getParameter("aord")));
		
		log.info("Updated ticket of id: " + r.getReimbId() + " with the time resolved: " + r.getResolved() + 
				"and resolver: " + r.getResolver() + " to status: " + r.getStatus() + ".");
		
		rDao.update(r, u.getUserId());
	}
	
	public static void getSessionUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		User u = (User)req.getSession().getAttribute("currentUser");
		log.trace("Returned session user: " + u.getUserName());
		res.getWriter().write(new ObjectMapper().writeValueAsString(u));
	}
	
	public static void getUserTickets(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		User u = (User)req.getSession().getAttribute("currentUser");
		res.getWriter().write(new ObjectMapper().writeValueAsString(rDao.getAllById(u)));
	}
	
	public static void getAllTickets(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		res.getWriter().write(new ObjectMapper().writeValueAsString(rDao.getAll()));
	}

}
