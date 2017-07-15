<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,Shopping.model.vo.*"
    pageEncoding="utf-8"%>
<%@page import="Shopping.factory.DAOFactory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>秀品网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
<script language="JavaScript">
function reg()
{
	var pattern = /^[a-zA-Z0-9_]+$/;
	var pattern2=/1((3\d)|(4[57])|(5[01256789])|(8\d))-?\d{8}/;
	
	if (document.form2.userId.value == "" ) 
	{
		alert("请输入用户名!");
		document.form2.userId.focus();
		return false;
	}
	else if (document.form2.userId.value.length < 3 )  
	{
		alert("用户名长度最少3个字符!");
		document.form2.userId.focus();
		return false;
	}
	else if (!pattern.test(document.form2.userId.value) )  
	{
		alert("用户名只能包含字母、数字!");
		document.form2.userId.focus();
		return false;
	}
	else if (document.form2.password.value == "" ) 
	{
		alert("请输入密码!");
		document.form2.password.focus();
		return false;
	}
	else if (document.form2.password.value.length < 3 )  
	{
		alert("密码长度最少3个字符!");
		document.form2.password.focus();
		return false;
	}
	else if (!pattern.test(document.form2.password.value) )  
	{
		alert("密码只能包含字母、数字!");
		document.form2.password.focus();
		return false;
	}
	else if (document.form2.password.value != document.form2.confirmPassword.value ) 
	{
		alert("前后两次密码不一致!");
		document.form2.realname.focus();
		return false;
	}
	else if (document.form2.phone.value == "" ) 
	{
		alert("请输入联系电话!");
		document.form2.phone.focus();
		return false;
	}
	/*else if (!pattern2.test(document.form2.phone.value)) 
	{
		alert("电话号码不符规则!");
		document.form2.phone.focus();
		return false;
	}*/
	else if (document.form2.address.value == "" ) 
	{
		alert("请输入联系地址!");
		document.form2.address.focus();
		return false;
	}
	doucument.log(pattern2.test(document.form2.phone.value));
}
</script>
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
		User user = (User)session.getAttribute("user");
	%>
<body>
<jsp:include page="./public_page/head.jsp"/>


<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>修改信息</h1>
			<form id="regForm" name="form2" action="Alter" method="post" onSubmit="return reg()">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
          
              <tr>
                <td width="42%" height="25" align="right" class="field">用户名：</td>
                <td width="58%" height="25">&nbsp;<input class="text" type="text" name="userId"  id="userId" value="<%=user.getUserName() %>"></td>
              </tr>
              <tr>
                <td width="42%" height="25" align="right" class="field">密码：</td>
                <td height="25" class="tabletd2">&nbsp;<input class="text" type="password" id="password" name="password" value="<%=user.getPassword()%>"></td>
              </tr>
               <tr>
                <td width="42%" height="25" align="right" class="field">确认密码*：</td>
                <td height="25" height="25" class="tabletd2">&nbsp;<input class="text" type="password" name="confirmPassword" value="<%=user.getPassword()%>"></td>
              </tr>
              <tr>
                <td height="25" align="right" class="field">性别：</td>
                <td height="25" class="tabletd2">&nbsp;
                <input name="gender" type="radio" value="0" <%if(user.getGender()==0){ %>checked<%} %>>&nbsp;男</input>
                <input type="radio" name="gender" value="1" <%if(user.getGender()==1){ %>checked<%} %>>&nbsp;女</input></td>
              </tr>
              <tr>
				<td width="42%" height="25" align="right" class="field">出生日期：</td>
				<td width="58%" height="25">&nbsp;<input id="birthday" class="text" type="text" name="birthday" value="<%=user.getBirthday() %>"/></td>
			</tr>
              
               <tr>
                <td height="25" align="right" class="field">联系电话：</td>
                <td height="25" class="tabletd1">&nbsp;<input class="text" type="text" name="phone" value="<%=user.getPhone() %>"></td>
              </tr>
              <tr>
                <td height="25" align="right" class="field">联系地址：</td>
                <td height="25" class="tabletd2">&nbsp;<input class="text" type="text" name="address" value="<%=user.getAddress() %>"></td>
              </tr>
					<tr>
						<td></td>
						<td><label class="ui-green"><input type="submit" name="submit" value="确认修改" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
</body>
</html>