package com.ysc.afterschool.admin.domain.db;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import lombok.Getter;

/**
 * 과목 관련 첨부파일 관리 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_subject_uploaded_file")
@Data
public class SubjectUploadedFile implements Domain {

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
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private FileType fileType;
	
	@ManyToOne
	@JoinColumn(name = "class_contents_id")
    @JsonIgnore
    private ClassContents classContents;
	
	@Getter
	public enum FileType {
		IMAGE("image"),
		VIDEO("video");
		
		private String name;
		
		private FileType(String name) {
			this.name = name;
		}
		
		public static FileType stringToType(String value) {
			for (FileType type : FileType.values()) {
				if (value.contains(type.name)) {
					return type;
				}
			}
			
			return null;
		}
	}
}
