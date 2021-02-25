package service;

import static dao.BoardDAO.*;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import dto.BoardDTO;
import dto.BoardListDTO;
import dto.MemberDTO;
import dto.PageDTO;

public class BoardListService {
	//전체 리스트 불러오기
	public ArrayList<BoardListDTO> BoardList(PageDTO pdto) {
		// 공통부분
		BoardDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// 여기까지
		pdto.calcPage(dao.getBoardTotal(), 10, 20);
		ArrayList<BoardListDTO> boardlist = dao.BoardList(pdto);
		close(con);
		
		return boardlist;
	}
	
	//loc설정된 리스트 불러오기
	public ArrayList<BoardListDTO> BoardList(MemberDTO mdto, PageDTO pdto) {
		// 공통부분
		BoardDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// 여기까지
		pdto.calcPage(dao.getBoardTotal(mdto), 10, 20);
		ArrayList<BoardListDTO> boardlist = dao.BoardList(mdto, pdto);
		close(con);
		
		return boardlist;
	}

	public ArrayList<BoardListDTO> BoardList(BoardDTO bdto, PageDTO pdto) {
		// TODO Auto-generated method stub
		BoardDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// 여기까지
		pdto.calcPage(dao.getBoardTotal(bdto), 10, 20);
		ArrayList<BoardListDTO> boardlist = dao.BoardList(bdto, pdto);
		close(con);
		
		return boardlist;
	}

	public ArrayList<BoardListDTO> BoardList(BoardDTO bdto, MemberDTO mdto, PageDTO pdto) {
		// TODO Auto-generated method stub
		BoardDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// 여기까지
		pdto.calcPage(dao.getBoardTotal(bdto, mdto), 10, 20);
		ArrayList<BoardListDTO> boardlist = dao.BoardList(bdto, mdto, pdto);
		close(con);
		
		return boardlist;
	}
	
	public ArrayList<BoardListDTO> myBoardList(MemberDTO mdto, PageDTO pdto){
		BoardDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		pdto.calcPage(dao.getMyBoardTotal(mdto), 10, 20);
		ArrayList<BoardListDTO> boardlist = dao.myBoardList(mdto, pdto);
		close(con);
		return boardlist;
	}

	public ArrayList<BoardListDTO> noticeBoard() {
		BoardDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<BoardListDTO> boardlist = dao.noticeBoard();
		close(con);
		return boardlist;
	}

	public Object noticeList(PageDTO pdto) {
		BoardDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		pdto.calcPage(dao.getNoticeTotal(), 10, 20);
		ArrayList<BoardListDTO> boardlist = dao.noticeList(pdto);
		close(con);
		return boardlist;
	}
}







