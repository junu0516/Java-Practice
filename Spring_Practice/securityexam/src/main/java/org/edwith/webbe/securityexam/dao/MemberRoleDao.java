package org.edwith.webbe.securityexam.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.edwith.webbe.securityexam.dto.MemberRole;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRoleDao {
	
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<MemberRole> rowMapper = BeanPropertyRowMapper.newInstance(MemberRole.class);
	
	public MemberRoleDao(DataSource dataSource) {
		
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<MemberRole> getRolesByEmail(String email){
		
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);

		return jdbc.query(MemberRoleDaoSqls.SELECT_ALL_BY_EMAIL, map, rowMapper);
	}

	public void addAdminRole(Long memberId) {
		
		Map<String, Object> params = Collections.singletonMap("memberId", memberId);
		jdbc.update(MemberRoleDaoSqls.INSERT_ADMIN_ROLE, params);
	}

	public void addUserRole(Long memberId) {
		
		Map<String, Object> params = Collections.singletonMap("memberId", memberId);
		jdbc.update(MemberRoleDaoSqls.INSERT_USER_ROLE, params);
	}	
	
}
