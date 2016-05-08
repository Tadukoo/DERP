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

		controller = new MainContentController();
		System.out.println("\nLoginServlet: doGet");
		User thisUser = (User)req.getSession().getAttribute("user");
		if (thisUser == null) {
			System.out.println("   User not logged in or session timed out");
			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		
		// pull poll info from the url
		String url = req.getQueryString();
		System.out.println(url);
		String username = url.substring(url.indexOf("user=")+5, url.indexOf("&"));
		System.out.println(username);
		String title = url.substring(url.indexOf("&")+7);
		title = title.replaceAll("%20", " ");
		System.out.println(title);
		
		Poll thisPoll = controller.GetPollByTitle(title, username);
		System.out.println(thisPoll.getPollId());
		System.out.println(thisPoll.getDescription());
		req.setAttribute("pollTitle", thisPoll.getTitle());
		req.setAttribute("agree", thisPoll.getYesVotes());
		req.setAttribute("disagree", thisPoll.getTotalVotes() - thisPoll.getYesVotes());
		thisPoll.setPageViews(thisPoll.getPageViews() + 1);
		req.getRequestDispatcher("/_view/poll.jsp").forward(req, resp);
		
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
}
