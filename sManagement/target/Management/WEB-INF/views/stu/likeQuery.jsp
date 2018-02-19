<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <!-- isELIgnored = false 表示不忽略el表达式 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<div align="center" style="height: 30px">
<font size="5">
<p align="center">安徽新华学院</p>
</font>
</div>


<br/>
<div style="width:100%;height:80%;overflow: auto;">
<fieldset>
<table align="center">
<tr style="background-color: gray;">
	<td width="100">学生学号</td>
	<td width="100">学生姓名</td>
	<td width="100">学生性别</td>
	<td width="100">入学时间</td>
	<td width="100">出生日期</td>
	<td width="100">联系方式</td>
	<td width="200">家庭住址</td>
	<td width="100">所在班级</td>
</tr>
<c:forEach items="${stu}" var="stu"> 
			<tr>
				<td>${stu.stuID}</td>
				<td>${stu.stuName}</td>
				<td>${stu.stuSex}</td>
				<td>${stu.stuTime}</td>
				<td>${stu.stuBirth}</td>
				<td>${stu.stuPhone}</td>
				<td>${stu.stuAddr}</td>
				<td>${stu.stuCls}</td>
			</tr>
</c:forEach>
</table>
</fieldset>
</div>

<br />
<br />
<br />
<div style="width:100%;height:80%;overflow: auto;left: 200">
<form action="${pageContext.request.contextPath}/stu"  method="post">

<table align="center" >
<p align="center" >
		<font color="red">${erroMsgStu}</font>
</p>
<tr>
	<td>学生学号 :<input type="text" name="stuID"/><input type="submit" name="delete" value="删除" /></td>
</tr>
<tr>
	<td>学生姓名 :<input type="text" name="stuName"/></td>
</tr>
<tr>
	<td>学生性别 :<input type="text" name="stuSex" /></td>
</tr>
<tr>
	<td>入学时间 :<input type="text" name="stuTime" /></td>
</tr>
<tr>
	<td>出生日期 :<input type="text" name="stuBirth" /></td>
</tr>
<tr>
	<td>联系方式 :<input type="text" name="stuPhone" /></td>
</tr>
<tr>
	<td>家庭住址 :<input type="text" name="stuAddr" /></td>
</tr>
<tr>
	<td>所在班级 :<input type="text" name="stuCls" /></td>
</tr>

</table>
<p align="center">
<input type="submit" name="update" value="修改"/>
<input type="submit" name="back" value="返回"/>
<input type="reset" value="重置"/>
</p>
</form>
</div>

</body>