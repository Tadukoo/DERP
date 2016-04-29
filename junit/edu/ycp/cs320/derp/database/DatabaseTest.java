package edu.ycp.cs320.derp.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.ycp.cs320.derp.model.Pair;
import edu.ycp.cs320.derp.model.Poll;
import edu.ycp.cs320.derp.model.User;
import edu.ycp.cs320.derp.persist.DatabaseProvider;
import edu.ycp.cs320.derp.persist.DerbyDatabase;
import edu.ycp.cs320.derp.persist.IDatabase;

public class DatabaseTest{
	private IDatabase db = null;
	
	ArrayList<User> users = null;
	ArrayList<Poll> polls = null;
	List<Pair<User, Poll>> pollUserList = null;
	List<Pair<User, Poll>> userPollList = null;	
	
	@Before
	public void setUp() throws Exception {
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testFindUserAndPollByTitle(){
		System.out.println("\n*** Testing findUserAndPollByTitle ***");
		
		String title = "I am the one";

		// get the list of (Author, Book) pairs from DB
		userPollList = db.findUserAndPollByTitle(title);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (userPollList.isEmpty()) {
			System.out.println("No poll found in database with title <" + title + ">");
			fail("No poll with title <" + title + "> returned from Database");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {			
			User user = null;
			Poll poll = null;
			for (Pair<User, Poll> userPoll : userPollList) {
				user = userPoll.getLeft();
				poll = userPoll.getRight();
			}
			assertEquals(2, user.getUserId());
			assertEquals("John", user.getFirstName());
			assertEquals("Wayne", user.getLastName());
			assertEquals("Gringo", user.getUserName());
			assertEquals("western", user.getPassword());
			assertEquals("TheMan@dead.com", user.getEmail());
			assertEquals("Hollywood", user.getInstitution());
			
			assertEquals(1, poll.getPollId());
			assertEquals(2, poll.getUserId());
			assertEquals("I am the one", poll.getTitle());
			assertEquals(55, poll.getTotalVotes());
			assertEquals(33, poll.getYesVotes());
			assertEquals(1000, poll.getPageViews());
			assertEquals("This is a desciption", poll.getDescription());
		}
	}
	
	@Test
	public void testFindPollByUserName(){
		System.out.println("\n*** Testing findPollByUserName ***");
		
		String userName = "Harry13";

		// get the list of (Author, Book) pairs from DB
		userPollList = db.findPollByUserName(userName);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (userPollList.isEmpty()) {
			System.out.println("No user found in database with username <" + userName + ">");
			fail("No user with username <" + userName + "> returned from Database");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {			
			User user = null;
			Poll poll = null;
			for(int i = 0; i < userPollList.size(); i++){
			//for (Pair<User, Poll> userPoll : userPollList) {
				user = userPollList.get(i).getLeft();
				poll = userPollList.get(i).getRight();
				
				assertEquals(1, user.getUserId());
				assertEquals("Harry", user.getFirstName());
				assertEquals("Hamlet", user.getLastName());
				assertEquals("Harry13", user.getUserName());
				assertEquals("password", user.getPassword());
				assertEquals("HaryHamlet@gmail.com", user.getEmail());
				assertEquals("York College of Pennsylvania", user.getInstitution());
				
				if(i == 0){
					assertEquals(9, poll.getPollId());
					assertEquals(1, poll.getUserId());
					assertEquals("Are Linked lists Useful", poll.getTitle());
					assertEquals(1, poll.getTotalVotes());
					assertEquals(0, poll.getYesVotes());
					assertEquals(5, poll.getPageViews());
					assertEquals("good", poll.getDescription());
				}else if(i == 1){
					assertEquals(8, poll.getPollId());
					assertEquals(1, poll.getUserId());
					assertEquals("Can you feel it", poll.getTitle());
					assertEquals(2, poll.getTotalVotes());
					assertEquals(1, poll.getYesVotes());
					assertEquals(2, poll.getPageViews());
					assertEquals("a", poll.getDescription());
				}else if(i == 2){
					assertEquals(6, poll.getPollId());
					assertEquals(1, poll.getUserId());
					assertEquals("Do you have health insurance", poll.getTitle());
					assertEquals(40, poll.getTotalVotes());
					assertEquals(7, poll.getYesVotes());
					assertEquals(60, poll.getPageViews());
					assertEquals("This", poll.getDescription());
				}else if(i == 3){
					assertEquals(7, poll.getPollId());
					assertEquals(1, poll.getUserId());
					assertEquals("Do you know Him?", poll.getTitle());
					assertEquals(3, poll.getTotalVotes());
					assertEquals(1, poll.getYesVotes());
					assertEquals(5, poll.getPageViews());
					assertEquals("is", poll.getDescription());
				}else if(i == 4){
					assertEquals(10, poll.getPollId());
					assertEquals(1, poll.getUserId());
					assertEquals("Does he need a tic tac?", poll.getTitle());
					assertEquals(6, poll.getTotalVotes());
					assertEquals(4, poll.getYesVotes());
					assertEquals(9, poll.getPageViews());
					assertEquals("idea", poll.getDescription());
				}else if(i == 5){
					assertEquals(4, poll.getPollId());
					assertEquals(1, poll.getUserId());
					assertEquals("Have you found them?", poll.getTitle());
					assertEquals(25, poll.getTotalVotes());
					assertEquals(13, poll.getYesVotes());
					assertEquals(30, poll.getPageViews());
					assertEquals("You", poll.getDescription());
				}else if(i == 6){
					assertEquals(11, poll.getPollId());
					assertEquals(1, poll.getUserId());
					assertEquals("Have you seen my Child?", poll.getTitle());
					assertEquals(5, poll.getTotalVotes());
					assertEquals(5, poll.getYesVotes());
					assertEquals(7, poll.getPageViews());
					assertEquals("idea", poll.getDescription());
				}else if(i == 7){
					assertEquals(5, poll.getPollId());
					assertEquals(1, poll.getUserId());
					assertEquals("Women…", poll.getTitle());
					assertEquals(10, poll.getTotalVotes());
					assertEquals(9, poll.getYesVotes());
					assertEquals(70, poll.getPageViews());
					assertEquals("Sure", poll.getDescription());
				}
			}
		}
	}
}
