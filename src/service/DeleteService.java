package service;

import static dao.BoardDAO.*;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;
import dao.CommentDAO;
import dto.BoardDTO;
import dto.CommentDTO;

public class DeleteService {

	public boolean BoardDel(BoardDTO bdto) {
		BoardDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		boolean delResult = dao.BD_Delete(bdto);

		if(delResult) {
			commit(con);
		} else {
			rollback(con);
		}		
		
		return delResult;
	}
	
	public boolean CommentDel(CommentDTO cmi) {
		CommentDAO dao = CommentDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		boolean delResult = dao.CmDelete(cmi);

		if(delResult) {
			commit(con);
		} else {
			rollback(con);
		}		
		
		return delResult;
	}

}