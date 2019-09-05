package com.example.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderItem;

/**
 * 注文した商品の詳細を操作するリポジトリー.
 * 
 * @author hiranoyuusuke
 *
 */
@Repository
public class OrderItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private SimpleJdbcInsert insert;
	
	//自動採番取得用メソッド
	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert =  new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("order_items");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}
	
	
	public OrderItem insert(OrderItem orderItem) {
//		String sql = "INSERT INTO order_idems(order_id, quantity ,size) VALUES(:orderId, :quantity, :size)";
		SqlParameterSource param =  new BeanPropertySqlParameterSource(orderItem);
		
//		 template.update(sql, param);
		 
		//executeAndReturnKeyがインサートを自動で実行してくれる
		 Number key =  insert.executeAndReturnKey(param);
		 
		 orderItem.setId(key.intValue());
		
		return orderItem;
	}
	
	/**
	 * カートから商品を削除する.
	 * @param id
	 */
	public void deleteByOrderItemId(Integer id) {
		String deleteSql = "DELETE FROM order_items WHERE id = :id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		template.update(deleteSql, param);
	}
	
	public void update(OrderItem orderItem) {
		String sql = "update order_items set order_id = :orderId where id = :id";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderItem.getOrderId()).addValue("id", orderItem.getId());
		
		template.update(sql, param);
		
		
	}
	
}
