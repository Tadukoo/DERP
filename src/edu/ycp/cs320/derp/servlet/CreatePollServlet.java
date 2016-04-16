package edu.ycp.cs320.derp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatePollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
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
		Double result = null;
		try {
			String title = req.getParameter("title");
			String description = req.getParameter("description");

			if(title == null || description == null || title == "" ||
					description == ""){
				errorMessage = "Please specify a title and description";
			}else{
				// TODO: Add Controller for actually putting polls in database.
				// Controller would check that title isn't in use and link isn't.
				
				req.getSession().setAttribute("title", title);
				req.getSession().setAttribute("link", (String)req.getSession().getAttribute("user")+'/'+title);
				req.getSession().setAttribute("description", description);
				
				resp.sendRedirect(req.getContextPath() + "/poll");
				
				return;
			}
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
		}
		
		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("result", result);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/createPoll.jsp").forward(req, resp);
	}
	// original
		/*@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			req.getRequestDispatcher("/_view/createPoll.jsp").forward(req, resp);
		}*/
		// original
	/*	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			
			// Decode form parameters and dispatch to controller
			String errorMessage = null;
			Double result = null;
			try {
				String title = req.getParameter("title");
				String link = req.getParameter("link");
				String description = req.getParameter("description");

				if(title == null || link == null || description == null || title == "" ||
						description == "" || link == ""){
					errorMessage = "Please specify a title, link, and description";
				}else{
					// TODO: Add Controller for actually putting polls in database.
					// Controller would check that title isn't in use and link isn't.
					req.getSession().setAttribute("title", title);
					req.getSession().setAttribute("link", link);
					req.getSession().setAttribute("description", description);
					
					resp.sendRedirect(req.getContextPath() + "/poll");
					
					return;
				}
			} catch (NumberFormatException e) {
				errorMessage = "Invalid double";
			}
			
			// Add result objects as request attributes
			req.setAttribute("errorMessage", errorMessage);
			req.setAttribute("result", result);
			
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/createPoll.jsp").forward(req, resp);
		}*/
}
