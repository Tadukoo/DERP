package edu.ycp.cs320.derp.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PairTest{
	private Pair<User, Poll> pair;
	private User emptyUser, definedUser;
	private Poll emptyPoll, definedPoll;
	
	@Before
	public void setUp() throws Exception{
		emptyUser = new User();
		emptyPoll = new Poll();
		
		pair = new Pair<User, Poll>(emptyUser, emptyPoll);
		
		definedUser = new User();
		definedUser.setUserId(25);
		definedUser.setFirstName("Tadukoo");
		definedUser.setLastName("Smith");
		definedUser.setEmail("realtadukoo@gmail.com");
		definedUser.setInstitution("Real World");
		definedUser.setUserName("Tadukoo12");
		definedUser.setPassword("wordsmith12");
		
		definedPoll = new Poll();
		definedPoll.setPollId(50);
		definedPoll.setUserId(25);
		definedPoll.setTitle("Is Rey Luke's Daughter?");
		definedPoll.setDescription("A poll to get to known a girl.");
		definedPoll.setPageViews(200);
		definedPoll.setTotalVotes(25);
		definedPoll.setYesVotes(23);
	}
	
	@Test
	public void testSetAndGetLeft(){		
		assertEquals(emptyUser, pair.getLeft());
		pair.setLeft(definedUser);
		assertEquals(definedUser, pair.getLeft());
	}
	
	@Test
	public void testSetAndGetRight(){
		assertEquals(emptyPoll, pair.getRight());
		pair.setRight(definedPoll);
		assertEquals(definedPoll, pair.getRight());
	}
	
	@Test
	public void testGetUserInfoFromPair(){
		assertEquals(0, pair.getLeft().getUserId());
		assertEquals(null, pair.getLeft().getFirstName());
		assertEquals(null, pair.getLeft().getEmail());
		assertEquals(null, pair.getLeft().getLastName());
		assertEquals(null, pair.getLeft().getInstitution());
		assertEquals(null,pair.getLeft().getPassword());
		assertEquals(null,pair.getLeft().getUserName());
		
		pair.setLeft(definedUser);
		
		assertEquals(25, pair.getLeft().getUserId());
		assertEquals("Tadukoo", pair.getLeft().getFirstName());
		assertEquals("realtadukoo@gmail.com", pair.getLeft().getEmail());
		assertEquals("Smith", pair.getLeft().getLastName());
		assertEquals("Real World", pair.getLeft().getInstitution());
		assertEquals("wordsmith12", pair.getLeft().getPassword());
		assertEquals("Tadukoo12", pair.getLeft().getUserName());
		
	}

	
	@Test
	public void testSetUserInfoFromPair(){
		assertEquals(0, pair.getLeft().getUserId());
		assertEquals(null, pair.getLeft().getFirstName());
		assertEquals(null, pair.getLeft().getEmail());
		
		pair.getLeft().setUserId(5);
		pair.getLeft().setFirstName("Derp");
		pair.getLeft().setEmail("hubakunychanikad@gmail.com");
		
		
		assertEquals(5, pair.getLeft().getUserId());
		assertEquals("Derp", pair.getLeft().getFirstName());
		assertEquals("hubakunychanikad@gmail.com", pair.getLeft().getEmail());
	}
	
	@Test
	public void testGetPollInfoFromPair(){
		assertEquals(0, pair.getRight().getPollId());
		assertEquals(0, pair.getRight().getUserId());
		assertEquals(null, pair.getRight().getTitle());
		assertEquals(0, pair.getRight().getTotalVotes());
		assertEquals(0, pair.getRight().getYesVotes());
		
		pair.setRight(definedPoll);
		
		assertEquals(50, pair.getRight().getPollId());
		assertEquals(25, pair.getRight().getUserId());
		assertEquals("Is Rey Luke's Daughter?", pair.getRight().getTitle());
		assertEquals(25, pair.getRight().getTotalVotes());
		assertEquals(23, pair.getRight().getYesVotes());
	}
	
	@Test
	public void testSetPollInfoFromPair(){
		assertEquals(0, pair.getRight().getPollId());
		assertEquals(0, pair.getRight().getUserId());
		assertEquals(null, pair.getRight().getTitle());
		assertEquals(0, pair.getRight().getTotalVotes());
		assertEquals(0, pair.getRight().getYesVotes());
		
		pair.getRight().setPollId(200);
		pair.getRight().setUserId(100);
		pair.getRight().setTitle("Will you get an A in CS320?");
		pair.getRight().setTotalVotes(50);
		pair.getRight().setYesVotes(1); // It's me
		
		assertEquals(200, pair.getRight().getPollId());
		assertEquals(100, pair.getRight().getUserId());
		assertEquals("Will you get an A in CS320?", pair.getRight().getTitle());
		assertEquals(50, pair.getRight().getTotalVotes());
		assertEquals(1, pair.getRight().getYesVotes());
	}
}
