package edu.ycp.cs320.derp.model;

public class Poll{
	private int pollId;
	private int userId;
	private String title;
	private int totalVotes;
	private int yesVotes;
	
	public Poll(){
		
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
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public int getTotalVotes(){
		return totalVotes;
	}
	
	public void setTotalVotes(int totalVotes){
		this.totalVotes = totalVotes;
	}
	
	public int getYesVotes(){
		return yesVotes;
	}
	
	public void setYesVotes(int yesVotes){
		this.yesVotes = yesVotes;
	}
}
