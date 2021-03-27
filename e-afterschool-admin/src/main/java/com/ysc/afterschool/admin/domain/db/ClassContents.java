package com.ysc.afterschool.admin.domain.db;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.web.multipart.MultipartFile;

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
	
	/** 과목 ID */
	private int subjectId;
	
	/** 사용자 ID */
	@Column(nullable = false, length = 45)
	private String userId;
	
	/** 사용자이름 */
	@Column(nullable = false, length = 100)
	private String userName;
	
	/** 수업내용 */
	@Lob
	@NotNull
	private String content;
	
	/** 생성일시 */
	@CreationTimestamp
	private LocalDateTime createDate;
	
	/** 첨부파일 */
	@OneToMany(mappedBy = "classContents", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<SubjectUploadedFile> uploadedFiles;
	
	@Transient
	private MultipartFile[] files;
}
