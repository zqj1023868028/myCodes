package com.zqj.pojo;

public class User {
	private int id;
	private String username;
	private String email;
	private int  grade;
	private String password;
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(int id, String username, String email, int grade, String password) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.grade = grade;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", grade=" + grade + ", password="
				+ password + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
