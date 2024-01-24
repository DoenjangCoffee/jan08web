package com.monwo.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.monwo.dao.MemberDAO;
import com.monwo.dto.MemberDTO;


@WebServlet("/join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Join() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("mid") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("join.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("login");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 사용 가능하게 하기
		request.setCharacterEncoding("UTF-8");
		// 값 잡기
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw1");
		
		//db에 보내기
		MemberDTO dto = new MemberDTO();
		dto.setMid(id);
		dto.setMname(name);
		dto.setMpw(pw);
		
		MemberDAO dao = new MemberDAO();
		int result = dao.addUser(dto);
		
		if (result == 1) {
			// 정상적으로 데이터입력을 완료하면 로그인페이지, 
			response.sendRedirect("login");
		} else {
			// 비정상적이면 에러로 보내기
			response.sendRedirect("./error.jsp");
		}
	
		
	}

}
