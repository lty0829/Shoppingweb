<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,Shopping.model.vo.*"
    pageEncoding="utf-8"%>
<%@page import="Shopping.factory.DAOFactory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String userid = request.getParameter("userid");
	int int_user=1;
	if(userid!=null)
		int_user = Integer.parseInt(userid);
	User user = DAOFactory.getIShoppingDAOInstance().finUserByid(int_user);
	String str = user.getBirthday();
	int pos1 = str.indexOf("-");
	int pos2 = str.indexOf("-", pos1+1);
	int year = Integer.parseInt(str.substring(0,pos1));
	int mouth = Integer.parseInt(str.substring(pos1+1,pos2));
	int day = Integer.parseInt(str.substring(pos2+1,str.length()));
 %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 秀品网</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../scripts/function.js"></script>
</head>
<script>
var month_big = new Array("1","3","5","7","8","10","12"); //包含所有大月的数组
var month_small = new Array("4","6","9","11"); //包含所有小月的数组 
 
//页面加载时调用的初始化select控件的option的函数
function init()
{
  var select_year = document.getElementById("year"); //获取id为"year"的下拉列表框
  var select_month = document.getElementById("month"); //获取id为"month"的下拉列表框
  var select_day = document.getElementById("day"); //获取id为"day"的下拉列表框
  //将年份选项初始化，从1980到2000
  for(var i = 1980; i <= 2017; i++)
  {
    select_year_option = new Option(i, i);
    select_year.options.add(select_year_option);
  }
   
  //将月份选项初始化，从1到12
  for(var i = 1; i <= 12; i++)
  {
    select_month_option = new Option(i, i);
    select_month.options.add(select_month_option);
  }
   
  //调用swap_day函数初始化日期  
  swap_day();
  getValue();
}
//判断数组array中是否包含元素obj的函数，包含则返回true，不包含则返回false
function array_contain(array, obj)
{
  for (var i = 0; i < array.length; i++)
  {
    if (array[i] === obj)
    {
      return true;
    }
  }
  return false;
}
 
//根据年份和月份调整日期的函数
function swap_day()
{
  var select_year = document.getElementById("year"); //获取id为"year"的下拉列表框
  var select_month = document.getElementById("month"); //获取id为"month"的下拉列表框
  var select_day = document.getElementById("day"); //获取id为"day"的下拉列表框
   
  select_day.options.length = 0; //在调整前先清空日期选项里面的原有选项
  var month = select_month.options[select_month.selectedIndex].value; //获取被选中的月份month
   
  //如果month被包含在month_big数组中，即被选中月份是大月，则将日期选项初始化为31天
  if(array_contain(month_big, month))
  {
    for(var i = 1; i <= 31; i++)
    {
      select_day_option = new Option(i, i);
      select_day.options.add(select_day_option);
    }
  }
   
  //如果month被包含在month_small数组中，即被选中月份是小月，则将日期选项初始化为30天
  else if(array_contain(month_small, month))
  {
    for(var i = 1; i <= 30; i++)
    {
      select_day_option = new Option(i, i);
      select_day.options.add(select_day_option);
    }
  }
   
  //如果month为2，即被选中的月份是2月，则调用initFeb()函数来初始化日期选项
  else
  {
    initFeb();   
  }
}
//判断年份year是否为闰年，是闰年则返回true，否则返回false
function isLeapYear(year)
{
  var a = year % 4;
  var b = year % 100;
  var c = year % 400;
  if( ( (a == 0) && (b != 0) ) || (c == 0) )
  {
    return true;
  }
  return false;
}
 
//根据年份是否闰年来初始化二月的日期选项
function initFeb()
{
  var select_year = document.getElementById("year"); //获取id为"year"的下拉列表框
  var select_day = document.getElementById("day"); //获取id为"day"的下拉列表框
  var year = parseInt(select_year.options[select_year.selectedIndex].value); //获取被选中的年份并转换成Int
   
  //如果是闰年，则将日期选项初始化为29天
  if(isLeapYear(year))
  {
    for(var i = 1; i <= 29; i++)
    {
      select_day_option = new Option(i, i);
      select_day.options.add(select_day_option);
    }
  }
   
  //如果不是闰年，则将日期选项初始化为28天
  else
  {
    for(var i = 1; i <= 28; i++)
    {
      select_day_option = new Option(i, i);
      select_day.options.add(select_day_option);
    }
  }
}
</script>
 <body onLoad="init()">
    <jsp:include page="../public_page/managehead.jsp"/>
    <div id="position" class="wrap">
	您现在的位置：<a href="index.html">秀品网</a> &gt; 管理后台
	</div>
	<div id="main" class="wrap">
	 <jsp:include page="../public_page/managehead1.jsp"/>
<div class="main">

		<script type="text/javascript">
		function getValue()
  		{
			 var obj = document.getElementById("year");
			 console.log(obj);
   			 for(var i=0;i<obj.length;i++){
   			 	
        		if(obj[i].value==<%=year%>)
            		obj[i].selected = true;
            	}
            	obj = document.getElementById("month");
   			 for(var i=0;i<obj.length;i++){
        		if(obj[i].value==<%=mouth%>)
            		obj[i].selected = true;
            	}
            	obj = document.getElementById("day");
   			 for(var i=0;i<obj.length;i++){
        		if(obj[i].value==<%=day%>)
            		obj[i].selected = true;
            	}
           }
    
					
		</script>
		<h2>修改用户</h2>
		<div class="manage">
			<form action="Userchange" method="post">
				<table class="form">
					
					<tr>
						<td class="field">用户名：</td>
						<td><input type="text" class="text" name="UserName" value="<%=user.getUserName() %>" readonly="readonly" /></td>
						<td><input type="hidden" name="userid" value="<%=user.getUserid() %>"/></td>
						<td><input type="hidden" name="password" value="<%=user.getPassword() %>"/></td>
					</tr>
					
					<tr>
						<td class="field">性别：</td>
						<td><input type="radio" name="Gender" value="0" <%if(user.getGender()==0){ %>checked="checked" <% }%>/>男 
						<input type="radio" name="Gender" value="1" <%if(user.getGender()!=0){ %>checked="checked" <% }%>/>女</td>
					</tr>
					
					<tr>
						<td class="field">手机号码：</td>
						<td><input type="text" class="text" name="Phone" value="<%=user.getPhone() %>" /></td>
					</tr>
					
					<tr>
						<td class="field">地址：</td>
						<td><input type="text" class="text" name="addre" value="<%=user.getAddress() %>" /></td>
					</tr>
				
					<tr>
						<td class="field">出生日期：</td>
						<td>
							<select name="birthyear" id="year" onChange="swap_day()">
							</select>年
							<select name="birthmonth" id="month" onChange="swap_day()">
							</select>月
							<select name="birthday" id="day">
							</select>日
						</td>
					</tr>
						
					<tr>
						<td></td>	
						<td><label class="ui-blue"><input type="submit" name="submit" value="更新" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>

</body>
</html>