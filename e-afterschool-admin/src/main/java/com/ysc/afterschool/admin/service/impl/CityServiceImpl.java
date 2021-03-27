package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.DomainParam;
import com.ysc.afterschool.admin.domain.db.City;
import com.ysc.afterschool.admin.repository.CityRepository;
import com.ysc.afterschool.admin.service.CityService;

/**
 * 도시 관리
 * 
 * @author hgko
 *
 */
@Transactional
@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityRepository cityRepository;

	@Transactional(readOnly = true)
	@Override
	public City get(Integer id) {
		return cityRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<City> getList() {
		return cityRepository.findAll();
	}

	@Override
	public boolean regist(City domain) {
		if (isNew(domain)) {
			return cityRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(City domain) {
		if (!isNew(domain)) {
			return cityRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		cityRepository.deleteById(id);
		return true;
	}

	private boolean isNew(City domain) {
		return !cityRepository.existsById(domain.getId());
	}

	@Override
	public List<City> getList(DomainParam param) {
		return null;
	}
}
