package Shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Shopping.factory.DAOFactory;
import Shopping.model.vo.Word;

public class LeaveWord extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LeaveWord() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
		String userid = request.getParameter("userid");
		String comid = request.getParameter("comid");
		String guestContent = request.getParameter("guestContent");
		if(userid==null){
			request.setAttribute("message", "用户名重复！");
			String str = "/product-view.jsp?id="+comid;
			RequestDispatcher rd=request.getRequestDispatcher(str);
	        rd.forward(request,response);
		}else if(guestContent.equals("")){
			request.setAttribute("message", "评论不能为空！");
			String str = "/product-view.jsp?id="+comid;
			RequestDispatcher rd=request.getRequestDispatcher(str);
	        rd.forward(request,response);
		}
		else{
			int int_com = Integer.parseInt(comid);
			int int_use = Integer.parseInt(userid);
			Date date = new Date();       
			Timestamp nousedate = new Timestamp(date.getTime());
			Word word = new Word();
			word.setCommodityID(int_com);
			word.setLeaveDate(nousedate);
			word.setUserID(int_use);
			String guestContent1 = new String(guestContent.getBytes("ISO-8859-1"),"utf-8");
			word.setLeaveWord(guestContent1);
			int result = DAOFactory.getIShoppingDAOInstance().insertLed(word);
			response.sendRedirect("product-view.jsp?id="+comid);
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
