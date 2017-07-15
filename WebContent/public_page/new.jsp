<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script language="JavaScript">
$(function() {
		$($(".news-list ul #span")[0]).nextUntil('#span').show();
	    $(".news-list ul #span").click(function(){
        $(this).nextUntil('#span').toggle();
        
    });		
})
</script>
<%
	int y=5;
 %>
<style type="text/css">
	.news-list dl dt { color:#404040; font-weight:bold; background:#fafafa; padding:2px 10px;cursor:pointer }
	.news-list dl dt { margin-left:10px; background:url(../images/bg.png) -232px -82px no-repeat; padding-left:25px; display:block}
	.news-list { border:1px solid #e0e0e0; height:<%=y*80%>px; }
	.news-list ul #span {white-space:nowrap; padding:2px 10px;cursor:pointer}
	.news-list ul #span1 { margin-left:10px;background:url(images/bg.png) -240px -153px no-repeat; padding-left:10px; white-space:nowrap; }
	
</style>
<div class="side">			
			<div class="spacer"></div>
			<div class="news-list">
				<h4>新闻动态</h4>
				<ul>
					<li id="span"><a href="#">所有</a></li>
					<li id="span1"><a href="news-view.html"  target="_self">抢钱啦</a></li>
					<li id="span1"><a href="news-view.html"  target="_self">抢钱啦</a></li>
					<li id="span1"><a href="news-view.html"  target="_self">抢钱啦</a></li>
					<li id="span1"><a href="news-view.html"  target="_self">抢钱啦</a></li>
					<li id="span1"><a href="news-view.html"  target="_self">抢钱啦</a></li>
					<li id="span1"><a href="news-view.html"  target="_self">抢钱啦</a></li>
					<li id="span1"><a href="news-view.html"  target="_self">抢钱啦</a></li>
                    <li id="span1"><a href="news-view.html"  target="_self">抢钱啦</a></li>
					<li id="span1"><a href="news-view.html"  target="_self">抢钱啦</a></li>
					<li id="span1"><a href="news-view.html"  target="_self">抢钱啦</a></li>
				</ul>
			</div>
		</div>
