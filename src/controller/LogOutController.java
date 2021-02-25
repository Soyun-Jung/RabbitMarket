package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.MemberDTO;
import service.LogoutService;

@WebServlet("/logout")
public class LogOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogOutController() {
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

		MemberDTO member = new MemberDTO();
		LogoutService Logoutsvc = new LogoutService();

		member.setMb_id(request.getParameter("MB_ID"));
		if(request.getParameter("MB_PW") != null) {
			member.setMb_pw(request.getParameter("MB_PW"));
		}

		if (Logoutsvc.memberLogout(member)) {
			HttpSession session = request.getSession();
			session.invalidate();
			
			if(request.getParameter("MB_PW") != null) {
				PrintWriter out = response.getWriter();
				out.println("<script>\r\n" +
							"alert('수정 되었습니다.다시 로그인 해주세요');\r\n" +
							"location.href='LogIn.jsp';\r\n" +
							"</script>\r\n");
			}else {
				PrintWriter out = response.getWriter();
				out.println("<script>\r\n" +
							"alert('로그아웃 되었습니다.');\r\n" +
							"location.href='Main.jsp';\r\n" +
							"</script>");
			}
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>\r\n" +
						"alert('로그아웃 실패');\r\n" +
						"history.back();\r\n" +
						"</script>");

		}
	}
}
