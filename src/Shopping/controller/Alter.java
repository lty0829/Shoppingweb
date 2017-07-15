package Shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Shopping.factory.DAOFactory;
import Shopping.model.vo.User;

public class Alter extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Alter() {
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

		String name = request.getParameter("userId");
		String pwd = request.getParameter("password");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String address= request.getParameter("address");
		String birthday = request.getParameter("birthday");
		String address1 = new String(address.getBytes("ISO-8859-1"),"utf-8");
		HttpSession session = request.getSession(true);
		User user1 = (User)session.getAttribute("user");
		User user = new User();
		user.setUserid(user1.getUserid());
		user.setUserName(name);
		user.setPassword(pwd);
		user.setPhone(phone);
		if(gender.equals("0"))user.setGender(0);
		else user.setGender(1);
		user.setAddress(address1);
		if(birthday.equals("")) birthday=null;
		user.setBirthday(birthday);
		int flag = DAOFactory.getIShoppingDAOInstance().updateUser(user);
		String str = "";
		if(flag == 0){
			request.setAttribute("message", "用户名重复！");
			str = "/user-alter.jsp";
			RequestDispatcher rd=request.getRequestDispatcher(str);
	        rd.forward(request,response);
		}else{
			session.removeAttribute("user");
			session.setAttribute("user", user);
			request.setAttribute("message", "修改成功！");
			str = "/index.jsp";
			RequestDispatcher rd=request.getRequestDispatcher(str);
	        rd.forward(request,response);
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
