package service;
import org.apache.log4j.Logger;

import dao.UserDao;
import model.User;

public class UserService {
	
	private UserDao uDao;
	private static Logger log = Logger.getLogger(UserService.class);

	public UserService() {
		// TODO Auto-generated constructor stub
	}

	public UserService(UserDao uDao) {
		super();
		this.uDao = uDao;
	}

	public User getUserVerify(String name, String password) {
		try{
			User u = uDao.getByName(name);
			if(u != null) {
				if(u.getPassWord().equals(password)) {
					log.info("Successfully logged in as " + name);
					return u;
				}
			}
			}catch(NullPointerException e) {
			log.debug("Could not find user with name: " + name + " and password: " + password +".");
			return null;
			}
		return null;
	}
	
}
