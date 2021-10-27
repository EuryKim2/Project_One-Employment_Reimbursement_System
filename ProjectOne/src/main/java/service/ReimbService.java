package service;

import org.apache.log4j.Logger;

import dao.ReimbDao;
import model.Reimb;

public class ReimbService {

	private ReimbDao rDao;
	private static Logger log = Logger.getLogger(ReimbService.class);
	
	public ReimbService() {
		// TODO Auto-generated constructor stub
	}

	public ReimbService(ReimbDao rDao) {
		super();
		this.rDao = rDao;
	}
	
	public Reimb getReimbVerify(int id) {
		try{
			Reimb r = rDao.getByName(id);
			if(r != null) {
					return r;
			}
			}catch(NullPointerException e) {
			log.debug("Could not find ticket with id: " + id +".");
			return null;
			}
		return null;
	}

	
}
