<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>秀品网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
<style type="text/css">
#code {
	font-family: Arial;
	font-style: italic;
	font-weight: bold;
	border: 0;
	letter-spacing: 2px;
	color: blue;
}
</style>
		<script type="text/javascript"">
        	var code ; //在全局定义验证码   
			//产生验证码  
			function createCode(){  
    		 code = "";   
    		 var codeLength = 4;//验证码的长度  
    		 var checkCode = document.getElementById("code");   
    		 var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',  
    		 'S','T','U','V','W','X','Y','Z');//随机数  
   			  for(var i = 0; i < codeLength; i++) {//循环操作  
   		     var index = Math.floor(Math.random()*36);//取得随机数的索引（0~35）  
   		     code += random[index];//根据索引取得随机数加到code上  
  		 	 }  
 			   checkCode.value = code;//把code值赋给验证码  
			}  
//校验验证码  
			function validate(){  
  		  var inputCode = document.getElementById("input").value.toUpperCase(); //取得输入的验证码并转化为大写        
   		 if(inputCode.length <= 0) { //若输入的验证码长度为0  
        if(alert("请输入验证码！")) 
        	return false;
        	return false;
    }         
    else if(inputCode != code ) { //若输入的验证码与产生的验证码不一致时  
        alert("验证码输入错误！@_@"); //则弹出验证码输入错误  
        createCode();//刷新验证码  
        document.getElementById("input").value = "";//清空文本框  
        return false;
    }         
    else { //输入正确时  
        alert("^-^"); //弹出^-^  
    }             
}  
        
          
        </script>
</head>
<body onload="createCode()">
	<jsp:include page="./public_page/head.jsp"/>
	<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>欢迎回到秀品网</h1>
			<form id="loginForm" method="post" action="Onlogin" onsubmit="return validate();">
				<table>
					<tr>
						<td class="field">用户名：</td>
						<td>
                            <input class="text" type="text" id="userId" name="userId" />
							<span></span>
						</td>
					</tr>
					<tr>
						<td class="field">登录密码：</td>
						<td>
                            <input class="text" type="password" id="password" name="password" />
							<span></span>
						</td>
					</tr>
					<tr>
					<td class="field">验证码：</td>
						<td>
                           <input type="button" id="code" onclick="createCode()" />
                            <a id="changeCode" href="#" onclick="createCode()">看不清，换一张</a><br>
	                        <input type="text" name="code" id="input"><div class="mess"></div>
							<span></span>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-green"><input type="submit" name="submit" value="立即登录" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
</body>
</html>