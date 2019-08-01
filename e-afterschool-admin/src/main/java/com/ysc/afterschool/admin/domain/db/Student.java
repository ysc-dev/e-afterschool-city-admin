package com.ysc.afterschool.admin.domain.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ysc.afterschool.admin.domain.AbstractDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 학생 관리 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_student")
@Data
@EqualsAndHashCode(callSuper = false)
public class Student extends AbstractDomain {

	/** 이름 */
	@Column(nullable = false, length = 20)
	private String name;
	
	/** 소속(학교 명) */
	@Column(nullable = false, length = 45)
	private String school;
	
	/** 학년 */
	private int grade;
	
	/** 학급(반) */
	private int classType;
	
	/** 번호 */
	private int number;
	
	/** 연락처 */
	@Column(length = 20)
	private String tel;
	
	/** 개인정보제공 동의 */
	private boolean agree;
	
	/** 주민등록번호 */
	@Column(length = 15)
	private String residentNumber;
}
