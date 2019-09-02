package com.example.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.domain.Order;



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
	

	private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs,i) -> {
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setDestinationName(rs.getString("destination_name"));
		order.setDestinationEmail(rs.getString("destination_email"));
		order.setDestinationZipcode(rs.getString("destination_zipcode"));
		order.setDestinationAddress(rs.getString("destination_address"));
		order.setDestinationTel(rs.getString("destination_tel"));
		order.setDeliveryTime(rs.getTimestamp("delivery_time"));
		order.setOrderDate(rs.getDate("order_date"));
		order.setPaymentMethod(rs.getInt("payment_method"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("total_price"));
		order.setUserId(rs.getInt("user_id"));
		return order;
	};
	


	
	

	private SimpleJdbcInsert insert;
	
	//自動採番取得用メソッド
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
