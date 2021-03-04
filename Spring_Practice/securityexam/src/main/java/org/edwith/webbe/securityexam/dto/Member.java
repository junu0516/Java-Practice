package org.edwith.webbe.securityexam.dto;

import java.util.Date;

public class Member {
	
	private Long id;
	private String name;
	private String password;
	private String email;
	private Date createDate;
	private Date modifyDate;
	
	public Member() {
		createDate = new Date();
		modifyDate = new Date();
	}

	public Member(Long id, String name, String password, String email, Date createDate, Date modifyDate) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate + "]";
	}
	
}
