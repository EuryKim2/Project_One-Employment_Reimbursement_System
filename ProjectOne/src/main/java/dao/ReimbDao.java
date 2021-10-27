package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.Reimb;
import model.User;

public class ReimbDao implements GenericDao<Reimb> {

	private DBConnection rCon;
	private static Logger log = Logger.getLogger(ReimbDao.class);
	
	public ReimbDao() {
		// TODO Auto-generated constructor stub
	}

	public ReimbDao(DBConnection rCon) {
		super();
		this.rCon = rCon;
	}

	@Override
	public List<Reimb> getAll() {
		List<Reimb> reimbList = new ArrayList<>();
		  
		  try(Connection con = rCon.getDBConnection()){
		  
			 String sql = "select * from ers_reimbursement";
		  
		  	PreparedStatement ps = con.prepareStatement(sql); 
		  	ResultSet rs = ps.executeQuery();
		  
		  	while(rs.next()) { 
		  		reimbList.add(new Reimb(rs.getInt(1),rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4),
			  			rs.getString(5),rs.getInt(6),rs.getInt(7), rs.getInt(8), rs.getInt(9))); 
		  		}
		  	
		  }catch(SQLException e) { 
			  e.printStackTrace();
			  log.debug("Could not get all tickets");
		  } 
		  
		  log.info("Returned all tickets");
		  return reimbList;
	}
	
	public List<Reimb> getAllById(User u) {
		List<Reimb> reimbList = new ArrayList<>();
		  
		  try(Connection con = rCon.getDBConnection()){
		  
			 String sql = "select * from ers_reimbursement where reimb_author=" + u.getUserId();
		  
		  	PreparedStatement ps = con.prepareStatement(sql); 
		  	ResultSet rs = ps.executeQuery();
		  
		  	while(rs.next()) { 
		  		reimbList.add(new Reimb(rs.getInt(1),rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4),
			  			rs.getString(5),rs.getInt(6),rs.getInt(7), rs.getInt(8), rs.getInt(9))); 
		  		}
		  }catch(SQLException e) { 
			  e.printStackTrace();
			  log.debug("Could not get all tickets with user id: " +u.getUserId());
		  } 
		  
		  log.info("Returned all tickets with user id: " + u.getUserId());
		  return reimbList;
	}
	
	public Reimb getByName(int id) {
		try(Connection con = rCon.getDBConnection()) {
			String sql = "select * from ers_reimbursement r where r.reimb_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Reimb r = new Reimb();
			while(rs.next()) {
				r = new Reimb(rs.getInt(1),rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4),
			  			rs.getString(5),rs.getInt(6),rs.getInt(7), rs.getInt(8), rs.getInt(9));
			}
			return r;

		} catch (SQLException e) {
			e.printStackTrace();
			log.trace(e);
		}
		return null;
	}

	@Override
	public Reimb getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Reimb entity, int id) {
		try(Connection con = rCon.getDBConnection()){
			
			String sql = "update ers_reimbursement set reimb_resolved=?, reimb_resolver=?, reimb_status_id=? where reimb_id=" + entity.getReimbId();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setTimestamp(1, entity.getResolved());
			ps.setInt(2, id);
			ps.setInt(3, entity.getStatus());
			ps.execute();

			
		}catch(SQLException e){
			e.printStackTrace();
			log.trace(e);
		}
		
	}

	@Override
	public void insert(Reimb entity) {
		try(Connection con = rCon.getDBConnection()){
			
			String sql = "{?= call insert_reimb(?,?,?,?,?,?,?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setDouble(2, entity.getAmount());
			cs.setTimestamp(3,  entity.getSubmitted());
			cs.setTimestamp(4,  entity.getResolved());
			cs.setString(5, entity.getDescr());
			cs.setInt(6, entity.getAuthor());
			cs.setInt(7, entity.getStatus());
			cs.setInt(8, entity.getType());
			cs.execute();
			
		}catch(SQLException e){
			e.printStackTrace();
			log.trace(e);
		}
	}

	@Override
	public void delete(Reimb entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Reimb entity) {
		// TODO Auto-generated method stub
		
	}

}
