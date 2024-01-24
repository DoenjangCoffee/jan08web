package com.monwo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.monwo.dao.BoardDAO;
import com.monwo.dto.BoardDTO;
import com.monwo.dto.CommentDTO;
import com.monwo.util.Util;


@WebServlet("/detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Detail() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//오는 no잡기
		
	
		
		
		//int no = Integer.parseInt(request.getParameter("no"));
		
		int no = Util.str3Int(request.getParameter("no")); // 위에 있는 걸 아래처럼 변경 했다.(이게 숫자여? 라는 질문)
		//데이터베이스에 질의하기
		
		//log
		BoardDAO dao = new BoardDAO();
		//LogDAO log = new LogDAO();
		dao.logWrite(Util.getIP(request), getServletName(), "no="+no);
		
		
		//로그인한 회원이라면 읽음수 올리기 01-19 프레임워크 프로그래밍
		HttpSession session = request.getSession();
		if (session.getAttribute("mid") != null) {
			//bno, 로그인한 mno
			dao.countUp(no,(String)session.getAttribute("mid"));
		}
		BoardDTO dto = dao.detail(no);
		
		//System.out.println(dto.getTitle());
		//System.out.println(dto.getContent() == null);
		if (no == 0 || dto.getContent() == null) {
			//null이면 에러로
			response.sendRedirect("./error.jsp");
		} else {
			//정상 출력
			
			//내용 가져오기
			request.setAttribute("detail", dto);
			
			// 해당 글 번호에 댓글이 있다면 List 뽑아내기
			List<CommentDTO>commentList = dao.commentList(no); // 
			
			if (commentList.size() > 0) {
				request.setAttribute("commentList", commentList);
			}
			//request.Dispatcher 호출하기.
			RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
			rd.forward(request, response);
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
