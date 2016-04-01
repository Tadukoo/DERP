package edu.ycp.cs320.derp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.derp.controller.GuessingGameController;
import edu.ycp.cs320.derp.model.GuessingGame;

public class PollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/poll.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		String title = (String) req.getSession().getAttribute("title");
		req.setAttribute("title", title);
		
		req.getRequestDispatcher("/_view/poll.jsp").forward(req, resp);
	}
}
