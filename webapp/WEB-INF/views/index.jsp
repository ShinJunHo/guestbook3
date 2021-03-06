<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>

	<!-- 방명록 리스트 -->
	<%-- list에서 하나씩 빼서 테이블를 채운다--%>

	<form action="/guestbook3/add" method="post">
		<table border=1 width=500>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan=4><textarea name="message" cols=60 rows=5></textarea></td>
			</tr>
			<tr>
				<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
			</tr>
		</table>
	</form>
	<%
		pageContext.setAttribute("newline","\n");
	%>
	<c:set var="count" value="${fn:length(list) }" />
	<c:forEach items="${list }" var="vo" varStatus="status">
	<br>
	<table width=510 border=1>
		<tr>
			<td>${count-status.index }</td>
			<td>${vo.name}</td>
			<td>${vo.reg_date }</td>
			<td><a href="/guestbook3/deleteform?id=${vo.no }">삭제</a></td>
		</tr>
		<tr>
			<td colspan=4>${fn:replace(vo.message,newline,"<br/>")}</td>
		</tr>
	</table>
</c:forEach>
	
</body>
</html>