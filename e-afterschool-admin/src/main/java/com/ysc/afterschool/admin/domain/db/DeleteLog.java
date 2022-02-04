package com.ysc.afterschool.admin.domain.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ysc.afterschool.admin.domain.Domain;

import lombok.Data;
import lombok.Getter;

/**
 * 삭제 로그
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_delete_log")
@Data
public class DeleteLog implements Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int deleteId;
	
	@Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
	private DeleteType deleteType;
	
	@Getter
	public enum DeleteType {
		학교, 학생, 강사, 과목, 수강신청
	}
}
