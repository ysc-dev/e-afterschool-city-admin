package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.Student;

public interface StudentRepository extends DefaultRepository<Student, Integer> {

	List<Student> findBySchoolAndGradeAndNameContaining(String school, int parseInt, String name);

	List<Student> findByGradeAndNameContaining(int parseInt, String name);

	List<Student> findBySchoolAndNameContaining(String school, String name);

	List<Student> findByNameContaining(String name);

}
