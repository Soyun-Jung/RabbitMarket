package service;

import static dao.MessageDAO.getInstance;
import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MessageDAO;
import dto.MessageDTO;
import dto.PageDTO;

public class MsgListService {

	public MsgListService() {
		
	}

	public ArrayList<MessageDTO> getMessageList(MessageDTO mdto, PageDTO pdto) {
		MessageDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		pdto.calcPage(dao.getMyMsgTotal(mdto), 10, 20);
		
		ArrayList<MessageDTO> msglist = dao.getMyMsgList(mdto, pdto);
		
		close(con);
		
		return msglist;
	}

	public Object sendMessageList(MessageDTO mdto, PageDTO pdto) {
		MessageDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		pdto.calcPage(dao.sendMyMsgTotal(mdto), 10, 20);
		
		ArrayList<MessageDTO> msglist = dao.sendMyMsgList(mdto, pdto);
		
		close(con);
		
		return msglist;
	}

	
}
