package service;

import static dao.MemberDAO.*;
import java.sql.Connection;
import dao.MemberDAO;
import static db.JdbcUtil.*;
import dto.MemberDTO;

public class UserFlashBackService {

	public UserFlashBackService() {
		
	}
	
	public boolean setUserFlashBack(MemberDTO mdto) {
		boolean tran = false;
		MemberDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		tran = dao.setUserFlashBack(mdto);
		if(tran) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return tran;
	}
}
