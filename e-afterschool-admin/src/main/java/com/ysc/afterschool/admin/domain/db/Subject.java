package com.ysc.afterschool.admin.domain.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ysc.afterschool.admin.domain.AbstractDomain;
import com.ysc.afterschool.admin.domain.db.Student.TargetType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

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
	
	/** 안내장 */
	@OneToOne
    @JoinColumn(name = "invitation_id")
	private Invitation invitation;
	
	/** 강사 */
	@OneToOne
    @JoinColumn(name = "teacher_id")
	private Teacher teacher;
	
	/** 과목 그룹 */
	@OneToOne
    @JoinColumn(name = "subject_group_id")
	private SubjectGroup subjectGroup;
	
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
	@Column(length = 255)
	private String location;
	
	/** 재료비 및 교구비 부가적인 설명 */
	@Column(length = 45)
	private String cost;
	
	/** 정원 */
	private int fixedNumber;
	
	/** 수강신청 인원 */
	private int applyNumber;
	
	/** 대기 정원 */
	private int waitFixedNumber;
	
	/** 신청 대기 인원 */
	private int waitingNumber;
	
	/** 과목특징 */
	@Column(nullable = false, length = 255)
	private String description;
	
	/** 대상 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TargetType targetType;
	
	/** 학년타입 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private GradeType gradeType;
	
	@Transient
	private String target;
	
	@Getter
	public enum GradeType { 
		NONE("전학년", 0, 0),
		GRADE_1_2("1-2학년", 1, 2),
		GRADE_1_3("1-3학년", 1, 3),
		GRADE_1_4("1-4학년", 1, 4),
		GRADE_3_4("3-4학년", 3, 4),
		GRADE_3_6("3-6학년", 3, 6),
		GRADE_4_6("4-6학년", 4, 6),
		GRADE_5_6("5-6학년", 5, 6),
		초_3_6_중등("초등 3-6학년, 중등", 3, 6);
		
		private String name;
		
		private int min;
		
		private int max;
		
		private GradeType(String name, int min, int max) {
			this.name = name;
			this.min = min;
			this.max = max;
		}
	}
}
