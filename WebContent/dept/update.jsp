<%@page import="kr.co.acorn.dto.DeptDto"%>
<%@page import="kr.co.acorn.dao.DeptDao"%>
<%@ page pageEncoding="utf-8" %>
<%
	request.setCharacterEncoding("utf-8");
	int no = Integer.parseInt(request.getParameter("no"));
	String name = request.getParameter("name");
	String loc = request.getParameter("loc");
	String tempPage = request.getParameter("page");
	
	DeptDao dao = DeptDao.getInstance();
	DeptDto dto = new DeptDto(no,name,loc);
	boolean isSuccess = dao.update(dto);
	if(isSuccess){
%>
	<script>
	alert('부서정보가 수정되었습니다.');
	location.href="view.jsp?page=<%=tempPage%>&no=<%=no%>";
	</script>
<%	}else{ %>
	<script>
	alert('DB Error');
	history.back(-1);
	</script>
<%	} %>
