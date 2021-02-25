package service;

import dao.MemberDAO;
import dto.MemberDTO;

import static dao.MemberDAO.*;
import static db.JdbcUtil.*;

import java.sql.Connection;

public class MemberService {
	
		public boolean memberJoin(MemberDTO member) {
			boolean result = false;
			MemberDAO dao = getInstance();
			Connection con = getConnection();
			dao.setConnection(con);
			
			result = dao.memberJoin(member);
			
			if(result) {
				commit(con);
			} else {
				rollback(con);
			}
			
			close(con);
			return result;
		}
}
