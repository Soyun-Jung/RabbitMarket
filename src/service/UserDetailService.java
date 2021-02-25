package service;

import java.sql.Connection;

import dao.MemberDAO;
import static dao.MemberDAO.*;
import static db.JdbcUtil.*;
import dto.UserListDTO;

public class UserDetailService {

	public UserDetailService() {
		
	}
	
	public boolean getUserDetail(UserListDTO mdto) {
		boolean result = false;
		MemberDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		result = dao.getUserDetail(mdto);
		close(con);
		return result;
	}
}
