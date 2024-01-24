package com.monwo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.monwo.db.DBConnection;
import com.monwo.dto.BoardDTO;
import com.monwo.dto.CommentDTO;
import com.monwo.util.Util;

public class BoardDAO extends AbstractDAO {

	public List<BoardDTO> boardList(int page) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		// db정보
		// DBConnection db = DBConnection.getInstance();
		// conn 객체
		Connection con = DBConnection.getInstance().getConnection();
		// pstmt
		PreparedStatement pstmt = null;
		// rs
		ResultSet rs = null;
		// sql
		String sql = "SELECT * FROM boardview LIMIT ?, 10";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (page-1) * 10);
			// page는 BoardList에서 1로 줬기 때문에 먼저 -1빼고 10개씩 보여주기 위해
			//(page-1)*10으로 코드를 작성한다.
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO e = new BoardDTO();
				e.setNo(rs.getInt("board_no"));
				e.setTitle(rs.getString("board_title"));
				e.setWrite(rs.getString("board_write"));
				e.setDate(rs.getString("board_date"));
				e.setCount(rs.getInt("board_count"));
				e.setComment(rs.getInt("comment"));
				list.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return list;
	}

	
	public BoardDTO detail(int no) {
		//countUp(no); // count 올리기
		
		BoardDTO dto = new BoardDTO();
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT b.board_no, b.board_title, b.board_content, m.mname as board_write,"
				+ " m.mid, b.board_date, b.board_ip,(SELECT COUNT(*) FROM visitcount WHERE board_no=b.board_no) AS board_count"
				+ " FROM board b JOIN member m ON b.mno=m.mno "
				+ "WHERE b.board_no=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getInt("board_no"));
				dto.setTitle(rs.getString("board_title"));
				dto.setContent(rs.getString("board_content"));
				dto.setWrite(rs.getString("board_write"));
				dto.setDate(rs.getString("board_date"));
				dto.setCount(rs.getInt("board_count"));
				dto.setMid(rs.getString("mid"));
				dto.setIp(rs.getString("board_ip"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	public void countUp(int no, String mid) {
		//조회수 올리기
		Connection con = db.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {// 사용자가 글을 읽었으면 1 , 안 읽었으면 0
			pstmt=con.prepareStatement("SELECT count(*) FROM visitcount "
					+ "WHERE board_no=? AND mno=(SELECT mno FROM member WHERE mid=?)");
			pstmt.setInt(1, no);
			pstmt.setString(2, mid);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int result = rs.getInt(1);
				if (result == 0) {
					realCountUp(no,mid);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, pstmt, con);
		}
	}


	private void realCountUp(int no, String mid) { // result값이 0이면 realCountUp이 실행된다.
		Connection con = db.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt = con.prepareStatement("INSERT INTO visitcount(board_no, mno)VALUES(?,(SELECT mno FROM member WHERE mid=?))");
			pstmt.setInt(1, no);
			pstmt.setString(2, mid);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, pstmt, con);
		}
	}


	public int write(BoardDTO dto) { //글 쓰기
		int result = 0;
		
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO board (board_title, board_content, mno, board_ip) "
				+ "VALUES(?,?,(SELECT mno FROM member WHERE mid=?), ?)";
		//서브 쿼리를 사용해서 
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getMid());
			pstmt.setString(4, dto.getIp()); // 글쓴이의 IP주소를 받아온다.
			result = pstmt.executeUpdate();//영향받은 행을 result에 저장합니다.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		return result;
	}

	public int delete(BoardDTO dto) {
		int result  = 0;
		
		Connection conn = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_del='0' WHERE board_no=? AND mno=(SELECT mno FROM member WHERE mid=?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			pstmt.setString(2, dto.getMid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null, pstmt, conn);
		}
		return result;
		
	}

	public int update(BoardDTO dto) {
		int result = 0;
		
		Connection conn = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_title = ?, board_content = ? "
				+ "WHERE board_no=? AND mno=(SELECT mno FROM member WHERE mid=?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNo());
			pstmt.setString(4, dto.getMid());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null, pstmt, conn);
		}
		return result;
		
		
	}


	public int totalCount() {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM boardview";
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, pstmt, con);
		}
		return result;
	}


	public List<CommentDTO> commentList(int no) {
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM  commentview WHERE board_no=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setCno(rs.getInt("cno"));
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setComment(rs.getString("ccomment"));
				dto.setCdate(rs.getString("cdate"));
				dto.setMno(rs.getInt("mno")); //  뷰 만들어서 mname, mid를 가져오게 해야한다.
				dto.setMname(rs.getString("mname"));
				dto.setMid(rs.getString("mid"));
				dto.setClike(rs.getInt("clike"));
				dto.setIp(Util.ipMarking(rs.getString("cip")));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, pstmt, con);
		}
		return list;
	}
}
