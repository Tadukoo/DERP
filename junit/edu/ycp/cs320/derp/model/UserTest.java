package edu.ycp.cs320.derp.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserTest{
	private User user;
	
	@Before
	public void setUp() throws Exception{
		user = new User();
	}
	
	@Test
	public void testSetAndGetUserID(){
		int id = 5;
		
		assertEquals(0, user.getUserId());
		user.setUserId(id);
		assertEquals(id, user.getUserId());
	}
	
	@Test
	public void testSetAndGetName(){
		String name = "user";
		
		assertEquals(null, user.getName());
		user.setName(name);
		assertEquals(name, user.getName());
	}
	
	@Test
	public void testSetAndGetEmail(){
		String email = "lferree@ycp.edu";
		
		assertEquals(null, user.getEmail());
		user.setEmail(email);
		assertEquals(email, user.getEmail());
	}
	
	@Test
	public void testSetAndGetIp(){
		String ip = "178.52.34.5";
		
		assertEquals(null, user.getIP());
		user.setIP(ip);
		assertEquals(ip, user.getIP());
	}
}
