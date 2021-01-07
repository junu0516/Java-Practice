package com.kh.common.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper {
	 /*
		Hash는 단방향 암호화 기법이고 Encryption은 양방향 암호화 기법이다.

		쉽게 설명하면 Hash는 평문을 암호화된 문장(텍스트)으로 만들어주는 기능을 하고,

		Encryption은 평문을 암호화된 문장(텍스트)로 만들어주는 기능을 하고 + 암호화된 문장을 다시 평문으로 만드는 복호화 기능도 한다숫자가 클 수록 Hash값이 복잡해지므로, 더 안전한 암호화 방법이 된다.

		예전에는 MD5, SHA1 등 16byte크기의 Hash 값을 사용했지만, 요즘은 SHA512 등 Hash값 크기가 큰 알고리즘을 추천한다.
	​
		비밀번호 등 일치 여부만을 확인하기 위해서 (복호화 불필요) 사용한다.
	 */

	public EncryptWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getParameter(String key) {
		String value = "";
		if(key != null && (key.equals("userPwd")||key.equals("newPwd"))) { //request에 담긴 매개변수 key가 userPwd, newPwd인 경우에 대해 적용
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-512"); //암호화할 객체 생성
				byte[] bytes = super.getParameter(key).getBytes((Charset.forName("UTF-8"))); //전달받은 비밀번호를 바이트 배열로 변환
				md.update(bytes); //바이트 배열을 md 객체에 넣어서 암호화

				value = Base64.getEncoder().encodeToString(md.digest()); //Base64 인코더(java.util.Base64)를 통해 암호화된 바이트 배열을 다시 문자열로 변환
				
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}else {
			value = super.getParameter(key);
		}
		return value;		
	}
}
