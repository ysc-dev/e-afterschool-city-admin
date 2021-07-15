package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.ysc.afterschool.admin.domain.db.QStudent;
import com.ysc.afterschool.admin.domain.db.Student;
import com.ysc.afterschool.admin.domain.param.StudentSearchParam;
import com.ysc.afterschool.admin.repository.ApplyCancelRepository;
import com.ysc.afterschool.admin.repository.ApplyWaitRepository;
import com.ysc.afterschool.admin.repository.StudentRepository;
import com.ysc.afterschool.admin.service.StudentService;

/**
 * 학생 관리 서비스
 * 
 * @author hgko
 *
 */
@Transactional
@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ApplyWaitRepository applyWaitRepository;
	
	@Autowired
	private ApplyCancelRepository applyCancelRepository;
	
	@Transactional(readOnly = true)
	@Override
	public Student get(Integer id) {
		return studentRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Student> getList() {
		return studentRepository.findAll();
	}

	@Override
	public boolean regist(Student domain) {
		if (isNew(domain)) {
			return studentRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(Student domain) {
		if (!isNew(domain)) {
			return studentRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		applyWaitRepository.deleteByStudentId(id);
		applyCancelRepository.deleteByStudentId(id);
		studentRepository.deleteById(id);
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Student> getList(StudentSearchParam param) {
		
		QStudent student = QStudent.student;

		BooleanBuilder builder = new BooleanBuilder();

		if (!param.getCity().equals("NONE")) {
			builder.and(student.city.eq(param.getCity()));
		}
		if (!param.getSchool().isEmpty()) {
			builder.and(student.school.eq(param.getSchool()));
		}
		if (!param.getGrade().equals("0")) {
			builder.and(student.grade.eq(Integer.parseInt(param.getGrade())));
		}
		
		builder.and(student.name.contains(param.getName()));
		builder.and(student.tel.contains(param.getTel()));
		
		return (List<Student>) studentRepository.findAll(builder);
	}

	private boolean isNew(Student domain) {
		return !studentRepository.existsById(domain.getId());
	}

	@Transactional(readOnly = true)
	@Override
	public List<Student> get(String name) {
		return studentRepository.findByName(name);
	}
}
