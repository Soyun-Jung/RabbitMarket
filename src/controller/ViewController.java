package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardListDTO;
import service.CommentListService;
import service.ViewService;

@WebServlet("/boardView")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewController() {
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
		
		ViewService viewsvc = new ViewService();
		BoardListDTO board = new BoardListDTO();
		if(request.getParameter("bNum") != null) {
			board.setBD_NUM(Integer.parseInt(request.getParameter("bNum")));
			request.setAttribute("BD_NUM", request.getParameter("bNum"));
		}else {
			board.setBD_NUM(Integer.parseInt(request.getParameter("BD_NUM")));
			request.setAttribute("BD_NUM", request.getParameter("BD_NUM"));
		}
		
		request.setAttribute("MB_ID", request.getParameter("MB_ID"));
		request.setAttribute("MB_LOC", request.getParameter("MB_LOC"));
		request.setAttribute("BD_TITLE", request.getParameter("BD_TITLE"));
		request.setAttribute("BD_REQUEST", request.getParameter("BD_REQUEST"));
		request.setAttribute("page", request.getParameter("page"));
		
		if(viewsvc.BoardView(board)) {
			CommentListService cls = new CommentListService();
			request.setAttribute("view", board);
			request.setAttribute("comment", cls.getCommentList(board));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardDetail.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
