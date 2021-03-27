package com.ysc.afterschool.admin.domain.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.ysc.afterschool.admin.domain.AbstractDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 사용자 관리 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_user")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractDomain {

	/** 사용자ID */
	@Column(nullable = false, length = 20)
	private String userId;
	
	/** 사용자명 */
	@Column(nullable = false, length = 100)
	private String name;
	
	/** 사용자비밀번호암호화 */
	@Column(nullable = false, length = 100)
	private String password;
	
	/** 이메일 */
	@Column(length = 45)
	private String email;
	
	/** 연락처 */
	@Column(length = 20)
	private String tel;
	
	/** 권한 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role;
	
	public enum UserRole {
		ADMIN, GUEST, TEACHER;
	}
}
