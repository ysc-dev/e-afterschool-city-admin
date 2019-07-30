<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp" >
  	<c:param name="icon" value="icon-home5" />
  	<c:param name="title" value="Dashboard" />
  	<c:param name="lastname" value="Dashboard" />
</c:import>

<div class="content">
	<div class="mb-3">
		<h6 class="mb-0 font-weight-semibold">
			Simple statistics
		</h6>
		<span class="text-muted d-block">통계 수치를 확인하세요.</span>
	</div>
	<div class="row">
		<div class="col-md-3">
			<div class="card card-body">
				<div class="media">
					<div class="mr-3 align-self-center">
						<i class="icon-users icon-3x text-success-400"></i>
					</div>

					<div class="media-body text-right">
						<h3 class="font-weight-semibold mb-0">${students}</h3>
						<span class="text-uppercase font-size-sm text-muted">학생 수</span>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="card card-body">
				<div class="media">
					<div class="mr-3 align-self-center">
						<i class="icon-make-group icon-3x text-indigo-400"></i>
					</div>

					<div class="media-body text-right">
						<h3 class="font-weight-semibold mb-0">${subjectGroups}</h3>
						<span class="text-uppercase font-size-sm text-muted">과목 그룹 수</span>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="card card-body">
				<div class="media">
					<div class="mr-3 align-self-center">
						<i class="icon-books icon-3x text-blue-400"></i>
					</div>

					<div class="media-body text-right">
						<h3 class="font-weight-semibold mb-0">${subjects}</h3>
						<span class="text-uppercase font-size-sm text-muted">수강 과목 수</span>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="card card-body">
				<div class="media">
					<div class="mr-3 align-self-center">
						<i class="icon-database-add icon-3x text-danger-400"></i>
					</div>

					<div class="media-body text-right">
						<h3 class="font-weight-semibold mb-0">${applies}</h3>
						<span class="text-uppercase font-size-sm text-muted">수강 신청 수</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>