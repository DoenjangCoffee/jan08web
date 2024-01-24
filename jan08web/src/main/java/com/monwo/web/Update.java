package com.monwo.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.monwo.dao.BoardDAO;
import com.monwo.dto.BoardDTO;
import com.monwo.util.Util;


@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Update() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// no 잡기
		HttpSession session = request.getSession();
		
		if (session.getAttribute("mid") != null) { //if문을 통해서 사용자가 로그인 했는지 확인
			//세션이 있을 때 = 정상 작업 진행하기.
			
			int no = Util.str3Int(request.getParameter("no"));
			//DAO에 질의하기
			BoardDAO dao = new BoardDAO();
			BoardDTO dto = dao.detail(no);
			//System.out.println(dto.getMid().equals(session.getAttribute("mid")));
			//System.out.println(session.getAttribute("mid").equals(dto.getMid()));
			//System.out.println(session.getAttribute("mid")==dto.getMid());
			//System.out.println(((String)session.getAttribute("mid")).equals(dto.getMid()));
			
			if (session.getAttribute("mid").equals(dto.getMid()) ) {
				request.setAttribute("update", dto);
				RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("./error.jsp");
			}	
		} else {
				response.sendRedirect("./login?login=nologin");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		if (request.getParameter("title") != null 
			&& request.getParameter("content") != null
			&& Util.intCheck(request.getParameter("no")) 
			&& session.getAttribute("mid") != null) {
			// 조건이 참일때
			BoardDTO dto = new BoardDTO();
			dto.setContent(request.getParameter("content"));
			dto.setTitle(request.getParameter("title"));
			dto.setNo(Util.str3Int(request.getParameter("no")));
			dto.setMid((String)session.getAttribute("mid"));
			
			BoardDAO dao = new BoardDAO();
			int result = dao.update(dto);
			System.out.println("수정 결과 : "+ result);
			
			response.sendRedirect("./detail?no="+request.getParameter("no"));
		} else {
			// 거짓일 때
			response.sendRedirect("./error.jsp");
		}				
		
		
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String no = request.getParameter("no");
		
		System.out.println(title);
		System.out.println(content);
		System.out.println(no);
	}

}
