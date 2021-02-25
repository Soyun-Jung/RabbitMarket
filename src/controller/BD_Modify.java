package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.BoardDTO;
import service.Modify_Service;

/**
 * Servlet implementation class DB_Modify
 */
@WebServlet("/BD_Modify")
public class BD_Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BD_Modify() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		BoardDTO board = new BoardDTO();
		Modify_Service prosvc = new Modify_Service();
		
		board.setBd_num(Integer.parseInt(request.getParameter("BD_NUM")));
		board.setBd_title(request.getParameter("BD_TITLE"));
		board.setBd_content(request.getParameter("BD_CONTENT"));
		board.setBd_state(request.getParameter("BD_STATE"));
		board.setBd_file(request.getParameter("BD_FILE"));
		
		request.setAttribute("BD_REQUEST", request.getParameter("BD_REQUEST"));
		request.setAttribute("page", request.getParameter("page"));
		
		if(prosvc.modiPro(board)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("bList");
			dispatcher.forward(request, response);
		} else {
			PrintWriter out=response.getWriter();
			out.println("<script>\r\n" +
						"alert('실패');\r\n" +
						"history.back();\r\n" +
						"</script>");
		}
	}
}
