package com.ysc.afterschool.admin.domain.db;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.ysc.afterschool.admin.domain.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 삭제 로그
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_delete_log")
@Data
@NoArgsConstructor
public class DeleteLog implements Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int deleteId;
	
	@Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
	private DeleteType deleteType;
	
	/** 생성일시 */
	@CreationTimestamp
	private LocalDateTime createDate;
	
	/**
	 * 생성자
	 * @param deleteId
	 * @param deleteType
	 */
	public DeleteLog(int deleteId, DeleteType deleteType) {
		this.deleteId = deleteId;
		this.deleteType = deleteType;
	}
	
	@Getter
	public enum DeleteType {
		School, 
		Student, 
		Teacher, 
		Subject, 
		SubjectNotice, 
		Apply, 
		Notice, 
		Invitation,
		ClassContents
	}
}
