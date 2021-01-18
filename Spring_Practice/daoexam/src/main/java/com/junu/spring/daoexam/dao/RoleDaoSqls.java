package com.junu.spring.daoexam.dao;

public class RoleDaoSqls {
	public static final String SELECT_ALL = "SELECT role_id, description FROM role order by role_id"; //상수로 지정
	public static final String UPDATE = "UPDATE role SET description = :description where role_id = :roleId";
	public static final String DELETE_BY_ROLE_ID = "DELETE FROM role WHERE role_id = :roleId";
	public static final String SELECT_BY_ROLE_ID = "SELECT role_id, description FROM role WHERE role_id = :roleId";
}
