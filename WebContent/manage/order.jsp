<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,Shopping.model.vo.*" pageEncoding="utf-8"%>
<%@page import="Shopping.factory.DAOFactory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String bir1 = request.getParameter("bir1");
			String bir2 = request.getParameter("bir2");
			String selectid = request.getParameter("selectid");


 %>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>后台管理 - 秀品网</title>
		<link type="text/css" rel="stylesheet" href="../css/style.css" />
		<style type="text/css">
		.classifyD{ margin-right: 30px} 
		
		</style>
		<script type="text/javascript" src="../scripts/jquery-1.8.3.min.js"></script>
		<script type="text/javascript"
			src="../scripts/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="../scripts/function.js"></script>
		<script language="JavaScript">
		
		$(function() {
		$(".classifyD").change(function() {
			var number = $(this).val();
			var url = "order.jsp?selectid=" + number;
			<%if(bir1!=null&&bir2!=null){  %>
				url+="&&bir1=<%=bir1%>&&bir2=<%=bir2%>";
			<%}%>
			location.href = url;
		});
			$("#birthday1").click(function(){
			
        WdatePicker({
            highLineWeekDay:true //周末高亮
            ,readOnly:true      //只读，只可用控件input中 修改内容
            ,dateFmt:'yyyy-MM-dd'
        });
    });
	})
	function openPage(curpage) {
		document.form1.cp.value = curpage;
		document.form1.submit();
	}
</script>
	</head>

	<body>
		<br><br><jsp:include page="../public_page/managehead.jsp" />
		<div id="position" class="wrap">
			您现在的位置： 管理后台
		</div>
		<%
			
			int int_select = 3;
			if (selectid != null)
				int_select = Integer.parseInt(selectid);
		%>

		<div id="main" class="wrap">
			<jsp:include page="../public_page/managehead1.jsp" />
			<div class="main">
				<h2>
					订单管理
				</h2>
				<div class="manage">
					<div class="spacer"></div>
					<form id="orderForm" method="post" action="../Order?manageid=1">
						<select class="classifyD" name="classify">
						<option value="3" <%if (int_select == 3) {%> selected="selected"
							<%}%>>
							全部订单
						</option>
						<option value="0" <%if (int_select == 0) {%> selected="selected"
							<%}%>>
							未发货
						</option>
						<option value="1" <%if (int_select == 1) {%> selected="selected"
							<%}%>>
							未收货
						</option>
						<option value="2" <%if (int_select == 2) {%> selected="selected"
							<%}%>>
							已完成
						</option>
					</select>
						开始日期：
						<input type="text" class="text" name="entityId" id="birthday" />
						结束日期：
						<input type="text" class="text" name="userName" id="birthday1"/>
						<label class="ui-blue">
							<input type="submit" name="submit" value="查询" />
						</label>
					</form>
					<div class="spacer"></div>
					<table class="list">
						<%
							String jspURL = "order.jsp";
							if(bir1!=null&&bir2!=null)
								jspURL +="bir1="+bir1+"&&bir2="+bir2+"&&"; 
							if (int_select != 3)
								jspURL +="selectid="+selectid;
							// 定义如下分页变量
							// 1、定义每页要显示的记录数
							double lineSize = 10;
							int currentPage = 1;
							//接受传过来的当前页
							try {
								currentPage = Integer.parseInt(request.getParameter("cp"));
							} catch (Exception e) {
								e.printStackTrace();
							}

							List<Orders> list = new ArrayList<Orders>();
							if(int_select!=3&&bir1!=null&&bir2!=null) list = DAOFactory.getIShoppingDAOInstance().findOrderByBirthdayandStatus(bir1,bir2,int_select);
						else if(int_select==3 &&bir1!=null&&bir2!=null)
								list = DAOFactory.getIShoppingDAOInstance().findOrderByBirthday(bir1,bir2);
							else if (int_select == 3)
								list = DAOFactory.getIShoppingDAOInstance().findAllOrder();
							else
								list = DAOFactory.getIShoppingDAOInstance().findOrderByStatue(
										int_select);
							double limitpage = Math.ceil((double) list.size() / lineSize);
							Iterator iterator = list.iterator();
							Orders order = null;
							for (int x = 0; x < (currentPage - 1) * lineSize; x++) {
								order = (Orders) iterator.next();
							}
							for (int x = 0; x < lineSize; x++) {
								if (iterator.hasNext()) {
									order = (Orders) iterator.next();
									User user = DAOFactory.getIShoppingDAOInstance()
											.finUserByid(order.getUserID());
						%>
						<tr>
							<th colspan="2">
								用户名:
								<%=user.getUserName()%>
								单号：<%=order.getOrderID()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 时间：<%=order.getOrderDate()%></th>
							<%
								if (order.getStatue() == 0) {
							%>
							<th colspan="2">
								状态:未发货
							</th>
							<%
								} else if (order.getStatue() == 1) {
							%>
							<th colspan="2">
								状态:收货未确认
							</th>
							<%
								} else {
							%>
							<th colspan="2">
								状态:对方已收货
							</th>
							<%
								}
							%>
						</tr>
						<%
							List<Follow> followlist = DAOFactory
											.getIShoppingDAOInstance().findFlwById(
													order.getOrderID());
									for (int i = 0; i < followlist.size(); i++) {
										Commodity com = DAOFactory
												.getIShoppingDAOInstance()
												.findComById(followlist.get(i).getCommodityid());
										if (i == 0) {
						%>

						<tr>
							<td class="first w3  c"><%=com.getCommodityName()%></td>
							<td>
								￥<%=com.getCommodityPrice()%></td>
							<td><%=followlist.get(i).getQuantity()%></td>
							<td class="w1 c" rowspan="<%=followlist.size()%>">
								<%
									if (order.getStatue() == 0) {
								%>
								<a href="../Order?orderid1=<%=order.getOrderID()%>">发货</a>
								<%
									} else if (order.getStatue() == 1) {
								%>
								未收货
								<%
									} else {
								%>

								已完成
								<%
									}
								%>
							</td>

						</tr>
						<%
							} else {
						%>
						<tr>
							<td class="first w3 c"><%=com.getCommodityName()%></td>
							<td>
								￥<%=com.getCommodityPrice()%></td>
							<td><%=followlist.get(i).getQuantity()%></td>
						</tr>
						<%
							}
									}
								}
							}
						%>
					</table>
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

	</body>
</html>
