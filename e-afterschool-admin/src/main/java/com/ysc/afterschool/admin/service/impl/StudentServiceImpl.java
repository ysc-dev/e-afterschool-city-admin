package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.Student;
import com.ysc.afterschool.admin.domain.param.StudentSearchParam;
import com.ysc.afterschool.admin.repository.StudentRepository;
import com.ysc.afterschool.admin.service.StudentService;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student get(Integer id) {
		return studentRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Cacheable("student.list")
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
		studentRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Student> getList(StudentSearchParam param) {
		if (!param.getSchool().isEmpty() && !param.getGrade().equals("0")) {
			return studentRepository.findBySchoolAndGradeAndNameContaining(param.getSchool(), 
					Integer.parseInt(param.getGrade()), param.getName());
		} else if (param.getSchool().isEmpty() && !param.getGrade().equals("0")) {
			return studentRepository.findByGradeAndNameContaining(Integer.parseInt(param.getGrade()), param.getName());
		} else if (!param.getSchool().isEmpty() && param.getGrade().equals("0")) {
			return studentRepository.findBySchoolAndNameContaining(param.getSchool(), param.getName());
		} else {
			return studentRepository.findByNameContaining(param.getName());
		}
	}

	private boolean isNew(Student domain) {
		return !studentRepository.existsById(domain.getId());
	}
}
