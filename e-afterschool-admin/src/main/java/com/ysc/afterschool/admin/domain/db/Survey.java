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

/**
 * 만족도 조사 및 설문조사 테이블
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_survey")
@Data
public class Survey implements Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int cityId;
	
	private int subjectId;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ScoreType value1;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ScoreType value2;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ScoreType value3;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ScoreType value4;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ScoreType value5;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ScoreType value6;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ScoreType value7;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ScoreType value8;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ScoreType value9;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ScoreType value10;
	
	/** 총 점수 */
	private int totalScore;
	
	/** 등록시간 */
	@CreationTimestamp
	private LocalDateTime createDate;
	
	@Getter
	public enum ScoreType {
		SCORE10(10, "매우만족"),
		SCORE8(8, "만족"),
		SCORE6(6, "보통"),
		SCORE4(4, "불만"),
		SCORE0(0, "매우불만");
		
		private int score;
		
		private String name;
		
		private ScoreType(int score, String name) {
			this.score = score;
			this.name = name;
		}
	}
}
