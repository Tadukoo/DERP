/**
 * File copied and modified from Library example Lab06.
 */

package edu.ycp.cs320.derp.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.derp.model.User;
import edu.ycp.cs320.derp.model.IpAddress;
import edu.ycp.cs320.derp.model.Poll;

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
				User.setFirstName(i.next());
				User.setLastName(i.next());
				User.setUserName(i.next());
				User.setPassword(i.next());
				User.setEmail(i.next());
				User.setInstitution(i.next());
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
				Poll.setUserId(Integer.parseInt(i.next()));  // no longer in Polls table
				Poll.setTitle(i.next());
				Poll.setTotalVotes(Integer.parseInt(i.next()));
				Poll.setYesVotes(Integer.parseInt(i.next()));
				Poll.setPageViews(Integer.parseInt(i.next()));
				Poll.setDescription(i.next());
				PollList.add(Poll);
			}
			System.out.println("PollList loaded from CSV file");			
			return PollList;
		} finally {
			readPolls.close();
		}
	}
	// reads initial IP Address data from CSV file and returns a List of PollUsers
	public static List<IpAddress> getIpaddresses() throws IOException {
		List<IpAddress> IPaddressList = new ArrayList<IpAddress>();
		ReadCSV readIpaddresses = new ReadCSV("Ipaddresses.csv");
		try {
			// auto-generated primary key for table Polls
						Integer IpId = 1;
						while (true) {
							List<String> tuple = readIpaddresses.next();
							if (tuple == null) {
								break;
							}
				Iterator<String> i = tuple.iterator();
				IpAddress ip = new IpAddress();
				
				// read Ip ID from CSV file, but don't use it
				// it's there for reference purposes, just make sure that it is correct
				// when setting up the PollUsers CSV file
				Integer.parseInt(i.next());
				// auto-generate Poll ID, instead
				ip.setIPId(IpId++);	
				ip.setUserId(Integer.parseInt(i.next()));
				ip.setIp(i.next());
				IPaddressList.add(ip);
			}
			System.out.println("IpList loaded from CSV file");			
			return IPaddressList;
		} finally {
			readIpaddresses.close();
		}
	}
}