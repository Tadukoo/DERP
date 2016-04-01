/**
 * File copied and modified from CS320 Library Solution Lab 03.
 * Original title was Library.
 */
package edu.ycp.cs320.derp.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class TempLoginDatabase{
	private ArrayList<String> usernames;
	private ArrayList<String> passwords;
	private Map<String, String> credentials;
	
	// create model - test version
	public TempLoginDatabase(){
		usernames = new ArrayList<String>();
		passwords = new ArrayList<String>();
		credentials = new TreeMap<String, String>();
		
		usernames.add("student");
		usernames.add("faculty");
		
		passwords.add("ycp");
		passwords.add("E&CS");
		
		for (int i = 0; i < usernames.size(); i++) {
			credentials.put(usernames.get(i), passwords.get(i));
		}
	}
	
	public boolean createNewUser(String name, String password){
		if(usernames.contains(name)){
			return false;
		}else{
			usernames.add(name);
			passwords.add(password);
			credentials.put(name, password);
			return true;
		}
	}

	// login name - test version
	public boolean validateUserName(String name) {
		return credentials.containsKey(name);
	}

	// login credentials - test version
	public boolean validatePW(String name, String pw) {
		if (credentials.containsKey(name)) {
			if  (credentials.get(name).equals(pw)) {
				return true;
			}
		}			
		return false;
	}
}
