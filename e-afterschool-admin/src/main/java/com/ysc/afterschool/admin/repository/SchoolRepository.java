package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.School;
import com.ysc.afterschool.admin.domain.db.School.SchoolType;

public interface SchoolRepository extends DefaultRepository<School, Integer> {
	
	School findByName(String name);

	List<School> findBySchoolType(SchoolType type);

	List<School> findBySchoolTypeAndNameContaining(SchoolType type, String name);

	List<School> findByNameContaining(String name);

	List<School> findByCityAndSchoolType(String city, SchoolType type);

	List<School> findByCityAndSchoolTypeAndNameContaining(String city, SchoolType type, String name);

	List<School> findByCityAndNameContaining(String city, String name);

	List<School> findByCity(String city);

}
