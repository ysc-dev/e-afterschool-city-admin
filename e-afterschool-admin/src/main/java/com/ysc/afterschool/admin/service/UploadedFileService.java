package com.ysc.afterschool.admin.service;

import java.util.List;

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
	
	public boolean delete(List<UploadedFile> files) {
		uploadedFileRepository.deleteInBatch(files);
		return true;
	}
	
	private boolean isNew(UploadedFile domain) {
		return !uploadedFileRepository.existsById(domain.getId());
	}
}
