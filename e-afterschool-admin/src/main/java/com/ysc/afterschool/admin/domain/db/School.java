package com.ysc.afterschool.admin.domain.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.ysc.afterschool.admin.domain.AbstractDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 학교 관리 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_school")
@Data
@EqualsAndHashCode(callSuper = false)
public class School extends AbstractDomain {

	/** 이름 */
	@Column(nullable = false, length = 20)
	private String name;
	
	/** 학생 수 */
	private int number;
	
	/** 학교타입 */
	@Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
	private SchoolType schoolType;
	
	/** 도시 */
	@Column(length = 45)
	private String city;
	
	@Getter
	public enum SchoolType {
		초등학교("초등학교"),
		중학교("중학교");
		
		private String name;
		
		private SchoolType(String name) {
			this.name = name;
		}
	}
}
