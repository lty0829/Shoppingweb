<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,Shopping.model.vo.*"
    pageEncoding="utf-8"%>
<%@page import="Shopping.factory.DAOFactory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>秀品网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
</head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
%>

<body>
	<div id="welcomeImage">
    	<img width="100%" height="150" src="images/banner.jpg" alt="welcome">
	</div>
	<jsp:include page="./public_page/head.jsp"/>
<div id="main" class="wrap">
	<jsp:include page="./public_page/head1.jsp"/>
	<div class="main">
		<div class="price-off">
            <div class="slideBox">
                <ul id="slideBox">
                    <li><img src="images/product/banner_1.jpg"/></li>
                    <li><img src="images/product/banner_2.jpg"/></li>
                    <li><img src="images/product/banner_3.jpg"/></li>
                    <li><img src="images/product/banner_4.jpg"/></li>
                </ul>
            </div>
			<h2>商品列表</h2>
			<ul class="product clearfix">
			<%
				List<Commodity> comlist = DAOFactory.getIShoppingDAOInstance().getLimitCom(0,8);
				Iterator iterator = comlist.iterator();
				while(iterator.hasNext()){
					Commodity com = (Commodity)iterator.next();
			 %>
			<li>
					<dl>
						<dt><a href="Newlook?id=<%=com.getCommodityID() %>"  target="_self"><img src="images/product/<%=com.getCommodityID() %>.jpg" /></a></dt>
						<dd class="title"><a href="Newlook?id=<%=com.getCommodityID() %>" target="_self"><%=com.getCommodityName() %></a></dd>
						<dd class="price">￥<%=com.getCommodityPrice() %></dd>
					</dl>
				</li>
				<%} %>		
			</ul>
		</div>
	
		
		
    </div>
</div>
</body>
</html>