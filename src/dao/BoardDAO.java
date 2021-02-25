package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BoardDTO;
import dto.BoardListDTO;
import dto.MemberDTO;
import dto.PageDTO;

import static db.JdbcUtil.*;

public class BoardDAO {
	private static BoardDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public BoardDAO() {
		
	}
	
	// getInstance메소드
	public static BoardDAO getInstance() {
		if (dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}

	// setConnection 메소드
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public boolean setBoardWrite(BoardDTO bdto) {
		boolean result = false;
		String sql = "INSERT INTO BOARD(BD_NUM, BD_TITLE, BD_MBID, BD_CONTENT, BD_FILE, BD_HIT, BD_DATE, BD_STATE, BD_DEL)\r\n" + 
				"VALUES(BD_NUM_SEQ.NEXTVAL, ?, ?, ?, ?, DEFAULT, DEFAULT, ?, DEFAULT)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, bdto.getBd_title());
			pstmt.setNString(2, bdto.getBd_mbid());
			pstmt.setNString(3, bdto.getBd_content());
			pstmt.setNString(4, bdto.getBd_file());
			pstmt.setNString(5, bdto.getBd_state());
			result = (pstmt.executeUpdate()==1)?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public boolean BoardBhit(BoardListDTO board) {
		// TODO Auto-generated method stub
		String sql = "UPDATE BOARD SET BD_HIT = BD_HIT+1 WHERE BD_NUM=?";
		boolean hitResult = false;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board.getBD_NUM());
			hitResult = (pstmt.executeUpdate()==1)?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return hitResult;
	}

	public boolean BoardView(BoardListDTO board) {
		boolean result = false;
		String sql = "SELECT *\r\n" + 
				"FROM BOARDLIST\r\n " +
				"WHERE BD_NUM = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board.getBD_NUM());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = true;
				board.setBD_DATE(rs.getNString("BD_DATE"));
				board.setBD_DEL(rs.getInt("BD_DEL"));
				board.setBD_HIT(rs.getInt("BD_HIT"));
				board.setMB_ID(rs.getNString("MB_ID"));
				board.setBD_NUM(rs.getInt("BD_NUM"));
				board.setBD_STATE(rs.getNString("BD_STATE"));
				board.setBD_TITLE(rs.getNString("BD_TITLE"));
				board.setMB_LEVEL(rs.getNString("MB_LEVEL"));
				board.setMB_LOC(rs.getNString("MB_LOC"));
				board.setMB_STATE(rs.getNString("MB_STATE"));
				board.setBD_CONTENT(rs.getNString("BD_CONTENT"));
				board.setBD_FILE(rs.getNString("BD_FILE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}

	public int BoardDel(int bNum) {
		int delResult = 0;
		String sql = "DELETE BOARDT WHERE BNUM=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			delResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return delResult;
	}
	
	public int getBoardTotal() {
		int result = 0;
		String sql = "SELECT COUNT(BD_NUM)\r\n" + 
				"FROM BOARDLIST\r\n" +
				"WHERE BD_STATE != 'NO'";
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
			close(pstmt);;
			close(rs);
		}
		return result;
	}

	public ArrayList<BoardListDTO> BoardList(PageDTO pdto) {
		String sql = "SELECT *\r\n" + 
				"FROM (SELECT BOARDLIST.*,\r\n" + 
				"            ROW_NUMBER() OVER(ORDER BY BD_NUM DESC) AS RN\r\n" + 
				"    FROM BOARDLIST\r\n" +
				"	 WHERE BD_STATE != 'NO')\r\n" +
				"WHERE RN BETWEEN ? AND ?";

		ArrayList<BoardListDTO> boardlist = new ArrayList<BoardListDTO>();
		BoardListDTO board = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pdto.getStart());
			pstmt.setInt(2, pdto.getEnd());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				board = new BoardListDTO();

				board.setBD_DATE(rs.getNString("BD_DATE"));
				board.setBD_DEL(rs.getInt("BD_DEL"));
				board.setBD_HIT(rs.getInt("BD_HIT"));
				board.setMB_ID(rs.getNString("MB_ID"));
				board.setBD_NUM(rs.getInt("BD_NUM"));
				board.setBD_STATE(rs.getNString("BD_STATE"));
				board.setBD_TITLE(rs.getNString("BD_TITLE"));
				board.setMB_LEVEL(rs.getNString("MB_LEVEL"));
				board.setMB_LOC(rs.getNString("MB_LOC"));
				board.setMB_STATE(rs.getNString("MB_STATE"));
				board.setBR_REPORT(rs.getInt("BR_REPORT"));
				
				boardlist.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardlist;
	}

	public int getBoardTotal(MemberDTO mdto) {
		int result = 0;
		String sql = "SELECT COUNT(BD_NUM)\r\n" + 
				"FROM BOARDLIST\r\n" +
				"WHERE MB_LOC = ? AND BD_STATE != 'NO'";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mdto.getMb_loc());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);;
			close(rs);
		}
		return result;
	}

	public ArrayList<BoardListDTO> BoardList(MemberDTO mdto, PageDTO pdto) {
		String sql = "SELECT *\r\n" + 
				"FROM (SELECT BOARDLIST.*,\r\n" + 
				"            ROW_NUMBER() OVER(ORDER BY BD_NUM DESC) AS RN\r\n" + 
				"    FROM BOARDLIST\r\n" + 
				"    WHERE MB_LOC = ? AND BD_STATE != 'NO')\r\n" +
				"WHERE RN BETWEEN ? AND ?";

		ArrayList<BoardListDTO> boardlist = new ArrayList<BoardListDTO>();
		BoardListDTO board = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mdto.getMb_loc());
			pstmt.setInt(2, pdto.getStart());
			pstmt.setInt(3, pdto.getEnd());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				board = new BoardListDTO();

				board.setBD_DATE(rs.getNString("BD_DATE"));
				board.setBD_DEL(rs.getInt("BD_DEL"));
				board.setBD_HIT(rs.getInt("BD_HIT"));
				board.setMB_ID(rs.getNString("MB_ID"));
				board.setBD_NUM(rs.getInt("BD_NUM"));
				board.setBD_STATE(rs.getNString("BD_STATE"));
				board.setBD_TITLE(rs.getNString("BD_TITLE"));
				board.setMB_LEVEL(rs.getNString("MB_LEVEL"));
				board.setMB_LOC(rs.getNString("MB_LOC"));
				board.setMB_STATE(rs.getNString("MB_STATE"));
				board.setBR_REPORT(rs.getInt("BR_REPORT"));

				boardlist.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardlist;
	}

	public int getBoardTotal(BoardDTO bdto) {
		int result = 0;
		String sql = "SELECT COUNT(BD_NUM)\r\n" + 
				"FROM BOARDLIST\r\n" +
				"WHERE BD_TITLE LIKE '%'||?||'%' AND BD_STATE != 'NO'";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, bdto.getBd_title());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);;
			close(rs);
		}
		return result;
	}

	//검색 아이템
	public ArrayList<BoardListDTO> BoardList(BoardDTO bdto, PageDTO pdto) {
		String sql = "SELECT *\r\n" + 
				"FROM (SELECT BOARDLIST.*,\r\n" + 
				"            ROW_NUMBER() OVER(ORDER BY BD_NUM DESC) AS RN\r\n" + 
				"    FROM BOARDLIST\r\n" + 
				"    WHERE BD_TITLE LIKE '%'||?||'%' AND BD_STATE != 'NO')\r\n" + 
				"	 WHERE RN BETWEEN ? AND ?";

		ArrayList<BoardListDTO> boardlist = new ArrayList<BoardListDTO>();
		BoardListDTO board = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, bdto.getBd_title());
			pstmt.setInt(2, pdto.getStart());
			pstmt.setInt(3, pdto.getEnd());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				board = new BoardListDTO();

				board.setBD_DATE(rs.getNString("BD_DATE"));
				board.setBD_DEL(rs.getInt("BD_DEL"));
				board.setBD_HIT(rs.getInt("BD_HIT"));
				board.setMB_ID(rs.getNString("MB_ID"));
				board.setBD_NUM(rs.getInt("BD_NUM"));
				board.setBD_STATE(rs.getNString("BD_STATE"));
				board.setBD_TITLE(rs.getNString("BD_TITLE"));
				board.setMB_LEVEL(rs.getNString("MB_LEVEL"));
				board.setMB_LOC(rs.getNString("MB_LOC"));
				board.setMB_STATE(rs.getNString("MB_STATE"));
				board.setBR_REPORT(rs.getInt("BR_REPORT"));

				boardlist.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardlist;
	}

	public int getBoardTotal(BoardDTO bdto, MemberDTO mdto) {
		int result = 0;
		String sql = "SELECT COUNT(BD_NUM)\r\n" + 
				"FROM BOARDLIST\r\n" +
				"WHERE MB_LOC=? BD_TITLE LIKE '%'||?||'%' AND BD_STATE != 'NO'";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mdto.getMb_loc());
			pstmt.setNString(2, bdto.getBd_title());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);;
			close(rs);
		}
		return result;
	}

	public ArrayList<BoardListDTO> BoardList(BoardDTO bdto, MemberDTO mdto, PageDTO pdto) {
		String sql = "SELECT *\r\n" + 
				"FROM (SELECT BOARDLIST.*,\r\n" + 
				"            ROW_NUMBER() OVER(ORDER BY BD_NUM DESC) AS RN\r\n" + 
				"    FROM BOARDLIST\r\n" + 
				"    WHERE MB_LOC = ? AND BD_TITLE LIKE '%'||?||'%' AND BD_STATE != 'NO')\r\n" + 
				"WHERE RN BETWEEN ? AND ?";

		ArrayList<BoardListDTO> boardlist = new ArrayList<BoardListDTO>();
		BoardListDTO board = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mdto.getMb_loc());
			pstmt.setNString(2, bdto.getBd_title());
			pstmt.setInt(3, pdto.getStart());
			pstmt.setInt(4, pdto.getEnd());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				board = new BoardListDTO();

				board.setBD_DATE(rs.getNString("BD_DATE"));
				board.setBD_DEL(rs.getInt("BD_DEL"));
				board.setBD_HIT(rs.getInt("BD_HIT"));
				board.setMB_ID(rs.getNString("MB_ID"));
				board.setBD_NUM(rs.getInt("BD_NUM"));
				board.setBD_STATE(rs.getNString("BD_STATE"));
				board.setBD_TITLE(rs.getNString("BD_TITLE"));
				board.setMB_LEVEL(rs.getNString("MB_LEVEL"));
				board.setMB_LOC(rs.getNString("MB_LOC"));
				board.setMB_STATE(rs.getNString("MB_STATE"));
				board.setBR_REPORT(rs.getInt("BR_REPORT"));

				boardlist.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardlist;
	}

	public int getMyBoardTotal(MemberDTO mdto) {
		int result = 0;
		String sql = "SELECT COUNT(BD_NUM)\r\n" + 
				"FROM BOARDLIST\r\n" +
				"WHERE MB_ID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mdto.getMb_id());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);;
			close(rs);
		}
		return result;
	}
	
	public ArrayList<BoardListDTO> myBoardList(MemberDTO mdto, PageDTO pdto) {
		String sql = "SELECT *\r\n" + 
				"FROM (SELECT BOARDLIST.*,\r\n" + 
				"            ROW_NUMBER() OVER(ORDER BY BD_NUM DESC) AS RN\r\n" + 
				"    FROM BOARDLIST\r\n" + 
				"    WHERE MB_ID = ?)\r\n" + 
				"WHERE RN BETWEEN ? AND ?";

		ArrayList<BoardListDTO> boardlist = new ArrayList<BoardListDTO>();
		BoardListDTO board = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mdto.getMb_id());
			pstmt.setInt(2, pdto.getStart());
			pstmt.setInt(3, pdto.getEnd());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				board = new BoardListDTO();

				board.setBD_DATE(rs.getNString("BD_DATE"));
				board.setBD_DEL(rs.getInt("BD_DEL"));
				board.setBD_HIT(rs.getInt("BD_HIT"));
				board.setMB_ID(rs.getNString("MB_ID"));
				board.setBD_NUM(rs.getInt("BD_NUM"));
				board.setBD_STATE(rs.getNString("BD_STATE"));
				board.setBD_TITLE(rs.getNString("BD_TITLE"));
				board.setMB_LEVEL(rs.getNString("MB_LEVEL"));
				board.setMB_LOC(rs.getNString("MB_LOC"));
				board.setMB_STATE(rs.getNString("MB_STATE"));
				board.setBR_REPORT(rs.getInt("BR_REPORT"));
				
				boardlist.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardlist;
	}

	public ArrayList<BoardListDTO> noticeBoard() {
		String sql = "SELECT *\r\n" + 
				"FROM (SELECT BOARDLIST.*,\r\n" + 
				"            ROW_NUMBER() OVER(ORDER BY BD_NUM DESC) AS RN\r\n" + 
				"    FROM BOARDLIST\r\n" + 
				"    WHERE BD_STATE = 'NO')\r\n" + 
				"WHERE RN BETWEEN 1 AND 3";

		ArrayList<BoardListDTO> boardlist = new ArrayList<BoardListDTO>();
		BoardListDTO board = null;

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				board = new BoardListDTO();

				board.setBD_DATE(rs.getNString("BD_DATE"));
				board.setBD_DEL(rs.getInt("BD_DEL"));
				board.setBD_HIT(rs.getInt("BD_HIT"));
				board.setMB_ID(rs.getNString("MB_ID"));
				board.setBD_NUM(rs.getInt("BD_NUM"));
				board.setBD_STATE(rs.getNString("BD_STATE"));
				board.setBD_TITLE(rs.getNString("BD_TITLE"));
				board.setMB_LEVEL(rs.getNString("MB_LEVEL"));
				board.setMB_LOC(rs.getNString("MB_LOC"));
				board.setMB_STATE(rs.getNString("MB_STATE"));
				board.setBR_REPORT(rs.getInt("BR_REPORT"));
				
				boardlist.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardlist;
	}

	public int getNoticeTotal() {
		int result = 0;
		String sql = "SELECT COUNT(BD_NUM)\r\n" + 
				"FROM BOARDLIST\r\n" +
				"WHERE BD_STATE = 'NO'";
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
			close(pstmt);;
			close(rs);
		}
		return result;
	}

	public ArrayList<BoardListDTO> noticeList(PageDTO pdto) {
		String sql = "SELECT *\r\n" + 
				"FROM (SELECT BOARDLIST.*,\r\n" + 
				"            ROW_NUMBER() OVER(ORDER BY BD_NUM DESC) AS RN\r\n" + 
				"    FROM BOARDLIST\r\n" + 
				"    WHERE BD_STATE = 'NO')\r\n" + 
				"WHERE RN BETWEEN ? AND ?";

		ArrayList<BoardListDTO> boardlist = new ArrayList<BoardListDTO>();
		BoardListDTO board = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pdto.getStart());
			pstmt.setInt(2, pdto.getEnd());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				board = new BoardListDTO();

				board.setBD_DATE(rs.getNString("BD_DATE"));
				board.setBD_DEL(rs.getInt("BD_DEL"));
				board.setBD_HIT(rs.getInt("BD_HIT"));
				board.setMB_ID(rs.getNString("MB_ID"));
				board.setBD_NUM(rs.getInt("BD_NUM"));
				board.setBD_STATE(rs.getNString("BD_STATE"));
				board.setBD_TITLE(rs.getNString("BD_TITLE"));
				board.setMB_LEVEL(rs.getNString("MB_LEVEL"));
				board.setMB_LOC(rs.getNString("MB_LOC"));
				board.setMB_STATE(rs.getNString("MB_STATE"));
				board.setBR_REPORT(rs.getInt("BR_REPORT"));
				
				boardlist.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardlist;
	}
	
	public boolean BD_Modify(BoardDTO board) {
		String sql = "UPDATE BOARD SET BD_TITLE=?, BD_CONTENT=?, BD_FILE=?, BD_STATE=? WHERE BD_NUM=?";
		boolean result = false;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getBd_title());
			pstmt.setString(2, board.getBd_content());
			pstmt.setString(3, board.getBd_file());
			pstmt.setString(4, board.getBd_state());
			pstmt.setInt(5, board.getBd_num());			
			result = (pstmt.executeUpdate()==1)?true:false;
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		
		return result;
	}

	public boolean BD_Delete(BoardDTO bdto) {		
		String sql = "UPDATE BOARD SET BD_DEL = -1 WHERE BD_NUM=?";
		boolean Del_result = false;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bdto.getBd_num());
			Del_result = (pstmt.executeUpdate()==1)?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return Del_result;
	}

	public boolean setBoardFlashBack(BoardDTO bdto) {
		boolean result = false;
		String sql = "UPDATE BOARD SET BD_DEL = 1 WHERE BD_NUM = ? AND BD_DEL = -1";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bdto.getBd_num());
			result = (pstmt.executeUpdate()==1)?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result; // BoardContentFlashBack.5
	}
}
