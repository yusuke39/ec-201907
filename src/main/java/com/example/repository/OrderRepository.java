package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;




import javax.annotation.PostConstruct;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.ResultSetExtractor;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import org.springframework.stereotype.Repository;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;


@Repository
public class OrderRepository {
	
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
	
	public Order shallowLoad(Integer id) {
		String sql = "SELECT id, user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method FROM orders WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Order order = template.queryForObject(sql, param, ORDER_ROW_MAPPER);
		return order;
	}
	
	public void update(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		String sql = "UPDATE orders SET id =:id, destination_name =:destinationName, destination_email =:destinationEmail, destination_zipcode =:destinationZipcode, destination_address =:destinationAddress, destination_tel =:destinationTel, delivery_time =:deliveryTime, payment_method =:paymentMethod WHERE id=:id";
		template.update(sql, param);
	
	}
	
	
	private static final ResultSetExtractor<List<Order>> ORDER_RESULT_SET_EXTRACTOR = (rs) -> {
		
		List<Order> orderList = new ArrayList<>();
		List<OrderItem> orderItemList = null;
		List<OrderTopping> orderToppingList = null;
		
		//注文IDが切り替わるタイミングを判定するためのID.
		int preId = -1;
		
		while(rs.next()) {
			
			//orderテーブルから注文IDを取得.
			int id = rs.getInt("A_id");
			
			//idとpreIdの値が等しくない場合に行う処理.
			//idが同じ場合はarticleオブジェクトの生成は行わない.
			if (id != preId) {
				
				Order order = new Order();
				
				order.setId(rs.getInt("A_id"));
				order.setUserId(rs.getInt("A_user_id"));
				order.setStatus(rs.getInt("A_status"));
				order.setTotalPrice(rs.getInt("A_total_price"));
				order.setDestinationName(rs.getString("A_destination_name"));
				order.setDestinationEmail(rs.getString("A_destination_email"));
				order.setDestinationZipcode(rs.getString("A_destination_zipcode"));
				order.setDestinationAddress(rs.getString("A_destination_address"));
				order.setDestinationTel(rs.getString("A_destination_tel"));
				order.setDeliveryTime(rs.getTimestamp("A_delivery_time"));
				order.setPaymentMethod(rs.getInt("A_payment_method"));
				
				//先にorderItemListをorderオブジェクトにセットする.
				//後にorderItemListにorderItemオブジェクトをセットして参照している.
				orderItemList = new ArrayList<>();
				order.setOrderItemList(orderItemList);
				orderList.add(order);
			}
			
			//orderItemテーブルからorderItemIDを取得.
			Integer orderItemCheckId = rs.getInt("B_id");
			
			//orderItemCheckIdが0ではない場合に行う処理.
			//0の時はnullなので、orderItemオブジェクトの生成は行わない.
			if (orderItemCheckId != 0) {
				
				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getInt("B_id"));
				orderItem.setItemId(rs.getInt("B_item_id"));
				orderItem.setOrderId(rs.getInt("B_order_id"));
				orderItem.setQuantity(rs.getInt("B_quantity"));
				//B_sizeをString型で取り出し、String→charに変換後、char→Character型に変換
				String str = rs.getString("B_size");
				char[] toChar = str.toCharArray();
				Character toCharacter =toChar[0];
				orderItem.setSize(toCharacter);
				//先にorderオブジェクトにセットしたリストに、orderItemオブジェクトをaddする.
				//先にorderItemListをorderオブジェクトにセットする.
				//後にorderItemListにorderItemオブジェクトをセットして参照している.
				orderToppingList = new ArrayList<>();
				orderItem.setOrderToppingList(orderToppingList);
				
				orderItemList.add(orderItem);
				//コメントリストにコメントつっこむ
			}
			Integer orderToppingCheckId = rs.getInt("D_id");
			
			
			if (orderToppingCheckId != 0) {
				
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("D_id"));
				orderTopping.setToppingId(rs.getInt("D_topping_id"));
				orderTopping.setOrderItemId(rs.getInt("D_order_item_id"));
				
				orderToppingList.add(orderTopping);
			}
			
			preId = id;
		}
		return orderList;
	};
	
	public List<Order> deepLoad(Integer id) {
		String sql = "SELECT id, user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method FROM orders WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Order> orderList = template.query(sql, param, ORDER_RESULT_SET_EXTRACTOR );
		return orderList;
	}


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
		
		return order;
	}
 
}
