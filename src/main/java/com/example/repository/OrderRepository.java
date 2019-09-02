package com.example.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;


import com.example.domain.Order;
import com.example.domain.OrderItem;


@Repository
public class OrderRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	

	private SimpleJdbcInsert insert;
	
	//自動採番取得用メソッド
	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert =  new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("orders");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}
	
	
	/**
	 * 注文商品のインサート.
	 * 
	 * @param order
	 * @return　注文した商品が入ったカートのドメイン
	 */
	public Order insert(Order order, OrderItem orderItem) {
		String sql = "INSERT INTO orders(user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode,"
					+ "	destination_address, destination_tel, delivery_time, payment_method)"
					+ " VALUES(:userId, :status, :totalPrice, :orderDate, :destinationName, :destinationEmail, :destinationZipcode,"
					+ " :destinationAddress, :destinationTel, :deliveryTime, :paymentMethod)";
		
		//ドメインの名前とSQLの？部分があっていれば自動的に入っていく
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		
		template.update(sql, param);
		
		Number key =  insert.executeAndReturnKey(param);
		
		order.setId(key.intValue());
		
		System.out.println(key);
		
		return order;
	}

}
