<%@page import="kr.co.acorn.dto.DeptDto"%>
<%@page import="kr.co.acorn.dto.EmpDto"%>
<%@page import="kr.co.acorn.dao.EmpDao"%>
<%@ page pageEncoding="utf-8" %>
<%
	request.setCharacterEncoding("utf-8");
	int no = Integer.parseInt(request.getParameter("no"));
	String name = request.getParameter("name");
	String job = request.getParameter("job");
	int mgr = Integer.parseInt(request.getParameter("mgr"));
	int sal = Integer.parseInt(request.getParameter("sal"));
	int comm = Integer.parseInt(request.getParameter("comm"));
	int deptNo = Integer.parseInt(request.getParameter("deptNo"));
	String tempPage = request.getParameter("page");
	
	DeptDto deptDto = new DeptDto(deptNo,null,null);

	EmpDao dao = EmpDao.getInstance();
	EmpDto dto = new EmpDto(no,name,job,mgr,null,sal,comm,deptDto);
	boolean isSuccess = dao.update(dto);
	if(isSuccess){
%>
	<script>
	alert('사원정보가 수정되었습니다.');
	location.href="view.jsp?page=<%=tempPage%>&no=<%=no%>";
	</script>
<%	}else{ %>
	<script>
	alert('DB Error');
	history.back(-1);
	</script>
<%	} %>
