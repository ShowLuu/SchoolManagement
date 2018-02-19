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
	<td width="333" >院系编号</td>
	<td width="333">院系名称</td>
	<td width="333">所在学院</td>
</tr>
<c:forEach items="${cls}" var="cls"> 
			<tr>
				<td>${cls.clsID}</td>
				<td>${cls.clsName}</td>
				<td>${cls.clsType}</td>
			</tr>
</c:forEach>
</table>
</fieldset>
</div>

<br />
<br />
<br />
<div style="width:100%;height:80%;overflow: auto;left: 200">
<form action="${pageContext.request.contextPath}/class"  method="post">

<table align="center" >
<p align="center" >
		<font color="red">${erroMsgClass}</font>
</p>
<tr>
	<td>班级编号 :<input type="text" name="clsID"/><input type="submit" name="delete" value="删除" /></td>
</tr>
<tr>
	<td>班级名称 :<input type="text" name="clsName"/></td>
</tr>
<tr>
	<td>所在系 :<input type="text" name="clsType" /></td>
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