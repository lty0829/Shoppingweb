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
import javax.servlet.http.HttpSession;

import Shopping.factory.DAOFactory;
import Shopping.model.vo.*;

public class managecast extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public managecast() {
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
		String castid = request.getParameter("castid");
		if(castid!=null){
			int int_cast=Integer.parseInt(castid);
			int result = DAOFactory.getIShoppingDAOInstance().dropCastById(int_cast);
			if(result!=0){
				response.sendRedirect("shopping.jsp");
			}
			else{
				response.sendRedirect("shopping.jsp");
			}
		}
		String commodityid = request.getParameter("commodityid");
		String userid = request.getParameter("userid");
		if(commodityid!=null&&userid!=null){
			int int_commodity=Integer.parseInt(commodityid);
			int int_user = Integer.parseInt(userid);
			Cast cast = new Cast();
			cast.setCommodityid(int_commodity);
			cast.setUserid(int_user);
			cast.setQuantity(1);
			int result = DAOFactory.getIShoppingDAOInstance().insertCast(cast);
			if(result==1){
				response.sendRedirect("shopping.jsp");
			}
			else{
				request.setAttribute("message", "商品已在购物车中");
				String str = "product-view.jsp?id="+int_commodity;
				RequestDispatcher rd=request.getRequestDispatcher(str);
		        rd.forward(request,response);
			}
		}
		else if(commodityid!=null&&userid==null){
			int int_commodity=Integer.parseInt(commodityid);
			request.setAttribute("message", "请登入");
			String str = "/login.jsp";
			RequestDispatcher rd=request.getRequestDispatcher(str);
	        rd.forward(request,response);
		}
		String getcom = request.getParameter("getcom");
		String number = request.getParameter("numberw");
		System.out.print(number+"sdfsdfs");
		if(getcom!=null&&number!=null){
			int int_commodity=Integer.parseInt(getcom);
			int int_number = Integer.parseInt(number);
			Commodity com = DAOFactory.getIShoppingDAOInstance().findComById(int_commodity);
			HttpSession session = request.getSession(true);
			User user = (User)session.getAttribute("user");
			if(int_number<=com.getQuantity()){
				if(user!=null){
					Orders order = new Orders();
					Date date = new Date();       
					Timestamp nousedate = new Timestamp(date.getTime());
					order.setOrderDate(nousedate);
					order.setStatue(0);
					order.setUserID(user.getUserid());
					order.setTotalprice(0);
					DAOFactory.getIShoppingDAOInstance().reduceComQuantity(int_number, int_commodity);
					int orderKey=DAOFactory.getIShoppingDAOInstance().insertOrders(order);
					Follow follow = new Follow();
					follow.setOrderid(orderKey);
					follow.setPrice(com.getCommodityPrice());
					follow.setQuantity(int_number);
					follow.setCommodityid(int_commodity);
					DAOFactory.getIShoppingDAOInstance().insertFollow(follow);
					response.sendRedirect("shopping-result.jsp");
				}
				else{
					request.setAttribute("message", "请登入");
					String str = "/login.jsp";
					RequestDispatcher rd=request.getRequestDispatcher(str);
			        rd.forward(request,response);
				}
			}
			else{
				request.setAttribute("message", "库存不够");
				String str = "/product-view.jsp?id="+getcom;
				RequestDispatcher rd=request.getRequestDispatcher(str);
		        rd.forward(request,response);
			}
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
		PrintWriter out = response.getWriter();
		boolean isgo = true;
		String price[] = request.getParameterValues("price");
		String number[] = request.getParameterValues("number");
		String comid[] = request.getParameterValues("comid");
		String check[] = request.getParameterValues("Fruit");
		if(check!=null){
		int j=0;
		for(int i=0;i<number.length&&isgo;i++){
			if(j<check.length&&check[j].equals(comid[i])) j++;
			else continue;
			int comidx = Integer.parseInt(comid[i]);
			int numx = Integer.parseInt(number[i]);
			Commodity com = DAOFactory.getIShoppingDAOInstance().findComById(comidx);
			if(numx>com.getQuantity()){
				isgo = false;
				request.setAttribute("message", "库存不够");
				String str = "/shopping.jsp";
				RequestDispatcher rd=request.getRequestDispatcher(str);
		        rd.forward(request,response);
			}
		}
		j=0;
		if(price!=null&&isgo){
			HttpSession session = request.getSession(true);
			User user = (User)session.getAttribute("user");
			if(user!=null){
				Orders order = new Orders();
				Date date = new Date();       
				Timestamp nousedate = new Timestamp(date.getTime());
				order.setOrderDate(nousedate);
				order.setStatue(0);
				order.setUserID(user.getUserid());
				order.setTotalprice(0);
				int orderKey=DAOFactory.getIShoppingDAOInstance().insertOrders(order);
				DAOFactory.getIShoppingDAOInstance().dropCastByUserid(user.getUserid());
				for(int i=0;i<price.length;i++){
					if(j<check.length&&check[j].equals(comid[i]))j++;
					else continue;
					int int_com=Integer.parseInt(comid[i]);
					int int_number = Integer.parseInt(number[i]);
					float int_price = Float.parseFloat(price[i]);
					DAOFactory.getIShoppingDAOInstance().reduceComQuantity(int_number, int_com);
					Follow follow = new Follow();
					follow.setOrderid(orderKey);
					follow.setPrice(int_price);
					follow.setQuantity(int_number);
					follow.setCommodityid(int_com);
					DAOFactory.getIShoppingDAOInstance().insertFollow(follow);
				}
				response.sendRedirect("shopping-result.jsp");
			}
			else{
				request.setAttribute("message", "请登入");
				String str = "/shopping.jsp";
				RequestDispatcher rd=request.getRequestDispatcher(str);
		        rd.forward(request,response);
			}
		}
		else{
			request.setAttribute("message", "购物车为空");
			String str = "/shopping.jsp";
			RequestDispatcher rd=request.getRequestDispatcher(str);
	        rd.forward(request,response);
		}
		}	
		else{
			request.setAttribute("message", "请选择");
			String str = "/shopping.jsp";
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
