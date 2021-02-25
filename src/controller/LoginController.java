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
import service.LoginService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
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
		LoginService logsvc = new LoginService();

		member.setMb_id(request.getParameter("userId"));
		member.setMb_pw(request.getParameter("userPw"));

		if (logsvc.memberLogin(member)) {
			HttpSession session = request.getSession();
			session.setAttribute("memberInfo", member);
			response.sendRedirect("Main.jsp");
		} else {
			PrintWriter out=response.getWriter();
			out.println("<script>\r\n" +
						"alert('로그인 실패');\r\n" +
						"history.back();\r\n" +
						"</script>");
		}

	}
	
}
