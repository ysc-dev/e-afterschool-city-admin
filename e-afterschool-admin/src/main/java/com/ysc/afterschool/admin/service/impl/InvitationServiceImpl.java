package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.DeleteLog;
import com.ysc.afterschool.admin.domain.db.DeleteLog.DeleteType;
import com.ysc.afterschool.admin.domain.db.Invitation;
import com.ysc.afterschool.admin.domain.param.InvitationSearchParam;
import com.ysc.afterschool.admin.repository.InvitationRepository;
import com.ysc.afterschool.admin.service.DeleteLogService;
import com.ysc.afterschool.admin.service.InvitationService;

/**
 * 안내장 관리 서비스
 * 
 * @author hgko
 *
 */
@Transactional
@Service
public class InvitationServiceImpl implements InvitationService {

	@Autowired
	private InvitationRepository invitationRepository;
	
	@Autowired
	private DeleteLogService deleteLogService;

	@Transactional(readOnly = true)
	@Override
	public Invitation get(Integer id) {
		return invitationRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Invitation> getList() {
		return invitationRepository.OrderByCreateDateDesc();
	}

	@Override
	public boolean regist(Invitation domain) {
		if (isNew(domain)) {
			return invitationRepository.save(domain) != null;
		} else {
			return false;
		}
	}

	@Override
	public boolean update(Invitation domain) {
		if (!isNew(domain)) {
			return invitationRepository.save(domain) != null;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(Integer id) {
		invitationRepository.deleteById(id);
		deleteLogService.regist(new DeleteLog(id, DeleteType.Invitation));
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Invitation> getList(InvitationSearchParam param) {
		if (param.getCityId() != 0) {
			return invitationRepository.findByCityIdOrderByCreateDateDesc(param.getCityId());
		}

		return getList();
	}

	private boolean isNew(Invitation domain) {
		return !invitationRepository.existsById(domain.getId());
	}

	@Override
	public Invitation registDomain(Invitation domain) {
		return invitationRepository.save(domain);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Invitation> getOrderbyList() {
		return invitationRepository.findAll(sortByIdDesc());
	}

	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "id");
	}

	@Transactional(readOnly = true)
	@Override
	public List<Invitation> getList(int cityId) {
		return invitationRepository.findByCityIdOrderByCreateDateDesc(cityId);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Invitation> getList(int cityId, boolean addType) {
		return invitationRepository.findByCityIdAndAddTypeOrderByCreateDateDesc(cityId, addType);
	}
}
