<%@page import="kr.co.acorn.dto.EmpDto"%>
<%@page import="kr.co.acorn.dao.EmpDao"%>
<%@ page pageEncoding="utf-8" %>
<%@ include file="../inc/header.jsp" %>  
<%
	String tempPage = request.getParameter("page");
	String tempNo = request.getParameter("no");
	if(tempPage == null || tempPage.length()==0){
		tempPage = "1";
	}
	if(tempNo == null || tempNo.length()==0){
		response.sendRedirect("list.jsp?page="+tempPage);
		return;
	}
	int cPage = 0;
	int no = 0;
	try{
		cPage = Integer.parseInt(tempPage);
	}catch(NumberFormatException e){
		cPage = 1;
	}
	try{
		no = Integer.parseInt(tempNo);
	}catch(NumberFormatException e){
		response.sendRedirect("list.jsp?page="+cPage);
		return;
	}
	
	EmpDao dao = EmpDao.getInstance();
	EmpDto dto = dao.select(no);
	if(dto == null){
		response.sendRedirect("list.jsp?page="+cPage);
		return;
	}
	String name = dto.getName();
	String job = dto.getJob();
	int mgr = dto.getMgr();
	String hiredate = dto.getHiredate();
	int sal = dto.getSal();
	int comm = dto.getComm();
	int deptNo = dto.getDeptDto().getNo();
%>
  <!-- breadcrumb start-->
  <nav aria-label="breadcrumb">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/index.jsp">Home</a></li>
      <li class="breadcrumb-item active" aria-current="page">사원관리</li>
    </ol>
  </nav>
  <!-- breadcrumb end-->

  <!-- main start-->
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <h3>
		  사원 상세보기
		</h3>
		<form name="f" method="post">
		  <div class="form-group row">
		    <label for="no" class="col-sm-2 col-form-label">사원번호</label>
		    <div class="col-sm-10">
		      <input type="number" class="form-control" id="no" readonly="readonly" name="no" value="<%=no %>">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="name" class="col-sm-2 col-form-label">이름</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="name" name="name" value="<%=name %>">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="job" class="col-sm-2 col-form-label">직책</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="job" name="job" value="<%=job %>">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="mgr" class="col-sm-2 col-form-label">사수</label>
		    <div class="col-sm-10">
		      <input type="number" class="form-control" id="mgr" name="mgr" value="<%=mgr %>">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="hiredate" class="col-sm-2 col-form-label">입사날짜</label>
		    <div class="col-sm-10">
		      <input type="string" class="form-control" id="hiredate" readonly="readonly" name="hiredate" value="<%=hiredate %>">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="sal" class="col-sm-2 col-form-label">월급</label>
		    <div class="col-sm-10">
		      <input type="number" class="form-control" id="sal" name="sal" value="<%=sal %>">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="comm" class="col-sm-2 col-form-label">상여금</label>
		    <div class="col-sm-10">
		      <input type="number" class="form-control" id="comm" name="comm" value="<%=comm %>">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="deptNo" class="col-sm-2 col-form-label">부서번호</label>
		    <div class="col-sm-10">
		      <input type="number" class="form-control" id="deptNo" name="deptNo" value="<%=deptNo %>">
		    </div>
		  </div>
		  <input type="hidden" name="page" value="<%=cPage%>"/>
		</form>
		
		<div class="text-right">
			<button type="button" id="prevPage" class="btn btn-outline-secondary">목록</button>
			<button type="button" id="updateEmp" class="btn btn-outline-success">수정</button>
			<button type="button" id="deleteEmp" class="btn btn-outline-danger">삭제</button>
        </div>
      </div>
    </div>
  </div>
  <!-- main end-->
  <%@ include file="../inc/footer.jsp" %> 
  <script>
  $(function(){
	  $("#no").focus();
	  $("#prevPage").click(function(){
		  history.back(-1);
	  });
	  $("#updateEmp").click(function(){
		  //자바스크립트 유효성 검사
		//자바스크립트 유효성 검사
		  if($("#no").val().length==0){
			  alert("사원번호를 입력하세요.");
			  $("#no").focus();
			  return;
		  }
		  if($("#name").val().length==0){
			  alert("이름을 입력하세요.");
			  $("#name").focus();
			  return;
		  }
		  if($("#job").val().length==0){
			  alert("직책을 입력하세요.");
			  $("#job").focus();
			  return;
		  }
		  if($("#sal").val().length==0){
			  alert("월급을 입력하세요.");
			  $("#sal").focus();
			  return;
		  }
		  if($("#deptNo").val().length==0){
			  alert("부서번호를 입력하세요.");
			  $("#deptNo").focus();
			  return;
		  }
		  f.action="update.jsp";
		  f.submit();
	  });
	  $("#deleteEmp").click(function(){
		  f.action="delete.jsp";
		  f.submit();
	  });
	  
	  
  });
  </script>
  
  
  
  