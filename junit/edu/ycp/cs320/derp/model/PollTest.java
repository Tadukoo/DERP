package edu.ycp.cs320.derp.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PollTest{
	private Poll poll;
	
	@Before
	public void setUp() throws Exception{
		poll = new Poll();
	}
	
	@Test
	public void testSetAndGetPollID(){
		int id = 5;
		
		assertEquals(0, poll.getPollId());
		poll.setPollId(id);
		assertEquals(id, poll.getPollId());
	}
	
	@Test
	public void testSetAndGetUserID(){
		int id = 5;
		
		assertEquals(0, poll.getUserId());
		poll.setUserId(id);
		assertEquals(id, poll.getUserId());
	}
	
	@Test
	public void testSetAndGetTitle(){
		String title = "user";
		
		assertEquals(null, poll.getTitle());
		poll.setTitle(title);
		assertEquals(title, poll.getTitle());
	}
	
	@Test
	public void testSetAndGetTotalVotes(){
		int votes = 25;
		
		assertEquals(0, poll.getTotalVotes());
		poll.setTotalVotes(votes);
		assertEquals(votes, poll.getTotalVotes());
	}
	
	@Test
	public void testSetAndGetYesVotes(){
		int votes = 10;
		
		assertEquals(0, poll.getYesVotes());
		poll.setYesVotes(votes);
		assertEquals(votes, poll.getYesVotes());
	}
	@Test
	public void testSetAndGetPageViews(){
		int views = 12;
		
		assertEquals(0, poll.getPageViews());
		poll.setPageViews(views);
		assertEquals(views, poll.getPageViews());
	}
	@Test
	public void testSetAndGetDescription(){
		String Description = "This is a description.";
		
		assertEquals(null, poll.getDescription());
		poll.setDescription(Description);
		assertEquals(Description, poll.getDescription());

	}
}
