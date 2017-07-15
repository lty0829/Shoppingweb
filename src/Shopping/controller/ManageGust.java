package Shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Shopping.factory.DAOFactory;

public class ManageGust extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ManageGust() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = utf-8");
		String gustid = request.getParameter("gustid");
		if(gustid!=null){
			int int_gust = Integer.parseInt(gustid);
			int result = DAOFactory.getIShoppingDAOInstance().dropLed(int_gust);
			response.sendRedirect("guestbook.jsp");
			
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = utf-8");
		String gstid = request.getParameter("gstid");
		String gustid = request.getParameter("gustid");
		if(gstid!=null&&gustid!=null){
			String replyContent = request.getParameter("replyContent");
			int int_gust = Integer.parseInt(gustid);
			if(!replyContent.equals("")){
				String replyContent1 = new String(replyContent.getBytes("ISO-8859-1"),"utf-8");
				if(gstid.equals("0")){
					DAOFactory.getIShoppingDAOInstance().updateReply(replyContent1, int_gust);
					response.sendRedirect("manage-result.jsp");
					
				}
				else{
					DAOFactory.getIShoppingDAOInstance().updateReply(replyContent1, int_gust);
					response.sendRedirect("manage-result.jsp");
				}
			}
			else{
				request.setAttribute("message", "²»ÄÜÎª¿Õ£¡");
				String str = "/manage/guestbook-modify.jsp?gstid="+gstid+"&&gustid="+gustid;
				RequestDispatcher rd=request.getRequestDispatcher(str);
		        rd.forward(request,response);
			}
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
