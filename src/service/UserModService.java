package service;

import static dao.MemberDAO.*;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import dto.MemberDTO;

public class UserModService {

	public UserModService() {
		
	}

	public boolean setUserMod(MemberDTO mdto) {
		MemberDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// 여기까지
		boolean modiReault = dao.memberModi(mdto);

		if (modiReault) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return modiReault;
	}
}
