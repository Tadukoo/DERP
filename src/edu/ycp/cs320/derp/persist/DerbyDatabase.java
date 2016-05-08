/**
 * File copied and modified from Library example Lab06.
 */

package edu.ycp.cs320.derp.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.derp.model.User;
import edu.ycp.cs320.derp.model.Poll;
import edu.ycp.cs320.derp.model.IpAddress;
import edu.ycp.cs320.derp.model.Pair;

public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		DerbyDatabase db = new DerbyDatabase();

//	db.createTables();
//		db.loadInitialData();/*
		try {
			db.connect();
			System.out.println("Successfully connected...");
		} catch (SQLException e) {
			System.out.println("Creating tables...");
			System.out.println("No database file found creating Database from scratch...");
			db.createTables();
			
			System.out.println("Loading initial data...");
			db.loadInitialData();
			
			System.out.println("Derp DB successfully initialized!");
		}
//		*/
	}
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;


	// transaction that retrieves a Poll, and its User by Title
		@Override
		public List<Pair<User, Poll>> findUserAndPollByTitle(final String title) {
			return executeTransaction(new Transaction<List<Pair<User,Poll>>>(){
				@Override
				public List<Pair<User, Poll>> execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					
					try {
						stmt = conn.prepareStatement(
								"select Users.*, Polls.* " +
								"  from  Users, Polls" +
								"  where Polls.title = ? " +
								"    and Users.user_id = Polls.user_id"
						);
						stmt.setString(1, title);
						
						List<Pair<User, Poll>> result = new ArrayList<Pair<User,Poll>>();
						
						resultSet = stmt.executeQuery();
						
						// for testing that a result was returned
						Boolean found = false;
						
						while (resultSet.next()) {
							found = true;
							
							User User = new User();
							loadUser(User, resultSet, 1);
							Poll Poll = new Poll();
							loadPoll(Poll, resultSet, 8);
							
							result.add(new Pair<User, Poll>(User, Poll));
						}
						
						// check if the title was found
						if (!found) {
							System.out.println("<" + title + "> was not found in the Polls table");
						}
						
						return result;
					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				}
			});
		}
		
	
	// transaction that retrieves a list of Polls with their Users, given User's name
	@Override
	public List<Pair<User, Poll>> findPollByUserName(final String userName) {
		return executeTransaction(new Transaction<List<Pair<User,Poll>>>() {
			@Override
			public List<Pair<User, Poll>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				// try to retrieve Users and Polls based on User's last name, passed into query
				try {
					stmt = conn.prepareStatement(
							"select Users.*, Polls.* " +
							"  from  Users, Polls " +
							"  where Users.userName = ? " +
							"    and Users.User_id = Polls.User_id "   +
							"  order by Polls.title asc, Polls.description asc"
					);
					stmt.setString(1, userName);
					
					// establish the list of (User, Poll) Pairs to receive the result
					List<Pair<User, Poll>> result = new ArrayList<Pair<User,Poll>>();
					
					// execute the query, get the results, and assemble them in an ArrayLsit
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						User User = new User();
						loadUser(User, resultSet, 1);
						Poll Poll = new Poll();
						loadPoll(Poll, resultSet, 8);
						
						result.add(new Pair<User, Poll>(User, Poll));
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	// Alex Keperling 5/2/2016
	@Override
	public User findUserInformation(final String userName) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				// try to retrieve Users and Polls based on User's last name, passed into query
				try {
					stmt = conn.prepareStatement(
							"select Users.* " +
							"  from  Users " +
							"  where Users.userName = ? " 
					);
					stmt.setString(1, userName);
					resultSet = stmt.executeQuery();
					User result = new User();
					if(resultSet.next()){
						loadUser(result, resultSet, 1);
					}
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	//Alex Keperling 5/2/2016 
	@Override
	public Poll findPollByTitle(final String title,final String userName) {
		return executeTransaction(new Transaction<Poll>() {
		@Override
		public Poll execute(Connection conn) throws SQLException {
			Poll found = new Poll();
			PreparedStatement stmt1 = null;
			
			ResultSet resultSet1    = null;						
			
			try {
				// first get the user
				stmt1 = conn.prepareStatement(
						"select Polls.* " +
						"  from  Users, Polls" +
						"  where Users.username = ?" +
						"  and Polls.title = ? " +
						"  and Users.User_id = Polls.User_id "
				);
				
				// get the Poll's User(s)
				stmt1.setString(2, title);
				stmt1.setString(1, userName);
				resultSet1 = stmt1.executeQuery();
				
				// poll from query
				if(resultSet1.next()){
					loadPoll(found, resultSet1, 1);
				}

				
				return found;
			} finally {
				DBUtil.closeQuietly(resultSet1);
				DBUtil.closeQuietly(stmt1);	
			}
		}
	});

	}
	
	// Written by Alex Keperling 4/22/16
	// transaction that retrieves all Users in Library
	@Override
	public List<User> findAllUsers() {
		return executeTransaction(new Transaction<List<User>>() {
			@Override
			public List<User> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from Users " +
							" order by User_lastname asc, User_firstname asc"
					);
					
					List<User> result = new ArrayList<User>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						User User = new User();
						loadUser(User, resultSet, 1);
						
						result.add(User);
					}
					
					// check if any Users were found
					if (!found) {
						System.out.println("No Users were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	/**
	 * insertPollIntoPollsTable 
	 * method to insert a poll into the database. If user does not exist for poll it will not post.
	 */
	@Override
	public Integer insertPollIntoPollsTable(final String title, final String description, final String userName) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;		
				
				ResultSet resultSet1 = null;
				ResultSet resultSet3 = null;
				
				// for saving User ID and Poll ID
				Integer User_id = -1;
				Integer Poll_id   = -1;

				// try to retrieve User_id (if it exists) from DB, for User's full name, passed into query
				try {
					stmt1 = conn.prepareStatement(
							"select User_id from Users " +
							"  where username = ? "
					);
					stmt1.setString(1, userName);
					
					// execute the query, get the result
					resultSet1 = stmt1.executeQuery();

					
					// if User was found then save User_id					
					try{ 
						resultSet1.next();
						
						User_id = resultSet1.getInt(1);
						System.out.println("User <" + userName+ "> found with ID: " + User_id);						
					
						// now insert new Poll into Polls table
						// prepare SQL insert statement to add new Poll to Polls table
						stmt2 = conn.prepareStatement(
								"insert into Polls (user_id, title, description, total_votes, yes_votes, page_views) " +
								"  values(?, ?, ?, ?, ?, ?) "
						);
						stmt2.setInt(1, User_id);
						stmt2.setString(2, title);
						stmt2.setString(3, description);
						stmt2.setInt(4, 0);
						stmt2.setInt(5, 0);
						stmt2.setInt(6, 0);
						
						// execute the update
						stmt2.executeUpdate();
						
						System.out.println("New Poll <" + title + "> inserted into Polls table");					
	
						// now retrieve Poll_id for new Poll, so that we can set up PollUser entry
						// and return the Poll_id, which the DB auto-generates
						// prepare SQL statement to retrieve Poll_id for new Poll
						stmt3 = conn.prepareStatement(
								"select Poll_id from Polls " +
								"  where title = ? and description = ? "
						);
						stmt3.setString(1, title);
						stmt3.setString(2, description);
	
						// execute the query
						resultSet3 = stmt3.executeQuery();
						
						// get the result - there had better be one
						if (resultSet3.next())
						{
							Poll_id = resultSet3.getInt(1);
							System.out.println("New Poll <" + title + "> ID: " + Poll_id);						
						}
						else	// really should throw an exception here - the new Poll should have been inserted, but we didn't find it
						{
							System.out.println("New Poll <" + title + "> not found in Polls table (ID: " + Poll_id);
						}
						
						System.out.println("New Poll <" + title + "> inserted into Polls table");					
						
						return Poll_id;
					} catch (SQLException e) {
						throw new SQLException("no user specified", e);
					}
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);					
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt3);					
				}
			}
		});
	}
	
	// written by Alex Keperling 4/25/16
	// transaction that deletes Poll
	@Override
	public Boolean removePollByTitle(final String title, final String userName)  {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				Boolean removed = false;
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;						
				
				ResultSet resultSet1    = null;			
				ResultSet resultSet2    = null;
				ResultSet resultSet3    = null;			
				
				try {
					// first get the user
					stmt1 = conn.prepareStatement(
							"select Polls.* " +
							"  from  Users, Polls" +
							"  where Users.username = ?" +
							" and Polls.title = ? " +
							" and Users.User_id = Polls.User_id "
					);
					
					// get the Poll's User(s)
					stmt1.setString(2, title);
					stmt1.setString(1, userName);
					resultSet1 = stmt1.executeQuery();
					
					// assemble list of Users from query
					Poll Poll = new Poll();					
				
					if(resultSet1.next()) {
						loadPoll(Poll, resultSet1, 1);
					}
				// got the poll that we need to remove
					if(Poll != null && Poll.getTitle() != null && title != null){
						if(Poll.getTitle().equals(title)){
							try{
								if(Poll.getPollId() > 0){
									//delete the poll
									stmt2 = conn.prepareStatement(
									"delete from Polls "+
									"where poll_id = ?");
									stmt2.setInt(1, Poll.getPollId());
									stmt2.executeUpdate();
									System.out.println("Deleted Poll with title <" + title + "> from DB");									
								}		
								
								// check that the result was removed
								stmt3 = conn.prepareStatement(
								"select Polls.* "+
								"from Polls " +
								"where Polls.poll_id = ?");
								stmt3.setInt(1, Poll.getPollId());
								resultSet3 = stmt3.executeQuery();
								if(!resultSet3.next()){
									System.out.println("Deletion of Poll sucessful");
									removed = true;
								}
							
							}finally{
								DBUtil.closeQuietly(resultSet2);
								DBUtil.closeQuietly(resultSet3);
								DBUtil.closeQuietly(stmt3);
								DBUtil.closeQuietly(stmt2);
							}
						}
					}
					return removed;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);	
				}
			}
		});
	}

	// wrapper SQL transaction function that calls actual transaction function (which has retries)
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	// SQL transaction function which retries the transaction MAX_ATTEMPTS times before failing
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}
	
	// TODO: Here is where you specify the location of your Derby SQL database
	// TODO: You will need to change this location to the same path as your workspace for this example
	// TODO: Change it here and in SQLDemo under CS320_Lab06->edu.ycp.cs320.sqldemo	
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/Users/Alex Keperling/Documents/CS320_TeamProject/Derp.db;create=true");		
		
		// Set autocommit to false to allow multiple the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	// retrieves User information from query result set
	private void loadUser(User User, ResultSet resultSet, int index) throws SQLException {
		User.setUserId(resultSet.getInt(index++));
		User.setFirstName(resultSet.getString(index++));
		User.setLastName(resultSet.getString(index++));
		User.setUserName(resultSet.getString(index++));
		User.setPassword(resultSet.getString(index++));
		User.setEmail(resultSet.getString(index++));
		User.setInstitution(resultSet.getString(index++)); 
	}
	
	// retrieves Poll information from query result set
	private void loadPoll(Poll Poll, ResultSet resultSet, int index) throws SQLException {
		Poll.setPollId(resultSet.getInt(index++));
		Poll.setUserId(resultSet.getInt(index++));  
		Poll.setTitle(resultSet.getString(index++));
		Poll.setTotalVotes(resultSet.getInt(index++));
		Poll.setYesVotes(resultSet.getInt(index++));
		Poll.setPageViews(resultSet.getInt(index++));
		Poll.setDescription(resultSet.getString(index++));
	}
	// Retrieves IPAddress information from query result set

	private void loadIpaddresses(IpAddress ip, ResultSet resultSet, int index) throws SQLException{

		ip.setIPId(resultSet.getInt(index++));
		ip.setUserId(resultSet.getInt(index++));
		ip.setIp(resultSet.getString(index++));
	}

	// Updated as of 4/15/16 Alex Keperling
	//  creates the Users and Polls tables
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;				
			
				try {
					stmt1 = conn.prepareStatement(
						"create table Users (" +
						"	User_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +									
						"	user_firstname varchar(40)," +
						"	user_lastname varchar(40)," +
						"   username varchar(40)," +
						"   password varchar(40)," +
						" 	email varchar(60),   " +
						" 	institution varchar(240)" +
						")"
					);	
					stmt1.executeUpdate();
					
					System.out.println("Users table created");
					
					stmt2 = conn.prepareStatement(
							"create table Polls (" +
							"	Poll_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	User_id integer constraint User_id references Users, " +
							"	title varchar(50)," +
							"	total_votes int," +
							"	yes_votes int,"	+
							"	page_views int," +
							"	description varchar(240)"+
							")"
					);
					stmt2.executeUpdate();
					
					System.out.println("Ip address table created");					
					
					// assuming only support for ipv4

					// using user_id1 as constraint name be careful of changes
					stmt3 = conn.prepareStatement(
							"create table Ipaddresses (" +
							"	ip_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1),"+		
							"	User_id integer constraint User_id1 references Users, " +
							" 	ip_address varchar(15)" +
							")"
					);
					stmt3.executeUpdate();
					
					System.out.println("IpAddress table created");					
										
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
				}
			}
		});
	}
	
	//Updated as of 4/15/16 Alex Keperling
	// loads data retrieved from CSV files into DB tables in batch mode
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<User> UserList;
				List<Poll> PollList;
				List<IpAddress> IpList;
				
				try {
					UserList = InitialData.getUsers();
					PollList = InitialData.getPolls();
					IpList   = InitialData.getIpaddresses();					
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertUser = null;
				PreparedStatement insertPoll = null;
				PreparedStatement insertIp   = null;

				try {
					// must completely populate Users primary keys
					insertUser = conn.prepareStatement("insert into Users (user_firstname, user_lastname, username, password, email, institution) values (?, ?, ?, ?, ?, ?)");
					for (User User : UserList) {
//						insertUser.setInt(1, User.getUserId());	// auto-generated primary key, don't insert this
						insertUser.setString(1, User.getFirstName());
						insertUser.setString(2, User.getLastName());
						insertUser.setString(3, User.getUserName());
						insertUser.setString(4, User.getPassword());
						insertUser.setString(5, User.getEmail());
						insertUser.setString(6, User.getInstitution());
						insertUser.addBatch();
					}
					insertUser.executeBatch();
					
					System.out.println("Users table populated");
					
					// must completely populate Polls table before populating PollUsers table because of primary keys
					insertPoll = conn.prepareStatement("insert into Polls (User_id, title, total_votes, yes_votes, page_views, description) values (?, ?, ?, ?, ?, ?)");
					for (Poll Poll : PollList) {
//						insertPoll.setInt(1, Poll.getPollId());		// auto-generated primary key, don't insert this
						insertPoll.setInt(1, Poll.getUserId());	// this is a key to the user table
						insertPoll.setString(2, Poll.getTitle());
						insertPoll.setInt(3, Poll.getTotalVotes());
						insertPoll.setInt(4, Poll.getYesVotes());
						insertPoll.setInt(5, Poll.getPageViews());
						insertPoll.setString(6, Poll.getDescription());
						insertPoll.addBatch();
					}
					insertPoll.executeBatch();
					
					System.out.println("Polls table populated");					
					// must wait until all Polls and all Users are inserted into tables before creating PollUser table
					// since this table consists entirely of foreign keys, with constraints applied
				
					insertIp = conn.prepareStatement("insert into Ipaddresses (User_id, ip_address) values (?, ?)");
					for (IpAddress IpAddress : IpList) {
//						insertIp.setInt(1, Ip.getPollId());		// auto-generated primary key, don't insert this
						insertIp.setInt(1, IpAddress.getUserId());
						insertIp.setString(2, IpAddress.getIp());
						insertIp.addBatch();
					}
					insertIp.executeBatch();	
					
					System.out.println("IPaddresses table populated");
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertPoll);
					DBUtil.closeQuietly(insertUser);
					DBUtil.closeQuietly(insertIp);					
				}
			}
		});
	}

	// written by Alex Keperling 4/26/16
	@Override
	public List<Poll> findAllPolls() {
		return executeTransaction(new Transaction<List<Poll>>() {
			@Override
			public List<Poll> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from Polls " +
							" order by total_votes desc"
					);
					
					List<Poll> result = new ArrayList<>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = true;
					
					if(resultSet.next() != true){
						found = false;
					}
					while (resultSet.next()) {
						
						Poll Poll = new Poll();
						loadPoll(Poll, resultSet, 1);
						
						result.add(Poll);
					}
					
					// check if any Users were found
					if (!found) {
						System.out.println("No Polls were found in the database");
					}
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	@Override
	public Integer generateNewUser(final String firstname, final String lastname,final String Username,final String password,final String email,final String Institution,final String IPAdress){
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null; 
				ResultSet resultSet1 = null;
				ResultSet resultSet3 = null;
				Integer result = 0;
				try {
					stmt1 = conn.prepareStatement(
							"select user_id from Users "+
							"where username = ?"
					);
					stmt1.setString(1, Username);
					resultSet1 = stmt1.executeQuery();
					
					if(resultSet1.next() == true){
						result = 1;
					}else{
						stmt2 = conn.prepareStatement(
								"insert into Users (user_firstname, user_lastname, username, password, email, institution) " +
								"values(?, ?, ?, ?, ?, ?)");
						stmt2.setString(1, firstname);
						stmt2.setString(2, lastname);
						stmt2.setString(3, Username);
						stmt2.setString(4, password);
						stmt2.setString(5, email);
						stmt2.setString(6, Institution);
						stmt2.executeUpdate();
						
						stmt3 = conn.prepareStatement(
								"select user_id from Users "+
								"where username = ?"
						);
						int user_id;
						stmt3.setString(1, Username);
						resultSet3 = stmt3.executeQuery();
						if(resultSet3.next() == false){
							result = 2;
						}
						user_id = resultSet3.getInt(1);
						
						stmt4 = conn.prepareStatement("insert into Ipaddresses (User_id, ip_address) values(?, ?)");
						stmt4.setString(2, IPAdress);
						stmt4.setInt(1, user_id);
						stmt4.executeUpdate();
						
						
						DBUtil.closeQuietly(resultSet3);
						DBUtil.closeQuietly(stmt3);
						DBUtil.closeQuietly(stmt4);
						DBUtil.closeQuietly(stmt2);
					}
					// returns 1 for username is already used
					// returns 2 for user not properly added
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
	// written by Alex Keperling 4/26/2016
	@Override
	public Boolean CheckPassword(final String Username,final String password) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
			PreparedStatement stmt1 = null;
			ResultSet resultSet1 = null;
			boolean check = false;
			User User = new User();
			try{
				stmt1 = conn.prepareStatement(
						"select * from Users "+
						"where username = ?"
				);
				stmt1.setString(1, Username);
				resultSet1 = stmt1.executeQuery();
				if(resultSet1.next()){
					loadUser(User, resultSet1, 1);;
				}
				// fixed null pointer exception 5/6/2016
				System.out.println(User.getPassword());
				if(User != null && User.getPassword() != null && password != null){
					if(User.getPassword().equals(password)){
						check = true;
					}
				}
			return check;	
			} finally {
				DBUtil.closeQuietly(resultSet1);
				DBUtil.closeQuietly(stmt1);
			}
		}
	});
}

	@Override
	public Boolean IncrementCounter(final int poll_id, final int CounterType) {
		return executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			PreparedStatement stmt = null;
			ResultSet resultSet = null;
			boolean returned = true;
			
			try {
				if(CounterType == 1){
				stmt = conn.prepareStatement(
						"update Polls set yes_votes = yes_votes+1"+
						"where poll_id = ?"
				);
				stmt.setInt(1, poll_id);
				stmt.execute();
								
				} else if(CounterType == 2){
					stmt = conn.prepareStatement(
							"update Polls set total_votes = total_votes+1"+
							"where poll_id = ?"
					);
					stmt.setInt(1, poll_id);
					stmt.execute();					
					
				}else if(CounterType == 3){
					stmt = conn.prepareStatement(
							"update Polls set page_views = page_views+1"+
							"where poll_id = ?"
					);
					stmt.setInt(1, poll_id);
					stmt.execute();
					
					
				}else{
					System.out.println("What type of counter are you looking for?");
					returned = false;
				}
				
				
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(stmt);
			}
			return returned;
		}
	});
}

	@Override
	public User findUserByuserid(final int id) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				User User = new User();
				try{
					stmt= conn.prepareStatement(
							"select * from Users where user_id=?" );
					stmt.setInt(1, id);
					resultSet = stmt.executeQuery();
					if(resultSet.next()){
						loadUser(User, resultSet, 1);;
					}
				}finally{
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
		return User;
		}
	});
}
///////////////////////////////////////The Functions after this point do not need to be implemented////////////

	@Override
	public Integer CheckIP(String Ipadress) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<Integer>() {
		@Override
		public Integer execute(Connection conn) throws SQLException {
			PreparedStatement stmt = null;
			ResultSet resultSet = null;
			
			try {
				stmt = conn.prepareStatement(
						""
				);
				
				
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(stmt);
			}
			return null;
		}
	});
}


	@Override
	public Integer AddIPadress(String Username, String ipadress) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<Integer>() {
		@Override
		public Integer execute(Connection conn) throws SQLException {
			PreparedStatement stmt = null;
			ResultSet resultSet = null;
			
			try {
				stmt = conn.prepareStatement(
						""
				);
				
				
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(stmt);
			}
			return null;
		}
	});
}

	@Override
	public Boolean ChangePassword(String Username, String password) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			PreparedStatement stmt = null;
			ResultSet resultSet = null;
			
			try {
				stmt = conn.prepareStatement(
						""
				);
				
				
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(stmt);
			}
			return null;
		}
	});
}
	@Override
	public List<String> FindIpaddressByUser(String userName) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<List<String>>() {
		@Override
		public List<String> execute(Connection conn) throws SQLException {
			PreparedStatement stmt = null;
			ResultSet resultSet = null;
			
			try {
				stmt = conn.prepareStatement(
						""
				);
				
				
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(stmt);
			}
			return null;
		}
	});
}



}

