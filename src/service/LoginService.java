package service;

import java.sql.Connection;

import dao.MemberDAO;
import dto.MemberDTO;

import static dao.MemberDAO.*;
import static db.JdbcUtil.*;

public class LoginService {

	public boolean memberLogin(MemberDTO member) {
		boolean result = false;
		MemberDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		if(dao.loginCheck(member)) {
			if(dao.memberLogin(member)) {
				result = true;
				dao.setAccessInfo(member);
			}
		}
		close(con);
		return result;
	}

}
