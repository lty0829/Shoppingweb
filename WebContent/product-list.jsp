<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,Shopping.model.vo.*" pageEncoding="utf-8"%>
<%@page import="Shopping.factory.DAOFactory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>秀品网 - 首页</title>
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="scripts/function.js"></script>
		<script language="JavaScript">
	function openPage(curpage) {
		document.form1.cp.value = curpage;
		document.form1.submit();
	}
</script>
	</head>
	<body>
		<jsp:include page="./public_page/head.jsp" />
		<%
			String csfid = request.getParameter("csf");
			String keyword1 = request.getParameter("keyword");
			String keyword = null;
			if (keyword1 != null)
				keyword = new String(keyword1.getBytes("ISO-8859-1"), "utf-8");
			if (csfid != null) {
				int int_csf = Integer.parseInt(csfid);
				Classify csf = DAOFactory.getIShoppingDAOInstance()
						.findCsfById(int_csf);
				List<Commodity> comlist = DAOFactory.getIShoppingDAOInstance()
						.getComByClssify(int_csf);
		%>
		<div id="position" class="wrap">
			您现在的位置：
			<a href="index.jsp">秀品网</a> &gt;<%=csf.getName()%>
		</div>
		<div id="main" class="wrap">
			<jsp:include page="./public_page/head1.jsp" />
			<div class="main">
				<div class="product-list">
					<h2><%=csf.getName()%></h2>
					<div class="clear"></div>
					<ul class="product clearfix">
						<%
							String jspURL = "product-list.jsp?csf="+int_csf;
								// 定义如下分页变量
								// 1、定义每页要显示的记录数
								double lineSize = 16;
								int currentPage = 1;
								//接受传过来的当前页
								try {
									currentPage = Integer.parseInt(request.getParameter("cp"));
								} catch (Exception e) {
									e.printStackTrace();
								}
								double limitpage = Math.ceil((double) comlist.size() / lineSize);
								Commodity com = null;
								if (comlist != null) {
									Iterator iterator = comlist.iterator();
									for (int x = 0; x < (currentPage - 1) * lineSize; x++) {
										com = (Commodity) iterator.next();
									}
									for (int x = 0; x < lineSize; x++) {
									if (iterator.hasNext()) {
										com = (Commodity) iterator.next();
						%>
						<li>
							<dl>
								<dt>
									<a href="Newlook?id=<%=com.getCommodityID()%>" target="_self"><img
											src="images/product/<%=com.getCommodityID()%>.jpg" />
									</a>
								</dt>
								<dd class="title">
									<a href="Newlook?id=<%=com.getCommodityID()%>" target="_self"><%=com.getCommodityName()%></a>
								</dd>
								<dd class="price">
									￥<%=com.getCommodityPrice()%></dd>
							</dl>
						</li>
						<%
							}
							}
						%>
					</ul>
					<div class="clear"></div>
					<div class="pager1">
						<div>
							<form action="<%=jspURL%>" method="post" name="form1"
								style="padding-left: 250px;">
								<input name="button1" type="button" value="首页"
									onClick="openPage(1)">
								<input name="button2" type="button" value="上一页"
									onClick="<%if (currentPage > 1) {%>openPage(<%=currentPage - 1%>)<%}%>">
								<a>第<%=currentPage%>页</a>
								<input name="button3" type="button" value="下一页"
									onClick="<%if (currentPage < limitpage) {%>openPage(<%=currentPage + 1%>)<%}%>">
								<input name="button4" type="button" value="尾页"
									onClick="openPage(<%=(int) limitpage%>)">
								<input name="cp" type="hidden" value="" />
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<%
			}
			} else if (keyword != null) {
		%>
		<div id="position" class="wrap">
			您现在的位置：
			<a href="index.jsp">秀品网</a>&gt;
			<%=keyword%>
		</div>
		<div id="main" class="wrap">
			<jsp:include page="./public_page/head1.jsp" />
			<div class="main">
				<div class="product-list">
					<h2>
						<%=keyword%>
					</h2>
					<div class="clear"></div>
					<ul class="product clearfix">
						<%
							List<Commodity> comlist = DAOFactory.getIShoppingDAOInstance()
										.searchComByName(keyword);
								String jspURL = "product-list.jsp?keyword="+keyword;
								// 定义如下分页变量
								// 1、定义每页要显示的记录数
								double lineSize = 16;
								int currentPage = 1;
								//接受传过来的当前页
								try {
									currentPage = Integer.parseInt(request.getParameter("cp"));
								} catch (Exception e) {
									e.printStackTrace();
								}
								double limitpage = Math.ceil((double) comlist.size() / lineSize);
								Commodity com = null;
								if (comlist != null) {
									Iterator iterator = comlist.iterator();
									for (int x = 0; x < (currentPage - 1) * lineSize; x++) {
										com = (Commodity) iterator.next();
									}
									for (int x = 0; x < lineSize; x++) {
									if (iterator.hasNext()) {
										com = (Commodity) iterator.next();
						%>
						<li>
							<dl>
								<dt>
									<a href="Newlook?id=<%=com.getCommodityID()%>" target="_self"><img
											src="images/product/<%=com.getCommodityID()%>.jpg" />
									</a>
								</dt>
								<dd class="title">
									<a href="Newlook?id=<%=com.getCommodityID()%>" target="_self"><%=com.getCommodityName()%></a>
								</dd>
								<dd class="price">
									￥<%=com.getCommodityPrice()%></dd>
							</dl>
						</li>
						<%
							}
							}
						%>
					</ul>
					<div class="clear"></div>
					<div class="pager1">
						<div>
							<form action="<%=jspURL%>" method="post" name="form1"
								style="padding-left: 250px;">
								<input name="button1" type="button" value="首页"
									onClick="openPage(1)">
								<input name="button2" type="button" value="上一页"
									onClick="<%if (currentPage > 1) {%>openPage(<%=currentPage - 1%>)<%}%>">
								<a>第<%=currentPage%>页</a>
								<input name="button3" type="button" value="下一页"
									onClick="<%if (currentPage < limitpage) {%>openPage(<%=currentPage + 1%>)<%}%>">
								<input name="button4" type="button" value="尾页"
									onClick="openPage(<%=(int) limitpage%>)">
								<input name="cp" type="hidden" value="" />
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<%
			}
			} else {
		%>
		<div id="position" class="wrap">
			您现在的位置：
			<a href="index.jsp">秀品网</a>&gt; 全部商品
		</div>
		<div id="main" class="wrap">
			<jsp:include page="./public_page/head1.jsp" />
			<div class="main">
				<div class="product-list">
					<h2>
						全部商品
					</h2>
					<div class="clear"></div>
					<ul class="product clearfix">
						<%						
							List<Commodity> comlist = DAOFactory.getIShoppingDAOInstance()
										.getAllCom();
								String jspURL = "product-list.jsp";
								// 定义如下分页变量
								// 1、定义每页要显示的记录数
								double lineSize = 16;
								int currentPage = 1;
								//接受传过来的当前页
								try {
									currentPage = Integer.parseInt(request.getParameter("cp"));
								} catch (Exception e) {
									e.printStackTrace();
								}
								double limitpage = Math.ceil((double) comlist.size() / lineSize);
								Commodity com = null;
								if (comlist != null) {
									Iterator iterator = comlist.iterator();
									for (int x = 0; x < (currentPage - 1) * lineSize; x++) {
										com = (Commodity) iterator.next();
									}
									for (int x = 0; x < lineSize; x++) {
									if (iterator.hasNext()) {
										com = (Commodity) iterator.next();
						%>
						<li>
							<dl>
								<dt>
									<a href="Newlook?id=<%=com.getCommodityID()%>" target="_self"><img
											src="images/product/<%=com.getCommodityID()%>.jpg" />
									</a>
								</dt>
								<dd class="title">
									<a href="Newlook?id=<%=com.getCommodityID()%>" target="_self"><%=com.getCommodityName()%></a>
								</dd>
								<dd class="price">
									￥<%=com.getCommodityPrice()%></dd>
							</dl>
						</li>
						<%
						}
							}
						%>
					</ul>
					<div class="clear"></div>
					<div class="pager1">
						<div>
							<form action="<%=jspURL%>" method="post" name="form1"
								style="padding-left: 250px;">
								<input name="button1" type="button" value="首页"
									onClick="openPage(1)">
								<input name="button2" type="button" value="上一页"
									onClick="<%if (currentPage > 1) {%>openPage(<%=currentPage - 1%>)<%}%>">
								<a>第<%=currentPage%>页</a>
								<input name="button3" type="button" value="下一页"
									onClick="<%if (currentPage < limitpage) {%>openPage(<%=currentPage + 1%>)<%}%>">
								<input name="button4" type="button" value="尾页"
									onClick="openPage(<%=(int) limitpage%>)">
								<input name="cp" type="hidden" value="" />
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<%
		
			}
			}
		%>


	</body>
</html>