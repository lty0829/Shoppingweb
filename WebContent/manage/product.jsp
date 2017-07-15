<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,Shopping.model.vo.*" pageEncoding="utf-8"%>
<%@page import="Shopping.factory.DAOFactory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>后台管理 - 秀品网</title>
		<link type="text/css" rel="stylesheet" href="../css/style.css" />
		<script type="text/javascript" src="../scripts/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="../scripts/function.js"></script>
	</head>
	<script language="JavaScript">
	$(function() {
		$(".classifyD").change(function() {
			var number = $(this).val();
			location.href = 'product.jsp?csfid=' + number;
		});
	})
	function openPage(curpage) {
		document.form1.cp.value = curpage;
		document.form1.submit();
	}
</script>
	<%
		String message = (String) request.getAttribute("message");
		if (message == null) {
			message = "";
		}
		if (!message.trim().equals("")) {
			out.println("<script language='javascript'>");
			out.println("alert('" + message + "');");
			out.println("</script>");
		}
		request.removeAttribute("message");
		String csfid = request.getParameter("csfid");
		int int_csf = 0;
		if (csfid != null)
			int_csf = Integer.parseInt(csfid);
	%>

	<body>
		<jsp:include page="../public_page/managehead.jsp" />
		<div id="position" class="wrap">
			您现在的位置： 管理后台
		</div>
		<div id="main" class="wrap">
			<jsp:include page="../public_page/managehead1.jsp" />
			<div class="main">
				<h2>
					商品管理
				</h2>
				<div class="manage">
					<div class="spacer"></div>
					<%
						List<Classify> csflist = DAOFactory.getIShoppingDAOInstance()
								.getAllCsf();
						Iterator iterator = null;
						if (csflist != null)
							iterator = csflist.iterator();
					%>
					<select class="classifyD" name="classify">
						<option value="0" <%if (int_csf == 0) {%> selected="selected"
							<%}%>>
							所有商品
						</option>
						<%
							while (iterator.hasNext()) {
								Classify csf = (Classify) iterator.next();
						%>
						<option value="<%=csf.getClassifyid()%>"
							<%if (int_csf == csf.getClassifyid()) {%> selected="selected"
							<%}%>><%=csf.getName()%></option>
						<%
							}
						%>
					</select>
					<div class="spacer"></div>
					<table class="list">
						<tr>
							<th>
								编号
							</th>
							<th>
								商品名称
							</th>
								
							<th>
							库存
							</th>
							<th>
								操作
							</th>
						
						</tr>
						<%
							String jspURL = "product.jsp?csfid=" + int_csf;
							// 定义如下分页变量
							// 1、定义每页要显示的记录数
							double lineSize = 10;
							// 2、定义一个当前是第几页
							int currentPage = 1;
							//接受传过来的当前页
							try {
								currentPage = Integer.parseInt(request.getParameter("cp"));
							} catch (Exception e) {
								e.printStackTrace();
							}

							List<Commodity> comlist = null;
							if (int_csf == 0) {
								comlist = DAOFactory.getIShoppingDAOInstance().getAllCom();
							} else {
								comlist = DAOFactory.getIShoppingDAOInstance().getComByClssify(
										int_csf);
							}
							double limitpage = Math.ceil((double) comlist.size() / lineSize);
							Iterator iterator1 = null;
							Commodity com = null;
							if (comlist != null)
								iterator1 = comlist.iterator();
							for (int x = 0; x < (currentPage - 1) * lineSize; x++) {
								com = (Commodity) iterator1.next();
							}
							for (int x = 0; x < lineSize; x++) {
								if (iterator1.hasNext()) {
									com = (Commodity) iterator1.next();
						%>
						<tr>
							<td class="first w4 c"><%=com.getCommodityID()%></td>
							<td class="thumb">
								<img src="../images/product/<%=com.getCommodityID()%>_small.jpg"><%=com.getCommodityName()%></td>
							<td class="w1 c"><%=com.getQuantity() %></td>
							<td class="w1 c">
								<a href="product-add.jsp?comid=<%=com.getCommodityID()%>">修改</a>
								<a class="manageDel"
									href="ManageCom?deleteid=<%=com.getCommodityID()%>">删除</a>
							</td>
						</tr>
						<%
							}
							}
						%>
					</table>
				</div>
			</div>
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
	</body>
</html>