package com.monwo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.monwo.dto.MemberDTO;

public class AdminDAO extends AbstractDAO {
	
	public List<MemberDTO> memberList() {// 파라미터 값이 없을 때 작동하는 메소드
		List<MemberDTO>list = new ArrayList<MemberDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "SELECT mno, mid, mname, mdate, mgrade FROM member";
		ResultSet rs = null;
		
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MemberDTO a = new MemberDTO();
				a.setMno(rs.getInt("mno"));
				a.setMid(rs.getString("mid"));
				a.setMname(rs.getString("mname"));
				a.setMdate(rs.getString("mdate"));
				a.setMgrade(rs.getInt("mgrade"));
				
				list.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, con);
		}
		return list;
	}
	
	public List<MemberDTO> memberList(int grade) {// 파라미터 값(grade)이 있을때 작동하는 메소드
		List<MemberDTO>list = new ArrayList<MemberDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "SELECT mno, mid, mname, mdate, mgrade FROM member WHERE mgrade=?";
		ResultSet rs = null;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, grade);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MemberDTO a = new MemberDTO();
				a.setMno(rs.getInt("mno"));
				a.setMid(rs.getString("mid"));
				a.setMname(rs.getString("mname"));
				a.setMdate(rs.getString("mdate"));
				a.setMgrade(rs.getInt("mgrade"));
				
				list.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, con);
		}
		return list;
	}

	public int gradeUpdate(MemberDTO dto) {
		int reuslt = 0;
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE member SET mgrade=? WHERE mno=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, dto.getMgrade());
			pstmt.setInt(2, dto.getMno());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null, pstmt, con);
			
		}
		return reuslt;
		
	}

}
