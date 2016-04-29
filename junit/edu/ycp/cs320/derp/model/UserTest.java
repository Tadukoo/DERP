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
	public void testSetAndGetFirstName(){
		String name = "user";
		
		assertEquals(null, user.getFirstName());
		user.setFirstName(name);
		assertEquals(name, user.getFirstName());
	}
	@Test
	public void testSetAndGetLastName(){
		String name = "Alpha";
		
		assertEquals(null, user.getLastName());
		user.setLastName(name);
		assertEquals(name, user.getLastName());
	}
	@Test
	public void testSetAndGetUserName(){
		String name = "user132";
		
		assertEquals(null, user.getUserName());
		user.setUserName(name);
		assertEquals(name, user.getUserName());
	}
	@Test
	public void testSetAndGetEmail(){
		String email = "lferree@ycp.edu";
		
		assertEquals(null, user.getEmail());
		user.setEmail(email);
		assertEquals(email, user.getEmail());
	}
	@Test
	public void testSetAndGetPassword(){
		String password = "password";
		assertEquals(null,user.getPassword());
		user.setPassword(password);
		assertEquals(password, user.getPassword());
		
	}
	@Test
	public void testSetAndGetInstitution(){
		String institution = "York College Of Pensylvania";
		assertEquals(null, user.getInstitution());
		user.setInstitution(institution);
		assertEquals(institution, user.getInstitution());
	}
	
}
