package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserListDTO;
import service.UserDetailService;


/**
 * Servlet implementation class UserDetailController
 */
@WebServlet("/UserDetailController")
public class UserDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetailController() {
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
		
		UserListDTO mdto = new UserListDTO();
		UserDetailService udserv = new UserDetailService();
		
		if(request.getParameter("page")!=null) {
			request.setAttribute("page", request.getParameter("page"));
		}
		
		mdto.setMB_ID(request.getParameter("MB_ID"));
		
		if(udserv.getUserDetail(mdto)) { // UserDetail.1
			RequestDispatcher dispatcher = request.getRequestDispatcher("UserDetail.jsp");
			request.setAttribute("userDetail", mdto);
			dispatcher.forward(request, response); // UserDetail.8
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("UserListController");
			dispatcher.forward(request, response); // UserDetail.8
		}
	}

}
