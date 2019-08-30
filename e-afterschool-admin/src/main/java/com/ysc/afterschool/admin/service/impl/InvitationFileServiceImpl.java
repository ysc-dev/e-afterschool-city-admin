package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.InvitationFile;
import com.ysc.afterschool.admin.domain.param.SearchParam;
import com.ysc.afterschool.admin.repository.InvitationFileRepository;
import com.ysc.afterschool.admin.service.InvitationFileService;

@Transactional
@Service
public class InvitationFileServiceImpl implements InvitationFileService {
	
	@Autowired
	private InvitationFileRepository invitationFileRepository;

	@Override
	public InvitationFile get(Integer id) {
		return invitationFileRepository.findById(id).get();
	}

	@Override
	public List<InvitationFile> getList() {
		return invitationFileRepository.findAll();
	}

	@Override
	public boolean regist(InvitationFile domain) {
		if (isNew(domain)) {
			return invitationFileRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(InvitationFile domain) {
		if (!isNew(domain)) {
			return invitationFileRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		invitationFileRepository.deleteById(id);
		return true;
	}

	private boolean isNew(InvitationFile domain) {
		return !invitationFileRepository.existsById(domain.getId());
	}

	@Override
	public List<InvitationFile> getList(SearchParam param) {
		return null;
	}

	@Override
	public List<InvitationFile> getList(int invitationId) {
		return invitationFileRepository.findByInvitationId(invitationId);
	}

	@Override
	public boolean delete(List<InvitationFile> list) {
		invitationFileRepository.deleteInBatch(list);
		return true;
	}
}
