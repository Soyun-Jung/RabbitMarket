package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardGetModController
 */
@WebServlet("/BoardGetModController")
public class BoardGetModController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardGetModController() {
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
		response.setContentType("text/html; chatset=UTF-8");
		
		request.setAttribute("BD_NUM", request.getParameter("BD_NUM"));
		request.setAttribute("BD_TITLE", request.getParameter("BD_TITLE"));
		request.setAttribute("BD_CONTENT", request.getParameter("BD_CONTENT"));
		request.setAttribute("BD_FILE", request.getParameter("BD_FILE"));
		request.setAttribute("BD_STATE", request.getParameter("BD_STATE"));
		request.setAttribute("BD_REQUEST", request.getParameter("BD_REQUEST"));
		request.setAttribute("page", request.getParameter("page"));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("BoardModify.jsp");
		dispatcher.forward(request, response);
	}

}
