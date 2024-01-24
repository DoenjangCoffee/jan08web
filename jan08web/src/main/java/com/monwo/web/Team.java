package com.monwo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.team.TeamC;

@WebServlet("/team")
public class Team extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Team() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TeamC t = new TeamC();
		t.choose(5);
		//System.out.println(t.team);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println("<htlm>");
		pw.println("<head>");
		pw.println("<title>Team뽑기</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h1>팀뽑기</h1>");
		for (int i = 0; i < t.team.size(); i++) {			
			pw.println("<h2>" + (i + 1) + "조</h2>");
			pw.println(t.team.get(i));
			pw.println("<hr>");
		}
		pw.println("</body>");
		pw.println("</htlm>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
