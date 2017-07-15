
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,Shopping.model.vo.*" pageEncoding="utf-8"%>
<%@page import="Shopping.factory.DAOFactory,java.sql.Timestamp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>秀品网 - 首页</title>
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="scripts/function.js"></script>
		<script type="text/javascript">
$(document).ready(function(){
 $("#can").hide();
 $("#iscan a").click(function(){
 $(this).parent().toggleClass("bg");
 $(this).parent().prevAll("#iscan").removeClass("bg")
 $(this).parent().nextAll("#iscan").removeClass("bg")
 $(this).parent().next().slideToggle();
 $(this).parent().prevAll("#can").slideUp("slow");
 $(this).parent().next().nextAll("#can").slideUp("slow");
 return false;
 });
});
function openPage(curpage) {
		document.form1.cp.value = curpage;
		document.form1.submit();
	}
</script>
		<style type="text/css">
#iscan a {
	color: #000
}
</style>
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
		String comid = request.getParameter("id");
		if (comid != null) {
			int int_com = Integer.parseInt(comid);
			Commodity com = DAOFactory.getIShoppingDAOInstance()
					.findComById(int_com);
			Classify csf = DAOFactory.getIShoppingDAOInstance()
					.findCsfById(com.getType());
			User user = (User) session.getAttribute("user");
	%>
	<script language="JavaScript">
	<%if (user != null) {%>
		$(function(){
		var number=1;
		$(".button").find("input").click(function(){
			 if(confirm("地址：<%=user.getAddress()%>\n确定购买？")) {
				  location.href = 'managecast?getcom=<%=com.getCommodityID()%>&&numberw='+number;
			}
			else{
				return false;
			}				
			});
			$(".number").find("span").click(function(){
        		//var $tds=$(this).parent().parent().children("div");
       // var $number=$($tds[2]);
        var $number= $(this).parent().find("input");//得到存储input对象
        var opr=$(this).attr("name");//判断增减
        number = $number.val();//数量
		var totalnum;
        if(opr=="del"){
            number--;
            if(number<=0){
                number=1;              
            }
        }else if(opr=="add"){
            number++;
        }
        $number.val(number);
    		});
		})
	<%}%>
</script>
	<body>
		<jsp:include page="./public_page/head.jsp" />
		<div id="position" class="wrap">
			您现在的位置：
			<a href="index.jsp">秀品网</a> &gt;
			<a href="product-list.jsp?csf=<%=com.getType()%>"><%=csf.getName()%></a>
			&gt; 商品
		</div>
		<div id="main" class="wrap">
			<jsp:include page="./public_page/head1.jsp" />


			<div id="product" class="main">
			
			<%//System.out.print(com.getCommodityName()); %>
				<h1><%=com.getCommodityName()%></h1>
				<div class="infos">
					<div class="thumb">
						<img src="images/product/<%=com.getCommodityID()%>.jpg"
							width="110" height="106" />
					</div>
					<div class="buy">
						商城价：
						<span class="price">￥<%=com.getCommodityPrice()%></span>
						<br />
						库 存：<%=com.getQuantity()%><br />
						<div class="number">
							<span name="del">-</span>
							<input id="number_id_0" type="text" name="number" value="1" />
							<span name="add">+</span>
						</div>
						<div class="button">
						<script type="text/javascript">
							function beyondcall(){
								alert("购物车超过十件");
							}
						</script>
							<input type="button" name="button" value="" />
								<%  
									int castnum=0;
									List<Cast> castlist=null;
									
									if(user!=null){
										castlist = DAOFactory.getIShoppingDAOInstance().getAllcastByid(user.getUserid());
									}
									if(castlist!=null) castnum=castlist.size();
								 %>
							<a
								href="<%if (user != null) 
								{
								if(castnum<10){%>managecast?commodityid=<%=com.getCommodityID()%>&&userid=<%=user.getUserid()%><%}
								else {%>#" onclick="beyondcall()<%}
																			
								}%>">放入购物车</a>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="introduce">
					<h2>
						<strong>商品详情</strong>
					</h2>
					<div class="text">
						<%=com.getDescription()%><br />
						......
						<br />
					</div>
				</div>
			</div>
			<div class="clear"></div>
			<div id="product" class="main">
				<div class="guestbook">
					<h2 id="iscan">
						<a href="#">全部留言</a>
					</h2>
					<div id="can">
						<ul>
							<%
							String jspURL = "product-view.jsp?id="+com.getCommodityID();
								List<Word> wordlist = DAOFactory.getIShoppingDAOInstance().findLedByComid(com.getCommodityID());
								if(wordlist==null) wordlist = new ArrayList<Word>();
									double lineSize = 10;
									int currentPage = 1;
									//接受传过来的当前页
									try {
										currentPage = Integer.parseInt(request.getParameter("cp"));
									} catch (Exception e) {
										e.printStackTrace();
									}
									double limitpage = Math
											.ceil((double) wordlist.size() / lineSize);
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


							<li>
								<dl>
									<dt>
										<%=word.getLeaveWord() %>
									</dt>
									<dd class="author">
										网友：<%=user1.getUserName() %>
										<span class="timer"><%=word.getLeaveDate() %></span>
									</dd>
									<dd>
										<%if(word.getStatus()!=0) %><%=word.getReply() %><%} %>
									</dd>
								</dl>
							</li>
							<%}
							} %>
						</ul>
						<div class="clear"></div>
						<div class="pager2">
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
					<div id="reply-box">
						<form id="guestBook" method="post"
							action="<%if (user == null) {%>LeaveWord?comid=<%=com.getCommodityID()%><%} else {%>LeaveWord?comid=<%=com.getCommodityID()%>&&userid=<%=user.getUserid()%><%}%>">
							<table>
								<tr>
									<td class="field">
										昵称：
									</td>
									<td>
										<input class="text" type="text" name="guestName"
											disabled="disabled"
											value="<%if (user != null) {%><%=user.getUserName()%><%} else {%>请登录<%}%>" />
									</td>
								</tr>
								<tr>
									<td class="field">
										留言内容：
									</td>
									<td>
										<textarea name="guestContent"></textarea>
										<span></span>
									</td>
								</tr>
								<tr>
									<td></td>
									<td>
										<label class="ui-blue">
											<input type="submit" name="submit" value="提交留言" />
										</label>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
	<%
		}
	%>
</html>