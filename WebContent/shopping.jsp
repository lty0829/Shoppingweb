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
</head>
  <%
  	User user = (User) session.getAttribute("user");
  	
   %>
   <script language="JavaScript">
	$(function(){
		$(".button").find("input").click(function(){
			 if(confirm("地址：<%=user.getAddress()%>\n确定购买？")) {
			 	
			}
			else{
				return false;
			}				
		});
	})
</script>
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
	%>
  <body>
    <jsp:include page="./public_page/head.jsp"/>
    <div id="position" class="wrap">
	您现在的位置：<a href="index.jsp">秀品网</a> &gt; 购物车
	</div>
	<div class="wrap">
	<div id="shopping">
		<form action="managecast" method="post">
			<table>
				<tr>
					<th>商品名称</th>
					<th>商品价格</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				<%
				int quantity=0;
				if(user!=null){
					List<Cast> list = DAOFactory.getIShoppingDAOInstance().getAllcastByid(user.getUserid());
					if(list!=null){
					Iterator iterator = list.iterator();
					while(iterator.hasNext()){
						Cast cast = (Cast)iterator.next();
						Commodity com = DAOFactory.getIShoppingDAOInstance().findComById(cast.getCommodityid());
				 %>
				<tr id="product_id_<%=quantity %>">
					
					<td class="thumb"><img src="images/product/<%=com.getCommodityID()%>_small.jpg" /><a href="Newlook?id=<%=com.getCommodityID() %>"><%=com.getCommodityName() %></a></td>
					<td class="price" id="price_id_<%=quantity %>>">
						<span>￥<%=com.getCommodityPrice() %></span>
						<input type="hidden" name="price" value="<%=com.getCommodityPrice() %>" />
					</td>
					<td class="number">
                        <span name="del">-</span>
                        <input id="number_id_<%=quantity %>" type="text" name="number" value="<%=cast.getQuantity() %>" />
                        <span name="add">+</span>
					</td>
					<td class="delete"><a href="managecast?castid=<%=cast.getCastid() %>">删除</a></td>
					<td><input type="hidden" name="comid" value="<%=com.getCommodityID() %>"/></td>
				</tr>
               <% quantity++;
               		}
               		}
               }%>
               
			</table>
            <div class="total"><span id="total">总计：￥0</span></div>
			<div class="button"><input type="submit" value="" /></div>
		</form>
	</div>
</div>
  </body>
</html>
