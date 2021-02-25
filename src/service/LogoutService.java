package service;

import static dao.MemberDAO.*;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import dto.MemberDTO;

public class LogoutService {

	public boolean memberLogout(MemberDTO member) {
		boolean result = false;
		
		MemberDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		result = dao.setLogoutAccessInfo(member);
		
		if(result) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

}
