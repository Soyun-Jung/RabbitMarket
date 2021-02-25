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
import service.MemeberDelService;


/**
 * Servlet implementation class DelController
 */
@WebServlet("/memberDel")
public class MemberDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDelController() {
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
		
		MemeberDelService delsvc = new MemeberDelService();
		MemberDTO mdto = new MemberDTO();
		mdto.setMb_id(request.getParameter("MB_ID"));
		
		if(delsvc.memberDel(mdto)) {
			HttpSession session = request.getSession();
			session.invalidate();
			PrintWriter out = response.getWriter();
			out.println("<script>\r\n" +
						"alert('회원탈퇴 되었습니다.');\r\n" +
						"location.href='Main.jsp';\r\n" +
						"</script>");
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>\r\n" +
						"alert('다시 시도해주세요');\r\n" +
						"history.back();\r\n" +
						"</script>");
		}
	}
}
