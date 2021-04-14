package com.ysc.afterschool.admin.service;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.User;
import com.ysc.afterschool.admin.domain.param.SearchParam;

public interface UserService extends CRUDService<User, SearchParam, Integer> {

	User login(String userId, String password);

	User get(String userId);

	List<User> getList(boolean pending);
}
