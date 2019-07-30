package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.Notice;

public interface NoticeRepository extends DefaultRepository<Notice, Integer> {

	List<Notice> findByTitleContaining(String title);

	List<Notice> findByUserNameContaining(String userName);

	List<Notice> findByContentContaining(String content);

}
