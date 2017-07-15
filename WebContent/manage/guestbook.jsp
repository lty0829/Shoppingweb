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
		<script type="text/javascript">
		$(function() {
		$(".classifyD").change(function() {
			var number = $(this).val();
			location.href = 'guestbook.jsp?status=' + number;
		});
	})
	function openPage(curpage) {
		document.form1.cp.value = curpage;
		document.form1.submit();
	}
</script>
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
		String status = request.getParameter("status");
		int int_stat=2;
		if(status!=null) int_stat = Integer.parseInt(status);
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
					留言管理
				</h2>
				<div class="manage">
				<div class="spacer"></div>
				<select class="classifyD" name="classify">
						<option value="2" <%if (int_stat == 2) {%> selected="selected"
							<%}%>>
							所有留言
						</option>
						<option value="0" <%if (int_stat == 0) {%> selected="selected"
							<%}%>>
							未回复
						</option>
						<option value="1" <%if (int_stat == 1) {%> selected="selected"
							<%}%>>
							已回复
						</option>
						
					</select>
				<div class="spacer"></div>
					<table class="list">
						<tr>
							<th>
								ID
							</th>
							<th>
								姓名
							</th>
							<th>
								时间
							</th>
							<th>
								留言内容
							</th>

							<th>
								状态
							</th>
							<th>
								操作
							</th>
						</tr>
						<%
							String jspURL = "guestbook.jsp";
							List<Word> wordlist = null;
							if(int_stat==2) 
							wordlist=DAOFactory.getIShoppingDAOInstance()
									.findAllLeaveword();
							else
							wordlist=DAOFactory.getIShoppingDAOInstance().findLedByStatus(int_stat);
							if (wordlist == null)
								wordlist = new ArrayList<Word>();
							double lineSize = 10;
							int currentPage = 1;
							//接受传过来的当前页
							try {
								currentPage = Integer.parseInt(request.getParameter("cp"));
							} catch (Exception e) {
								e.printStackTrace();
							}
							double limitpage = Math.ceil((double) wordlist.size() / lineSize);
							Word word = null;
								if (wordlist != null) {
									Iterator iterator = wordlist.iterator();
									for (int x = 0; x < (currentPage - 1) * lineSize; x++) {
										word = (Word) iterator.next();
									}
									for (int x = 0; x < lineSize; x++) {
									if (iterator.hasNext()) {
										word = (Word) iterator.next();
										User user1  = DAOFactory.getIShoppingDAOInstance().finUserByid(word.getUserID());
						%>
						<tr>
							<td class="first w4 c">
								<%=word.getLeaveWordID() %>
							</td>
							<td class="w1 c">
								<%=user1.getUserName() %>
							</td>
							
							<td>
								<%=word.getLeaveDate() %>
							</td>
							<td>
								<%=word.getLeaveWord() %>
							</td>
							<%if(word.getStatus()!=0){ %>
							<td class="w1 c">
								已回复
							</td>
							<td class="w1 c">
								<a href="guestbook-modify.jsp?gstid=1&&gustid=<%=word.getLeaveWordID() %>">修改</a>
								<a class="manageDel" href="ManageGust?gustid=<%=word.getLeaveWordID() %>">删除</a>
							</td>
							<%} else{ %>
							<td class="w1 c">
							
							</td>
							<td class="w1 c">
								<a href="guestbook-modify.jsp?gstid=0&&gustid=<%=word.getLeaveWordID() %>">回复</a>
								<a class="manageDel" href="ManageGust?gustid=<%=word.getLeaveWordID() %>">删除</a>
							</td>
							<%} %>
							
						</tr>
						<%}
						}
						} %>
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
