package edu.ycp.cs320.derp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.derp.controller.MainContentController;
import edu.ycp.cs320.derp.model.Poll;
import edu.ycp.cs320.derp.model.User;

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
		User thisUser = (User) req.getSession().getAttribute("user");
		
		Poll thisPoll = (Poll) req.getSession().getAttribute("poll");
		req.setAttribute("pollTitle", thisPoll.getTitle());
		req.setAttribute("agree", thisPoll.getYesVotes());
		req.setAttribute("disagree", thisPoll.getTotalVotes() - thisPoll.getYesVotes());
		thisPoll.setPageViews(thisPoll.getPageViews() + 1);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		String button = req.getParameter("button");
		Poll thisPoll = (Poll) req.getSession().getAttribute("poll");
		if (button == null) {
		    //no button has been selected
		} else if (button.equals("Dissagree")) {
		    thisPoll.setTotalVotes(thisPoll.getTotalVotes() + 1);
		} else if (button.equals("Agree")) {
			thisPoll.setYesVotes(thisPoll.getYesVotes() + 1);
		} else {
		    //someone has altered the HTML and sent a different value!
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
