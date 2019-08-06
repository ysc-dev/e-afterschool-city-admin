<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp">
  	<c:param name="icon" value="icon-list"/>
  	<c:param name="title" value="수강 관리"/>
  	<c:param name="lastname" value="수강 관리"/>
</c:import>

<div class="content">
	<div id="list_content" class="card mb-0">
		<div class="card-header header-elements-inline bg-white">
			<h4 class="card-title font-weight-bold">
				<i class="icon-list mr-2"></i>신청된 수강 목록
			</h4>
			<div class="header-elements">
			
			</div>
		</div>
		<div class="card-body">
			<div class="d-flex mt-1 mb-2">
				<label class="col-form-label font-weight-bold mr-3">검색조건 <i class="icon-arrow-right13"></i></label>
				<div class="mr-2">
					<select class="form-control select-search" name="subject" data-width="300">
						<option value="">- 전 체 -</option>
						<c:forEach var="subject" items="${subjects}" varStatus="status">
							<option value="${subject.id}">
								${subject.name} - ${subject.week}
							</option>
						</c:forEach>
					</select>
				</div>
				<div class="mr-2">
					<select class="form-control select-search" name="school" data-width="200">
						<option value="">- 전 체 -</option>
						<c:forEach var="school" items="${schools}" varStatus="status">
							<option value="${school}">${school}</option>
						</c:forEach>
					</select>
				</div>
				<div class="mr-3">
					<select class="form-control form-control-select2" name="grade" data-width="100">
						<option value="">- 전 체 -</option>
						<c:forEach var="item" begin="1" end="6" step="1">
							<option value="${item}">${item} 학년</option>
						</c:forEach>
					</select>
				</div>
				<button id="searchBtn" class="btn bg-teal-400"><i class="icon-search4 mr-2"></i> 조 회</button>
			</div>
			
			<table class="table table-bordered" id="applyTable">
				<thead class="text-center">
					<tr class="table-active">
						<th>번호</th>
						<th>과목</th>
						<th>수강기간</th>
						<th>운영시간</th>
						<th>유형</th>
						<th>강사명</th>
						<th>학생명</th>
						<th>소속(학교 명)</th>
						<th>학년 반 번호</th>
						<th>연락처</th>
					</tr>
				</thead>
				<tbody class="text-center"></tbody>
			</table>
		</div>
	</div>
</div>
	