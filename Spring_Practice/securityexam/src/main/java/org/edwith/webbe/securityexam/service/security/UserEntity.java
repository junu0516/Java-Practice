package org.edwith.webbe.securityexam.service.security;

/*로그인 아이디와 암호 정보를 가짐*/
public class UserEntity {
	private String loginUserId;
	private String password;
	
	public UserEntity() {}

	public UserEntity(String loginUserId, String password) {
		super();
		this.loginUserId = loginUserId;
		this.password = password;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserEntity [loginUserId=" + loginUserId + ", password=" + password + "]";
	}
	
}
