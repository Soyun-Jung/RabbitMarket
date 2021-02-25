package service;

import dao.TestDAO;
import static dao.TestDAO.*;
import static db.JdbcUtil.*;

import java.sql.Connection;

import org.json.simple.JSONArray;

public class TestService {

	public TestService() {
		
	}
	
	public String testIdCheck(String test) {
		TestDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		test = dao.test(test);
		close(con);
		return test;
	}

	public JSONArray testCommentList() {
		TestDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		JSONArray test = dao.testCommentList();
		close(con);
		return test;
	}
}
