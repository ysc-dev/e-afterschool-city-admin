package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.School;
import com.ysc.afterschool.admin.domain.db.School.SchoolType;

public interface SchoolRepository extends DefaultRepository<School, Integer> {

	List<School> findBySchoolType(SchoolType type);

}
