package com.ysc.afterschool.admin.domain.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_notice")
@Data
@EqualsAndHashCode(callSuper = false)
public class Notice extends AbstractDomain {

	@NotNull
	private String title;

	@Lob
	@NotNull
	private String content;

	@Enumerated(EnumType.STRING)
	private PostStatus status;
	
	private int hit;
	
	@Column(nullable = false, length = 20)
	private String userId;
	
	@Column(nullable = false, length = 100)
	private String userName;
	
	@OneToOne
    @JoinColumn(name = "file_id")
	private UploadedFile uploadedFile;
	
	public enum PostStatus {
		Y, N
	}
}
