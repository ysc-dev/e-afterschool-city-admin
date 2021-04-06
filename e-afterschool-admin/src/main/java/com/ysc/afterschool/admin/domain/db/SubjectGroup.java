package com.ysc.afterschool.admin.domain.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ysc.afterschool.admin.domain.AbstractDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 수강 과목 그룹 관리 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_subject_group")
@Data
@EqualsAndHashCode(callSuper = false)
public class SubjectGroup extends AbstractDomain {

	/** 이름 */
	@Column(nullable = false, length = 20)
	private String name;
	
	/** 설명 */
	@Column(nullable = false, length = 255)
	private String description;
	
	/** 순서 */
	private int sequence;
}
