package com.ysc.afterschool.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.Survey;
import com.ysc.afterschool.admin.domain.param.SurveySearchParam;
import com.ysc.afterschool.admin.repository.SurveyRepository;
import com.ysc.afterschool.admin.service.SurveyService;

/**
 * 만족도 및 설문조사 관리
 * 
 * @author hgko
 *
 */
@Transactional
@Service
public class SurveyServiceImpl implements SurveyService {

	@Autowired
	private SurveyRepository surveyRepository;
	
	@Transactional(readOnly = true)
	@Override
	public Survey get(Long id) {
		return surveyRepository.findById(id).get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Survey> getList() {
		return surveyRepository.findAll();
	}

	@Override
	public boolean regist(Survey domain) {
		if (isNew(domain)) {
			return surveyRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(Survey domain) {
		if (!isNew(domain)) {
			return surveyRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Long id) {
		surveyRepository.deleteById(id);
		return true;
	}

	private boolean isNew(Survey domain) {
		return !surveyRepository.existsById(domain.getId());
	}

	@Transactional(readOnly = true)
	@Override
	public List<Survey> getList(SurveySearchParam param) {
		int subjectId = param.getSubjectId();
		if (subjectId == 0) {
			return surveyRepository.findByCityIdAndSurveyType(param.getCityId(), param.getSurveyType());
		} else if (subjectId == -1) {
			return new ArrayList<Survey>();
		}
		
		return surveyRepository.findBySubjectIdAndSurveyType(subjectId, param.getSurveyType());
	}
}
