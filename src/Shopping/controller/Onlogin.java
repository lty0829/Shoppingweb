package Shopping.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Shopping.factory.DAOFactory;
import Shopping.model.vo.User;

/**
 * Servlet implementation class Onlogin
 */
public class Onlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Onlogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = utf-8");
		String name = request.getParameter("userId");
		String psd = request.getParameter("password");
		try {
			User user = DAOFactory.getIShoppingDAOInstance().login(name, psd);
			if (user == null) {
				response.sendRedirect("login.jsp");
			}else{
				HttpSession session = request.getSession(true);
				session.setAttribute("user", user);
				switch (user.getPrior()) {
				case 0:
					response.sendRedirect("index.jsp");
					break;
				case 1:
					response.sendRedirect("manage/index.jsp");
					System.out.println("admins");
					break;
	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
