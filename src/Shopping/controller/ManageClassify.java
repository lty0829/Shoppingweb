package Shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Shopping.factory.DAOFactory;
import Shopping.model.vo.Classify;

public class ManageClassify extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ManageClassify() {
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
		String deleteid = request.getParameter("deleteid?");
		if(deleteid!=null){
			int int_detele = Integer.parseInt(deleteid);
			int result = DAOFactory.getIShoppingDAOInstance().dropCsfById(int_detele);
			if(result!=0){
				response.sendRedirect("productClass.jsp");
			}
			else{
				request.setAttribute("message", " ß∞‹");
				String str = "/manage/productClass.jsp";
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
		String ifupdate = request.getParameter("ifupdate");
		String name = request.getParameter("className");
		String name1 = new String(name.getBytes("ISO-8859-1"),"utf-8");
		Classify csf = new Classify();
		csf.setName(name1);
		if(ifupdate.equals("")){
			int result = DAOFactory.getIShoppingDAOInstance().insertCsf(csf);
			if(result!=0){
				response.sendRedirect("manage-result.jsp");
			}
			else{
				request.setAttribute("message", " ß∞‹");
				String str = "/manage/productClass-add.jsp";
				RequestDispatcher rd=request.getRequestDispatcher(str);
		        rd.forward(request,response);
			}
		}
		else{
			int int_mod = Integer.parseInt(ifupdate);
			csf.setClassifyid(int_mod);
			int result = DAOFactory.getIShoppingDAOInstance().updateCsf(csf);
			if(result!=0){
				response.sendRedirect("manage-result.jsp");
			}
			else{
				request.setAttribute("message", "÷ÿ∏¥");
				String str = "/manage//productClass-add.jsp?modifyid="+int_mod;
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
