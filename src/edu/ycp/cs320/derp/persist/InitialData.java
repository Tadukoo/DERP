/**
 * File copied and modified from Library example Lab06.
 */

package edu.ycp.cs320.derp.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.derp.model.User;
import edu.ycp.cs320.derp.model.Poll;
import edu.ycp.cs320.derp.model.PollUser;

public class InitialData {

	// reads initial User data from CSV file and returns a List of Users
	public static List<User> getUsers() throws IOException {
		List<User> UserList = new ArrayList<User>();
		ReadCSV readUsers = new ReadCSV("Users.csv");
		try {
			// auto-generated primary key for Users table
			Integer UserId = 1;
			while (true) {
				List<String> tuple = readUsers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				User User = new User();

				// read User ID from CSV file, but don't use it
				// it's there for reference purposes, just make sure that it is correct
				// when setting up the PollUsers CSV file				
				Integer.parseInt(i.next());
				// auto-generate User ID, instead
				User.setUserId(UserId++);				
				User.setName(i.next());
				User.setEmail(i.next());
				User.setIP(i.next());
				UserList.add(User);
			}
			System.out.println("UserList loaded from CSV file");
			return UserList;
		} finally {
			readUsers.close();
		}
	}
	
	// reads initial Poll data from CSV file and returns a List of Polls
	public static List<Poll> getPolls() throws IOException {
		List<Poll> PollList = new ArrayList<Poll>();
		ReadCSV readPolls = new ReadCSV("Polls.csv");
		try {
			// auto-generated primary key for table Polls
			Integer PollId = 1;
			while (true) {
				List<String> tuple = readPolls.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Poll Poll = new Poll();
				
				// read Poll ID from CSV file, but don't use it
				// it's there for reference purposes, just make sure that it is correct
				// when setting up the PollUsers CSV file
				Integer.parseInt(i.next());
				// auto-generate Poll ID, instead
				Poll.setPollId(PollId++);				
//				Poll.setUserId(Integer.parseInt(i.next()));  // no longer in Polls table
				Poll.setTitle(i.next());
				Poll.setTotalVotes(Integer.parseInt(i.next()));
				Poll.setYesVotes(Integer.parseInt(i.next()));
				PollList.add(Poll);
			}
			System.out.println("PollList loaded from CSV file");			
			return PollList;
		} finally {
			readPolls.close();
		}
	}
	
	// reads initial PollUser data from CSV file and returns a List of PollUsers
	public static List<PollUser> getPollUsers() throws IOException {
		List<PollUser> PollUserList = new ArrayList<PollUser>();
		ReadCSV readPollUsers = new ReadCSV("Poll_Users.csv");
		try {
			while (true) {
				List<String> tuple = readPollUsers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				PollUser PollUser = new PollUser();
				PollUser.setPollId(Integer.parseInt(i.next()));				
				PollUser.setUserId(Integer.parseInt(i.next()));
				PollUserList.add(PollUser);
			}
			System.out.println("PollUserList loaded from CSV file");			
			return PollUserList;
		} finally {
			readPollUsers.close();
		}
	}
}