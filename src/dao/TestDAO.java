package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import static db.JdbcUtil.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TestDAO {

	private static TestDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public TestDAO() {
		
	}
	
	// getInstance메소드
	public static TestDAO getInstance() {
		if (dao == null) {
			dao = new TestDAO();
		}
		return dao;
	}

	// setConnection 메소드
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public String test(String test) {
		String result = null;
		String sql = "{CALL IDCHECK(?, ?)}";
		try {
			CallableStatement proc = con.prepareCall(sql);
			proc.setNString(1, test);
			proc.registerOutParameter(2, Types.INTEGER);
			proc.executeQuery();
			result = (proc.getInt(2)==0)?"사용가능":"사용불가";
			proc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public JSONArray testCommentList() {
		String sql = "\r\n" + 
				"SELECT *\r\n" + 
				"FROM COMMENTS\r\n" + 
				"WHERE CM_BDNUM = ?";
		JSONArray result = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			rs = pstmt.executeQuery();
			
			JSONObject comments;
			result = new JSONArray();
			while(rs.next()) {
				comments = new JSONObject();
				comments.put("CM_MBID", rs.getNString("CM_MBID"));
				comments.put("CM_CONTENT", rs.getNString("CM_CONTENT"));
				comments.put("CM_DATE", rs.getNString("CM_DATE"));
				result.add(comments);
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
