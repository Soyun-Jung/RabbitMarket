package service;

import static db.JdbcUtil.*;
import java.sql.Connection;
import static dao.CommentDAO.*;
import dao.CommentDAO;
import dto.CommentDTO;

public class CommentWriteService {

	public Boolean writeComments(CommentDTO comments) {
		boolean tran = false;
		CommentDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		tran = dao.WriteComments(comments);
		
		if(tran) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return tran;
	}
	

}


