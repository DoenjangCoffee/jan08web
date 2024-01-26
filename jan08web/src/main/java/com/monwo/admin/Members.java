package com.monwo.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monwo.dao.AdminDAO;
import com.monwo.dto.MemberDTO;
import com.monwo.util.Util;


@WebServlet("/admin/members")
public class Members extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Members() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();
		List<MemberDTO> list = null;
		
		if (request.getParameter("grade") == null || request.getParameter("grade").equals("")) {
			list = dao.memberList(); // 파라미터가 null면 회원관리 페이지 출려
		} else {
			list = dao.memberList(Util.str2Int(request.getParameter("grade")));
		}
		
		
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/members.jsp");
		rd.forward(request, response);

		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DB에 변경
		System.out.println(request.getParameter("currentgrade"));
		String mno = request.getParameter("mno");
		String grade = request.getParameter("grade");
		
		MemberDTO dto = new MemberDTO();
		AdminDAO dao = new AdminDAO();
		
		dto.setMno(Util.str2Int(mno));
		dto.setMgrade(Util.str2Int(grade));
		
		dao.gradeUpdate(dto);
		
		//페이지 이동
		if (request.getParameter("currentgrade") == null || request.getParameter("currentgrade").equals("")) {
			response.sendRedirect("./members");
		} else {
			response.sendRedirect("./members?grade="+request.getParameter("currentgrade"));
		}
		
		
	}

}
