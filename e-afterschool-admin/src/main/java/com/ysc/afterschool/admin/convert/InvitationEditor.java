package com.ysc.afterschool.admin.convert;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ysc.afterschool.admin.service.InvitationService;

@Component
@Scope("prototype")
public class InvitationEditor extends PropertyEditorSupport {
	
	@Autowired
	private InvitationService invitationService;

	public void setAsText(String text) throws IllegalArgumentException {
		System.err.println("Invitation Id : " + text);
		this.setValue(invitationService.get(Integer.parseInt(text)));
	}
}
