package edu.ycp.cs320.derp.controller;

import edu.ycp.cs320.derp.persist.IDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.derp.model.Pair;
import edu.ycp.cs320.derp.model.Poll;
import edu.ycp.cs320.derp.model.User;
import edu.ycp.cs320.derp.persist.DatabaseProvider;
import edu.ycp.cs320.derp.persist.DerbyDatabase;

public class MainContentController {

	private IDatabase db  = null;
	
	public MainContentController(){
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();	
	}
	/**
	 * Method to insert Polls into the database.
	 * 
	 * @param title
	 * @param description
	 * @param username
	 * @return boolean ifInsert
	 */
	public boolean InsertPoll(String title, String description, String username){
		// insert new poll into the table
		Integer Poll_id = db.insertPollIntoPollsTable(title, description, username);
		
		// check to see if insertion succeeded
		if(Poll_id > 0)
		{
			System.out.println("New Poll (ID: " + Poll_id + ") sucessfully added to Poll Table: <" +title + ">");
			return true;
		}
		else
		{
			System.out.println("Failed to insert new Poll(ID: " + Poll_id + ") into Poll Table: <" +title + ">");
			return false;
		}
	}

	/**
	 * Method to return a ArrayList of User Poll pairs.
	 * Main use is to create a search result to click a hyperlink.
	 ***/
	public Poll SearchByPollTitle(String title, String username){
		
		// get the list of (User, Poll) pairs from the db
		Poll userPolls= db.findPollByTitle(title, username);
		
		if(userPolls.getPollId() != 0){
			System.out.println("No Polls found for Title: " + title);
			return null;
		}
		else{
			System.out.println("Polls and User Pairs found and returned");
		}
		return userPolls;
	}
	
	//TODO:Implement Stub
	/**
	 * Returns a specified poll comprised of the Poll and the username.
	 * Temporarily a stub.
	 * @param title
	 * @param author
	 * @return
	 */

public Poll SearchByPollTitleAndAuthor(String title, String username){
		
	
		// get the list of (User, Poll) pairs from the db
//		User jack = new User(1,"Jack","Reacher","jackreacker@gmail.com", "The Organization", "jaReacher", "bam");
//		Poll strong = new Poll(1,1,"PowerLevel",23, 5, 0, "The number of Votes is how Strong you are.");
//		Pair<User, Poll> JackReacher = new Pair<User, Poll>(jack, strong);
	Poll  returned= db.findPollByTitle(title, username);
	return returned;		
	}

//TODO: implement Stub
/**
 * Returns True if the username and password match a database entry
 * Temporarily a stub
 * @param userName
 * @param password
 * @return
 */
public Boolean UserNamePasswordCheck(String userName, String password){
		return db.CheckPassword(userName, password);
	}

//TODO: Implement Stub
/**
 * Method to create an new user account and add it to the database.
 * @param Username
 * @param Password
 * @param email
 * @param ipAdress
 * @return true if it was successfully added to the database
 * the integer it returns tells what error happened
 */
public Boolean CreateUserAccount(String firstname, String lastname, String Username, String Password, String email, String ipAdress, String Institution){
	final int result = db.generateNewUser(firstname, lastname, Username, Password, email, Institution, ipAdress);
	if(result == 1){
		System.out.println("Username already exits!");
		return false;
	}
	if(result == 2){
		System.out.println("User not properly stored in database!");
		return false;
	} if(result == 0){
		return true;
	}
	System.out.println("General User creation error.");
		return false;
	}
//Implemented 5/1/2016 Alex Keperling
/**
 * Method to increment the positive poll counter for a specific poll
 * @param Username
 * @param PollTitle
 * @return
 */
public Boolean IncrementYesPollCounter(int poll_id){
		return db.IncrementCounter(poll_id, 1);
	}
//Implemented 5/1/2016 Alex Keperling
/**
* Method to increment the total poll counter for a specific poll
* @param Username
* @param PollTitle
* @return
*/
public Boolean IncrementTotalPollCounter(int poll_id){
		return db.IncrementCounter(poll_id, 2);
	}
//Implemented 5/1/2016 Alex Keperling
/**
* Method to increment the pageview poll counter for a specific poll
* @param Username
* @param PollTitle
* @return
*/
public Boolean IncrementPollPageViewCounter(int Poll_id){
		return db.IncrementCounter(Poll_id, 3);
	}
public User GetUserByUsername(String username){
	User temp = db.findUserInformation(username);
	temp.setPassword(null);
	return temp;
}

public Poll GetPollByTitle(String title, String username ){
	return db.findPollByTitle(title, username);
}
public List<Poll> FindAllPolls(){
	return db.findAllPolls();
}

public Boolean removePoll(String title, String username){
	return db.removePollByTitle(title, username);
}
public List<Poll> findPollByTitle(String title){
	List<Pair<User, Poll>> returned= db.findUserAndPollByTitle(title);
	List<Poll> polls = new ArrayList<Poll>();
	if(!returned.equals(null)){
		for(int i=0; i<returned.size(); i++) {
		polls.add( returned.get(i).getRight());	
		}
	}
	return polls;
}
//TODO: exception happening
public List<Poll> findPollByUsername(String userName){
	List<Pair<User, Poll>> returned= db.findPollByUserName(userName);
	List<Poll> polls = new ArrayList<Poll>();
	for(int i=0; i<returned.size(); i++) {
		System.out.println("ds");
		polls.add( returned.get(i).getRight());	
	}
	return polls;
}
public User findUserbyUserId(int id){
	return db.findUserByuserid(id);
}

///////////////////////////////////////////Not needed for produce///////////////////////////////
//TODO: implement stub
/**
* Method to check that the ip adress is in the database for that user.
* @param Username
* @param IPAdress
* @return boolean for if it was in the database
*/
public Boolean IPAdressCheck(String Username, String IPAdress){
		return true;
	}

//TODO: implement Stub
/**
* method to add the users ip address to their address list
* @param Username
* @param IpAddress
* @return
*/
public Boolean IPaddressAdd(String Username, String IpAdress){
		return true;
	}
//TODO: Implement Stub
/**
* method to change a users password.
* @param Username
* @param Password
* @return true if the change was made in the database
*/
public Boolean ChangePassword(String Username, String Password){
		return true;
	}
//TODO: implement Stub
/**
* Method used to change the email of a User
* @param Username
* @param email
* @return true if email has been change, false if it hasn't.
*/
public Boolean ChangeEmail(String Username,String email){
		return true;
	}
}