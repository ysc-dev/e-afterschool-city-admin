package com.ysc.afterschool.admin.domain.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ysc.afterschool.admin.domain.AbstractDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 수강 과목 관리 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_subject")
@Data
@EqualsAndHashCode(callSuper = false)
public class Subject extends AbstractDomain {

	/** 이름 */
	@Column(nullable = false, length = 255)
	private String name;
	
	/** 강사 */
	@OneToOne
    @JoinColumn(name = "teacher_id")
	private Teacher teacher;
	
	/** 과목 그룹 */
	private int subjectGroupId;
	
	/** 수강기간 */
	@Column(nullable = false, length = 255)
	private String period;
	
	/** 요일 */
	@Column(nullable = false, length = 45)
	private String week;
	
	/** 운영시간 */
	@Column(nullable = false, length = 45)
	private String time;
	
	/** 장소 */
	@Column(nullable = false, length = 255)
	private String location;
	
	/** 재료비 및 교구비 부가적인 설명 */
	@Column(nullable = false, length = 45)
	private String cost;
	
	/** 정원 */
	private int fixedNumber;
	
	/** 수강신청 인원 */
	private int applyNumber;
	
	/** 수강신청 시작 날짜 */
	@Column(nullable = false, length = 20)
	private String applyStartTime;
	
	/** 수강신청 종료 날짜 */
	@Column(nullable = false, length = 20)
	private String applyEndTime;
	
	/** 과목특징 */
	@Column(nullable = false, length = 255)
	private String description;
}
