<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp">
  	<c:param name="icon" value="icon-users"/>
  	<c:param name="title" value="학생 관리"/>
  	<c:param name="lastname" value="학생관리"/>
</c:import>

<div class="content">
	<div class="row">
		<div class="col-md-2">
			<div class="card">
				<div class="card-header bg-transparent header-elements-inline">
					<span class="text-uppercase font-size-md font-weight-bold">검색 조건</span>
					<div class="header-elements">
						<div class="list-icons">
	                		<a class="list-icons-item" data-action="collapse"></a>
	               		</div>
	          		</div>
				</div>
				<div class="card-body">
					<div class="font-size-xs text-muted mb-2">학교 선택</div>
					<div class="form-group">
						<select class="form-control select-search" name="school">
							<option value="">- 전 체 -</option>
							<c:forEach var="school" items="${schools}" varStatus="status">
								<option value="${school}">${school}</option>
							</c:forEach>
						</select>
					</div>
					<div class="font-size-xs text-muted mb-2">학년 선택</div>
					<div class="form-group">
						<select class="form-control form-control-select2" name="grade">
							<option value="">- 전 체 -</option>
							<c:forEach var="item" begin="1" end="6" step="1">
								<option value="${item}">${item} 학년</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<input type="search" class="form-control" placeholder="학생 이름" name="name" autocomplete="off">
					</div>
					<button type="button" id="searchBtn" class="btn bg-blue-400 btn-block">
						<i class="icon-search4 mr-2"></i>검 색
					</button>
				</div>
			</div>
			
			<div class="card">
				<div class="card-header bg-transparent header-elements-inline">
					<span class="text-uppercase font-size-sm font-weight-semibold">Action</span>
					<div class="header-elements">
						<div class="list-icons">
	                		<a class="list-icons-item" data-action="collapse"></a>
	               		</div>
	          		</div>
				</div>
				<div class="card-body">
					<a href="${pageContext.request.contextPath}/notice/regist" class="btn bg-teal-400 btn-block">
						<i class="icon-pencil5 mr-2"></i>학생 등록
					</a>
				</div>
			</div>
		</div>
		
		<div class="col-md-10">
			<div class="card">
				<div class="card-header">
					<h5 class="card-title">등록된 학생 목록</h5>
				</div>
				<div class="table-responsive">
					<table class="table text-nowrap" id="studentTable">
						<thead class="text-center">
							<tr class="table-active">
								<th>번호</th>
								<th>이름</th>
								<th>소속(학교 명)</th>
								<th>학년</th>
								<th>학급(반)</th>
								<th>번호</th>
								<th>연락처</th>
								<th>개인정보동의</th>
								<th>주민번호</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody class="text-center"></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
	