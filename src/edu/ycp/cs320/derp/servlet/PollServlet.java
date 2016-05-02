package edu.ycp.cs320.derp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.derp.controller.MainContentController;
import edu.ycp.cs320.derp.model.Poll;

/*import edu.ycp.cs320.derp.model.Pair;
import edu.ycp.cs320.derp.model.Poll;
import edu.ycp.cs320.derp.model.User;
import edu.ycp.cs320.derp.controller.MainContentController;
*/
public class PollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MainContentController controller;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nLoginServlet: doGet");
		if((String)req.getSession().getAttribute("username")==null || (String)req.getSession().getAttribute("username")==""){
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);}
		String name= (String)req.getSession().getAttribute("username");
		String pollname= (String)req.getSession().getAttribute("pollname");
		controller = new MainContentController();
		Poll poll =new Poll();
		poll = controller.SearchByPollTitle(pollname,name);
		req.setAttribute("poll", poll);
		req.setAttribute("polltitle", pollname);
		req.setAttribute("pollsummary", poll.getDescription());
		req.setAttribute("agrees", poll.getYesVotes());
		req.setAttribute("disagrees", poll.getTotalVotes() - poll.getYesVotes());
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		String name= (String)req.getSession().getAttribute("username");
		String pollname= (String)req.getSession().getAttribute("pollname");;
		Poll poll = (Poll) req.getSession().getAttribute("poll");
		controller = new MainContentController();
		controller.IncrementTotalPollCounter(poll.getPollId());
		if (req.getParameter("Agree") == null) {
			controller.IncrementYesPollCounter(poll.getPollId());
			}
		
		req.getRequestDispatcher("/_view/poll.jsp").forward(req, resp);
	}
  //private MainContentController controller = null;

	
/*	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		String errorMessage = null;
		String pollTitle = null;
		String author = null;
		Pair<User,Poll> Poll = null;
		
		// Decode form parameters and dispatch to the controller
		pollTitle = req.getParameter("poll_Title");
		author = req.getParameter("author_name");
		
		// check for attributes
		if ( pollTitle == null || pollTitle.equals("")){
			errorMessage = "Please specify a Poll";
		} if(author == null || author.equals("")){
			if(errorMessage == null){
				errorMessage = "Please specify a user";
			}else{
				errorMessage = errorMessage + "and a User.";
			}}
		
		// create request
		if(errorMessage != null){
			// will print error message
		}else{
			controller = new MainContentController();
			
			// get list of Pair returned by query
			Poll = controller.SearchByPollTitleAndAuthor(pollTitle, author);
			
		}
		pollTitle = (String) req.getSession().getAttribute("pollTitle");
		req.setAttribute("pollTitle", pollTitle);
		req.setAttribute("Poll", Poll.getRight());
		req.getRequestDispatcher("/_view/poll.jsp").forward(req, resp);
	}*/
}
