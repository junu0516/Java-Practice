package com.kh.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy{

	@Override
	public File rename(File originalFile) {
		// TODO Auto-generated method stub
		String originalName = originalFile.getName();
		
		//수정명 : 파일 업로드 시간(연월시분초) + 10000~99999 사이의 랜덤값 + 확장자 로 수정
		//aaa.jsp -> 202012150101.jsp와 같은 식으로 이름이 변환되는 것
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); //대소문자 구분 주의
		String currentTime = sdf.format(new Date()); //현재 시간 가져오기
		
		//랜덤값 가져오기
		//(int)(Math.random()*최대값)+최소값 형태
		int randomNum = (int)(Math.random()*90000)+10000;
		
		//확장자 가져오기
		String ext = "";
		int dot = originalName.lastIndexOf(".");
		ext = originalName.substring(dot); //dot 이후의 모든 문자열을 가져옴  -> 확장자
		
		String newFileName = currentTime + randomNum + ext; // 현재시간 + 랜덤넘버 + 확장자
		File newFile = new File(originalFile.getParent(),newFileName); //전달받은 파일을 변경된 파일명으로 파일 객체를 새엇ㅇ해서 리턴
		
		return newFile;
	}
}
