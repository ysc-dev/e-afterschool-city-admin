package com.ysc.afterschool.admin.domain;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

/**
 * 공통 도메인
 * 
 * @author hgko
 *
 */
@MappedSuperclass
@Data
public abstract class AbstractDomain implements Domain {

	/** ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	
	/** 생성일시 */
	@CreationTimestamp
	private LocalDateTime createDate;
	
	/** 수정일시 */
	@UpdateTimestamp
	private LocalDateTime updateDate;
}
