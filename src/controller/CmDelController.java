package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CommentDTO;
import service.DeleteService;

/**
 * Servlet implementation class CmDelController
 */
@WebServlet("/CmDelete")
public class CmDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CmDelController() {
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
    	
    	CommentDTO cmi = new CommentDTO();
    	DeleteService delsvc = new DeleteService();
    	
    	cmi.setCm_bdnum(Integer.parseInt(request.getParameter("bNum")));
    	cmi.setCm_date(request.getParameter("date"));
		cmi.setCm_mbid(request.getParameter("mbid"));
		cmi.setCm_bdmbid(request.getParameter("bdmbid"));
    	
    	if(delsvc.CommentDel(cmi)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("boardView");
			request.setAttribute("bNum", request.getParameter("bNum"));
			request.setAttribute("page", request.getParameter("page"));
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
