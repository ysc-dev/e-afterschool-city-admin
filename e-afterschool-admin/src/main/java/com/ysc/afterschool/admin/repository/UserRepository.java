package com.ysc.afterschool.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ysc.afterschool.admin.domain.db.User;

public interface UserRepository extends DefaultRepository<User, Integer> {

	User findByUserIdAndPassword(String userId, String password);

	User findByUserId(String userId);

	@Query("SELECT e FROM User e WHERE e.pending = ?1")
	List<User> findByPending(boolean pending);

}
