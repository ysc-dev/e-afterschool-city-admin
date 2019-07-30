package com.ysc.afterschool.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysc.afterschool.admin.domain.db.User;
import com.ysc.afterschool.admin.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User login(String userId, String password) {
		return userRepository.findByUserIdAndPassword(userId, password);
	}
}
