<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,Shopping.model.vo.*"
    pageEncoding="utf-8"%>
<%@page import="Shopping.factory.DAOFactory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 秀品网</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../scripts/function.js"></script>
<script language="JavaScript">
	function openPage(curpage) {
		document.form1.cp.value = curpage;
		document.form1.submit();
	}
</script>
</head>

 <body>
    <jsp:include page="../public_page/managehead.jsp"/>
    <div id="position" class="wrap">
	您现在的位置: 管理后台
	</div>
	<div id="main" class="wrap">
	 <jsp:include page="../public_page/managehead1.jsp"/>
	<div class="main">
		<h2>用户管理</h2>
		<div class="manage">
		
			<table class="list">
				<tr>
					<th>用户名</th>
					<th>性别</th>
					<th>手机号码</th>
					<th>地址</th>
					<th>出生日期</th>
					<th>操作</th>
				</tr>
				
				<%
				
				String jspURL = "user.jsp";
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
				
				
				List<User> list=DAOFactory.getIShoppingDAOInstance().getAllUser();
				double limitpage = Math.ceil((double)list.size()/lineSize);
				Iterator iterator = null;
				User user =null;
				String sex="";
				if(list!=null){
					iterator = list.iterator();
				for (int x = 0; x < (currentPage - 1) * lineSize; x++) {
								user = (User)iterator.next();
							}
					for (int x = 0; x < lineSize; x++) {
						if (iterator.hasNext()) {
					user = (User)iterator.next();
					if(user.getGender()==0){
						sex="男";
					}
					else sex="女";
				
				%>
				
				<tr>
					<td class="first w4 c"><%=user.getUserName() %></td>
					<td class="w1 c"><%=sex %></td>
					<td class="w2 c"><%=user.getPhone() %></td>
					<td class="w4 c"><%=user.getAddress() %></td>
					<td class="w4 c"><%=user.getBirthday() %></td>
					<td class="w1 c"><a href="user-modify.jsp?userid=<%=user.getUserid() %>">修改</a> </td>
				</tr>
				
				
				
				<%} 
				}
				}%>
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
						onClick="<%if(currentPage>1){%>openPage(<%=currentPage - 1%>)<%} %>">
					<a>第<%=currentPage%>页</a>
					<input name="button3" type="button" value="下一页"
						onClick="<%if(currentPage<limitpage){%>openPage(<%=currentPage + 1%>)<%} %>">
					<input name="button4" type="button" value="尾页" onClick="openPage(<%=(int)limitpage %>)">
					<input name="cp" type="hidden" value="" />
				</form>
				</div>
			</div>
</div>
</body>
</html>