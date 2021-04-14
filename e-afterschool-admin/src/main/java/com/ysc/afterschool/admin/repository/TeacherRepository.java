package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.Teacher;

public interface TeacherRepository extends DefaultRepository<Teacher, Integer> {

	List<Teacher> findByNameContaining(String name);

	int deleteByUserId(int userId);

}
