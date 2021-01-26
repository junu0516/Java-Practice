package com.junu.spring.guestbook.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.junu.spring.guestbook.dto.Log;

@Repository
public class LogDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	
	public LogDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("log").usingGeneratedKeyColumns("id");
		//usingGeneratedKeyColumns : id 자동 입력을 위한 메소드 -> 매개변수로 받은 이름에 해당하는 컬럼을 자동으로 생성해주는 것
		
	}
	
	public Long insert(Log log) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(log);
		return insertAction.executeAndReturnKey(params).longValue();
		//executeAndReturnKey : insert 쿼리문을 내부적으로 실행 후 자동으로 생성된 id값을 리턴함
		//리턴된 id의 longValue()를 리턴
	}
}
