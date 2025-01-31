package com.sp.app.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
  - 컬럼명이 user_name 처럼 _ 가 있는 컬럼을 findBy 메소드로 검색하면 
     findBy테이블명_컬럼명() 으로 인식한다.
  - 따라서 컬럼명에 _ 가 있는 경우  findBy 메소드로 검색이 힘들다.
*/

@Entity
@Table(name="bbs")
@Getter
@Setter
@NoArgsConstructor
public class Board implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="num", columnDefinition="NUMBER")
	@SequenceGenerator(name="S_MY_SEQ", sequenceName="bbs_seq", allocationSize = 1)	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="S_MY_SEQ")
	private long num;
	
	@Column(name="name", nullable=false, length=30)
	private String name;
	
	@Column(name="pwd", nullable=false, length=50)
	private String pwd;
	
	@Column(name="subject", nullable=false, length=500)
	private String subject;
	
	@Column(name="content", nullable=false, length=4000)
	private String content;
	
	// 필드명을  ipAddr 로 컬럼명을 설정하면 ip_addr 로 컬럼이 만들어진다.
	// 이런 경우 @Column(name="ipaddr") 로  설정하면 문제를 해결할수 있다.
        // @Column(name="ipAddr") 로 컬럼명을 설정하면 ip_addr 로 컬럼이 만들어 진다.
	@Column(name="ipaddr", nullable=false, length=50, 
			updatable = false)
	private String ipAddr;
	
	// LocalDateTime 타입을 사용하는 경우 'yyyy-MM-ddTHH:mm:ss' 형식으로 출력
	@Column(nullable=false, columnDefinition = "DATE DEFAULT SYSDATE", 
			insertable = false, updatable = false)
	private String reg_date;
	
/*
	@Column(nullable=false, columnDefinition = "NUMBER",
			insertable = false, updatable = false)
	@ColumnDefault("0")
	private int hitCount;
*/

	@Column(name="hitcount", nullable=false, columnDefinition = "NUMBER DEFAULT 0", 
			insertable = false, updatable = false) // INSERT, UPDATE 제외
	private int hitCount;

	// @PreUpdate : UPDATE 전 호출
	/*
	@PrePersist // INSERT 전 호출
    public void prePersist() {
    	// reg_date 에서 insertable = false 옵션을 사용하지 않는 경우
		// String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // 시분초가 있으면 ORA-01861
		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.reg_date = this.reg_date == null ? now : this.reg_date;
    }
    */
}
