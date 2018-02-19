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
	<td width="100">成绩编号</td>
	<td width="100">学生学号</td>
	<td width="100">学生姓名</td>
	<td width="100">课程编号</td>
	<td width="100">课程名称</td>
	<td width="100">课程成绩</td>
	<td width="200">考试时间</td>
	<td width="200">备注信息</td>
</tr>
<c:forEach items="${score}" var="score"> 
			<tr>
				<td>${score.scoreID}</td>
				<td>${score.scoreStuID}</td>
				<td>${score.scoreStuName}</td>
				<td>${score.scoreSubID}</td>
				<td>${score.scoreSubName}</td>
				<td>${score.scoreSubScore}</td>
				<td>${score.scoreTime}</td>
				<td>${score.scoreRemark}</td>
			</tr>
</c:forEach>
</table>
</fieldset>
</div>

<br />
<br />
<br />
<div style="width:100%;height:80%;overflow: auto;left: 200">
<form action="${pageContext.request.contextPath}/score"  method="post">

<table align="center" >
<p align="center" >
		<font color="red">${erroMsgscore}</font>
</p>
<tr>
	<td>成绩编号 :<input type="text" name="scoreID"/><input type="submit" name="delete" value="删除" /></td>
</tr>
<tr>
	<td>学生学号 :<input type="text" name="scoreStuID"/></td>
</tr>
<tr>
	<td>学生姓名:<input type="text" name="scoreStuName" /></td>
</tr>
<tr>
	<td>课程编号 :<input type="text" name="scoreSubID" /></td>
</tr>
<tr>
	<td>课程名称:<input type="text" name="scoreSubName" /></td>
</tr>
<tr>
	<td>课程成绩:<input type="text" name="scoreSubScore" /></td>
</tr>
<tr>
	<td>考试时间 :<input type="text" name="scoreTime" /></td>
</tr>
<tr>
	<td>备注信息:<input type="text" name="scoreRemark" /></td>
</tr>

</table>
<p align="center">
<input type="submit" name="insert" value="新增"/>
<input type="submit" name="update" value="修改"/>
<input type="submit" name="back" value="返回"/>
<input type="reset" value="重置"/>
</p>
</form>
</div>

</body>