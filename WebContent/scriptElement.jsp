<%--scriptElement.jsp--%>

<%@ page pageEncoding="utf-8"%>

<%!
	private String name;
	private String id;
	public int plus(int a, int b){
	return a+b;
	}
%>

<%
	out.println("1 + 2 = "+plus(1,2)+"<br>");
%>

1+2 = <%=plus(1,2)%>, <%out.println(plus(1,2)); %><br>