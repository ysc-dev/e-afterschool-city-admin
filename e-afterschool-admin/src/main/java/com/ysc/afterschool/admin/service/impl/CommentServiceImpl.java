package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.Comment;
import com.ysc.afterschool.admin.domain.param.SearchParam;
import com.ysc.afterschool.admin.repository.CommentRepository;
import com.ysc.afterschool.admin.service.CommentService;

@Transactional
@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public Comment get(Integer id) {
		return commentRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Comment> getList() {
		return commentRepository.findAll();
	}

	@Override
	public boolean regist(Comment domain) {
		if (isNew(domain)) {
			return commentRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(Comment domain) {
		if (!isNew(domain)) {
			return commentRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		commentRepository.deleteById(id);
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Comment> getList(SearchParam param) {
		return getList();
	}

	private boolean isNew(Comment domain) {
		return !commentRepository.existsById(domain.getId());
	}

	@Override
	public List<Comment> getList(int subjectNoticeId) {
		return commentRepository.findBySubjectNoticeId(subjectNoticeId);
	}
}
