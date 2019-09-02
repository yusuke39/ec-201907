package com.example.repository;

import java.util.List;

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


@Repository
public class OrderRepository {
	
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
	 * ステータスとユーザーIDを使ってショッピングカートの有無を検索する.
	 * 
	 * @param status
	 * @param userId
	 * @return　検索結果をリストに詰めて返す
	 */
	public List<Order> findByStatusAndUserId(Integer status, Integer userId){
		String sql = "SELECT user_id, status, total_price ,order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, "
					+ "delivery_time, payment_method FROM orders WHERE status = :status AND user_id = :userId";
		
		SqlParameterSource param =  new MapSqlParameterSource().addValue("status", status).addValue("userId", userId);
		
		List<Order> orderList = template.query(sql, param, ORDER_ROW_MAPPER);
		
		return orderList;
		
	}
	
	
	/**
	 * 注文商品のインサート.
	 * 
	 * @param order
	 * @return　注文した商品が入ったカートのドメイン
	 */
	public Order insert(Order order) {
//		String sql = "INSERT INTO orders(user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode,"
//					+ "	destination_address, destination_tel, delivery_time, payment_method)"
//					+ " VALUES(1, :status, :totalPrice, :orderDate, :destinationName, :destinationEmail, :destinationZipcode,"
//					+ " :destinationAddress, :destinationTel, :deliveryTime, :paymentMethod)";
		
		//ドメインの名前とSQLの？部分があっていれば自動的に入っていく
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		
//		template.update(sql, param);
		
		//executeAndReturnKeyが勝手にインサート文を実行してくれる
		Number key =  insert.executeAndReturnKey(param);
		
		order.setId(key.intValue());
		
		System.out.println("オーダー" + key);
		
		return order;
	}
 
}
