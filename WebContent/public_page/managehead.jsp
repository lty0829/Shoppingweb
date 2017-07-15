<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,Shopping.model.vo.*"
    pageEncoding="utf-8"%>
<%@page import="Shopping.factory.DAOFactory,java.text.SimpleDateFormat"%>

  <div id="header" class="wrap">
	<div id="logo"><img src="../images/logo.gif" /></div>
	<div class="help"><a href="../outlog">注销</a></div>
	<%
		String str = request.getRequestURL().toString();
		int pos = str.lastIndexOf("/");
		int pos1=str.lastIndexOf("-");
		int pos2=str.lastIndexOf(".");
		String s =null;
		if(pos1 != -1)
			s = str.substring(pos+1,pos1);
		else if(pos2!=-1)
			s = str.substring(pos+1,pos2);
		else
			s = "index"; 
		%>
	<div class="navbar">
		<ul class="clearfix">
			<li <%if(s.equals("index")){%>class="current"<%} %>><a href="index.jsp">首页</a></li>
			<li <%if(s.equals("user")){%>class="current"<%} %>      ><a href="user.jsp">用户</a></li>
			<li <%if(s.equals("product")||s.equals("productClass")){%>class="current"<%} %>><a href="product.jsp">商品</a></li>
			<li <%if(s.equals("order")){%>class="current"<%} %>><a href="order.jsp">订单</a></li>
			<li <%if(s.equals("guestbook")){%>class="current"<%} %>><a href="guestbook.jsp">留言</a></li>
		</ul>
	</div>
</div>
	<%
		User user = (User)session.getAttribute("user");
		if(user==null||user.getPrior()==0){
		%>
		<div id="childNav">
	<div class="welcome wrap">
		非法进入
	</div>
	<script type="text/javascript">
							setTimeout("location.href='../index.jsp'", 1000);
	</script>
</div>
		
		
		<% 
		}
		else{
	 	Date d = new Date();
	 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
       	String dateNowStr = sdf.format(d); 
		
	 %>
<div id="childNav">
	<div class="welcome wrap">
		管理员<%=user.getUserName() %></>您好，今天是<%=dateNowStr %>，欢迎回到管理后台。
	</div>
</div>
<%} %>

