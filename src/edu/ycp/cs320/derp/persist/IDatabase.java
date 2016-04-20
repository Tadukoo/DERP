package edu.ycp.cs320.derp.persist;

import java.util.List;

import edu.ycp.cs320.derp.model.Pair;
import edu.ycp.cs320.derp.model.Poll;
import edu.ycp.cs320.derp.model.User;
import edu.ycp.cs320.derp.model.IpAddress;

public interface IDatabase {
	public List<Pair<User, Poll>> findUserAndPollByTitle(String title);
	public List<Pair<User, Poll>> findUserAndPollByUserName(String userName);
	public Integer insertPollIntoPollsTable(String title, String isbn, String lastName, String firstName);
	public List<Pair<User, Poll>> findAllPollsWithUsers();
	public List<User> findAllUsers();
	public List<User> removePollByTitle(String title);	
	// bellow not implemented in DerbyDatabase
	public List<String> FindIpaddressByUser(String userName);
	
}
