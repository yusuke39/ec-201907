package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Order;

@Repository
public class OrderHistoryRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final String TABLE_ORDERS = "orders";
	
	
	private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, i) -> {
		// ここに結果の処理を書く
		Order order = new Order();

		order.setId(rs.getInt("id"));
		order.setUserId(rs.getInt("user_id"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("total_Price"));
		order.setDestinationName(rs.getString("destination_name"));
		order.setDestinationEmail(rs.getString("destination_email"));
		order.setDestinationZipcode(rs.getString("destination_zipcode"));
		order.setDestinationAddress(rs.getString("destination_address"));
		order.setDestinationTel(rs.getString("destination_tel"));
		order.setDeliveryTime(rs.getTimestamp("delivery_time"));
		order.setPaymentMethod(rs.getInt("payment_method"));

		return order;
	};
	
	public List<Order> findByUserId(Integer userId){
		String sql = "SELECT id,user_id,status,total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address,destination_tel,delivery_time,payment_method "
				+ "FROM " + TABLE_ORDERS + " WHERE user_id = :userId ORDER BY order_date DESC";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id",userId);
		
		List<Order> orderList = template.query(sql, param, ORDER_ROW_MAPPER);
		
		return orderList;
	}
	
}
