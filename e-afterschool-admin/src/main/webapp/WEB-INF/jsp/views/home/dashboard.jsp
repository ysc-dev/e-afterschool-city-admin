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
						<i class="icon-notification2 icon-3x text-primary-400"></i>
					</div>

					<div class="media-body text-right">
						<h3 class="font-weight-semibold mb-0">${invitations}</h3>
						<span class="font-size-sm text-muted">안내장 수</span>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="card card-body">
				<div class="media">
					<div class="mr-3 align-self-center">
						<i class="icon-office icon-3x text-indigo-400"></i>
					</div>

					<div class="media-body text-right">
						<h3 class="font-weight-semibold mb-0">${schools}</h3>
						<span class="font-size-sm text-muted">학교 수</span>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="card card-body">
				<div class="media">
					<div class="mr-3 align-self-center">
						<i class="icon-user-tie icon-3x text-warning-400"></i>
					</div>

					<div class="media-body text-right">
						<h3 class="font-weight-semibold mb-0">${teachers}</h3>
						<span class="text-uppercase font-size-sm text-muted">강사 수</span>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="card card-body">
				<div class="media">
					<div class="mr-3 align-self-center">
						<i class="icon-users icon-3x text-teal-400"></i>
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
						<i class="icon-make-group icon-3x text-success-400"></i>
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
						<i class="icon-books icon-3x text-info-400"></i>
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
						<i class="icon-paperplane icon-3x text-blue-600"></i>
					</div>

					<div class="media-body text-right">
						<h3 class="font-weight-semibold mb-0">${applies}</h3>
						<span class="text-uppercase font-size-sm text-muted">수강 신청 수</span>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="card card-body">
				<div class="media">
					<div class="mr-3 align-self-center">
						<i class="icon-history icon-3x text-slate-400"></i>
					</div>

					<div class="media-body text-right">
						<h3 class="font-weight-semibold mb-0">${applyWaits}</h3>
						<span class="text-uppercase font-size-sm text-muted">수강 대기 수</span>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="card card-body">
				<div class="media">
					<div class="mr-3 align-self-center">
						<i class="icon-blocked icon-3x text-warning-600"></i>
					</div>

					<div class="media-body text-right">
						<h3 class="font-weight-semibold mb-0">${applyCancels}</h3>
						<span class="text-uppercase font-size-sm text-muted">수강 취소 수</span>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="card card-body">
				<div class="media">
					<div class="mr-3 align-self-center">
						<i class="icon-bubble-notification icon-3x text-orange-400"></i>
					</div>

					<div class="media-body text-right">
						<h3 class="font-weight-semibold mb-0">${notices}</h3>
						<span class="text-uppercase font-size-sm text-muted">공지사항 수</span>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="card card-body">
				<div class="media">
					<div class="mr-3 align-self-center">
						<i class="icon-clipboard3 icon-3x text-brown-400"></i>
					</div>

					<div class="media-body text-right">
						<h3 class="font-weight-semibold mb-0">${surveyList}</h3>
						<span class="text-uppercase font-size-sm text-muted">만족도 및 설문조사 수</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>