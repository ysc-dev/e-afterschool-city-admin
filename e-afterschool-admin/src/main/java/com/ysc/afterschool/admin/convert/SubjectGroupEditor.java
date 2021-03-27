package com.ysc.afterschool.admin.convert;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ysc.afterschool.admin.service.SubjectGroupService;

/**
 * 과목 그룹 컬럼 변환
 * 
 * @author hgko
 *
 */
@Component
@Scope("prototype")
public class SubjectGroupEditor extends PropertyEditorSupport {
	
	@Autowired
	private SubjectGroupService subjectGroupService;

	public void setAsText(String text) throws IllegalArgumentException {
		this.setValue(subjectGroupService.get(Integer.parseInt(text)));
	}
}
