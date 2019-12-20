<%@page import="kr.co.acorn.dto.EmpDto"%>
<%@page import="kr.co.acorn.dao.EmpDao"%>
<%@ page pageEncoding="utf-8" %>
<%
	request.setCharacterEncoding("utf-8");
	int no = Integer.parseInt(request.getParameter("no"));
	String tempPage = request.getParameter("page");
	
	EmpDao dao = EmpDao.getInstance();
	boolean isSuccess = dao.delete(no);
	if(isSuccess){
%>
	<script>
	alert('사원정보가 삭제 되었습니다.');
	location.href="list.jsp?page=<%=tempPage%>";
	</script>
<%	}else{ %>
	<script>
	alert('DB Error');
	history.back(-1);
	</script>
<%	} %>
