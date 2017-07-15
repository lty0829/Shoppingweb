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
</head>
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
    <jsp:include page="../public_page/managehead.jsp"/>
    <div id="position" class="wrap">
	您现在的位置： 管理后台
	</div>
	<div id="main" class="wrap">
	 <jsp:include page="../public_page/managehead1.jsp"/>
	<div class="main">
		<h2>添加分类</h2>
		<div class="manage">
			<form action="ManageClassify" method ="post">
				<table class="form">
					<% 
						String modifyid = request.getParameter("modifyid");
						String name="";
						if(modifyid!=null){
						int int_mod = Integer.parseInt(modifyid);
						 name = DAOFactory.getIShoppingDAOInstance().findCsfById(int_mod).getName();
						 }
					%>
					<tr>
						<td class="field">分类名称：</td>
						<td><input type="text" class="text" name="className" value="<%=name %>" /></td>
						<td><input type="hidden" name="ifupdate" value="<%if(modifyid!=null){ %><%=modifyid%><%} %>">
					</tr>
					
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="<%if(modifyid!=null){ %>修改<%}else{%>添加<%} %>" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
 </body>
</html>