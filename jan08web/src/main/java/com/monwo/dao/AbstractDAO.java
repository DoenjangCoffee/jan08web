package com.monwo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.monwo.db.DBConnection;

// 부모DAO DBConn, close 만 가지고 있는다.
//그럼 자식 클래스는 
public class AbstractDAO {
	DBConnection db = DBConnection.getInstance();
	
	public void logWrite(String ip, String url, String data) {
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
	
	 void close(ResultSet rs, PreparedStatement pstmt, Connection con) {// 접근 제어가 없으면 디폴트값을 갖는다.
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
