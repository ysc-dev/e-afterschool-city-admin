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
	
	@Column(nullable = false, length = 20)
	private String name;
	
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
		this.name = deleteType.getName();
	}
	
	@Getter
	public enum DeleteType {
		School("학교"),
		Student("학생"),
		Teacher("강사"),
		Subject("과목"),
		SubjectNotice("과목공지사항"),
		Apply("수강신청"),
		Notice("공지사항"),
		Invitation("안내장"),
		ClassContents("수업관리");
		
		private String name;
		
		private DeleteType(String name) {
			this.name = name;
		}
	}
}
