package com.ysc.afterschool.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ysc.afterschool.admin.service.common.SmsService;

@Controller
@RequestMapping("sms")
public class SmsController {
	
	@Autowired
	private SmsService smsService;
	
	@GetMapping("send")
	public void send() {
		
	}
}
