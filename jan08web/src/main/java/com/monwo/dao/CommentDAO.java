package com.monwo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.monwo.dto.CoffeeDTO;
import com.monwo.dto.CommentDTO;

public class CommentDAO extends AbstractDAO {
	
	public int commentWrite(CommentDTO dto) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "INSERT INTO comment (ccomment, board_no, mno, cip)"
				+ "VALUES(?, ?, (SELECT mno FROM member WHERE mid=?), ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getComment());
			pstmt.setInt(2, dto.getBoard_no());
			pstmt.setString(3, dto.getMid());
			pstmt.setString(4, dto.getIp());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null, pstmt, con);
		}
		return result;
	}

	public int commenDelete(CommentDTO dto) {
		int result = 0;
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE comment SET cdel=0 WHERE cno=? AND mno=(SELECT mno FROM member WHERE mid=?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getCno());
			pstmt.setString(2, dto.getMid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null, pstmt, con);
		}
		
		return result;
	}

	public int commentUpdate(CommentDTO dto) {
		int result = 0;
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE comment SET ccomment=? WHERE cno=? AND mno=(SELECT mno FROM member WHERE mid=?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getComment());
			pstmt.setInt(2, dto.getCno());
			pstmt.setString(3, dto.getMid());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null, pstmt, con);
		}
		
		return result;
	}

	public int save(CoffeeDTO dto) {
		int result = 1;
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO coffee (fname, fcount)VALUES(? , ?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getFname());
			pstmt.setInt(2, dto.getFcount());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null, pstmt, con);
		}
		
		return result;
	}
}
