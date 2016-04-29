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
	
	
	// transaction that retrieves all Polls in Library, with their respective Users
	@Override
	public List<Pair<User, Poll>> findAllPollsWithUsers() {
		return executeTransaction(new Transaction<List<Pair<User,Poll>>>() {
			@Override
			public List<Pair<User, Poll>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select Users.*, Polls.* " +
							"  from Users, Polls, PollUsers " +
							"  where Users.User_id = PollUsers.User_id " +
							"    and Polls.Poll_id     = PollUsers.Poll_id "   +
							"  order by Polls.title asc"
					);
					
					List<Pair<User, Poll>> result = new ArrayList<Pair<User,Poll>>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						User User = new User();
						loadUser(User, resultSet, 1);
						Poll Poll = new Poll();
						loadPoll(Poll, resultSet, 4);
						
						result.add(new Pair<User, Poll>(User, Poll));
					}
					
					// check if any Polls were found
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
	
	
	// transaction that inserts new Poll into the Polls table
	// also first inserts new User into Users table, if necessary
	// and then inserts entry into PollUsers junction table
	@Override
	public Integer insertPollIntoPollsTable(final String title, final String isbn, final String lastName, final String firstName) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;				
				
				ResultSet resultSet1 = null;
//	(unused)	ResultSet resultSet2 = null;
				ResultSet resultSet3 = null;
//	(unused)	ResultSet resultSet4 = null;
				ResultSet resultSet5 = null;				
//	(unused)	ResultSet resultSet6 = null;
				
				// for saving User ID and Poll ID
				Integer User_id = -1;
				Integer Poll_id   = -1;

				// try to retrieve User_id (if it exists) from DB, for User's full name, passed into query
				try {
					stmt1 = conn.prepareStatement(
							"select User_id from Users " +
							"  where User_lastname = ? and User_firstname = ? "
					);
					stmt1.setString(1, lastName);
					stmt1.setString(2, firstName);
					
					// execute the query, get the result
					resultSet1 = stmt1.executeQuery();

					
					// if User was found then save User_id					
					if (resultSet1.next())
					{
						User_id = resultSet1.getInt(1);
						System.out.println("User <" + lastName + ", " + firstName + "> found with ID: " + User_id);						
					}
					else
					{
						System.out.println("User <" + lastName + ", " + firstName + "> not found");
				
						// if the User is new, insert new User into Users table
						if (User_id <= 0) {
							// prepare SQL insert statement to add User to Users table
							stmt2 = conn.prepareStatement(
									"insert into Users (User_lastname, User_firstname) " +
									"  values(?, ?) "
							);
							stmt2.setString(1, lastName);
							stmt2.setString(2, firstName);
							
							// execute the update
							stmt2.executeUpdate();
							
							System.out.println("New User <" + lastName + ", " + firstName + "> inserted in Users table");						
						
							// try to retrieve User_id for new User - DB auto-generates User_id
							stmt3 = conn.prepareStatement(
									"select User_id from Users " +
									"  where User_lastname = ? and User_firstname = ? "
							);
							stmt3.setString(1, lastName);
							stmt3.setString(2, firstName);
							
							// execute the query							
							resultSet3 = stmt3.executeQuery();
							
							// get the result - there had better be one							
							if (resultSet3.next())
							{
								User_id = resultSet3.getInt(1);
								System.out.println("New User <" + lastName + ", " + firstName + "> ID: " + User_id);						
							}
							else	// really should throw an exception here - the new User should have been inserted, but we didn't find them
							{
								System.out.println("New User <" + lastName + ", " + firstName + "> not found in Users table (ID: " + User_id);
							}
						}
					}
					
					// now insert new Poll into Polls table
					// prepare SQL insert statement to add new Poll to Polls table
					stmt4 = conn.prepareStatement(
							"insert into Polls (title, isbn) " +
							"  values(?, ?) "
					);
					stmt4.setString(1, title);
					stmt4.setString(2, isbn);
					
					// execute the update
					stmt4.executeUpdate();
					
					System.out.println("New Poll <" + title + "> inserted into Polls table");					

					// now retrieve Poll_id for new Poll, so that we can set up PollUser entry
					// and return the Poll_id, which the DB auto-generates
					// prepare SQL statement to retrieve Poll_id for new Poll
					stmt5 = conn.prepareStatement(
							"select Poll_id from Polls " +
							"  where title = ? and isbn = ? "
					);
					stmt5.setString(1, title);
					stmt5.setString(2, isbn);

					// execute the query
					resultSet5 = stmt5.executeQuery();
					
					// get the result - there had better be one
					if (resultSet5.next())
					{
						Poll_id = resultSet5.getInt(1);
						System.out.println("New Poll <" + title + "> ID: " + Poll_id);						
					}
					else	// really should throw an exception here - the new Poll should have been inserted, but we didn't find it
					{
						System.out.println("New Poll <" + title + "> not found in Polls table (ID: " + Poll_id);
					}
					
					// now that we have all the information, insert entry into PollUsers table
					// which is the junction table for Polls and Users
					// prepare SQL insert statement to add new Poll to Polls table
					stmt6 = conn.prepareStatement(
							"insert into PollUsers (Poll_id, User_id) " +
							"  values(?, ?) "
					);
					stmt6.setInt(1, Poll_id);
					stmt6.setInt(2, User_id);
					
					// execute the update
					stmt6.executeUpdate();
					
					System.out.println("New enry for Poll ID <" + Poll_id + "> and User ID <" + User_id + "> inserted into PollUsers junction table");						
					
					System.out.println("New Poll <" + title + "> inserted into Polls table");					
					
					return Poll_id;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
//	(unused)		DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);					
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt3);					
// (unused)			DBUtil.closeQuietly(resultSet4);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(resultSet5);
					DBUtil.closeQuietly(stmt5);
// (unused)			DBUtil.closeQuietly(resultSet6);
					DBUtil.closeQuietly(stmt6);
				}
			}
		});
	}
	
	
	// transaction that deletes Poll (and possibly its User) from Library
	@Override
	public List<User> removePollByTitle(final String title) {
		return executeTransaction(new Transaction<List<User>>() {
			@Override
			public List<User> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;							
				
				ResultSet resultSet1    = null;			
				ResultSet resultSet2    = null;
//				ResultSet resultSet3    = null;
//				ResultSet resultSet4    = null;
				ResultSet resultSet5    = null;
//				ResultSet resultSet6    = null;				
				
				try {
					// first get the User(s) of the Poll to be deleted
					// just in case it's the only Poll by this User
					// in which case, we should also remove the User(s)
					stmt1 = conn.prepareStatement(
							"select Users.* " +
							"  from  Users, Polls, PollUsers " +
							"  where Polls.title = ? " +
							"    and Users.User_id = PollUsers.User_id " +
							"    and Polls.Poll_id     = PollUsers.Poll_id"
					);
					
					// get the Poll's User(s)
					stmt1.setString(1, title);
					resultSet1 = stmt1.executeQuery();
					
					// assemble list of Users from query
					List<User> Users = new ArrayList<User>();					
				
					while (resultSet1.next()) {
						User User = new User();
						loadUser(User, resultSet1, 1);
						Users.add(User);
					}
					
					// check if any Users were found
					// this shouldn't be necessary, there should not be a Poll in the DB without an User
					if (Users.size() == 0) {
						System.out.println("No User was found for title <" + title + "> in the database");
					}
										
					// now get the Poll(s) to be deleted
					// we will need the Poll_id to remove associated entires in PollUsers (junction table)
				
					stmt2 = conn.prepareStatement(
							"select Polls.* " +
							"  from  Polls " +
							"  where Polls.title = ? "
					);
					
					// get the Poll(s)
					stmt2.setString(1, title);
					resultSet2 = stmt2.executeQuery();
					
					// assemble list of Polls from query
					List<Poll> Polls = new ArrayList<Poll>();					
				
					while (resultSet2.next()) {
						Poll Poll = new Poll();
						loadPoll(Poll, resultSet2, 1);
						Polls.add(Poll);
					}
					
					// first delete entries in PollUsers junction table
					// can't delete entries in Polls or Users tables while they have foreign keys in junction table
					// prepare to delete the junction table entries (PollUsers)
					stmt3 = conn.prepareStatement(
							"delete from PollUsers " +
							"  where Poll_id = ? "
					);
					
					// delete the junction table entries from the DB for this title
					// this works if there are not multiple Polls with the same name
					stmt3.setInt(1, Polls.get(0).getPollId());
					stmt3.executeUpdate();
					
					System.out.println("Deleted junction table entries for Poll(s) <" + title + "> from DB");									
					
					// now delete entries in Polls table for this title
					stmt4 = conn.prepareStatement(
							"delete from Polls " +
							"  where title = ? "
					);
					
					// delete the Poll entries from the DB for this title
					stmt4.setString(1, title);
					stmt4.executeUpdate();
					
					System.out.println("Deleted Poll(s) with title <" + title + "> from DB");									
					
					// now check if the User(s) have any Polls remaining in the DB
					// only need to check if there are any entries in junction table that have this User ID
					for (int i = 0; i < Users.size(); i++) {
						// prepare to find Polls for this User
						stmt5 = conn.prepareStatement(
								"select Polls.Poll_id from Polls, PollUsers " +
								"  where PollUsers.User_id = ? "
						);
						
						// retrieve any remaining Polls for this User
						stmt5.setInt(1, Polls.get(i).getUserId());
						resultSet5 = stmt5.executeQuery();						

						// if nothing returned, then delete User
						if (!resultSet5.next()) {
							stmt6 = conn.prepareStatement(
								"delete from Users " +
								"  where User_id = ?"
							);
							
							// delete the User from DB
							stmt6.setInt(1, Users.get(i).getUserId());
							stmt6.executeUpdate();
							
							System.out.println("Deleted User <" + Users.get(i).getFirstName() + ", " + 
							Users.get(i).getEmail() + "> from DB");
							
							// we're done with this, so close it, since we might have more to do
							DBUtil.closeQuietly(stmt6);
						}
						
						// we're done with this, so close it since we might have more to do
						DBUtil.closeQuietly(resultSet5);
						DBUtil.closeQuietly(stmt5);
					}
					return Users;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(resultSet2);
//					DBUtil.closeQuietly(resultSet3);
//					DBUtil.closeQuietly(resultSet4);
					
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);					
					DBUtil.closeQuietly(stmt4);					
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

	@Override
	public List<String> FindIpaddressByUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// TODO: Here is where you specify the location of your Derby SQL database
	// TODO: You will need to change this location to the same path as your workspace for this example
	// TODO: Change it here and in SQLDemo under CS320_Lab06->edu.ycp.cs320.sqldemo	
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/Users/Tadukoo/Desktop/Tadukoo Verse/Programming/CS320 Soft Eng and Des/Derp.db;create=true");		
		
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

	// Updated as of 4/15/16
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
	//Updated as of 4/15/16
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
					//TODO:fix commented out section
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


	@Override
	public Integer generateNewUser(String Username, String password, String email, String IPaddress) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer CheckIP(String Ipadress) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer AddIPadress(String Username, String ipadress) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Boolean CheckPassword(String Username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean ChangePassword(String Username, String password) {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public Boolean IncrementPollCounter(String Username, String PollTitle, int PollType) {
		// TODO Auto-generated method stub
		return null;
	}
}

