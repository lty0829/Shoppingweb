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

public class Order extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Order() {
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
		String orderid = request.getParameter("orderid");
		if(orderid!=null){
			int int_order = Integer.parseInt(orderid);
			DAOFactory.getIShoppingDAOInstance().updateOrderstate(2, int_order);
			response.sendRedirect("order.jsp");
		}
		String orderid1 = request.getParameter("orderid1");
		if(orderid1!=null){
			int int_order1 = Integer.parseInt(orderid1);
			DAOFactory.getIShoppingDAOInstance().updateOrderstate(1, int_order1);
			response.sendRedirect("manage/order.jsp");
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
		String manageid = request.getParameter("manageid");
		String commodid = request.getParameter("commodid");
		if(manageid!=null){
			String bir1 = request.getParameter("entityId");
			String bir2 = request.getParameter("userName");
			response.sendRedirect("manage/order.jsp?bir1="+bir1+"&&bir2="+bir2);
		}
		else if(commodid!=null){
			String bir1 = request.getParameter("entityId");
			String bir2 = request.getParameter("userName");
			response.sendRedirect("order.jsp?bir1="+bir1+"&&bir2="+bir2);
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
