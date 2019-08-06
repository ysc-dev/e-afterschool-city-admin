package com.ysc.afterschool.admin.domain.db;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.ysc.afterschool.admin.domain.Domain;

import lombok.Data;

/**
 * 횟수별 수업내용 테이블 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_class_contents")
@Data
public class ClassContents implements Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int subjectId;
	
	@Lob
	@NotNull
	private String content;
	
	/** 파일 이름 */
	@Column( length = 100)
	private String fileName;

	/** 파일 데이터 */
	@Column(columnDefinition = "longblob")
	private byte[] file;

	/** 파일 확장자 */
	@Column(length = 100)
	private String contentType;
	
	@CreationTimestamp
	private LocalDateTime createDate;
}
