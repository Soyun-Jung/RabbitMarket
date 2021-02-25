package service;

import dto.MessageDTO;
import static db.JdbcUtil.*;

import java.sql.Connection;

import static dao.MessageDAO.*;
import dao.MessageDAO;

public class MessageDetailService {

	public MessageDetailService() {
		
	}

	public boolean getMessageDetail(MessageDTO mdto) {
		boolean result = false;
		MessageDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		result = dao.getMessageDetail(mdto);
		close(con);
		return result;
	}
}
