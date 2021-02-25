package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import service.BoardWriteService;


/**
 * Servlet implementation class BoardWriteController
 */
@WebServlet("/BoardWriteController")
public class BoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		BoardDTO bdto = new BoardDTO();
		BoardWriteService bwserv = new BoardWriteService();
		
		bdto.setBd_content(request.getParameter("BD_CONTENT"));
		bdto.setBd_file(request.getParameter("BD_FILE"));
		bdto.setBd_title(request.getParameter("BD_TITLE"));
		bdto.setBd_mbid(request.getParameter("BD_MBID"));
		bdto.setBd_state(request.getParameter("BD_STATE"));
		
		request.setAttribute("BD_REQUEST", request.getParameter("BD_REQUEST"));
		request.setAttribute("page", request.getParameter("page"));
		if(bwserv.boardWrite(bdto)) { // BoardWrite.1
			RequestDispatcher dispatcher = request.getRequestDispatcher("bList");
			dispatcher.forward(request, response); // BoardWrite.8
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardWrite.jsp");
			dispatcher.forward(request, response); // BoardWrite.8
		}
	}

}
