package edu.ycp.cs320.derp.database;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

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
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception{
	}
	
	@Before
	public void setUp() throws Exception {
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
		
	}

	@After
	public void tearDown() throws Exception {
	}
}
