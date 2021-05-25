package com.company.Spring_Annotation_Board.user;

/**
 * DO/DAO 패턴
 *	=> DO(Data Object) 클래스 
 *		데이터베이스의 데이터(즉 테이블의 행)을 객체화하는 클래스 구조이다
 *		 즉 데이터를 하나의 객체로 관리할 목적으로 만들어 둔 클래스 객체이다
 *		DO 클래스에는 필드, 각 필드당 getter/setter 매서드 포함
 */		

public class UserDO {
	//필드 (property, 중간저장소)
	private String id;			//사용자 ID
	private String password;	//패스워드
	private String pwencrypt;	//암호화
	private String name;		//이름
	private String role;		//역할
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPwencrypt() {
		return pwencrypt;
	}
	public void setPwencrypt(String pwencrypt) {
		this.pwencrypt = pwencrypt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
