package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.SubjectUploadedFile;

public interface SubjectUploadedFileRepository extends DefaultRepository<SubjectUploadedFile, Integer> {

	List<SubjectUploadedFile> findByClassContentsId(int classId);

	void deleteByClassContentsId(int classContentsid);

}
