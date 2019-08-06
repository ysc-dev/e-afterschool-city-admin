package com.ysc.afterschool.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.UploadedFile;
import com.ysc.afterschool.admin.repository.UploadedFileRepository;

@Transactional
@Service
public class UploadedFileService {

	@Autowired
	private UploadedFileRepository uploadedFileRepository;
	
	public boolean regist(UploadedFile domain) {
		if (isNew(domain)) {
			return uploadedFileRepository.save(domain) != null;
		} else {
			return false;
		}	
	}
	
	public boolean delete(int id) {
		uploadedFileRepository.deleteById(id);
		return true;
	}
	
	private boolean isNew(UploadedFile domain) {
		return !uploadedFileRepository.existsById(domain.getId());
	}
}
