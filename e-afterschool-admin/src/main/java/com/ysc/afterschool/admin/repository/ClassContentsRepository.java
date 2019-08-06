package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.ClassContents;

public interface ClassContentsRepository extends DefaultRepository<ClassContents, Integer> {

	void deleteBySubjectId(int subjectId);

	List<ClassContents> findBySubjectId(int subjectId);

}
