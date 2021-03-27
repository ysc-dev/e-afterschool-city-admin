package com.ysc.afterschool.admin.domain.db;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.web.multipart.MultipartFile;

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

	/** 제목 */
	@NotNull
	private String title;

	/** 내용 */
	@Lob
	@NotNull
	private String content;
	
	/** 조회수 */
	private int hit;
	
	/** 공지사항일 경우 또는 중요할 공지일 경우 */
	private boolean important;
	
	/** 작성자 ID */
	private int userId;
	
	/** 작성자 이름 */
	@Column(nullable = false, length = 100)
	private String userName;
	
	/** 과목 ID */
	private int subjectId;
	
	/** 댓글 */
	@OneToMany(mappedBy = "subjectNotice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<Comment> comments;
	
	/** 첨부파일 */
	@OneToMany(mappedBy = "subjectNotice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<SubjectNoticeFile> uploadedFiles;
	
	@Transient
	private MultipartFile[] files;
}
