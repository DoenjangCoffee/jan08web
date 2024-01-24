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
import com.monwo.dto.MemberDTO;
import com.monwo.util.Util;


@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Delete() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//01-16 로그인 한 사용자 + 내글
		HttpSession session = request.getSession();
		
			//no가 숫자야? + 로그인 했어?
			if (Util.intCheck(request.getParameter("no")) && session.getAttribute("mid") != null) {
				//숫자네 -> 삭제를 진행 -> board로 진행
				//System.out.println("숫자입니다.");
				//번호 잡기
				//int no = request.getParameter("no");
				int no = Util.str3Int(request.getParameter("no"));
				
				
				//DAO에게 일 시키기
				BoardDAO dao = new BoardDAO();
				
				// board_no, mid가 같이 있는 클래스는 boardDTO(01-16)
				BoardDTO dto = new BoardDTO();
				dto.setNo(no);
				dto.setMid((String)session.getAttribute("mid"));
				int result = dao.delete(dto);
				// 잘 삭제되었는지 값 받기
				System.out.println("삭제여부 : "+result);
				if (result == 1) {
					//값이 1이면 어디로
					response.sendRedirect("./board");
				}else {
					//값이 0이면 어디로
					response.sendRedirect("./error.jsp");
				}
				
				
			} else {
				//숫자가 아니네 -> 에러 표시
				//System.out.println("문자입니다.");
				response.sendRedirect("./error.jsp");
			}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
