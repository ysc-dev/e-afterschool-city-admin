package com.ysc.afterschool.admin.service;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.Subject;
import com.ysc.afterschool.admin.domain.param.SubjectSearchParam;

public interface SubjectService extends CRUDService<Subject, SubjectSearchParam, Integer> {

	List<Subject> getList(int invitationId);
	
	boolean deleteBySubjectGroup(int subjectGroupId);
}
