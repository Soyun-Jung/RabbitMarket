package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.MessageDTO;
import dto.PageDTO;

public class MessageDAO {
	private static MessageDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public MessageDAO() {
		
	}
	
	public static MessageDAO getInstance() {
		if (dao == null) {
			dao = new MessageDAO();
		}
		return dao;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public boolean MessageSend(MessageDTO ms) {
		boolean result = false;
		String sql = "INSERT INTO MESSAGE(MS_RECID, MS_SENDID, MS_TITLE, MS_TEXT, MS_DATE)\r\n" + 
				"VALUES(?, ?, ?, ?, DEFAULT)";
				
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, ms.getMS_RECID());
			pstmt.setNString(2, ms.getMS_SENDID());
			pstmt.setNString(3, ms.getMS_TITLE());
			pstmt.setNString(4, ms.getMS_TEXT());
			result = (pstmt.executeUpdate()==1)?true:false;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int getMyMsgTotal(MessageDTO mdto) {
		int result = 0;
		String sql = "SELECT COUNT(MS_RECID)\r\n" + 
				"FROM MESSAGE\r\n" + 
				"WHERE MS_RECID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mdto.getMS_RECID());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}

	public ArrayList<MessageDTO> getMyMsgList(MessageDTO mdto, PageDTO pdto) {
		ArrayList<MessageDTO> msglist = null;
		String sql = "SELECT *\r\n" + 
				"FROM (SELECT MESSAGE.*,\r\n" + 
				"        ROW_NUMBER() OVER(ORDER BY MS_DATE DESC) AS RN\r\n" + 
				"    FROM MESSAGE\r\n" + 
				"    WHERE MS_RECID = ?)\r\n" + 
				"WHERE RN BETWEEN ? AND ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mdto.getMS_RECID());
			pstmt.setInt(2, pdto.getStart());
			pstmt.setInt(3, pdto.getEnd());
			rs=pstmt.executeQuery();
			msglist = new ArrayList<MessageDTO>();
			while(rs.next()) {
				MessageDTO msg = new MessageDTO();
				msg.setMS_RECID(rs.getNString("MS_RECID"));
				msg.setMS_SENDID(rs.getNString("MS_SENDID"));
				msg.setMS_DATE(rs.getNString("MS_DATE"));
				msg.setMS_TITLE(rs.getNString("MS_TITLE"));
				msg.setMS_TEXT(rs.getNString("MS_TEXT"));
				msglist.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return msglist;
	}

	public int sendMyMsgTotal(MessageDTO mdto) {
		int result = 0;
		String sql = "SELECT COUNT(MS_SENDID)\r\n" + 
				"FROM MESSAGE\r\n" + 
				"WHERE MS_SENDID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mdto.getMS_SENDID());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}

	public ArrayList<MessageDTO> sendMyMsgList(MessageDTO mdto, PageDTO pdto) {
		ArrayList<MessageDTO> msglist = null;
		String sql = "SELECT *\r\n" + 
				"FROM (SELECT MESSAGE.*,\r\n" + 
				"        ROW_NUMBER() OVER(ORDER BY MS_DATE DESC) AS RN\r\n" + 
				"    FROM MESSAGE\r\n" + 
				"    WHERE MS_SENDID = ?)\r\n" + 
				"WHERE RN BETWEEN ? AND ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mdto.getMS_SENDID());
			pstmt.setInt(2, pdto.getStart());
			pstmt.setInt(3, pdto.getEnd());
			rs=pstmt.executeQuery();
			msglist = new ArrayList<MessageDTO>();
			while(rs.next()) {
				MessageDTO msg = new MessageDTO();
				msg.setMS_RECID(rs.getNString("MS_RECID"));
				msg.setMS_SENDID(rs.getNString("MS_SENDID"));
				msg.setMS_DATE(rs.getNString("MS_DATE"));
				msg.setMS_TITLE(rs.getNString("MS_TITLE"));
				msg.setMS_TEXT(rs.getNString("MS_TEXT"));
				msglist.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return msglist;
	}

	public boolean getMessageDetail(MessageDTO mdto) {
		boolean result = false;
		String sql = "SELECT *\r\n" + 
				"FROM MESSAGE\r\n" + 
				"WHERE MS_SENDID = ? AND MS_RECID = ? AND MS_DATE = TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mdto.getMS_SENDID());
			pstmt.setNString(2, mdto.getMS_RECID());
			pstmt.setNString(3, mdto.getMS_DATE());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = true;
				mdto.setMS_TEXT(rs.getNString("MS_TEXT"));
				mdto.setMS_TITLE(rs.getNString("MS_TITLE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}

}
