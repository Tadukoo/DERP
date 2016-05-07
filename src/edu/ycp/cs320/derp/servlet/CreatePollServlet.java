package edu.ycp.cs320.derp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.derp.controller.MainContentController;
import edu.ycp.cs320.derp.model.User;
import edu.ycp.cs320.derp.model.Poll;

public class CreatePollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MainContentController controller;
		
	// new
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		User user = (User)req.getSession().getAttribute("user");
		if (!(boolean)req.getSession().getAttribute("login")) {
			System.out.println("   User: <" + user + "> not logged in or session timed out");
			// user is not logged in, or the session expired
			resp.sendRedirect("/_view/login.jsp");
			return;
		}
			// now we have the user's User object,
			// proceed to handle request...
			
			req.getRequestDispatcher("/_view/createPoll.jsp").forward(req, resp);	
	}
	
	// new
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("he");
		// Decode form parameters and dispatch to controller
		String errorMessage = null;
		String title = req.getParameter("polltitle");
		String desc = req.getParameter("polldescription");
		User user = (User) req.getSession().getAttribute("user");
		String name =user.getUserName();
		

		// Decode form parameters and dispatch to controller
		if (desc == null || title == null || desc.equals("") || title.equals("")) {
			errorMessage = "Please specify poll title and or desciption";
		} else {
			controller = new MainContentController();
			controller.InsertPoll(title, desc, name);
			req.getSession().setAttribute("poll", controller.GetPollByTitle(title, name));
			resp.sendRedirect(req.getContextPath() + "/poll");
			return;
				
			}
			
		}
}
