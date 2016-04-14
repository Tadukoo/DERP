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

	private IDatabase db    = null;
	
	public MainContentController(){
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();	
	}
	/**
	 * Method to insert Polls into the database.
	 * 
	 * @param title
	 * @param isbn
	 * @param lastName
	 * @param firstName
	 * @return boolean ifInsert
	 */
	public boolean InsertPollController(String title, String isbn, String lastName, String firstName){
		// insert new poll intro the table
		Integer Poll_id = db.insertPollIntoPollsTable(title, isbn, lastName, firstName);
		
		// check to see if insertion suceeded
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
	public ArrayList<Pair<User, Poll>> SearchByPollTitle(String title){
		
		// get the list of (User, Poll) pairs from the db
		List<Pair<User, Poll>> userPollsList= db.findUserAndPollByTitle(title);
		
		if(userPollsList.isEmpty()){
			System.out.println("No Polls found for Title: " + title);
			return null;
		}
		else{
			System.out.println("Polls and User Pairs found and returned");
		}
		return (ArrayList<Pair<User, Poll>>) userPollsList;
	}
	
	//TODO:Implement Stub
	/**
	 * Returns a specified poll comprised of the Poll and the username.
	 * Temporarily a stub.
	 * @param title
	 * @param author
	 * @return
	 */
public Pair<User, Poll> SearchByPollTitleAndAuthor(String title, String author){
		
		// get the list of (User, Poll) pairs from the db
		User jack = new User(1,"Jack","jackreacker@gmail.com", "192.152.234.234");
		Poll strong = new Poll(1,1,"PowerLevel",23, 5);
		Pair<User, Poll> JackReacher = new Pair<User, Poll>(jack, strong);
	return JackReacher;		
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
//TODO: Implement Stub
/**
 * Method to create an new user accoutn and add it to the database.
 * @param Username
 * @param Password
 * @param email
 * @param ipAdress
 * @return true if it was sucessfully added to the database
 */
public Boolean CreateUserAccount(String Username, String Password, String email, String ipAdress){
		return true;
	}
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
 * @param IpAdress
 * @return
 */
public Boolean IPaddressAdd(String Username, String IpAdress){
		return true;
	}
//TODO: Implemnt Stub
/**
 * method to change a users password.
 * @param Username
 * @param Password
 * @return true if the change was made in the database
 */
public Boolean ChangePassword(String Username, String Password){
		return true;
	}
//TODO: Implement Stub
/**
 * Method to increment the positive poll counter for a specific poll
 * @param Username
 * @param PollTitle
 * @return
 */
public Boolean IncrementPositivePollCounter(String Username,String PollTitle){
		return true;
	}
//TODO: Implement Stub
/**
* Method to increment the total poll counter for a specific poll
* @param Username
* @param PollTitle
* @return
*/
public Boolean IncrementTotalPollCounter(String Username,String PollTitle){
		return true;
	}
//TODO: Implement Stub
/**
* Method to increment the pageview poll counter for a specific poll
* @param Username
* @param PollTitle
* @return
*/
public Boolean IncrementPollPageViewCounter(String Username,String PollTitle){
		return true;
	}
}

