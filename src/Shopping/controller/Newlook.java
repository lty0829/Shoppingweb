package Shopping.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Shopping.factory.DAOFactory;
import Shopping.model.vo.Commodity;

/**
 * Servlet implementation class Newlook
 */
public class Newlook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Newlook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = utf-8");
		String comid = request.getParameter("id");
		if(comid!=null){
			int int_com=Integer.parseInt(comid);
			Commodity com = DAOFactory.getIShoppingDAOInstance().findComById(int_com);
			if (com == null) {
				response.sendRedirect("index.jsp");
			}else{
				HttpSession session = request.getSession(true);
				session.setAttribute("newlook", com);
				response.sendRedirect("product-view.jsp?id="+comid);
			}
		}
		else{
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = utf-8");
		String Seach_name = request.getParameter("seach_name");
		if(Seach_name!=null&&!Seach_name.equals("")){
			response.sendRedirect("product-list.jsp?keyword="+Seach_name);
		}
		else{
		response.sendRedirect("index.jsp");
		}
	}

}
