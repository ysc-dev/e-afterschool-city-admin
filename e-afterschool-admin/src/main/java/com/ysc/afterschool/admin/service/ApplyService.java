package com.ysc.afterschool.admin.service;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.Apply;
import com.ysc.afterschool.admin.domain.param.ApplySearchParam;
import com.ysc.afterschool.admin.domain.param.StudentSearchParam;

public interface ApplyService extends CRUDService<Apply, ApplySearchParam, Integer> {

	List<Apply> getList(int studentId);

	boolean delete(List<Apply> applies);

	List<Apply> getListFromStudent(StudentSearchParam param);

	List<Apply> getListFromSubject(int subjectId);
}
