package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BoardListDTO;
import dto.CommentDTO;

public class CommentDAO {
	private static CommentDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public CommentDAO() {
		
	}
	
	// getInstance메소드
	public static CommentDAO getInstance() {
		if (dao == null) {
			dao = new CommentDAO();
		}
		return dao;
	}

	// setConnection 메소드
	public void setConnection(Connection con) {
		this.con = con;
	}

	public ArrayList<CommentDTO> getCmList(BoardListDTO board) {
		String sql = "SELECT *\r\n" +
					"FROM COMMENTS WHERE CM_BDNUM=?";
		ArrayList<CommentDTO> cmList = new ArrayList<CommentDTO>();
		CommentDTO comments = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board.getBD_NUM());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				comments = new CommentDTO();
				
				comments.setCm_bdmbid(rs.getNString("CM_BDMBID"));
				comments.setCm_bdnum(rs.getInt("CM_BDNUM"));
				comments.setCm_content(rs.getNString("CM_CONTENT"));
				comments.setCm_date(rs.getNString("CM_DATE"));
				comments.setCm_mbid(rs.getNString("CM_MBID"));
				comments.setCm_state(rs.getInt("CM_STATE"));
				
				cmList.add(comments);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return cmList;
	}

	public boolean WriteComments(CommentDTO comments) {
		boolean result = false;
		String sql = "INSERT INTO COMMENTS(CM_BDNUM, CM_BDMBID, CM_MBID, CM_CONTENT, CM_DATE, CM_STATE)\r\n" + 
				"VALUES(?, ?, ?, ?, DEFAULT, DEFAULT)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comments.getCm_bdnum());
			pstmt.setNString(2, comments.getCm_bdmbid());
			pstmt.setNString(3, comments.getCm_mbid());
			pstmt.setNString(4, comments.getCm_content());
			
			result = (pstmt.executeUpdate()==1)?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int getBoardCount() {
		int result = 0;
		String sql = "SELECT MAX(BD_NUM)\r\n" + 
				"FROM BOARD";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1); // BoardList.4
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return result; // BoardList.5
	}

	public boolean CmDelete(CommentDTO cmi) {
		boolean delResult = false;
		String sql = "UPDATE COMMENTS SET CM_STATE = -1"
				+ " WHERE CM_BDNUM = ? AND CM_BDMBID = ? AND CM_MBID = ? AND CM_DATE = TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') AND CM_STATE = 1";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cmi.getCm_bdnum());
			pstmt.setNString(2, cmi.getCm_bdmbid());
			pstmt.setNString(3, cmi.getCm_mbid());
			pstmt.setNString(4, cmi.getCm_date());
		delResult = (pstmt.executeUpdate()==1)?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return delResult;
	}
	
	public boolean setCommentFlashBack(CommentDTO cmi) {
		boolean result = false;
		String sql = "UPDATE COMMENTS SET CM_STATE = 1 WHERE CM_BDNUM = ? AND CM_BDMBID = ? AND CM_MBID = ? AND CM_DATE = TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') AND CM_STATE = -1";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cmi.getCm_bdnum());
			pstmt.setNString(2, cmi.getCm_bdmbid());
			pstmt.setNString(3, cmi.getCm_mbid());
			pstmt.setNString(4, cmi.getCm_date());
			result = (pstmt.executeUpdate()==1)?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
}
