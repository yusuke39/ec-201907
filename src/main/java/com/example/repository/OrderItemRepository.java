package com.example.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.domain.Order;

import ch.qos.logback.classic.db.names.TableName;



/**
 * ショッピングカート（注文した商品が外部キーで紐づく）
 * 
 * @author hiranoyuusuke
 *
 */
@Repository
public class OrderItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private SimpleJdbcInsert insert;
	
	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert =  new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("orders");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}
	
	
	public Order insert(Order order) {
		String sql = "INSERT INTO(id,user_id,status,total_price,order_date,destination_name,destination_email,destination_zipcode,"
					+ "	destination_address,destination_tel,delivery_time,payment_method) VALUES(:id,:userId,:status) ";
		
		SqlParameterSource param = new MapSqlParameterSource();
		
		Number key =  insert.executeAndReturnKey(param);
		
		order.setId(key.intValue());
		
		System.out.println(key);
		
		return order;
	}
	

}
