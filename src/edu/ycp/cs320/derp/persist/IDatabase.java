package edu.ycp.cs320.derp.persist;

import java.util.List;

import edu.ycp.cs320.derp.model.Pair;
import edu.ycp.cs320.derp.model.Poll;
import edu.ycp.cs320.derp.model.User;
import edu.ycp.cs320.derp.model.IpAddress;

public interface IDatabase {
	public List<Pair<User, Poll>> findUserAndPollByTitle(String title);
	public List<Pair<User, Poll>> findPollByUserName(String userName);
	public Integer insertPollIntoPollsTable(String title, String isbn, String lastName, String firstName);
	public List<Pair<User, Poll>> findAllPollsWithUsers();
	public List<User> findAllUsers();
	public List<User> removePollByTitle(String title);	
	// below not implemented in DerbyDatabase
	public List<String> FindIpaddressByUser(String userName);
	public Integer generateNewUser(String Username, String password, String email, String IPaddress);
	public Integer CheckIP(String Ipadress);
	public Integer AddIPadress(String Username, String ipadress);
	public Boolean CheckPassword(String Username, String password);
	public Boolean ChangePassword(String Username, String password);
	public Boolean IncrementPollCounter(String Username,String PollTitle, int PollType);
	
	
}
