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
	
	@Column(length = 45)
	private String tableName;
	
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
		this.tableName = deleteType.getTableName();
	}
	
	@Getter
	public enum DeleteType {
		School("학교", "tb_school"),
		Student("학생", "tb_student"),
		Teacher("강사", "tb_teacher"),
		Subject("과목", "tb_subject"),
		SubjectNotice("과목공지사항", "tb_subject_notice"),
		Apply("수강신청", "tb_apply"),
		Notice("공지사항", "tb_notice"),
		Invitation("안내장", "tb_invitation"),
		ClassContents("수업관리", "tb_class_contents"),
		SubjectGroup("과목그룹", "tb_subject_group");
		
		private String name;
		
		private String tableName;
		
		private DeleteType(String name, String tableName) {
			this.name = name;
			this.tableName = tableName;
		}
	}
}
