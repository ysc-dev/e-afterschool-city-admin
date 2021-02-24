package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.Student;

public interface StudentRepository extends DefaultRepository<Student, Integer> {

	List<Student> findBySchoolAndGradeAndNameContaining(String school, int parseInt, String name);

	List<Student> findByGradeAndNameContaining(int parseInt, String name);

	List<Student> findBySchoolAndNameContaining(String school, String name);

	List<Student> findByNameContaining(String name);

	List<Student> findByCityAndSchoolAndGradeAndNameContaining(String city, String school, int parseInt, String name);

	List<Student> findByCityAndGradeAndNameContaining(String city, int parseInt, String name);

	List<Student> findByCityAndSchoolAndNameContaining(String city, String school, String name);

	List<Student> findByCityAndNameContaining(String city, String name);

	List<Student> findByName(String name);


}
