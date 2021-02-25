package service;

import java.sql.Connection;

import dao.BoardDAO;

import static dao.BoardDAO.*;
import static db.JdbcUtil.*;
import dto.BoardDTO;

public class BoardWriteService {

	public BoardWriteService() {
		
	}
	
	public boolean boardWrite(BoardDTO bdto) {
		boolean tran = false;
		BoardDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		tran = dao.setBoardWrite(bdto);
		if(tran) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return tran;
	}
}
