package com.ysc.afterschool.admin.service;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.InvitationFile;
import com.ysc.afterschool.admin.domain.param.SearchParam;

public interface InvitationFileService extends CRUDService<InvitationFile, SearchParam, Integer> {

	List<InvitationFile> getList(int invitationId);
	
	boolean delete(List<InvitationFile> list);
}
