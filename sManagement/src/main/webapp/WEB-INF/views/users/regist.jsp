<? xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    
    <script type="text/javascript">
    	
    	function checkPwd(){
    		
    		var newP = document.getElementById("newPassword").value;
    		var oldP = document.getElementById("oldPassword").value;
    		
    		if(newP!=oldP){
    			document.getElementById("oldPassword").value='';
    			document.getElementById("oldPassword").focus();
    			return false;
    		}
    		
    	}
    
    </script>
</head>


<body>

<p align="center">注册学籍</p>

	<form action="${pageContext.request.contextPath}/user?operator=save_user" method="post">
			<table align="center">
				<tr>
					<td>用户名:</td>
					<td><input type="text" name="username"></input></td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input type="password" id="newPassword" name="newPassword"></input></td>
				</tr>
				<tr>
					<td>确认密码:</td>
					<td><input type="password" id="oldPassword" name="oldPassword" onchange="checkPwd();"></input></td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<input type="submit" value="保存"/>
					</td>
				</tr>
		</table>
		</form>
</body>
</html>
