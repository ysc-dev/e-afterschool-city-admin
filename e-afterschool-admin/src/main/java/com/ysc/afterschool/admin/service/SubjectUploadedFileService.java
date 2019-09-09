package com.ysc.afterschool.admin.service;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.SubjectUploadedFile;
import com.ysc.afterschool.admin.domain.param.SearchParam;

public interface SubjectUploadedFileService extends CRUDService<SubjectUploadedFile, SearchParam, Integer> {

	List<SubjectUploadedFile> getList(int classId);

	boolean deleteByFile(int classContentsid);

}
