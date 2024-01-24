package com.monwo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.monwo.dao.CommentDAO;
import com.monwo.dto.CommentDTO;
import com.monwo.util.Util;


@WebServlet("/commentDel")
public class CommentDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CommentDel() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("mid") != null 
				&& Util.intCheck2(request.getParameter("no"))
				&& Util.intCheck(request.getParameter("cno"))
				) {
			CommentDTO dto = new CommentDTO();
			dto.setMid((String)session.getAttribute("mid"));
			dto.setCno(Util.str3Int(request.getParameter("cno")));
			dto.setBoard_no(Util.str3Int(request.getParameter("no")));
			
			CommentDAO dao = new CommentDAO();
			int result = dao.commenDelete(dto);
			if (result == 1) {
				response.sendRedirect("./detail?no="+request.getParameter("no"));
			}else {
				response.sendRedirect("./error.jsp");
			}
			
		} else {
			response.sendRedirect("./error.jsp");
		}
		System.out.println(request.getParameter("no"));
		System.out.println(request.getParameter("cno"));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int result = 0; // 데이터스코프 때문에 result를 위에서 적어준다.
		if (session.getAttribute("mid") != null &&
				Util.intCheck(request.getParameter("no"))) {
			CommentDTO dto = new CommentDTO();
			dto.setMid((String)session.getAttribute("mid"));
			dto.setCno(Util.str3Int(request.getParameter("no")));
		
			
			CommentDAO dao = new CommentDAO();
			result = dao.commenDelete(dto);
			
		PrintWriter pw = response.getWriter();
		pw.print(result);
		}
	}
}