<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,Shopping.model.vo.*"
    pageEncoding="utf-8"%>
<%@page import="Shopping.factory.DAOFactory"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<script language="JavaScript">
		
		$(function() {
		$(".checkd").find('input').change(function() {
			$("#form").submit();  
		});
	})
		
	
</script>
	
	<%
		String str = request.getRequestURL().toString();
		int pos = str.lastIndexOf("/");
		int pos1=str.lastIndexOf(".");
		String s =null;
		if(pos1 != -1&&pos!=-1)
			s = str.substring(pos+1,pos1);
		else
			s = ""; 
		List<Classify> csfList = DAOFactory.getIShoppingDAOInstance().getAllCsf();
		request.setAttribute("csflist",csfList);
		Iterator csfIter =  csfList.iterator();
		List<String> ifcsf = (List<String>)session.getAttribute("ifcsf");
		if(ifcsf==null) ifcsf = new ArrayList<String>();
	 %>
	<div class="lefter">
		<div class="box">
			<form id="form" action="CheckCom" method="post">
			<h2>商品分类</h2>
			<dl>
			
				<dt>百货</dt>
				<%
				while(csfIter.hasNext()){	
					Classify csf = (Classify)csfIter.next(); %>
				<dd class="boxd"><a href="product-list.jsp?csf=<%=csf.getClassifyid() %>"><%=csf.getName() %></a></dd>
				<%}if(s.equals("product-list")){ %>
				<dt>复选</dt>
				
				<%for(Classify csf : csfList){%>
					<dd class="checkd"><input name="checkcom" type="checkbox" value="<%=csf.getClassifyid()%>" <%if(ifcsf.contains(csf.getClassifyid()+"")){ %>checked<%} %>/><%=csf.getName() %></dd>
				
				
				<%} }%>
				
			</dl>
			</form>
		</div>
		
		
			<%
			Commodity com = (Commodity)session.getAttribute("newlook");
			if(com!=null){
		 %>
		<div class="spacer"></div>
		<div class="last-view">
			<h2>最近浏览</h2>
			<dl class="clearfix">
				<dt><img src="images/product/<%=com.getCommodityID()%>_small.jpg" /></dt>
				<dd><a href="product-view.jsp?id=<%=com.getCommodityID()%>"><%=com.getCommodityName() %></a></dd>
				<dt>&nbsp;</dt>
				<dd>&nbsp;</dd>
				<dt>&nbsp;</dt>
				<dd>&nbsp;</dd>
		  </dl>
				
	
		</div>
		<%}
		else{%>
		<div class="spacer"></div>
		<div class="last-view">
			<h2>最近浏览</h2>
			<dl class="clearfix">
				<dt>无</dt>
				<dd>无</dd>
				<dt>&nbsp;</dt>
				<dd>&nbsp;</dd>
				 </dl>
		</div>
		<% }%>
		
	</div>
	