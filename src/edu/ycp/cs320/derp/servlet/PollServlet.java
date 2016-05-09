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
		String username = url.substring(url.indexOf("user=")+5, url.indexOf("&"));
		String title = url.substring(url.indexOf("&")+7);
		title = title.replaceAll("%20", " ");
		
		Poll thisPoll = controller.GetPollByTitle(title, username);
		req.setAttribute("pollTitle", thisPoll.getTitle());
		req.setAttribute("agree", thisPoll.getYesVotes());
		req.setAttribute("disagree", thisPoll.getTotalVotes() - thisPoll.getYesVotes());
		req.setAttribute("description", thisPoll.getDescription());
		thisPoll.setPageViews(thisPoll.getPageViews() + 1);
		req.getRequestDispatcher("/_view/poll.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		User thisUser = (User)req.getSession().getAttribute("user");
		if (thisUser == null) {
			System.out.println("   User not logged in or session timed out");
			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		String button = req.getParameter("button");
		String url = req.getQueryString();
		String username = url.substring(url.indexOf("user=")+5, url.indexOf("&"));
		String title = url.substring(url.indexOf("&")+7);
		title = title.replaceAll("%20", " ");
		Poll thisPoll = controller.GetPollByTitle(title, username);
		
		if (button == null) {
		    //no button has been selected		
			//req.getSession().setAttribute("search", "search");
			req.getSession().setAttribute("search", "I am the one");
			//System.out.println(req.getAttribute("search"));
			resp.sendRedirect(req.getContextPath() + "/search");
			return;
		} else if (button.equals("Disagree")) {
		    controller.IncrementTotalPollCounter(thisPoll.getPollId());
		} else if (button.equals("Agree")) {
			controller.IncrementYesPollCounter(thisPoll.getPollId());
			controller.IncrementTotalPollCounter(thisPoll.getPollId());
		} else {
		    //someone has altered the HTML and sent a different value!
		}
		doGet(req, resp);
	}
}
