package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.Invitation;

public interface InvitationRepository extends DefaultRepository<Invitation, Integer> {

	List<Invitation> findByCityIdOrderByCreateDateDesc(int cityId);

	List<Invitation> OrderByCreateDateDesc();

	List<Invitation> findByCityIdAndAddTypeOrderByCreateDateDesc(int cityId, boolean addType);
	
}
