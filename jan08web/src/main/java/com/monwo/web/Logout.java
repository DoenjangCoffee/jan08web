package com.monwo.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Logout() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 세션 쿠기
		 *  
		 * 세션 : 서버에 저장된다.[필요할 때 마다 꺼내쓴다.]			쿠키: 클라이언트에 저장된다.[보안에 취약하다](브라우저)
		 * 		  로그인 정보를 저장한다.									  쇼핑정보, 장바구니, 방문내역
		 * 		  자바 계열로 만든다.										  자바 스크립트
		 * 		  은닉														  오픈?
		 */
		
		//세션 종료
		HttpSession session = request.getSession(); // 세션 인스턴스 생성
		if (session.getAttribute("mname") != null) { // 세션이 있다면
			//session.setMaxInactiveInterval(3600); set은 세션 시간 연장하기. 
			System.out.println("세션 유효시간 : "+ session.getMaxInactiveInterval());// 세션 유효시간 메소드[web xml에서 유효시간 조절 할 수 있다.]- 기본 30분
			System.out.println("mname : "+session.getAttribute("mname"));
			session.removeAttribute("mname"); // mname세션만 종료
		}
		if (session.getAttribute("mid") != null) {
			System.out.println("mid : "+session.getAttribute("mid"));
			session.removeAttribute("mid");// mid세션만 종료
		}
		session.invalidate();// 모든 세션을 종료한다.
		//removeAttribute()는 현재 세션에서 특정 key-value만 제거를 한다.
		// 키만 제거하면 httpSession 인스턴스가 남아있어
		//invalidate()로 해주는 것이 좋다.
		
		//login 페이지로 전달하기
		//response.sendRedirect("./logout.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("logout.jsp");
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post으로 들어왔어요");
	}

}
