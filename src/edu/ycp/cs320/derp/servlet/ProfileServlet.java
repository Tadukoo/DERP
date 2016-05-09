package edu.ycp.cs320.derp.servlet;


import java.util.List;
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.derp.model.Poll;
import edu.ycp.cs320.derp.model.User;

import edu.ycp.cs320.derp.controller.MainContentController;

public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MainContentController controller;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User thisUser = (User)req.getSession().getAttribute("user");
		if (thisUser == null) {
			System.out.println("   User: <" + thisUser.getUserName() + "> not logged in or session timed out");
			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		controller = new MainContentController();
		req.setAttribute("fullname", thisUser.getFirstName() + " " + thisUser.getLastName());
		req.setAttribute("username", thisUser.getUserName());
		req.setAttribute("email", thisUser.getEmail());
		req.setAttribute("Inst", thisUser.getInstitution());
		
		List<Poll> pollList = controller.findPollByUsername(thisUser.getUserName());
		User pollUser = null;
		Poll poll1=null;
		Poll poll2=null;
		Poll poll3=null;
		int dis = 0;
		
		if( pollList.size()>0){
			poll1=pollList.get(0);
			req.setAttribute("fullname1", poll1.getTitle());
			pollUser = controller.findUserbyUserId(poll1.getUserId());
			req.setAttribute("poll1user", pollUser.getUserName());
			dis =poll1.getTotalVotes() - poll1.getYesVotes();
			req.setAttribute("info1", "Agree:" + poll1.getYesVotes() + " DisAgree:" + dis);
		
			if( pollList.size()>1){
				poll2=pollList.get(1);
				req.setAttribute("fullname2", poll2.getTitle());
				pollUser = controller.findUserbyUserId(poll2.getUserId());
				req.setAttribute("poll2user", pollUser.getUserName());
				dis =poll2.getTotalVotes() - poll2.getYesVotes();
				req.setAttribute("info2", "Agree:" + poll2.getYesVotes() + " DisAgree:" + dis);
			
				if( pollList.size()>2){
					poll3=pollList.get(2);
					req.setAttribute("fullname3", poll3.getTitle());
					pollUser = controller.findUserbyUserId(poll3.getUserId());
					req.setAttribute("poll3user", pollUser.getUserName());
					dis =poll3.getTotalVotes() - poll3.getYesVotes();
					System.out.println(poll3.getYesVotes());
					req.setAttribute("info3", "Agree:" + poll3.getYesVotes() + " DisAgree:" + dis);
				}
			}
		}
			
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
	}
}
