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
	 * Returns a specified Pair of User and Poll.
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
}

