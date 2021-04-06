package com.ysc.afterschool.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.Apply;
import com.ysc.afterschool.admin.domain.db.Student;
import com.ysc.afterschool.admin.domain.param.ApplySearchParam;
import com.ysc.afterschool.admin.domain.param.StudentSearchParam;
import com.ysc.afterschool.admin.repository.ApplyRepository;
import com.ysc.afterschool.admin.service.ApplyService;
import com.ysc.afterschool.admin.service.StudentService;

/**
 * 수강 신청 관리
 * 
 * @author hgko
 *
 */
@Transactional
@Service
public class ApplyServiceImpl implements ApplyService {
	
	@Autowired
	private ApplyRepository applyRepository;
	
	@Autowired
	private StudentService studentService;

	@Transactional(readOnly = true)
	@Override
	public Apply get(Integer id) {
		return applyRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Apply> getList() {
		return applyRepository.findAll();
	}

	@Override
	public boolean regist(Apply domain) {
		if (isNew(domain)) {
			return applyRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(Apply domain) {
		if (!isNew(domain)) {
			return applyRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		applyRepository.deleteById(id);
		return true;
	}
	
	@Override
	public boolean delete(List<Apply> applies) {
		applyRepository.deleteInBatch(applies);
		return true;
	}
	
	private boolean isNew(Apply domain) {
		return !applyRepository.existsById(domain.getId());
	}

	@Transactional(readOnly = true)
	@Override
	public List<Apply> getList(ApplySearchParam param) {
		String subjectId = param.getSubjectId();
		String school = param.getSchool();
		String grade = param.getGrade();
		
		List<Apply> applies = null;
		if (!subjectId.isEmpty()) {
			applies = applyRepository.findBySubjectId(Integer.parseInt(subjectId));
		} else {
			applies = applyRepository.findByInvitationId(param.getInvitationId());
		}
		
		if (!school.isEmpty() && !grade.equals("0")) {
			return applies.stream()
					.filter(data -> {return data.getStudent().getSchool().equals(param.getSchool()) 
							&& data.getStudent().getGrade() == Integer.parseInt(param.getGrade());})
					.collect(Collectors.toList());
		} else if (!school.isEmpty() && grade.equals("0")) {
			return applies.stream()
					.filter(data -> {return data.getStudent().getSchool().equals(param.getSchool());})
					.collect(Collectors.toList());
		} else if (school.isEmpty() && !grade.equals("0")) {
			return applies.stream()
					.filter(data -> {return data.getStudent().getGrade() == Integer.parseInt(param.getGrade());})
					.collect(Collectors.toList());
		} else {
			return applies;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<Apply> getList(int studentId) {
		return applyRepository.findByStudentId(studentId);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Apply> getListFromStudent(StudentSearchParam param) {
		List<Apply> applies = new ArrayList<>();
		if (!param.getName().isEmpty()) {
			for (Student student : studentService.get(param.getName())) {
				for (Apply apply : applyRepository.findByStudentId(student.getId())) {
					applies.add(apply);
				}
			}
		}
		return applies;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Apply> getListFromSubject(int subjectId) {
		return applyRepository.findBySubjectId(subjectId);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Apply> getList(int infoId, int studentId) {
		return applyRepository.findByInvitationIdAndStudentId(infoId, studentId);
	}
}
