/**
 * File copied and modified from CS320 Library Solution Lab 03 LoginServlet.
 */
package edu.ycp.cs320.derp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.derp.controller.CreateAccountController;
import edu.ycp.cs320.derp.model.TempLoginDatabase;

public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TempLoginDatabase model;
	private CreateAccountController controller;

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
		String pw = null;
		boolean validAccount = false;

		// Decode form parameters and dispatch to controller
		name = req.getParameter("username");
		pw   = req.getParameter("password");

		System.out.println("   Name: <" + name + "> PW: <" + pw + ">");			

		if (name == null || pw == null || name.equals("") || pw.equals("")) {
			errorMessage = "Please specify both user name and password";
		} else {
			model      = new TempLoginDatabase();
			controller = new CreateAccountController(model);
			validAccount = controller.createAccount(name, pw);

			if (!validAccount) {
				errorMessage = "Username is already taken.";
			}
		}

		// Add parameters as request attributes
		req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("password", req.getParameter("password"));

		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("login",        validAccount);

		// if login is valid, start a session
		if (validAccount) {
			System.out.println("   Valid account created - starting session, redirecting to "
					+ "/userHome");

			// store user object in session
			req.getSession().setAttribute("user", name);

			// redirect to /index page
			resp.sendRedirect(req.getContextPath() + "/userHome");

			return;
		}

		System.out.println("   Invalid login - returning to /Login");

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
}
