package service;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.BoardDAO;
import static dao.BoardDAO.*;
import dto.BoardListDTO;

public class ViewService {

	public boolean BoardView(BoardListDTO board) {
		boolean result = false;
		// 공통부분
		BoardDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// 여기까지
		
		if(dao.BoardView(board)) {
			if(dao.BoardBhit(board)) {
				result = true;
				commit(con);
			} else {
				rollback(con);
			}
		}
		
		close(con);
		return result;
	}

}