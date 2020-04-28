package com.ysc.afterschool.admin.service;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.Invitation;
import com.ysc.afterschool.admin.domain.param.InvitationSearchParam;

public interface InvitationService extends CRUDService<Invitation, InvitationSearchParam, Integer> {

	Invitation registDomain(Invitation domain);

	List<Invitation> getOrderbyList();
}
