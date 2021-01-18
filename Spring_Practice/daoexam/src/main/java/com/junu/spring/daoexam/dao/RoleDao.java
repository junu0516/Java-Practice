package com.junu.spring.daoexam.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.junu.spring.daoexam.dto.Role;
import static com.junu.spring.daoexam.dao.RoleDaoSqls.*;

@Repository //dao 객체에는 repository 어노테이션을 선언(저장소의 역할 수행)
public class RoleDao {
	private NamedParameterJdbcTemplate jdbc; //jdbc 템플릿은 바인딩할 때 ? 사용 -> 매핑이 번거로움... 그래서 이름을 사용해서 바인딩하는 NamedParameterJdbcTemplate 사용
	private SimpleJdbcInsert insertAction; //insert 쿼리문 실행을 위한 객체
	private RowMapper<Role> rowMapper = BeanPropertyRowMapper.newInstance(Role.class);

	public RoleDao(DataSource dataSource) { //dataSource객체를 매개변수로 받음
		//스프링4.3 부터는 컴포넌트 스캔으로 객체를 찾아서, 기본생성자가 없을 경우 자동으로 객체를 주입해줌(Bean 반환)
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("role");//어떤 테이블에 넣을지 매개변수에 명시
	}
	
	public List<Role> selectAll(){
		return jdbc.query(SELECT_ALL, Collections.emptyMap(),rowMapper); //가운데 매개변수로 받은 것은 비어있는 Map 객체(바인딩한 값을 전달할 목적으로 사용)
		//rowMapper : select 하나하나의 결과를 dto에 저장할 목적으로 사용(BeanPropertyRowMapper -> row 값을 담아주는 것)
		
	}
	
	public int insert(Role role) { //insert 쿼리문 실행
		SqlParameterSource params = new BeanPropertySqlParameterSource(role); //SqlParameterSource : 매개변수로 받은 role 객체의 변수를 보고 알아서 db 테이블의 컬럼과 매핑시킴
		return insertAction.execute(params);
	}
	
	public int update(Role role) { //update 쿼리문 실행
		SqlParameterSource params = new BeanPropertySqlParameterSource(role);
		return jdbc.update(UPDATE, params);
	}
	
	public int deleteById(Integer id) { //delete 쿼리문 실행
		Map<String, ?> params = Collections.singletonMap("roleId", id); //delete는 값을 하나만 쓰기 때문에 위처럼 굳이 SqlParameterSource를 사용하기 보다는, singletonMap을 통해 값 하나만 넣어서 쓰도록 함 
		return jdbc.update(DELETE_BY_ROLE_ID, params);
	}
	
	public Role selectById(Integer id) {
		try {
			Map<String, ?> params = Collections.singletonMap("roleId", id);
			return jdbc.queryForObject(SELECT_BY_ROLE_ID, params, rowMapper);
		}catch(EmptyResultDataAccessException e) { //조건에 맞는 결과값이 없을 때
			e.printStackTrace();
			return null; //예외 발생시 null 반환
		}
	}
	
}
