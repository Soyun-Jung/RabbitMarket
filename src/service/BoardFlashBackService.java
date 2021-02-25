package service;

import java.sql.Connection;

import static dao.BoardDAO.*;
import dao.BoardDAO;
import dao.CommentDAO;

import static db.JdbcUtil.*;
import dto.BoardDTO;
import dto.CommentDTO;

public class BoardFlashBackService {

	public BoardFlashBackService() {
		
	}
	
	public boolean setBoardFlashBack(BoardDTO bdto) {
		boolean tran = false;
		BoardDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		tran = dao.setBoardFlashBack(bdto);
		if(tran) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return tran;
	}

	public boolean CommentFlachBack(CommentDTO cmi) {
		boolean tran = false;
		CommentDAO dao = CommentDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		tran = dao.setCommentFlashBack(cmi);
		if(tran) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return tran;
	}
	
}
