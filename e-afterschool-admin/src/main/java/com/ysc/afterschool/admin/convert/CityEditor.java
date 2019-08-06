package com.ysc.afterschool.admin.convert;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ysc.afterschool.admin.repository.CityRepository;

@Component
@Scope("prototype")
public class CityEditor extends PropertyEditorSupport {
	
	@Autowired
	private CityRepository cityRepository;

	public void setAsText(String text) throws IllegalArgumentException {
		System.err.println("Invitation Id : " + text);
		this.setValue(cityRepository.findById(Integer.parseInt(text)).get());
	}
}
