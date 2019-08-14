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
	
	private int subjectId;
	
	@Column(nullable = false, length = 45)
	private String userId;
	
	@Column(nullable = false, length = 100)
	private String userName;
	
	@Lob
	@NotNull
	private String content;
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy = "classContents", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<SubjectUploadedFile> uploadedFiles;
	
	@Transient
	private MultipartFile[] images;
}
