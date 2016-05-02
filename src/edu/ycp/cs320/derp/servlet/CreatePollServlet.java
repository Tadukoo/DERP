package edu.ycp.cs320.derp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.derp.controller.MainContentController;

public class CreatePollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MainContentController controller;
		
	// new
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String user = (String) req.getSession().getAttribute("user");
		if (user == null) {
			System.out.println("   User: <" + user + "> not logged in or session timed out");
			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
			
			// now we have the user's User object,
			// proceed to handle request...
			
			req.getRequestDispatcher("/_view/poll.jsp").forward(req, resp);	
	}
	
	// new
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Decode form parameters and dispatch to controller
		String errorMessage = null;
		String title = null;
		String desc = null;
		String name = (String)req.getSession().getAttribute("username");
		

		// Decode form parameters and dispatch to controller
		title = req.getParameter("polltitle");
		desc = req.getParameter("polldescription");
		
		if (name == null || title == null || name.equals("") || title.equals("") || desc == null || desc.equals("")) {
			errorMessage = "Please specify poll title and or desciption";
		} else {
			controller = new MainContentController();
			controller.InsertPoll(title, desc, name);
			resp.sendRedirect(req.getContextPath() + "/userHome");
			return;
				
			}
			
		}
}
