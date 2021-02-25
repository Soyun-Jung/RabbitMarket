package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MessageDTO;
import service.MessageDetailService;

/**
 * Servlet implementation class MessageDetailController
 */
@WebServlet("/MessageDetailController")
public class MessageDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageDetailController() {
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
		
		MessageDTO mdto = new MessageDTO();
		MessageDetailService mdserv = new MessageDetailService();
		
		mdto.setMS_SENDID(request.getParameter("MS_SENDID"));
		mdto.setMS_RECID(request.getParameter("MS_RECID"));
		mdto.setMS_DATE(request.getParameter("MS_DATE"));
		
		if(mdserv.getMessageDetail(mdto)) {
			request.setAttribute("msg", mdto);
			RequestDispatcher dispatcher = request.getRequestDispatcher("MessageDetail.jsp");
			dispatcher.forward(request, response);
		}
	}

}
