package com.ysc.afterschool.admin.repository;

import com.ysc.afterschool.admin.domain.db.User;

public interface UserRepository extends DefaultRepository<User, Integer> {

	User findByUserIdAndPassword(String userId, String password);

}
