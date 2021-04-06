package com.ysc.afterschool.admin.service;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.ApplyWait;
import com.ysc.afterschool.admin.domain.param.ApplySearchParam;

public interface ApplyWaitService extends CRUDService<ApplyWait, ApplySearchParam, Integer> {

	List<ApplyWait> getList(int subjectId);

}
