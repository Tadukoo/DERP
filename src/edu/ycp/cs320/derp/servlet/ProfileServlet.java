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
		controller = new MainContentController();
		req.setAttribute("fullname", thisUser.getFirstName() + " " + thisUser.getLastName());
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
		List<Poll> pollList = controller.FindAllPolls();
		Poll poll1=null;
		Poll poll2=null;
		Poll poll3=null;
		for(int i=0;i<pollList.size();i++){
			if(pollList.get(i).getUserId() == thisUser.getUserId()){
				if(poll1!=null){
					poll1=pollList.get(i);
				}
				if(poll2!=null){
					poll2=pollList.get(i);
				}
				if(poll3!=null){
					poll3=pollList.get(i);
				}
				
			}
			
		}
		req.setAttribute("fullname1", poll1.getTitle());
		req.setAttribute("fullname2", poll2.getTitle());
		req.setAttribute("fullname3", poll3.getTitle());
		int dis =poll1.getTotalVotes() - poll1.getYesVotes();
		req.setAttribute("info1", "Agree:" + poll1.getYesVotes() + " DisAgree:" + dis);
		dis =poll2.getTotalVotes() - poll2.getYesVotes();
		req.setAttribute("info2", "Agree:" + poll2.getYesVotes() + " DisAgree:" + dis);
		dis =poll3.getTotalVotes() - poll3.getYesVotes();
		req.setAttribute("info3", "Agree:" + poll3.getYesVotes() + " DisAgree:" + dis);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
	}
}
