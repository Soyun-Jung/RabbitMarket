package service;

import static dao.BoardDAO.*;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;
import dto.BoardDTO;

public class Modify_Service {

	public boolean modiPro(BoardDTO board) {
		BoardDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		boolean result = dao.BD_Modify(board);
		
		if(result) {
			commit(con);
		} else {
			rollback(con);
		}
		
		return result;
	}

}
