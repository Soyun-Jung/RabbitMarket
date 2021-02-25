package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import static dao.CommentDAO.*;
import dao.CommentDAO;
import dto.BoardListDTO;
import dto.CommentDTO;

public class CommentListService {

	public ArrayList<CommentDTO> getCommentList(BoardListDTO board) {
		// 공통부분
		CommentDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// 여기까지
		
		ArrayList<CommentDTO> cmlist = dao.getCmList(board);
		close(con);
		
		return cmlist;
	}

}
