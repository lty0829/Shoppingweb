package Shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Shopping.factory.DAOFactory;
import Shopping.model.vo.User;

public class Userchange extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Userchange() {
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
		int gender=0;
		String Userid = request.getParameter("userid");
		String Password = request.getParameter("password");
		String UserName = request.getParameter("UserName");
		String Phone = request.getParameter("Phone");
		String Gender = request.getParameter("Gender");
		String addre = request.getParameter("addre");
		String birthyear= request.getParameter("birthyear");
		String birthmonth= request.getParameter("birthmonth");
		String birthday= request.getParameter("birthday");
		String Birthday=birthyear+"-"+birthmonth+"-"+birthday;
		String address = new String(addre.getBytes("ISO-8859-1"),"utf-8");
		if(Gender.equals("0")){ gender=0;}
		else gender=1;		
		User user = new User();
		user.setUserid(Integer.parseInt(Userid));
		user.setPassword(Password);
		user.setUserName(UserName);
		user.setPhone(Phone);
		user.setGender(gender);
		user.setAddress(address);
		user.setBirthday(Birthday);
		String path = "user.jsp" ;	 
		int flag = DAOFactory.getIShoppingDAOInstance().updateUser(user);
		System.out.print(flag);
		if(flag!=0){
				response.sendRedirect(path);		
				
				
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
