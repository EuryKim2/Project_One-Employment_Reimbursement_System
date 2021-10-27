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

import model.User;

public class UserDao implements GenericDao<User> {
	
	private DBConnection uCon;
	private static Logger log = Logger.getLogger(UserDao.class);
	
	public UserDao() {
	}
	
	public UserDao(DBConnection uCon) {
		super();
		this.uCon = uCon;
	}

	@Override
	public List<User> getAll() {
		List<User> userList = new ArrayList<>();
		  
		  try(Connection con = uCon.getDBConnection()){
		  
			  String sql = "select * from ers_users";
		  
		  	PreparedStatement ps = con.prepareStatement(sql); 
		  	ResultSet rs = ps.executeQuery();
		  
		  	while(rs.next()) { 
			  	userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
			  			rs.getString(4),rs.getString(5),rs.getString(6), rs.getInt(7))); 
		  		}
		  }catch(SQLException e) { 
			  e.printStackTrace();
			  log.trace(e);
		  } 
		  
		  return userList;

	}
	
	@Override
	public User getByName(String name) {
		
		try(Connection con = uCon.getDBConnection()) {
			String sql = "select * from ers_users u where u.ers_username=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			User u = new User();
			while(rs.next()) {
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
			  			rs.getString(4),rs.getString(5),rs.getString(6), rs.getInt(7));
			}
			return u;

		} catch (SQLException e) {
			e.printStackTrace();
			log.trace(e);;
		}
		return null;
	}

	@Override
	public void update(User entity) {
		try(Connection con = uCon.getDBConnection()){
			
			String sql = "update userinfo set userlevel=? where userid=" + entity.getUserId();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, entity.getRole());
			ps.execute();

			
		}catch(SQLException e){
			e.printStackTrace();
			log.trace(e);
		}
		
	}

	@Override
	public void insert(User entity) {
		try(Connection con = uCon.getDBConnection()){
			
			String sql = "{?= call insert_user(?,?,?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, entity.getUserName());
			cs.setString(3, entity.getPassWord());
			cs.setInt(4, entity.getRole());
			cs.execute();
			
		}catch(SQLException e){
			e.printStackTrace();
			log.trace(e);
		}
		
	}

	@Override
	public void delete(User entity) {
		try(Connection con = uCon.getDBConnection()){
			
			String sql = "delete from table where userid=" + entity.getUserId();
			PreparedStatement cs = con.prepareStatement(sql);

			
		}catch(SQLException e){
			e.printStackTrace();
			log.error(e);
		}
		
	}

}
