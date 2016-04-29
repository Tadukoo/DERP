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
}
