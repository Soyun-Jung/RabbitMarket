package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CommentDTO;
import service.CommentWriteService;

/**
 * Servlet implementation class CommentController
 */
@WebServlet("/CommentWrite")
public class CmWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CmWriteController() {
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

		CommentDTO comments = new CommentDTO();
		CommentWriteService cws = new CommentWriteService();
		
		comments.setCm_content(request.getParameter("cmtext"));
		comments.setCm_bdnum(Integer.parseInt(request.getParameter("BD_NUM")));
		comments.setCm_bdmbid(request.getParameter("BD_MBID"));
		comments.setCm_mbid(request.getParameter("CM_MBID"));
		
		request.setAttribute("MB_ID", request.getParameter("MB_ID"));
		request.setAttribute("MB_LOC", request.getParameter("MB_LOC"));
		request.setAttribute("BD_TITLE", request.getParameter("BD_TITLE"));
		request.setAttribute("BD_REQUEST", request.getParameter("BD_REQUEST"));
		request.setAttribute("page", request.getParameter("page"));
		if(cws.writeComments(comments)) {
			request.setAttribute("bNum", comments.getCm_bdnum());
			RequestDispatcher dispatcher = request.getRequestDispatcher("boardView");
			dispatcher.forward(request, response);
		}

	}

}
