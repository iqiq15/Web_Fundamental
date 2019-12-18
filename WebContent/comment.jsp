<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1> jsp comment</h1>
	<!-- html 주석 -->
	<%-- jsp 주석 --%>
	<ul>
		<%
		//int age = 10;
		for(int i=0;i<100;i++){
		out.println("<li>"+i+"</li>");
		}
		%>
	</ul>
</body>
</html>