package com.monwo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monwo.dao.MemberDAO;
import com.monwo.dto.MemberDTO;


@WebServlet("/idCheck")
public class IdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public IdCheck() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		MemberDTO dto = new MemberDTO();
		dto.setMid(id);
		MemberDAO dao = new MemberDAO();
		int result = dao.idDu(dto);
		
		if (result == 1) {
			System.out.println("이미 있는 ID : "+result);
		}else {
			System.out.println("없는 ID : "+result);
		}
		
		PrintWriter pw = response.getWriter();
		pw.print(result);
	}

}
