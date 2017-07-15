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
					回复留言
				</h2>
				<div class="manage">
					<%
						String gstid = request.getParameter("gstid");
						String gustid = request.getParameter("gustid");
						if (gstid != null && gustid != null) {
							int int_gust = Integer.parseInt(gustid);
							Word word = DAOFactory.getIShoppingDAOInstance().findWordByid(
									int_gust);
							User user1 = DAOFactory.getIShoppingDAOInstance().finUserByid(
									word.getUserID());
					%>
					<form action="ManageGust?gstid=<%=gstid%>&&gustid=<%=gustid %>" method="post">
						<table class="form">

							<tr>
								<td class="field">
									留言编号：
								</td>
								<td><%=word.getLeaveWordID()%></td>
							</tr>
							<tr>
								<td class="field">
									留言姓名：
								</td>
								<td><%=user1.getUserName()%></td>
							</tr>
							<tr>
								<td class="field">
									留言内容：
								</td>
								<td><%=word.getLeaveWord()%></td>
							</tr>
							<tr>
								<td class="field">
									回复内容：
								</td>
								<td>
									<textarea name="replyContent"></textarea>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<label class="ui-blue">
										<input type="submit" name="submit" value="更新" />
									</label>
								</td>
							</tr>

						</table>
					</form>
					<%
						}
					%>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</body>
</html>
