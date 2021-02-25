package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MessageDTO;
import dto.PageDTO;
import service.MsgListService;

/**
 * Servlet implementation class MsgListController
 */
@WebServlet("/MsgList")
public class MsgListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MsgListController() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		MsgListService listsvc = new MsgListService();
		MessageDTO mdto = new MessageDTO();
		PageDTO pdto = new PageDTO();
		
		if(request.getParameter("page") != null) {
			pdto.setNowPage(Integer.parseInt(request.getParameter("page")));
		}else {
			pdto.setNowPage(1);
		}
		
		switch (request.getParameter("MS_REQUEST")) {
		case "MS1":
			mdto.setMS_RECID(request.getParameter("MS_RECID"));
			request.setAttribute("msglist", listsvc.getMessageList(mdto, pdto));
			break;
		case "MS2":
			mdto.setMS_SENDID(request.getParameter("MS_SENDID"));
			request.setAttribute("msglist", listsvc.sendMessageList(mdto, pdto));
			break;
		}
		
		request.setAttribute("page", pdto);
		request.setAttribute("MS_REQUEST", request.getParameter("MS_REQUEST"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("MessageList.jsp");
		dispatcher.forward(request, response);
		
	}

}
