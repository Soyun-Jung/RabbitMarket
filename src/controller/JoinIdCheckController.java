package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberDTO;
import service.IdCheckService;

/**
 * Servlet implementation class JoinIdCheck
 */
@WebServlet("/joinIdCheck")
public class JoinIdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JoinIdCheckController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		IdCheckService idChecksvc=new IdCheckService();
		MemberDTO mdto = new MemberDTO();
		mdto.setMb_id(request.getParameter("userId"));

		if(idChecksvc.idcheck(mdto)) {
			PrintWriter out = response.getWriter();
			out.println("<script>\r\n" +
						"alert('사용가능한 아이디 입니다');\r\n" +
						"history.back();\r\n" +
						"</script>");
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>\r\n" +
					"alert('사용 불가능한 아이디 입니다.');\r\n" +
					"location.href='Join.jsp?MB_LEVEL=" + request.getParameter("MB_LEVEL") + "';\r\n" +
					"</script>");
		}

	}

}
