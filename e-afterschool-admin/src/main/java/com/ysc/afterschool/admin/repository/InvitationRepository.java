package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.Invitation;

public interface InvitationRepository extends DefaultRepository<Invitation, Integer> {

	List<Invitation> findByCityId(int cityId);

	List<Invitation> findByCityIdOrderByCreateDateDesc(int cityId);

	List<Invitation> OrderByCreateDateDesc();
	
}
