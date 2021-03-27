package com.ysc.afterschool.admin.domain.db;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ysc.afterschool.admin.domain.Domain;

import lombok.Data;

/**
 * 첨부파일 관리 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_uploaded_file")
@Data
public class UploadedFile implements Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** 파일 이름 */
	@Column(nullable = false, length = 100)
	private String fileName;

	/** 파일 데이터 */
	@Column(columnDefinition = "longblob")
	private byte[] content;

	/** 파일 확장자 */
	@Column(nullable = false, length = 100)
	private String contentType;
	
	/** 생성일시 */
	@CreationTimestamp
	private LocalDateTime createDate;
	
	/** 공지사항 */
	@ManyToOne
	@JoinColumn(name = "notice_id")
    @JsonIgnore
    private Notice notice;
}
