package com.monwo.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/admin/index") // url 의 경로 = 실제 파일과 다르게 호출할 url을 지정한다.
public class AdminIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminIndex() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (String.valueOf(session.getAttribute("mid")).equals("monwo")) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/admin.jsp"); // 파일 있는 경로
			rd.forward(request, response);
		}else {
			response.sendRedirect("error.jsp");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
