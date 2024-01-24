package com.monwo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class LogDAO extends AbstractDAO{
	public void write(String ip, String url, String data) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql="INSERT INTO iplog (iip, iurl, idata) VALUES (?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ip);
			pstmt.setString(2, url);
			pstmt.setString(3, data);
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null, pstmt, con);
		}
	}
	

	public void write(Map<String, Object> log) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql="INSERT INTO iplog (iip, iurl, idata) VALUES (?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, (String)log.get("ip"));
			pstmt.setString(2, (String)log.get("url"));
			pstmt.setString(3, (String)log.get("data"));
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null, pstmt, con);
		}
	}
	
}
