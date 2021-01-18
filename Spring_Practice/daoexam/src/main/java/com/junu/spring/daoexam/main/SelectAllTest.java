package com.junu.spring.daoexam.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.junu.spring.daoexam.config.ApplicationConfig;
import com.junu.spring.daoexam.dao.RoleDao;
import com.junu.spring.daoexam.dto.Role;

public class SelectAllTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		RoleDao roleDao = ac.getBean(RoleDao.class);
		
		List<Role> list = roleDao.selectAll();
		for(Role role : list) {
			System.out.println(role);
		}
	}

}
