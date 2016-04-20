package edu.ycp.cs320.derp.model;

public class IpAddress {

	private int IPId;
	private int UserId;
	private String ip;
	
	public IpAddress(){
		
	}
	public IpAddress(int IpId, int UserId, String ip){
		this.IPId = IpId;
		this.UserId = UserId;
		this.ip = ip;
	}
	public int getIPId() {
		return IPId;
	}
	public void setIPId(int iPId) {
		IPId = iPId;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
}
