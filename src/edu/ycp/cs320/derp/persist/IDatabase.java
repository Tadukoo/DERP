package edu.ycp.cs320.derp.persist;

import java.util.List;

import edu.ycp.cs320.derp.model.Pair;
import edu.ycp.cs320.derp.model.Poll;
import edu.ycp.cs320.derp.model.User;
import edu.ycp.cs320.derp.model.IpAddress;

public interface IDatabase {
	public List<Pair<User, Poll>> findUserAndPollByTitle(String title);
	public List<Pair<User, Poll>> findPollByUserName(String userName);
	// First two done by Logan.
	public Integer insertPollIntoPollsTable(String title, String Description, String userName);
	public List<User> findAllUsers();
	public Boolean removePollByTitle(String title, String userName);
	public List<Poll> findAllPolls();// sorted by totalCount
	public Integer generateNewUser(String firstname, String lastname, String Username, String password, String email, String Institution, String IPAdress);
	// below not implemented in DerbyDatabase
	public Boolean CheckPassword(String Username, String password);
	public Boolean IncrementCounter(String Username,String PollTitle, int CounterType);
	
	// Not necessarily needed
	public List<String> FindIpaddressByUser(String userName);
	public Integer CheckIP(String Ipadress);
	public Integer AddIPadress(String Username, String ipadress);
	public Boolean ChangePassword(String Username, String password);
	
	
}
