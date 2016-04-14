package edu.ycp.cs320.derp.model;

public class PollUser{
	private int pollId;
	private int userId;
	
	public PollUser(){
		
	}
	
	public int getPollId(){
		return pollId;
	}
	
	public void setPollId(int pollId){
		this.pollId = pollId;
	}
	
	public int getUserId(){
		return userId;
	}
	
	public void setUserId(int userId){
		this.userId = userId;
	}
}
