package com.ysc.afterschool.admin.service;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.Student;
import com.ysc.afterschool.admin.domain.param.StudentSearchParam;

public interface StudentService extends CRUDService<Student, StudentSearchParam, Integer> {

	List<Student> get(String name);
}
