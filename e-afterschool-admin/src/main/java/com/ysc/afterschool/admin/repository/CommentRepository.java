package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.Comment;

public interface CommentRepository extends DefaultRepository<Comment, Integer> {

	List<Comment> findBySubjectNoticeId(int subjectNoticeId);

}
