package service;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.MessageDAO;
import dto.MessageDTO;

public class MsgWriteService {

	public MsgWriteService() {}
	
	public boolean MsgWrite(MessageDTO ms) {
		boolean tran = false;
		MessageDAO dao = MessageDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		tran = dao.MessageSend(ms);
		if(tran) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return tran;
	}

}
