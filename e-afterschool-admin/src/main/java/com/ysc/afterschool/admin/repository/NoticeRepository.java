package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.Notice;

public interface NoticeRepository extends DefaultRepository<Notice, Integer> {

	List<Notice> findByTitleContainingOrderByCreateDateDesc(String title);

	List<Notice> findByUserNameContainingOrderByCreateDateDesc(String userName);

	List<Notice> findByContentContainingOrderByCreateDateDesc(String content);

	List<Notice> OrderByCreateDateDesc();

}
