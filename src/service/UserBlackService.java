package service;

import static dao.MemberDAO.*;
import java.sql.Connection;
import dao.MemberDAO;
import static db.JdbcUtil.*;
import dto.MemberDTO;

public class UserBlackService {

	public UserBlackService() {
		
	}
	
	public boolean setUserBlack(MemberDTO mdto) {
		boolean tran = false;
		MemberDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		tran = dao.setUserBlack(mdto);
		if(tran) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return tran;
	}
}
