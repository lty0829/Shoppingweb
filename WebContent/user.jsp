<%@ page language="java" import="java.util.*,Shopping.model.vo.*" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
		User user = (User) session.getAttribute("user");
	%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>秀品网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
	<jsp:include page="./public_page/head.jsp"/>
	
	<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
		
				<table>
				<form action="user-alter.jsp">
					<tr>
						<td class="field">用户名：</td>
						<td>
                            <%= user.getUserName()%>
					
						</td>
						
					</tr>
					<tr>
						<td class="field">手机号码</td>
						<td>
							<%=user.getPhone() %>
						</td>
					</tr>
					
					<tr>
						<td class="field">性别</td>
						<%if(user.getGender()==0){ %>
						<td>
							男
						</td>
						<%}else{ %>
						<td>
							女
						</td>
						<%} %>
						
					</tr>
					
					<tr>
						<td class="field">地址</td>
						<td><%=user.getAddress() %></td>
					</tr>
					
					<tr>
						<td class="field">生日</td>
						<td><%=user.getBirthday() %></td>
					
					</tr>
			
						<td><label class="ui-green"><input type="submit" name="submit" value="修改信息" /></label></td>
					</tr>
					</form>
				</table>
				
		</div>
	</div>
	<div class="clear"></div>
</div>
</body>
</html>