package model;


public class User {
	
	private int userId;
	private String userName;
	private String userPW;
	private String firstName;
	private String lastName;
	private String email;
	private int role;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return userPW;
	}
	public void setPassWord(String userPW) {
		this.userPW = userPW;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getUserId() {
		return userId;
	}
	
	public User() {
	}
	
	public User(String userName, String userPW, String firstName, String lastName, String email,
			int role) {
		super();
		this.userName = userName;
		this.userPW = userPW;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}
	
	
	public User(int userId, String userName, String userPW, String firstName, String lastName, String email,
			int role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPW = userPW;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", passWord=" + userPW + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", role=" + role + "]";
	}
	
}
