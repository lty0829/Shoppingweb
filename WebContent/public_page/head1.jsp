<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,Shopping.model.vo.*"
    pageEncoding="utf-8"%>
<%@page import="Shopping.factory.DAOFactory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%
		List<Classify> csfList = DAOFactory.getIShoppingDAOInstance().getAllCsf();
		Iterator csfIter =  csfList.iterator();
		
	 %>
	<div class="lefter">
		<div class="box">
			<h2>商品分类</h2>
			<dl>
				<dt>百货</dt>
				<%
				while(csfIter.hasNext()){	
					Classify csf = (Classify)csfIter.next(); %>
				<dd><a href="product-list.jsp?csf=<%=csf.getClassifyid() %>"><%=csf.getName() %></a></dd>
				<%} %>
			</dl>
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
	