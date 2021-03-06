package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import service.DeleteService;

/**
 * Servlet implementation class BD_Delete
 */
@WebServlet("/BD_Delete")
public class BD_Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BD_Delete() {
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
    	
    	BoardDTO bdto = new BoardDTO();
    	DeleteService delsvc = new DeleteService();
    	
    	bdto.setBd_num(Integer.parseInt(request.getParameter("bd_num")));
		
    	if(delsvc.BoardDel(bdto)) {
    		request.setAttribute("page", request.getParameter("page"));
    		RequestDispatcher dispatcher = request.getRequestDispatcher("bList");
    		dispatcher.forward(request, response);
		} else {
			PrintWriter out=response.getWriter();
			out.println("<script>\r\n" +
						"alert('다시 시도해주세요');\r\n" +
						"history.back();\r\n" +
						"</script>");
		}
    	
	}
}
