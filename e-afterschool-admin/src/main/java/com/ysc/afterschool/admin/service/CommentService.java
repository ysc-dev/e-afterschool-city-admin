package com.ysc.afterschool.admin.service;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.Comment;
import com.ysc.afterschool.admin.domain.param.SearchParam;

public interface CommentService extends CRUDService<Comment, SearchParam, Integer> {

	List<Comment> getList(int subjectNoticeId);

}
