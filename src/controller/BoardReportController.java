package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ReportDTO;
import service.BoardReportService;

/**
 * Servlet implementation class BoardReportController
 */
@WebServlet("/BoardReportController")
public class BoardReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReportController() {
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
		
		ReportDTO rdto = new ReportDTO();
		BoardReportService brserv = new BoardReportService();
		
		rdto.setBR_BDNUM(Integer.parseInt(request.getParameter("BR_BDNUM")));
		rdto.setBR_BDMBID(request.getParameter("BR_BDMBID"));
		rdto.setBR_MBID(request.getParameter("BR_MBID"));
		
		if(brserv.setBoardReport(rdto)) {
			PrintWriter out = response.getWriter();
			out.println("<script>\r\n"
					+ "alert('신고완료');\r\n"
					+ "history.back();\r\n"
					+ "</script>");
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>\r\n"
					+ "alert('이미 신고한 게시글 입니다.');\r\n"
					+ "history.back();\r\n"
					+ "</script>");
		}
	}

}
