package com.junu.spring.daoexam.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.junu.spring.daoexam.config.ApplicationConfig;
import com.junu.spring.daoexam.dao.RoleDao;
import com.junu.spring.daoexam.dto.Role;

public class JDBCTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		RoleDao roleDao = ac.getBean(RoleDao.class);
		
		/*	
		Role role = new Role(); //db에 넣어줄 데이터를 만들기 위해 우선 role 객체 생성
		role.setRoleId(500);
		role.setDescription("manager2");
		
		int count = roleDao.insert(role); //insert 메소드 수행 -> insert 쿼리문 수행
		System.out.println(count+"개의 데이터 입력");
		
		 */
		
		Role role = new Role();
		role.setRoleId(101);
		role.setDescription("newbie");
		int count = roleDao.update(role);
		System.out.println(count+"개의 데이터 수정");
		
		role = roleDao.selectById(101);
		System.out.println(role); //조회값 확인
		
		count = roleDao.deleteById(101);
		System.out.println(count+"개의 데이터 삭제");
		
		role = roleDao.selectById(101);
		System.out.println(role); //조회값 확인
	}
}
