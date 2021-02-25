package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.ReportDTO;

public class BoardReportDAO {

	private static BoardReportDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public BoardReportDAO() {
		
	}
	
	public static BoardReportDAO getInstance() {
		if (dao == null) {
			dao = new BoardReportDAO();
		}
		return dao;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public boolean setBoardReport(ReportDTO rdto) {
		boolean result = false;
		String sql = "INSERT INTO BOARDREPORT(BR_BDNUM, BR_BDMBID, BR_MBID)\r\n" + 
				"VALUES(?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rdto.getBR_BDNUM());
			pstmt.setNString(2, rdto.getBR_BDMBID());
			pstmt.setNString(3, rdto.getBR_MBID());
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
