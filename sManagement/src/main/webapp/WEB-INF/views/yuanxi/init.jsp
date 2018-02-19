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

<div style="width:100%;height:60;overflow: auto;">
<table align="center" height="80">
	<td colspan="2">
	  <input type="button" name="num" value="院系管理" onclick="location.href='${pageContext.request.contextPath}/yuanxi?operator=init'">
	</td>
	<td colspan="2">
	  <input type="button" name="num" value="班级管理" onclick="location.href='${pageContext.request.contextPath}/class?operator=init'">
	</td>
	<td colspan="2">
	  <input type="button" name="num" value="课程管理" onclick="location.href='${pageContext.request.contextPath}/subject?operator=init'">
	</td>
	<td colspan="2">
	  <input type="button" name="num" value="学生管理" onclick="location.href='${pageContext.request.contextPath}/stu?operator=init'">
	</td>
	<td colspan="2">
	  <input type="button" name="num" value="成绩管理" onclick="location.href='${pageContext.request.contextPath}/score?operator=init'">
	</td>
</table>
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
<c:forEach items="${yuanxi}" var="yuanxi"> 
			<tr>
				<td>${yuanxi.xiID}</td>
				<td>${yuanxi.xiName}</td>
				<td>${yuanxi.yuan}</td>
			</tr>
</c:forEach>
</table>
</fieldset>
</div>

<br />
<br />
<br />
<div style="width:100%;height:80%;overflow: auto;left: 200">
<form action="${pageContext.request.contextPath}/yuanxi"  method="post">

<table align="center" >
<p align="center" >
		<font color="red">${erroMsgInsert}</font>
</p>
<tr>
	<td>院系编号 :<input type="text" name="xiID"/><input type="submit" name="delete" value="删除" /><input type="submit" name="byID" value="查找"/></td>
</tr>
<tr>
	<td>院系名称 :<input type="text" name="xiName"/><input type="submit" name="likeQuery" value="模糊查询"/></td>
</tr>
<tr>
	<td>所在学院 :<input type="text" name="yuan" /><input type="submit" name="likeQuery" value="模糊查询"/></td>
</tr>
</table>
<p align="center">
<input type="submit" name="insert" value="新增"/>
<input type="submit" name="update" value="修改"/>
<input type="submit" name="query" value="查询"/>
<input type="submit" name="back" value="返回"/>
<input type="reset" value="重置"/>
</p>
</form>
</div>

<table align="center">
<p align="center">ID命名规范</p>
<tr>
<td>学生班级院系(学生入学信息)</td>
<td>select stuID,stuName,stuSex,stuTime,stuBirth,stuPhone,stuAddr,clsName,clsType,yuan from yuanxi,class,stu group by stuID;</td>
</tr>



</table>


<%-- <form action="${pageContext.request.contextPath}/yuanxi?operator=yuanxiUpdate" method="post">
<table align="center" >
<tr>
	<td>院系编号:<input type="text" name="xiID" /></td>
</tr>
<tr>
	<td>院系名称:<input type="text" name=xiName />
	</td>
</tr>
<tr>
	<td>所在学院:<input type="text" name="yuan" />
	</td>
</tr>
<tr align="center">
	<td> 
		<input type="submit" name="update" value="修改" onclick="location.href='${pageContext.request.contextPath}/yuanxi?operator=yuanxiUpdate'"  />
		<input type="reset" value="重置"/>
	</td>
</tr>
</table>
</form> --%>

<%-- <form action="${pageContext.request.contextPath}/yuanxi?operator=yuanxiQuery" method="post">
<table align="center" >
<tr>
	<td>院系编号:<input type="text" name="xiID" /></td>
</tr>
<tr>
	<td>院系名称:<input type="text" name="xiName" /></td>
	<td>
	<input type="submit" value="模糊查询" onclick="location.href='${pageContext.request.contextPath}/yuanxi?operator=yuanxiLikeQUery'" />
	<input type="reset" value="重置"/>
	</td>
</tr>
<tr>
	<td>所在学院:<input type="text" name="yuan" /></td>
	<td>
	<input type="submit" value="模糊查询" onclick="location.href='${pageContext.request.contextPath}/yuanxi?operator=yuanxiLikeQUery'" />
	<input type="reset" value="重置"/>
	</td>
</tr>
<tr align="center">
	<td> 
		<input type="submit" value="查询" onclick="location.href='${pageContext.request.contextPath}/yuanxi?operator=yuanxiQuery'" />
	</td>
</tr>
</table>
</form>

<form action="${pageContext.request.contextPath}/yuanxi?operator=yuanxiLikeQUery" method="post">
<table align="center" >
<tr>
	<td>  院系名称:<input type="text" name="xiName" />
	<input type="submit" value="模糊查询" onclick="location.href='${pageContext.request.contextPath}/yuanxi?operator=yuanxiLikeQUery'" />
	<input type="reset" value="重置"/>
	</td>
</tr>
<tr>
	<td>  所在学院:<input type="text" name="yuan" />
	<input type="submit" value="模糊查询" onclick="location.href='${pageContext.request.contextPath}/yuanxi?operator=yuanxiLikeQUery'" />
	<input type="reset" value="重置"/>
	</td>
</tr>
</table>
</form> --%>

<%-- <form action="${pageContext.request.contextPath}/yuanxi?operator=yuanxiDelete" method="post">
<table align="center" >
<tr>
	<td>院系编号:<input type="text" name="xiID" />
	<input type="submit" value="删除" onclick="location.href='${pageContext.request.contextPath}/yuanxi?operator=yuanxiDelete'" />
	<input type="reset" value="重置"/>
	</td>
</tr>
</table>
</form> --%>


</body>