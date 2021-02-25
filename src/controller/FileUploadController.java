package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class FileUploadController
 */
@WebServlet("/FileUploadController")
public class FileUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadController() {
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
		
		int size = 100 * 1024 * 1024;
		String filePath = "D:/ICIA/Jong Won/java/SEMI_proj/WebContent/FileUpload";
		MultipartRequest multi = new MultipartRequest(request, filePath, size, "UTF-8", new DefaultFileRenamePolicy());
		
		request.setAttribute("BD_TITLE", multi.getParameter("BD_TITLE"));
		request.setAttribute("BD_STATE", multi.getParameter("BD_STATE"));
		request.setAttribute("BD_CONTENT", multi.getParameter("BD_CONTENT"));
		request.setAttribute("BD_FILE", multi.getFilesystemName((String)multi.getFileNames().nextElement()));
		request.setAttribute("BD_REQUEST", multi.getParameter("BD_REQUEST"));
		request.setAttribute("page", multi.getParameter("page"));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("BoardWrite.jsp");
		dispatcher.forward(request, response);
	}

}
