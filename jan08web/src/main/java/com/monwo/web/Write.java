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

/**
 * Servlet implementation class Write
 */
@WebServlet("/write")
public class Write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Write() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("mname")==null) {
			response.sendRedirect("./login"); // url주소 까지 변경해서 화면에 보여준다.
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("write.jsp"); // url주소는 고정, 화면만 변경해준다.
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 깨지는 거 처리하는 방법
		request.setCharacterEncoding("UTF-8");
		
		//세션에 들어있는 mid가져오기
		HttpSession session = request.getSession();
		
		//if문으로 로그인 되어있는 사람만 아래 로직 수행하도록 변경해야 한다.
		if (session.getAttribute("mid") == null || session.getAttribute("mname") == null) {
			// 로그인 하지 않았다면 login으로 가게 한다.
			response.sendRedirect("./login?login=nologin");
		} else {
			// 로그인이 되어있다면 아래 로직을 수행한다.
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			// HTML태그를 특수기호로 변경하기
			title = Util.removeTag(title); 
			/*
			 * System.out.println(title); System.out.println(content);
			 */
			
			//DAO write메소드 만들기
			BoardDTO dto = new BoardDTO();
			dto.setTitle(title);
			dto.setContent(content);
			dto.setMid((String)session.getAttribute("mid"));// session.getAttribute는 Object 타입이고 dto.setMid()안에 들어가는건 String이기 때문에 캐스팅 해줘야한다.
			dto.setIp(Util.getIP(request));
			
			BoardDAO dao = new BoardDAO();
			int result = dao.write(dto);
			System.out.println("글쓰기 결과는"+ result);
			// 객체를 던진다?
			
			//페이지 이동하기
			if (result == 1) {
				response.sendRedirect("./board");
			}else {
				response.sendRedirect("./error.jsp");
			}
			
		}
	}
}
