package Shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Shopping.factory.DAOFactory;
import Shopping.model.vo.Cast;
import Shopping.model.vo.Classify;
import Shopping.model.vo.Commodity;
import Shopping.model.vo.Follow;
import Shopping.model.vo.Orders;
import Shopping.model.vo.User;

/**
 * Servlet implementation class Hello
 */
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hello() {
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
		PrintWriter out = response.getWriter();
		Cast cast= new Cast();
		cast.setCommodityid(1);
		cast.setQuantity(0);		
		Follow follow = new Follow();
		follow.setCommodityid(1);
		follow.setOrderid(1);
		follow.setQuantity(1);
		follow.setPrice(1);
		//DAOFactory.getIShoppingDAOInstance().insertFollow(follow);
		Date date = new Date();       
		Timestamp nousedate = new Timestamp(date.getTime());
		Orders order = new Orders();
		order.setOrderDate(nousedate);
		order.setStatue(0);
		order.setTotalprice(1);
		order.setUserID(1);
		
		Commodity com=DAOFactory.getIShoppingDAOInstance().findComById(1);
		com.setCommodityName("没有这个万3");
		com.setCommodityID(51);
		String str = "2015-2-1";
		int pos1 = str.indexOf("-");
		int pos2 = str.indexOf("-", pos1+1);
		int year = Integer.parseInt(str.substring(0,pos1));
		int mouth = Integer.parseInt(str.substring(pos1+1,pos2));
		int day = Integer.parseInt(str.substring(pos2+1,str.length()));
		out.print(year+"-"+mouth+"-"+day);
	//	Iterator teamNewsIter = list2.iterator();
		
//		while (teamNewsIter.hasNext()) {
//			Commodity newsTemp = (Commodity) teamNewsIter.next(); 
//			out.println(newsTemp.getCommodityName()+"<br>");
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}

}
