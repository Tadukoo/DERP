package edu.ycp.cs320.derp.model;

public class User{
	private int userId;
	private String name;
	private String email;
	private String ip;
	
	public User(){
		
	}
	public User(int userId, String name, String email, String ip){
		this.userId = userId;
		this.name = name;
		this.email= email;
		this.ip = ip;
	}
	public int getUserId(){
		return userId;
	}
	
	public void setUserId(int userId){
		this.userId = userId;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getIP(){
		return ip;
	}
	
	public void setIP(String ip){
		this.ip = ip;
	}
}
