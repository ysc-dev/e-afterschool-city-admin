package com.ysc.afterschool.admin.domain.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.ysc.afterschool.admin.domain.AbstractDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 과목별 공지사항 테이블 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_subject_notice")
@Data
@EqualsAndHashCode(callSuper = false)
public class SubjectNotice extends AbstractDomain {

	@NotNull
	private String title;

	@Lob
	@NotNull
	private String content;
	
	private int hit;
	
	/** 공지사항일 경우 또는 중요할 공지일 경우 */
	private boolean important;
	
	/** 작성자 ID */
	@Column(nullable = false, length = 20)
	private String userId;
	
	/** 작성자 이름 */
	@Column(nullable = false, length = 100)
	private String userName;
	
	private int subjectId;
}
