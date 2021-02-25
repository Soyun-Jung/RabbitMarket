package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberDTO;
import service.MemberService;


@WebServlet("/memberJoin")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public JoinController() {
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
		
		// dto와 service import
		MemberDTO member = new MemberDTO();
		MemberService joinsvc = new MemberService();
	
		// jsp에서 넘어온 정보를
		// dto 타입인 member에 저장
		switch (request.getParameter("MB_LEVEL")) {
		case "A":
			member.setMb_id(request.getParameter("userId"));
			member.setMb_pw(request.getParameter("userPw"));
			member.setMb_name(request.getParameter("userName"));
			member.setMb_phone(request.getParameter("userPhone"));
			member.setMb_loc(request.getParameter("userLocal"));
			member.setMb_level(request.getParameter("MB_LEVEL"));
			member.setMb_postCode(Integer.parseInt(request.getParameter("sample4_postcode")));
			member.setMb_roadAddr(request.getParameter("sample4_roadAddress"));
			member.setMb_jibunAddr(request.getParameter("sample4_jibunAddress"));
			member.setMb_detailAddr(request.getParameter("sample4_detailAddress"));
			member.setMb_exAddr(request.getParameter("sample4_extraAddress"));
			break;
		case "M":
			member.setMb_id(request.getParameter("userId"));
			member.setMb_pw(request.getParameter("userPw"));
			member.setMb_name(request.getParameter("userName"));
			member.setMb_level(request.getParameter("MB_LEVEL"));
			break;
		}
		
		if(joinsvc.memberJoin(member)) {
			PrintWriter out = response.getWriter();	
			out.println("<script>\r\n" +
						"alert('회원가입 성공');\r\n" +
						"location.href='LogIn.jsp';\r\n" +
						"</script>");
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>\r\n" +
						"alert('회원가입 실패');\r\n" +
						"history.back();\r\n" +
						"</script>");
		}	
	}
	
}
