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
 * 과목별 공지사항에 대한 댓글 - 커뮤니티 댓글
 * 
 * @author inspace
 *
 */
@Entity
@Table(name = "tb_comment")
@Data
public class Comment implements Domain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** 공지사항 ID */
	private int subjectNoticeId;
	
	/** 작성자 ID */
	@Column(nullable = false, length = 20)
	private String userId;
	
	/** 작성자 이름 */
	@Column(nullable = false, length = 100)
	private String userName;
	
	@Lob
	@NotNull
	private String content;

	@CreationTimestamp
	private LocalDateTime createDate;
}
