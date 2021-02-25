package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MessageDTO;
import service.MsgWriteService;

/**
 * Servlet implementation class MessageWriteController
 */
@WebServlet("/SendMsg")
public class MessageWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageWriteController() {
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

		MessageDTO ms = new MessageDTO();
		MsgWriteService mws = new MsgWriteService();

		ms.setMS_RECID(request.getParameter("MS_RECID"));
		ms.setMS_SENDID(request.getParameter("MS_SENDID"));
		ms.setMS_TITLE(request.getParameter("MS_TITLE"));
		ms.setMS_TEXT(request.getParameter("MS_TEXT"));

		if(mws.MsgWrite(ms)) { // BoardWrite.1
			PrintWriter out = response.getWriter();
			out.println("<script>\r\n"
					+ "window.close();\r\n"
					+ "</script>");
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>\r\n"
					+ "history.back();\r\n"
					+ "</script>");
		}
	}

}
