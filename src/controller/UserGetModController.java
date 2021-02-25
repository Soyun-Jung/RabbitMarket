package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserModGetUserInfo
 */
@WebServlet("/UserGetModController")
public class UserGetModController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserGetModController() {
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
		
		request.setAttribute("MB_ID", request.getParameter("MB_ID"));
		request.setAttribute("MB_PW", request.getParameter("MB_PW"));
		request.setAttribute("MB_NAME", request.getParameter("MB_NAME"));
		request.setAttribute("MB_LOC", request.getParameter("MB_LOC"));
		request.setAttribute("MB_PHONE", request.getParameter("MB_PHONE"));
		request.setAttribute("MB_POSTCODE", request.getParameter("MB_POSTCODE"));
		request.setAttribute("MB_ROADADDR", request.getParameter("MB_ROADADDR"));
		request.setAttribute("MB_JIBUNADDR", request.getParameter("MB_JIBUNADDR"));
		request.setAttribute("MB_DETAILADDR", request.getParameter("MB_DETAILADDR"));
		request.setAttribute("MB_EXADDR", request.getParameter("MB_EXADDR"));
		request.setAttribute("MB_LEVEL", request.getParameter("MB_LEVEL"));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserMod.jsp");
		dispatcher.forward(request, response); // UserGetMod.1
	}

}
