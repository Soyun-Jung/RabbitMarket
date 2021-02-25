package service;

import static dao.MemberDAO.*;
import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.MemberDAO;
import dto.MemberDTO;

public class MemeberDelService {

	public boolean memberDel(MemberDTO mdto) {
		boolean tran = false;
		MemberDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		tran = dao.memberDel(mdto);
		if(tran) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return tran;
	}
}
