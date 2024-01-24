package com.monwo.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monwo.dao.BoardDAO;
import com.monwo.dao.LogDAO;
import com.monwo.dto.BoardDTO;
import com.monwo.util.Util;

/**
 * Servlet implementation class BoardList
 */
@WebServlet("/board")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//페이지 
		int page = 1;
		if (request.getParameter("page")!=null && request.getParameter("page") != "") {
			page = Util.str3Int(request.getParameter("page"));
		}
		
		//log
		Map<String, Object> log = new HashMap<String, Object>();
		log.put("ip", Util.getIP(request));
		log.put("url", "./board");
		log.put("data", "page="+page);
		LogDAO logDAO = new LogDAO();
		logDAO.write(log);
		
		//파라미터 잡아주기
		
		//DAO 연결하기
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> list =dao.boardList(page);
		int totalCount = dao.totalCount();
		
		//요청하기 
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("page", page);
		RequestDispatcher rd = request.getRequestDispatcher("board.jsp");
		rd.forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
