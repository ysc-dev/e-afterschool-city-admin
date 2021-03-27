package com.ysc.afterschool.admin.domain.db;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ysc.afterschool.admin.domain.Domain;

import lombok.Data;

/**
 * 과목별 공지사항에 대한 댓글 - 커뮤니티 댓글
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_comment")
@Data
public class Comment implements Domain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** 과목별 공지사항 */
	@ManyToOne
	@JoinColumn(name = "subject_notice_id")
    @JsonIgnore
    private SubjectNotice subjectNotice;
	
	/** 작성자 ID */
	private int userId;
	
	/** 작성자 이름 */
	@Column(nullable = false, length = 100)
	private String userName;
	
	/** 댓글내용 */
	@Lob
	@NotNull
	private String content;

	/** 생성일시 */
	@CreationTimestamp
	private LocalDateTime createDate;
}
