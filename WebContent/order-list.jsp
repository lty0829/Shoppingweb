<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,Shopping.model.vo.*"
    pageEncoding="utf-8"%>
<%@page import="Shopping.factory.DAOFactory"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>秀品网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
<style type="text/css">
	#shopping .total1{height:30px;}
#shopping .total1 span{float:right;line-height:30px;}
.big{ margin-left: 80px;font-size:x-large;margin-top: 10px;margin-bottom: 10px  }
</style>
</head>
  <%
  	User user = (User) session.getAttribute("user");
  	
   %>
 
<%
		String message = (String) request.getAttribute("message");
		if (message == null)
		{
			message = "";
		}
		if (!message.trim().equals(""))
		{
			out.println("<script language='javascript'>");
			out.println("alert('" + message + "');");
			out.println("</script>");
		}
		request.removeAttribute("message");
		String orderid = request.getParameter("orderid");
		List<Follow> flwlist = null;
		Orders order = null;
		if(orderid!=null){
		int int_order = Integer.parseInt(orderid);
		flwlist = DAOFactory.getIShoppingDAOInstance().findFlwById(int_order);
		order = DAOFactory.getIShoppingDAOInstance().findorderByid(int_order);
		}
		else flwlist = new ArrayList<Follow>();
	%>
  <body >
    <jsp:include page="./public_page/head.jsp"/>
    <div id="position" class="wrap">
	您现在的位置：<a href="index.jsp">秀品网</a> &gt; 购物车
	</div>
	<div class="wrap">
	<div id="shopping">
			<div class ="big"> 状态 ：<%if(order.getStatue()==0){%>未发货<%}else if(order.getStatue()==1){%>未收货<%}else{%>已完成<%} %></div>
			<table>
				<tr>
					<th>商品名称</th>
					<th>商品价格</th>
					<th>购买数量</th>
				</tr>
				<%
				double quantity=0;
				if(user!=null){
					if(flwlist!=null){
					Iterator iterator = flwlist.iterator();
					while(iterator.hasNext()){
						Follow follow = (Follow)iterator.next();
						Commodity com = DAOFactory.getIShoppingDAOInstance().findComById(follow.getCommodityid());
				 %>
				<tr id="product_id_<%=quantity %>">
				
					<td class="thumb"><img src="images/product/<%=com.getCommodityID()%>_small.jpg" /><a href="Newlook?id=<%=com.getCommodityID() %>"><%=com.getCommodityName() %></a></td>
					<td class="price">
						<span>￥<%=com.getCommodityPrice() %></span>
					</td>
					<td class="number">
					 <%=follow.getQuantity() %>
					</td>
					<td><input type="hidden" name="comid" value="<%=com.getCommodityID() %>"/></td>
				</tr>
               <% quantity+=follow.getQuantity()*com.getCommodityPrice();
               		}
               		}
               }%>
               
			</table>
            <div class="total1"><span id="total1">总计：￥<%=quantity %></span></div>
	</div>
</div>
  </body>
</html>
