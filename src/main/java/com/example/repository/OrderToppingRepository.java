package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderTopping;

@Repository
public class OrderToppingRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public void insert(OrderTopping orderTopping) {
		String sql = "INSERT INTO order_toppings(topping_id,order_item_id) VALUES(:toppingId,:orderItemId)";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderTopping.getOrderItemId()).addValue("toppingId", orderTopping.getToppingId());
		
		template.update(sql, param);
		
	}
	
	/**
	 * カートから注文トッピングを削除する.
	 * @param orderItemId
	 */
	public void deleteByOrderItemId(Integer orderItemId) {
		String deleteSql = "DELETE FROM order_toppings WHERE order_item_id = :orderItemId";

		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);

		template.update(deleteSql, param);
	}
}