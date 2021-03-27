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
import com.ysc.afterschool.admin.domain.CommonFile;
import com.ysc.afterschool.admin.domain.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 과목 관련 첨부파일 관리 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_invitation_file")
@Data
@NoArgsConstructor
public class InvitationFile implements Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** 파일 이름 */
	@Column(nullable = false, length = 100)
	private String fileName;

	/** 파일 확장자 */
	@Column(nullable = false, length = 100)
	private String contentType;
	
	/** 파일 사이즈 */
	private Long size;
	
	/** 생성일시 */
	@CreationTimestamp
	private LocalDateTime createDate;
	
	/** 안내장 */
	@ManyToOne
	@JoinColumn(name = "invitation_id")
    @JsonIgnore
    private Invitation invitation;
	
	public InvitationFile(CommonFile commonFile) {
		this.fileName = commonFile.getFileName();
		this.contentType = commonFile.getContentType();
		this.size = commonFile.getSize();
	}
}
