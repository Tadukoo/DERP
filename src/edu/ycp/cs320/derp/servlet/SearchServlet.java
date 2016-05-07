package edu.ycp.cs320.derp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.derp.controller.MainContentController;
import edu.ycp.cs320.derp.model.Poll;
import edu.ycp.cs320.derp.model.User;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MainContentController controller;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User thisUser = (User) req.getSession().getAttribute("user");
		String search = (String)req.getSession().getAttribute("search");
		List<Poll> pollList = controller.findPollByTitle(search);
		Poll poll1 = null;
		Poll poll2 = null;
		Poll poll3 = null;
		if(pollList.size() >0){
			poll1=pollList.get(0);
		}
		if(pollList.size() >1){
			poll2=pollList.get(1);
		}
		if(pollList.size() >2){
			poll3=pollList.get(2);
		}
		/*int mostSimilar = 0;
		int place = 0;
		for(int i=0;i<pollList.size();i++){
			char[] charArray = pollList.get(i).getTitle().toCharArray();
			char[] charArrayS = search.toCharArray();
			int count=0;
			for(int j=0;j<charArrayS.length;j++){
				for(int k=0;k<charArray.length;k++){
					if(charArrayS[j]==charArray[k]){
						count++;
					}
					
				}
			}
			if(count>mostSimilar){
				place=i;
			}
			
		}
		int mostSimilar2 = 0;
		int place2 = 0;
		for(int i=0;i<pollList.size();i++){
			char[] charArray = pollList.get(i).getTitle().toCharArray();
			char[] charArrayS = search.toCharArray();
			int count=0;
			for(int j=0;j<charArrayS.length;j++){
				for(int k=0;k<charArray.length;k++){
					if(charArrayS[j]==charArray[k]){
						count++;
					}
					
				}
			}
			if(count>mostSimilar2&&count<mostSimilar){
				place2=i;
			}
			
		}
		int mostSimilar3 = 0;
		int place3 = 0;
		for(int i=0;i<pollList.size();i++){
			char[] charArray = pollList.get(i).getTitle().toCharArray();
			char[] charArrayS = search.toCharArray();
			int count=0;
			for(int j=0;j<charArrayS.length;j++){
				for(int k=0;k<charArray.length;k++){
					if(charArrayS[j]==charArray[k]){
						count++;
					}
					
				}
			}
			if(count>mostSimilar3&&count<mostSimilar2){
				place3=i;
			}
			
		}*/
		req.setAttribute("fullname1", poll1.getTitle());
		req.setAttribute("fullname2", poll2.getTitle());
		req.setAttribute("fullname3", poll3.getTitle());
		int dis =poll1.getTotalVotes() - poll1.getYesVotes();
		req.setAttribute("info1", "Agree:" + poll1.getYesVotes() + " DisAgree:" + dis);
		dis =poll2.getTotalVotes() - poll2.getYesVotes();
		req.setAttribute("info2", "Agree:" + poll2.getYesVotes() + " DisAgree:" + dis);
		dis =poll3.getTotalVotes() - poll3.getYesVotes();
		req.setAttribute("info3", "Agree:" + poll3.getYesVotes() + " DisAgree:" + dis);
		req.getRequestDispatcher("/_view/userHome.jsp").forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getSession().setAttribute("search", req.getAttribute("search"));
		req.getRequestDispatcher("/_view/searchresults.jsp").forward(req, resp);
		
	}
}
