package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import dto.MemberDTO;
import dto.PageDTO;
import service.BoardListService;


@WebServlet("/bList")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardListController() {
		super();
		// TODO Auto-generated constructor stub
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PageDTO pdto = new PageDTO();
		MemberDTO mdto = new MemberDTO();
		BoardDTO bdto = new BoardDTO();
		BoardListService blserv = new BoardListService();

		if(request.getParameter("page")!=null) {
			pdto.setNowPage(Integer.parseInt(request.getParameter("page")));
		}else {
			pdto.setNowPage(1);
		}
		
		request.setAttribute("notice", blserv.noticeBoard());
		request.setAttribute("BD_REQUEST", request.getParameter("BD_REQUEST"));
		request.setAttribute("page", pdto);
		
		switch (request.getParameter("BD_REQUEST")) {
		case "BD1":
			request.setAttribute("board", blserv.BoardList(pdto));
			break;
		case "BD2":
			mdto.setMb_id(request.getParameter("MB_ID"));
			request.setAttribute("MB_ID", mdto.getMb_id());
			request.setAttribute("board", blserv.myBoardList(mdto, pdto));
			break;
		case "BD3":
			if(request.getParameter("MB_LOC").equals("all")) {
				request.setAttribute("board", blserv.BoardList(pdto));
				request.setAttribute("BD_REQUEST", "BD1");
			}else {
				mdto.setMb_loc(request.getParameter("MB_LOC"));
				request.setAttribute("MB_LOC", mdto.getMb_loc());
				request.setAttribute("board", blserv.BoardList(mdto, pdto));
			}
			break;
		case "BD4":
			bdto.setBd_title(request.getParameter("BD_TITLE"));
			request.setAttribute("BD_TITLE", bdto.getBd_title());
			request.setAttribute("board", blserv.BoardList(bdto, pdto));
			break;
		case "BD5":
			if(request.getParameter("MB_LOC").equals("all")) {
				request.setAttribute("board", blserv.BoardList(pdto));
				request.setAttribute("BD_REQUEST", "BD1");
			}else {
				bdto.setBd_title(request.getParameter("BD_TITLE"));
				mdto.setMb_loc(request.getParameter("MB_LOC"));
				request.setAttribute("BD_TITLE", bdto.getBd_title());
				request.setAttribute("MB_LOC", mdto.getMb_loc());
				request.setAttribute("board", blserv.BoardList(bdto, mdto, pdto));	
			}
			break;
		case "BD6":
			request.setAttribute("board", blserv.noticeList(pdto));
			break;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("BoardList.jsp");
		dispatcher.forward(request, response);
	}

}
