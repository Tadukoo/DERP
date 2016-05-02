/**
 * File copied and modified from CS320 Library Solution Lab 03 LoginServlet.
 */
package edu.ycp.cs320.derp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.derp.controller.MainContentController;

//import edu.ycp.cs320.derp.controller.CreateAccountController;
//import edu.ycp.cs320.derp.model.TempLoginDatabase;

public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private TempLoginDatabase model;
//	private CreateAccountController controller;
	private MainContentController controller;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nCreateAccountServlet: doGet");

		req.getRequestDispatcher("/_view/createAccount.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nCreateAccountServlet: doPost");

		String errorMessage = null;
		String name = null;
		String email = null;
		String pw = null;
		String repw = null;
		boolean validAccount = true;

		// Decode form parameters and dispatch to controller
		name = req.getParameter("user");
		email = req.getParameter("email");
		pw   = req.getParameter("pass");
		repw   = req.getParameter("repass");

		System.out.println("   Name: <" + name + "> PW: <" + pw + ">");	
		
		req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("password", req.getParameter("password"));

		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("creatingAccount",   validAccount);

		if (name == null || pw == null || name.equals("") || pw.equals("")) {
			errorMessage = "Please specify both user name and password";
		} /*else {
			controller = new MainContentController();
			System.out.println("valid login strst");
			System.out.println("valid login end");

			if (validAccount) {
				System.out.println("unam taken");
				errorMessage = "Username is already taken.";
			}
			else if(pw!=repw)
			{
				System.out.println("pass no match");
				errorMessage = "Passwords do not match.";
			}
			else{
				System.out.println("else");
				controller.CreateUserAccount(name, pw, email, req.getRemoteAddr(), null, null, null);
				req.getSession().setAttribute("user", name);
				req.getRequestDispatcher("/_view/userHome.jsp").forward(req, resp);
				
			}
			
		}*/
		System.out.println("   Invalid login - returning to /Login");

		// Forward to view to render the result HTML document
		req.getSession().setAttribute("user", name);
		req.getRequestDispatcher("/_view/userHome.jsp").forward(req, resp);
	}
}
