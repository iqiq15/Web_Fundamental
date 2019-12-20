<%@ page import="kr.co.acorn.dto.EmpDto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="kr.co.acorn.dao.EmpDao"%>
<%@ page pageEncoding="utf-8" %>
<%@ include file="../inc/header.jsp" %> 
  <%
    int len = 10;
    int pageLength = 2;
	int start = 0;
	int totalRows = 0;
	int totalPage = 0;
	int startPage = 0;
	int endPage = 0;
	int pageNum = 0;
	int cPage = 0;
	
  	String tempPage = request.getParameter("page");
    if(tempPage == null || tempPage.length()==0){
    	cPage = 1;
    }
    try{
    	cPage = Integer.parseInt(tempPage);
    }catch(NumberFormatException e){
    	cPage = 1;
    }
    //An = a1 + (n-1)*d
    EmpDao dao = EmpDao.getInstance();//얘바꾸기	
   	totalRows = dao.getTotalRows();	
   	totalPage = totalRows % len == 0 ? totalRows/len : totalRows/len+1;
  	if(totalPage == 0){
  		totalPage = 1;
  	}
  	
  	if(cPage>totalPage){
  		response.sendRedirect("list.jsp?page=1");
  		return;
  	}
  	
  	start = (cPage - 1) * len; 
  	pageNum = totalRows + (cPage -1)*(-len);
  	ArrayList<EmpDto> list = dao.select(start,len);//
  	
  	/*
  		totalRows = 132;
  		len = 5;
  		pageLength = 10;
  					startPage    endPage
  		cPage = 1      1            10
  		cPage = 5      1            10
  		cPage = 14     11           20
  		cPage = 18     11           20
  		cPage = 22     21           27
  		
  		cPage = 1-10   n=>1   n=>currentBlock
  		cPage = 11-20  n=>2   n=>currentBlock
  		
  		startPage = 1 + (currentBlock-1) * pageLength
  		endPage = pageLength + (currentBlock-1) * pageLength
  	*/
  	
  	int currentBlock = cPage % pageLength == 0 ? 
  			(cPage/pageLength) : (cPage/pageLength+1) ;
  			
  	int totalBlock = totalPage % pageLength == 0 ? 
  			(totalPage/pageLength) : (totalPage/pageLength+1) ;
  	
  	startPage = 1 + (currentBlock-1) * pageLength;
  	endPage = pageLength + (currentBlock-1) * pageLength;	
  
  	if(currentBlock == totalBlock){
  		endPage = totalPage;
  	}
  	
  	
  	
  	
  	
  	
  %> 
  <!-- breadcrumb start-->
  <nav aria-label="breadcrumb">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/index.jsp">Home</a></li>
      <li class="breadcrumb-item active" aria-current="page">Library</li>
    </ol>
  </nav>
  <!-- breadcrumb end-->
	
  <!-- main start-->
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        
		<h3>Crawling(bitcoin)</h3>
		<div class="table-responsive-lg">
			<form>
				<div class="form-group row">
					<label for="selectCoin" class="col-sm-3 col-form-label">코인선택</label>
					<div class="form-group col-sm-9">
						<select class="form-control">
						  <option>Coin..</option>
						  <option>비트코인</option>
						  <option>이더리움</option>
						  <option>리플</option>
						  <option>비트코인캐쉬</option>
						  <option>라이트코인</option>
						</select>
					</div>
				</div>
				<div class="form-group row">
					<label for="staticEmail" class="col-sm-3 col-form-label">시작날짜</label>
					<div class="form-group col-sm-3">
						<select class="form-control">
						  <option>Year..</option>
						</select>
					</div>
					<div class="form-group col-sm-3">
						<select class="form-control">
						  <option>Coin..</option>
						  <option>비트코인</option>
						  <option>이더리움</option>
						  <option>리플</option>
						  <option>비트코인캐쉬</option>
						  <option>라이트코인</option>
						</select>
					</div>
					<div class="form-group col-sm-3">
						<select class="form-control">
						  <option>Coin..</option>
						  <option>비트코인</option>
						  <option>이더리움</option>
						  <option>리플</option>
						  <option>비트코인캐쉬</option>
						  <option>라이트코인</option>
						</select>
					</div>
				</div>
				<div class="form-group row">
					<label for="staticEmail" class="col-sm-3 col-form-label">끝날짜</label>
					<div class="form-group col-sm-3">
						<select class="form-control">
						  <option>Coin..</option>
						  <option>비트코인</option>
						  <option>이더리움</option>
						  <option>리플</option>
						  <option>비트코인캐쉬</option>
						  <option>라이트코인</option>
						</select>
					</div>
					<div class="form-group col-sm-3">
						<select class="form-control">
						  <option>Coin..</option>
						  <option>비트코인</option>
						  <option>이더리움</option>
						  <option>리플</option>
						  <option>비트코인캐쉬</option>
						  <option>라이트코인</option>
						</select>
					</div>
					<div class="form-group col-sm-3">
						<select class="form-control">
						  <option>Coin..</option>
						  <option>비트코인</option>
						  <option>이더리움</option>
						  <option>리플</option>
						  <option>비트코인캐쉬</option>
						  <option>라이트코인</option>
						</select>
					</div>
				</div>
			</form>
						
			<div class="text-right">
				<a href="write.jsp?page=<%=cPage %>" class="btn btn-outline-success">검색</a>
			</div>
			<table class="table table-hover">
	          <colgroup>
	            <col width="10%"/>
	          	<col width="15%"/>
	          	<col width="15%"/>
	          	<col width="15%"/>
	          	<col width="15%"/>
	          	<col width="15%"/>
	          	<col width="15%"/>
	          </colgroup>
			  <thead>
			    <tr>
			      <th scope="col">Date</th>
			      <th scope="col">Open</th>
			      <th scope="col">High</th>
			      <th scope="col">Low</th>
			      <th scope="col">Close</th>
			      <th scope="col">Volume</th>
			      <th scope="col">Market Cap</th>
			    </tr>
			  </thead>
			  <tbody>
			    
			    <tr>
			      <td></td>
			      <td></td>
			      <td></td>
			      <td></td>
			      <td></td>
			      <td></td>
			      <td></td>
			    </tr>
			    
			    <tr>
			      <td colspan="6">데이터가 존재하지 않습니다.</td>
			    </tr>
			   
			   
			  </tbody>
			</table>
		</div>
       
        
        
        
      </div>
    </div>
  </div>
  <!-- main end-->
  <%@ include file="../inc/footer.jsp" %> 
  
  
  
  
  