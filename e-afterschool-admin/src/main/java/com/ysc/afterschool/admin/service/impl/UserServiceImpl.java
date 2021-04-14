package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.User;
import com.ysc.afterschool.admin.domain.param.SearchParam;
import com.ysc.afterschool.admin.repository.UserRepository;
import com.ysc.afterschool.admin.service.UserService;

/**
 * 사용자 관리 서비스
 * 
 * @author hgko
 *
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	@Override
	public User get(Integer id) {
		return userRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> getList() {
		return userRepository.findAll();
	}
	
	@Override
	public boolean regist(User domain) {
		if (isNew(domain)) {
			return userRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(User domain) {
		if (!isNew(domain)) {
			return userRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		userRepository.deleteById(id);
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> getList(SearchParam param) {
		return getList();
	}

	private boolean isNew(User domain) {
		return !userRepository.existsById(domain.getId());
	}

	@Override
	public User login(String userId, String password) {
		return userRepository.findByUserIdAndPassword(userId, password);
	}
	
	@Transactional(readOnly = true)
	@Override
	public User get(String userId) {
		return userRepository.findByUserId(userId);
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> getList(boolean pending) {
		return userRepository.findByPending(pending);
	}
}
