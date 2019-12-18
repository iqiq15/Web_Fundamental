<%-- pageDirective --%>
<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page pageEncoding="utf-8" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page session="true" %>
<%@ page buffer="10kb" %>
<%@ page autoFlush="false" %>
<%@ page isThreadSafe="false" %>
<%@ page info="jsp 페이지 입니다." %>
<%@ page errorPage="/error/error.jsp" %>
<%@ page isELIgnored="true" %>

<%
	Calendar c = Calendar.getInstance();
	SimpleDateFormat s = new SimpleDateFormat();
	
	session.setAttribute("aa","aa");
	
	//int a = Integer.parseInt("123a");
	String id = request.getParameter("id");
	out.println(id);
%>
${param.id }
