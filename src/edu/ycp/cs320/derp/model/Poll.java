package edu.ycp.cs320.derp.model;

public class Poll{
	private int pollId;
	private int userId;
	private String title;
	private int totalVotes;
	private int yesVotes;
	private int pageViews;
	private String Description;
	public Poll(){
		
	}
	public Poll(int pollId, int userId, String title, int totalVotes, int yesVotes,int pageView, String Description){
		this.pollId = pollId;
		this.userId = userId;
		this.title = title;
		this.totalVotes = totalVotes;
		this.yesVotes = yesVotes;
		this.pageViews = pageView;
		this.Description = Description;
	}
	public Poll(int pollId, int userId, String title, String Description){
		this.pollId = pollId;
		this.userId = userId;
		this.title = title;
		this.totalVotes = 0;
		this.yesVotes = 0;
		this.pageViews = 0;
		this.Description = Description;
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
	public int getPageViews() {
		return pageViews;
	}
	public void setPageViews(int pageViews) {
		this.pageViews = pageViews;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
}
