package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.DomainParam;
import com.ysc.afterschool.admin.domain.db.ApplyCancel;
import com.ysc.afterschool.admin.repository.ApplyCancelRepository;
import com.ysc.afterschool.admin.service.ApplyCancelService;

@Transactional
@Service
public class ApplyCancelServiceImpl implements ApplyCancelService {

	@Autowired
	private ApplyCancelRepository applyCancelRepository;

	@Transactional(readOnly = true)
	@Override
	public ApplyCancel get(Integer id) {
		return applyCancelRepository.findById(id).get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<ApplyCancel> getList() {
		return applyCancelRepository.findAll();
	}

	@Override
	public boolean regist(ApplyCancel domain) {
		if (isNew(domain)) {
			return applyCancelRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(ApplyCancel domain) {
		if (!isNew(domain)) {
			return applyCancelRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		applyCancelRepository.deleteById(id);
		return true;
	}

	private boolean isNew(ApplyCancel domain) {
		return !applyCancelRepository.existsById(domain.getId());
	}

	@Override
	public List<ApplyCancel> getList(DomainParam param) {
		return null;
	}

}
