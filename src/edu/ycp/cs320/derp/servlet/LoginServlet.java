/**
 * File copied and modified from CS320 Library Solution Lab 03.
 */
package edu.ycp.cs320.derp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.derp.controller.MainContentController;

//import edu.ycp.cs320.derp.model.TempLoginDatabase;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private TempLoginDatabase model;
	private MainContentController controller;
	String errorMessage = null;
	String name         = null;
	String pw           = null;
	boolean validLogin  = false;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nLoginServlet: doGet");

		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nLoginServlet: doPost");

		

		// Decode form parameters and dispatch to controller
		name = req.getParameter("user");
		pw   = req.getParameter("pass");
		System.out.println("   Name: <" + name + "> PW: <" + pw + ">");

		if(name == null || pw == null || name.equals("") || pw.equals("")){
			errorMessage = "Please specify both user name and password";
		}else{
			System.out.print("else");
			controller = new MainContentController();
			System.out.println("valid login strst");
			validLogin = controller.UserNamePasswordCheck(name, pw);
			System.out.println("valid login end");

			if(!validLogin){
				System.out.println("invalid login");
				errorMessage = "Username and/or password invalid";
			}

		}
		System.out.print("setting attributes");
		// Add parameters as request attributes
		req.setAttribute("username", req.getParameter("user"));
		req.setAttribute("password", req.getParameter("pass"));

		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("login",        validLogin);

		// if login is valid, start a session
		if (validLogin) {
			System.out.println("   Valid login - starting session, redirecting to /userHome");

			// store user object in session
			req.getSession().setAttribute("user", name);

			// redirect to /index page
			System.out.println(req.getContextPath());
			resp.sendRedirect(req.getContextPath() + "/userHome");

			return;
		}

		System.out.println("   Invalid login - returning to /Login");

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
}
