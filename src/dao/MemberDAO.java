package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import dto.MemberDTO;
import dto.PageDTO;
import dto.UserListDTO;

import static db.JdbcUtil.*;

public class MemberDAO {
	private static MemberDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public MemberDAO() {
		
	}
	
	public static MemberDAO getInstance() {
		if (dao == null) {
			dao = new MemberDAO();
		}
		return dao;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public boolean memberJoin(MemberDTO member) {
		boolean result = false;
		String sql = "INSERT INTO MEMBERS(MB_ID, MB_PW, MB_NAME, MB_LOC, MB_PHONE, MB_LEVEL, MB_STATE, MB_POSTCODE, MB_ROADADDR, MB_JIBUNADDR, MB_DETAILADDR, MB_EXADDR) \r\n"
				+ "VALUES(?, ?, ?, ?, ?, ?, DEFAULT, ?, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMb_id());
			pstmt.setString(2, member.getMb_pw());
			pstmt.setString(3, member.getMb_name());
			pstmt.setString(4, member.getMb_loc());
			pstmt.setString(5, member.getMb_phone());
			pstmt.setString(6, member.getMb_level());
			pstmt.setInt(7, member.getMb_postCode());
			pstmt.setNString(8, member.getMb_roadAddr());
			pstmt.setNString(9, member.getMb_jibunAddr());
			pstmt.setNString(10, member.getMb_detailAddr());
			pstmt.setNString(11, member.getMb_exAddr());
			result = (pstmt.executeUpdate()==1)?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public boolean idCheck(MemberDTO mdto) {
		boolean result = false;
		String sql = "{CALL IDCHECK(?, ?)}";
		try {
			CallableStatement proc = con.prepareCall(sql);
			proc.setNString(1, mdto.getMb_id());
			proc.registerOutParameter(2, Types.INTEGER);
			proc.executeQuery();
			result = (proc.getInt(2)==0)?true:false;
			proc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean loginCheck(MemberDTO member) {
		boolean result = false;
		String sql = "{CALL LOGINCHECK(?, ?, ?)}";
		try {
			CallableStatement proc = con.prepareCall(sql);
			proc.setNString(1, member.getMb_id());
			proc.setNString(2, member.getMb_pw());
			proc.registerOutParameter(3, Types.INTEGER);
			proc.execute();
			result = (proc.getInt(3)==1)?true:false;
			proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean memberLogin(MemberDTO member) {
		boolean result = false;
		String sql = "SELECT * FROM MEMBERS WHERE MB_ID=? AND MB_PW=? AND MB_STATE = 'O'";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMb_id());
			pstmt.setString(2, member.getMb_pw());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = true;
				member.setMb_name(rs.getString("MB_NAME"));
				member.setMb_loc(rs.getString("MB_LOC"));
				member.setMb_level(rs.getString("MB_LEVEL"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}
	
	public void setAccessInfo(MemberDTO member) {
		String sql = "INSERT INTO ACCESSINFO(AC_MBID, AC_DATE, AC_STATE)\r\n" + 
				"VALUES(?, DEFAULT, DEFAULT)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, member.getMb_id());
			if(pstmt.executeUpdate()==1) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
	}
	
	public boolean setLogoutAccessInfo(MemberDTO member) {
		boolean result = false;
		String sql = "INSERT INTO ACCESSINFO(AC_MBID, AC_DATE, AC_STATE)"
				+ " VALUES(?, DEFAULT, -1)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, member.getMb_id());
			result =  (pstmt.executeUpdate() == 1)?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int getUserTotal() {
		int result = 0;
		String sql = "SELECT COUNT(MB_ID)\r\n" + 
				"FROM MEMBERS";
		try {
			pstmt = con.prepareStatement(sql);
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
	
	public ArrayList<UserListDTO> getUserList(PageDTO pdto) {
		ArrayList<UserListDTO> result = null;
		String sql = "SELECT *\r\n" + 
				"FROM (SELECT USERLIST.*,\r\n" + 
				"            ROW_NUMBER() OVER (ORDER BY MB_ID) AS \"MB_NUM\"\r\n" + 
				"        FROM USERLIST)\r\n" + 
				"WHERE MB_NUM BETWEEN ? AND ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pdto.getStart());
			pstmt.setInt(2, pdto.getEnd());
			result = new ArrayList<UserListDTO>();
			UserListDTO mdto;
			rs = pstmt.executeQuery();
			while(rs.next()) {
				mdto = new UserListDTO();
				mdto.setMB_ID(rs.getNString("MB_ID"));
				mdto.setMB_NAME(rs.getNString("MB_NAME"));
				mdto.setMB_LOC(rs.getNString("MB_LOC"));
				mdto.setMB_STATE(rs.getNString("MB_STATE"));
				mdto.setMB_LEVEL(rs.getNString("MB_LEVEL"));
				mdto.setBR_REPORT(rs.getInt("BR_REPORT"));
				mdto.setMB_POSTCODE(rs.getInt("MB_POSTCODE"));
				mdto.setMB_ROADADDR(rs.getNString("MB_ROADADDR"));
				mdto.setMB_JIBUNADDR(rs.getNString("MB_JIBUNADDR"));
				mdto.setMB_DETAILADDR(rs.getNString("MB_DETAILADDR"));
				mdto.setMB_EXADDR(rs.getNString("MB_EXADDR"));
				mdto.setMB_PHONE(rs.getNString("MB_PHONE"));
				mdto.setMB_PW(rs.getNString("MB_PW"));
				result.add(mdto);
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
	
	public boolean getUserDetail(UserListDTO mdto) {
		boolean result = false;
		String sql = "SELECT *\r\n" + 
				"FROM USERLIST\r\n" + 
				"WHERE MB_ID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mdto.getMB_ID());
			rs = pstmt.executeQuery();
			while(rs.next()) { // UserDetail.4
				result = true;
				mdto.setMB_NAME(rs.getNString("MB_NAME"));
				mdto.setMB_LOC(rs.getNString("MB_LOC"));
				mdto.setMB_STATE(rs.getNString("MB_STATE"));
				mdto.setMB_LEVEL(rs.getNString("MB_LEVEL"));
				mdto.setBR_REPORT(rs.getInt("BR_REPORT"));
				mdto.setMB_POSTCODE(rs.getInt("MB_POSTCODE"));
				mdto.setMB_ROADADDR(rs.getNString("MB_ROADADDR"));
				mdto.setMB_JIBUNADDR(rs.getNString("MB_JIBUNADDR"));
				mdto.setMB_DETAILADDR(rs.getNString("MB_DETAILADDR"));
				mdto.setMB_EXADDR(rs.getNString("MB_EXADDR"));
				mdto.setMB_PHONE(rs.getNString("MB_PHONE"));
				mdto.setMB_PW(rs.getNString("MB_PW"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return result; // UserDetail.5
	}

	public boolean setUserBlack(MemberDTO mdto) {
		boolean result = false;
		String sql = "UPDATE MEMBERS SET MB_STATE = 'B' WHERE MB_ID = ? AND MB_STATE = 'O'";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mdto.getMb_id());
			result = (pstmt.executeUpdate()==1)?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public boolean setUserFlashBack(MemberDTO mdto) {
		boolean result = false;
		String sql = "UPDATE MEMBERS SET MB_STATE = 'O' WHERE MB_ID = ? AND MB_STATE = 'B'";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mdto.getMb_id());
			result = (pstmt.executeUpdate()==1)?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public boolean memberModi(MemberDTO mdto) {
		boolean memberModiResult = false;
		String sql = "UPDATE MEMBERS SET MB_PW=?, MB_LOC=?, MB_PHONE=?, MB_LEVEL = ?, MB_POSTCODE = ?, MB_ROADADDR = ?, MB_JIBUNADDR = ?, MB_DETAILADDR = ?, MB_EXADDR = ? WHERE MB_ID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getMb_pw());
			pstmt.setString(2, mdto.getMb_loc());
			pstmt.setString(3, mdto.getMb_phone());
			pstmt.setString(4, mdto.getMb_level());
			pstmt.setInt(5, mdto.getMb_postCode());
			pstmt.setNString(6, mdto.getMb_roadAddr());
			pstmt.setNString(7, mdto.getMb_jibunAddr());
			pstmt.setNString(8, mdto.getMb_detailAddr());
			pstmt.setNString(9, mdto.getMb_exAddr());
			pstmt.setString(10, mdto.getMb_id());
			memberModiResult = (pstmt.executeUpdate()==1)?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return memberModiResult;
	}
	
	public boolean memberDel(MemberDTO mdto) {
		boolean result = false;
		String sql = "{CALL MEMBERDEL(?)}";
		try {
			CallableStatement proc = con.prepareCall(sql);
			proc.setNString(1, mdto.getMb_id());
			result = (proc.executeUpdate()==1)?true:false;
			proc.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}
	
}
