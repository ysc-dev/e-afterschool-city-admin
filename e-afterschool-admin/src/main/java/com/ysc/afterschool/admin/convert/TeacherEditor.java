package com.ysc.afterschool.admin.convert;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ysc.afterschool.admin.service.TeacherService;

/**
 * 강사 컬럼 변환
 * 
 * @author hgko
 *
 */
@Component
@Scope("prototype")
public class TeacherEditor extends PropertyEditorSupport {

	@Autowired
	private TeacherService teacherService;

	public void setAsText(String text) throws IllegalArgumentException {
		this.setValue(teacherService.get(Integer.parseInt(text)));
	}
}
