package service;

import java.sql.Connection;
import java.util.ArrayList;

import static dao.MemberDAO.*;
import dao.MemberDAO;
import static db.JdbcUtil.*;
import dto.PageDTO;
import dto.UserListDTO;

public class UserListService {

	public UserListService() {
		
	}
	
	public ArrayList<UserListDTO> getUserList(PageDTO pdto){
		ArrayList<UserListDTO> userList = null;
		MemberDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		pdto.calcPage(dao.getUserTotal(), 10, 10);
		userList = dao.getUserList(pdto);
		
		close(con);
		return userList;
	}
}
