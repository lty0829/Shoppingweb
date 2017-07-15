<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,Shopping.model.vo.*"  
    pageEncoding="UTF-8"%>
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
		<%			List<Classify> csflist = DAOFactory.getIShoppingDAOInstance().getAllCsf();
					String comid = request.getParameter("comid");
					if(comid!=null){
						int int_com = Integer.parseInt(comid);
						Commodity com = DAOFactory.getIShoppingDAOInstance().findComById(int_com);
				 %>
		<h2>修改商品</h2>
		<div class="manage">
			<form method="post" id="productAdd" action="ManageCom?isalter=1" enctype="multipart/form-data">
				<table class="form">
			
					<tr>
						<td class="field">商品名称(*)：</td>
						<td><input type="text" class="text" name="productName" value="<%=com.getCommodityName() %>" /><span></span></td>
						<td><input type="hidden" name="productid" value="<%=com.getCommodityID()%>" /></td>
					</tr>
                    <tr>
						<td class="field">描述(*)：</td>
						<td><input type="text" class="text" name="productDetail" value="<%=com.getDescription() %>" /></td>
					</tr>
					<tr>
						<td class="field">生产厂家(*)：</td>
						<td><input type="text" class="text" name="productGet" value="<%=com.getProductor() %>" /></td>
					</tr>
					<tr>
						<td class="field">所属分类(*)：</td>
						<td>
							<select name="parentId">
								<%  
								    for(Classify csf:csflist){
								    		if(csf.getClassifyid()!=com.getType()){   
								        %>
								       <option value="<%=csf.getClassifyid()%>"><%=csf.getName() %></option>
								       <%}
										else{%>
										<option selected="selected" value="<%=csf.getClassifyid()%>"><%=csf.getName() %></option>										       
								       <%}
								       } %>
							</select>
						</td>
					</tr>					
					<tr>
						<td class="field">商品价格(*)：</td>
						<td><input type="text" class="text tiny" name="productPrice"  value="<%=com.getCommodityPrice() %>"/><span></span></td>
					</tr>
					
					<tr>
						<td class="field">库存(*)：</td>
						<td><input type="text" class="text tiny" name="productNumber"  value="<%=com.getQuantity() %>"/><span></span></td>
					</tr>
					<tr>
						<td class="field">商品图片(*)：</td>
						<td><input type="file" class="text" name="photo1" /><span></span></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="确定" /></label></td>
					</tr>
					
				</table>
			</form>
		</div>
		<%}else{ %>
		<h2>增加商品</h2>
		<div class="manage">
			<form method="post" id="productAdd" action="ManageCom" enctype="multipart/form-data">
				<table class="form">
			
					<tr>
						<td class="field">商品名称(*)：</td>
						<td><input type="text" class="text" name="productName" value="" /><span></span></td>
					</tr>
                    <tr>
						<td class="field">描述(*)：</td>
						<td><input type="text" class="text" name="productDetail" value=""/><span></span></td>
					</tr>
					<tr>
						<td class="field">生产厂家(*)：</td>
						<td><input type="text" class="text" name="productGet" value=""/><span></span></td>
					</tr>
					<tr>
						<td class="field">所属分类(*)：</td>
						<td>
							<select name="parentId">
								<%  
								    for(Classify csf:csflist){
								    %>
										<option  value="<%=csf.getClassifyid()%>"><%=csf.getName() %></option>										       
								       <%
								       } %>
							</select>
						</td>
					</tr>					
					<tr>
						<td class="field">商品价格(*)：</td>
						<td><input type="text" class="text tiny" name="productPrice" /> 元<span></span></td>
					</tr>
					
					<tr>
						<td class="field">库存(*)：</td>
						<td><input type="text" class="text tiny" name="productNumber" /><span></span></td>
					</tr>
					<tr>
						<td class="field">商品图片(*)：</td>
						<td><input type="file" class="text" name="photo"/><span></span></td>
					</tr>
					<tr>
						<td>1</td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="确定" /></label></td>
					</tr>
					
				</table>
			</form>
		</div>
		<%} %>
	</div>
	<div class="clear"></div>
</div>
 </body>
</html>
