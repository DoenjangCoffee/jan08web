package com.monwo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monwo.dao.CommentDAO;
import com.monwo.dto.CoffeeDTO;


@WebServlet("/coffee")
public class Coffee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Coffee() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("coffee.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String menu = request.getParameter("menu");
		int count = 1;
		int result = 0;
		CommentDAO dao = new CommentDAO();
		CoffeeDTO dto = new CoffeeDTO();
		dto.setFname(menu);
		dto.setFcount(count);
		
		result=dao.save(dto);
		
		PrintWriter pw = response.getWriter();
		pw.print(result);
		
	}

}
