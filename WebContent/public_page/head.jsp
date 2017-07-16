<%@ page language="java" import="java.util.*,Shopping.model.vo.*"
	pageEncoding="UTF-8"%>
<%@page import="Shopping.factory.DAOFactory"%>
	<%
		User user = (User) session.getAttribute("user");
	%>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
	<div class="help">
	<%
	if(user!=null){ 
	List<Cast> list = DAOFactory.getIShoppingDAOInstance().getAllcastByid(user.getUserid());
	int quantity =0;
	if(list!=null){
		quantity = list.size();
	}
	%>
	<a href="user.jsp"><%=user.getUserName() %></a>
	<a href="shopping.jsp" class="shopping">购物车<%=quantity %>件</a>
	<a href="order.jsp">订单管理</a>
	<a href="outlog">注销</a>

	<% }
		else{
	%>
	<a href="login.jsp">登录</a>
	<a href="userreg.jsp">注册</a>
	<%} %>
	</div>
    <div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="index.jsp">首页</a></li>
		</ul>
	</div>
	
	
</div>
<div id="childNav">
	<div class="wrap">
		<ul class="clearfix">
			<li class="first"><a href="product-list.jsp?all=0">全部商品</a></li>
			<% 
				List<Classify> csflist= DAOFactory.getIShoppingDAOInstance().getAllCsf();
				for(int i=0;i<csflist.size()&&i<8;i++){
			 %>
			 <li><a href="product-list.jsp?csf=<%=csflist.get(i).getClassifyid() %>"><%=csflist.get(i).getName() %></a></li>
			 <%} %>
			<li class="last"> 
				<form method="post" action="Newlook">
				<div class="form-group">
					<input type="text" class="form-control" name="seach_name" placeholder="搜索商品">
					<input type="submit" name="submit" value="搜索" />
				</div>
				</form>
			</li>
		</ul>
	</div>
	
</div>