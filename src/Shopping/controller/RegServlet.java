package Shopping.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Shopping.factory.DAOFactory;
import Shopping.model.vo.User;

public class RegServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegServlet() {
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
		doPost(request, response);
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
		
		String name = request.getParameter("userId");
		String pwd = request.getParameter("password");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String address= request.getParameter("address");
		String birthday = request.getParameter("birthday");
		String address1 = new String(address.getBytes("ISO-8859-1"),"utf-8");
		User user = new User();
		user.setUserName(name);
		user.setPassword(pwd);
		user.setPhone(phone);
		if(gender.equals("0"))user.setGender(0);
		else user.setGender(1);
		user.setAddress(address1);
		if(birthday.equals("")) birthday=null;
		user.setBirthday(birthday);
		int flag = DAOFactory.getIShoppingDAOInstance().save(user);
		String str = "";
		if(flag == 0){
			request.setAttribute("message", "用户名重复！");
			str = "/userreg.jsp";
			RequestDispatcher rd=request.getRequestDispatcher(str);
	        rd.forward(request,response);
		}else{
			str = "/reg-result.jsp";
			RequestDispatcher rd=request.getRequestDispatcher(str);
	        rd.forward(request,response);
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
