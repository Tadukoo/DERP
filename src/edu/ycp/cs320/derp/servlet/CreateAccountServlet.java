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
import edu.ycp.cs320.derp.model.User;

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

		//User thisUser = (User)req.getParameter("user");
		boolean validAccount = true;

		// Decode form parameters and dispatch to controller
		String fstnme =req.getParameter("user");
		String lstnme = req.getParameter("user");
		String usrname = req.getParameter("user");
		String email = req.getParameter("email");
		String pw   = req.getParameter("pass");
		String repw   = req.getParameter("reppass");
		String inst = req.getParameter("institution");

		System.out.println("   Name: <" + usrname + "> PW: <" + pw + ">");	
		
		controller = new MainContentController();
		String errorMessage;
		
		validAccount =controller.UserNamePasswordCheck(usrname, pw);
		
		if (usrname == null || pw == null || usrname.equals("") || pw.equals("")||fstnme == null || lstnme == null || fstnme.equals("") || lstnme.equals("")) {
			errorMessage = "Please specify both user name and password";
			System.out.println("   Invalid login - returning to /Login");
			req.getRequestDispatcher("/_view/createAccount.jsp").forward(req, resp);
			req.getSession().setAttribute("login", false);
		} else {

			if (validAccount) {
				System.out.println("unam taken");
				errorMessage = "Username is already taken.";
				System.out.println("   Invalid login Username taken- returning to /Login");
				req.getRequestDispatcher("/_view/createAccount.jsp").forward(req, resp);
				req.getSession().setAttribute("login", false);
			}
			else if(!pw.equals(repw))
			{
				errorMessage = "Passwords do not match.";
				System.out.println("   Invalid login  mismatch password- returning to /Login");
				req.getRequestDispatcher("/_view/createAccount.jsp").forward(req, resp);
				req.getSession().setAttribute("login", false);
			}
			else{
				System.out.println("else");
				controller.CreateUserAccount(fstnme, lstnme, usrname, pw, email, req.getRemoteAddr(), inst);
				req.getSession().setAttribute("login", true);
				User thisUser = controller.GetUserByUsername(usrname);
				req.getSession().setAttribute("user", thisUser);
				req.getRequestDispatcher("/_view/userHome.jsp").forward(req, resp);
				
			}
			
		}

		// Add result objects as request attributes
		
	}
}
