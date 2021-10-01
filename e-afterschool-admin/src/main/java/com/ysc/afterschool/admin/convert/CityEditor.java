package com.ysc.afterschool.admin.convert;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ysc.afterschool.admin.repository.CityRepository;

/**
 * 도시 컬럼 변환
 * 
 * @author hgko
 *
 */
@Component
@Scope("prototype")
public class CityEditor extends PropertyEditorSupport {

	@Autowired
	private CityRepository cityRepository;

	public void setAsText(String text) throws IllegalArgumentException {
		this.setValue(cityRepository.findById(Integer.parseInt(text)).get());
	}
}
