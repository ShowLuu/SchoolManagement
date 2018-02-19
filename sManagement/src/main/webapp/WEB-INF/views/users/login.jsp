<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<htm>
<head>
<title>登录</title>
<script type="text/javascript">
	function getRandImage() {
		//$("#randImage").attr('src',"/validate?r="+new Date());
		document.getElementById("randImage").src = "${pageContext.request.contextPath}/checkCode?r="
				+ new Date();
	}
</script>


</head>

<body>

	<p align="center">欢迎请登录</p>
	<p align="center">
		<font color="red">${errorMessage}</font>
	</p>
	<form action="${pageContext.request.contextPath}/user?operator=login" method="post">
		<table align="center">
			<tr>
				<td>用户名:</td>
				<td><input type="text" name="username"></input></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="password" name="password"></input></td>
			</tr>
			<tr>
				<td style="font-size: 12px">验证码:</td>
				<td><input type='text' name='code' size='6' maxlength="6"
					value="" /> 
					<a href="#" onclick='getRandImage()'>
					<img alt="看不清,换一张" name="randImage" id="randImage"
						src="${pageContext.request.contextPath}/checkCode" width="60"
						height="20" border="1" align="absmiddle" /></a>
						<a href="#" onclick='getRandImage();'>
						<span style="font-size: 12px; color: blue">换一张</span></a>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2" style="display: inline;">
				<input type="submit" value="登陆" />
					<p>
						没有账号,<a href="${pageContext.request.contextPath}/user?operator=regist">注册</a>一个
					</p></td>
			</tr>
		</table>
	</form>
</body>
</html>