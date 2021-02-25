package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberDTO;
import service.UserModService;


/**
 * Servlet implementation class UserSetModController
 */
@WebServlet("/UserSetModController")
public class UserSetModController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSetModController() {
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
		
		MemberDTO mdto = new MemberDTO();
		UserModService umserv = new UserModService();
		
		request.setAttribute("MB_ID", request.getParameter("MB_ID"));
		request.setAttribute("MB_PW", request.getParameter("MB_PW"));
		
		mdto.setMb_id(request.getParameter("MB_ID"));
		mdto.setMb_pw(request.getParameter("MB_PW_MOD"));
		mdto.setMb_loc(request.getParameter("MB_LOC_MOD"));
		mdto.setMb_phone(request.getParameter("MB_PHONE_MOD"));
		mdto.setMb_postCode(Integer.parseInt(request.getParameter("sample4_postcode")));
		mdto.setMb_roadAddr(request.getParameter("sample4_roadAddress"));
		mdto.setMb_jibunAddr(request.getParameter("sample4_jibunAddress"));
		mdto.setMb_detailAddr(request.getParameter("sample4_detailAddress"));
		mdto.setMb_exAddr(request.getParameter("sample4_extraAddress"));
		mdto.setMb_level(request.getParameter("MB_LEVEL_MOD"));
		
		if(umserv.setUserMod(mdto)) { // UserSetMod.1
			RequestDispatcher dispatcher = request.getRequestDispatcher("logout");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("UserGetModController");
			dispatcher.forward(request, response); // UserSetMod.8
		}
	}

}
