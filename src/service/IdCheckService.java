package service;

import static dao.MemberDAO.*;
import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.MemberDAO;
import dto.MemberDTO;

public class IdCheckService {

	public boolean idcheck(MemberDTO mdto) {
		MemberDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		boolean result = dao.idCheck(mdto);
		close(con);
		
		return result;
	}

}
