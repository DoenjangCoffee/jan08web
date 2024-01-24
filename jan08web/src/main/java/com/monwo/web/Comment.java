package com.monwo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.monwo.dao.CommentDAO;
import com.monwo.dto.CommentDTO;
import com.monwo.util.Util;

@WebServlet("/comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Comment() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글처리
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		if (session.getAttribute("mid")!=null && 
				request.getParameter("commentcontent") != null &&
				request.getParameter("bno") !=null) {
			//오는 값 받기
			String commentcontent = request.getParameter("commentcontent"); // 댓글 내용
			
			//HTML에서 태그를 특수기호로 변경하기
			commentcontent = Util.removeTag(commentcontent);// 사용자가 적은 특수기호 제거하기
			
			String bno = request.getParameter("bno") ; //글번호
			//System.out.println(commentcontent+ " : "+bno);
			
			//enter처리(사용자가 엔터를 누르면 줄 바꿈)
			commentcontent = Util.addBR(commentcontent);
			
			//저장하기
			CommentDTO dto = new CommentDTO();
			CommentDAO dao = new CommentDAO();
			dto.setComment(commentcontent);
			dto.setBoard_no(Util.str3Int(bno));
			dto.setMid((String)session.getAttribute("mid"));
			dto.setIp(Util.getIP(request));
			
			dao.commentWrite(dto);
			//이동하기
			response.sendRedirect("./detail?no="+bno);
		} else {
			response.sendRedirect("./login");
		}
		
	}

}