<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
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
	<td width="333" >课程编号</td>
	<td width="333">课程名称</td>
	<td width="333">备注信息</td>
</tr>
<c:forEach items="${subject}" var="subject"> 
			<tr>
				<td>${subject.subID}</td>
				<td>${subject.subName}</td>
				<td>${subject.subRemark}</td>
			</tr>
</c:forEach>
</table>
</fieldset>
</div>
<br />
<br />
<br />
<div style="width:100%;height:80%;overflow: auto;left: 200">
<form action="${pageContext.request.contextPath}/subject"  method="post">

<table align="center" >
<p align="center" >
		<font color="red">${erroMsgSubject}</font>
</p>
<tr>
	<td>班级编号 :<input type="text" name="subID"/><input type="submit" name="delete" value="删除" /></td>
</tr>
<tr>
	<td>班级名称 :<input type="text" name="subName"/></td>
</tr>
<tr>
	<td>所在系 :<input type="text" name="subRemark" /></td>
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