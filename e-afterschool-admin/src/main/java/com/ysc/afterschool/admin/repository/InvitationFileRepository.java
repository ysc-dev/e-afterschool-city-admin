package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.InvitationFile;

public interface InvitationFileRepository extends DefaultRepository<InvitationFile, Integer> {

	List<InvitationFile> findByInvitationId(int invitationId);

}
