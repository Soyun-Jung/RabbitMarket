package service;

import static db.JdbcUtil.*;
import java.sql.Connection;
import static dao.BoardReportDAO.*;
import dao.BoardReportDAO;
import dto.ReportDTO;

public class BoardReportService {

	public BoardReportService() {
		
	}
	
	public boolean setBoardReport(ReportDTO rdto) {
		boolean tran = false;
		BoardReportDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		tran = dao.setBoardReport(rdto);
		if(tran) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return tran;
	}
}
