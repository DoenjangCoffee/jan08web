package com.monwo.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.monwo.dao.LogDAO;
import com.monwo.dao.MemberDAO;
import com.monwo.dto.MemberDTO;
import com.monwo.util.Util;


@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = null;
		if (session.getAttribute("mname") == null) {
			//로그인 페이지 열어주기
			url ="login.jsp";
		} else {
			url="already.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("id") != null && request.getParameter("pw")!= null) {
			MemberDTO dto = new MemberDTO();
			dto.setMid(request.getParameter("id"));
			dto.setMpw(request.getParameter("pw"));
			
			MemberDAO dao = new MemberDAO();
			dto = dao.login(dto);
			
			//로그인한 사용자 ip저장하기
			Map<String, Object> log = new HashMap<String, Object>();
			log.put("ip", Util.getIP(request));
			log.put("url", "./login");
			log.put("data", "id : "+dto.getMid() + "pw : "+ dto.getMpw() + "결과 : "+ dto.getCount());
			
			LogDAO logDAO = new LogDAO();
			logDAO.write(log);
			
			if (dto.getCount() == 1) {
				System.out.println("정상로그인");
				//세션 만들기 (외워도 좋다.)
				HttpSession session = request.getSession();
				session.setAttribute("mname", dto.getMname());//mname이라는 이름으로 세션을 만듬
				session.setAttribute("mid", dto.getMid()); //mid라는 이름으로 세션을 만듬
				// 페이지 이동 = board
				response.sendRedirect("./board");
			} else {
				System.out.println("로그인이 안됩니다");
				//페이지 이동 = login?error = 4567
				response.sendRedirect("./login?error=4567");
			}
			
		} else {

		}
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		System.out.println("id :"+id+" pw : "+pw);
	}

}
