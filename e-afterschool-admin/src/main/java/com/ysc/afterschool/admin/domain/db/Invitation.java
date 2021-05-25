package com.ysc.afterschool.admin.domain.db;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.web.multipart.MultipartFile;

import com.ysc.afterschool.admin.domain.AbstractDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 안내장 목록 테이블 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_invitation")
@Data
@EqualsAndHashCode(callSuper = false)
public class Invitation extends AbstractDomain {

	/** 안내장명 */
	@Column(nullable = false, length = 255)
	private String name;
	
	/** 신청 마감일 */
	@Column(nullable = false, length = 45)
	private String deadlineDate;
	
	/** 설명 */
	@Column(length = 255)
	private String description;
	
	/** 안내장 타입 */
	@Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
	private InvitationType type;
	
	/** 지역 */
	@OneToOne
    @JoinColumn(name = "city_id")
	private City city;
	
	/** 첨부파일 */
	@OneToMany(mappedBy = "invitation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private List<InvitationFile> uploadedFiles;
	
	/** 추가 모집 일 경우 */
	private boolean addType;
	
	@Transient
	private MultipartFile[] images;
	
	@Transient
	private String typeContent;
	
	@Getter
	public enum InvitationType {
		대기(1, "준비 중입니다.", "대기하기", "수강 신청 전"),
		수강신청(2, "수강신청하기", "시작하기", "수강 신청 중"),
		마감(3, "마감되었습니다.", "마감하기", "수강 신청 마감");
		
		private int id;
		
		private String name;
		
		private String type;
		
		private String content;
		
		private InvitationType(int id, String name, String type, String content) {
			this.id = id;
			this.name = name;
			this.type = type;
			this.content = content;
		}
	}
}
