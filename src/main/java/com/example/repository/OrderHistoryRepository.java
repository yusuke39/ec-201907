package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;

@Repository
public class OrderHistoryRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final String TABLE_ORDERS = "orders";
	private static final String TABLE_ORDER_ITEM = "order_items";
	private static final String TABLE_ORDER_TOPPING = "order_toppings";
	private static final String TABLE_ITEM = "items";
	private static final String TABLE_TOPPING = "toppings";
	
	
	private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, i) -> {
		// ここに結果の処理を書く
		Order order = new Order();

		order.setId(rs.getInt("id"));
		order.setUserId(rs.getInt("user_id"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("total_price"));
		order.setDestinationName(rs.getString("destination_name"));
		order.setDestinationEmail(rs.getString("destination_email"));
		order.setDestinationZipcode(rs.getString("destination_zipcode"));
		order.setDestinationAddress(rs.getString("destination_address"));
		order.setDestinationTel(rs.getString("destination_tel"));
		order.setDeliveryTime(rs.getTimestamp("delivery_time"));
		order.setPaymentMethod(rs.getInt("payment_method"));

		return order;
	};
	
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
				orderItem.setOrederId(rs.getInt("B_order_id"));
				orderItem.setQuantity(rs.getInt("B_quantity"));
				//B_sizeをString型で取り出し、String→charに変換後、char→Character型に変換
				String str = rs.getString("B_size");
				char[] toChar = str.toCharArray();
				Character toCharacter =toChar[0];
				orderItem.setSize(toCharacter);
				
				//itemオブジェクトに値をセット
				Item item = new Item();
				item.setId(rs.getInt("C_id"));
				item.setName(rs.getString("C_name"));
				item.setName(rs.getString("C_name"));
				item.setDescription(rs.getString("C_description"));
				item.setPriceM(rs.getInt("C_price_m"));
				item.setPriceL(rs.getInt("C_price_l"));
				item.setImagePath(rs.getString("C_image_path"));
				item.setDeleted(rs.getBoolean("C_deleted"));
				orderItem.setItem(item);
				
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
	
	public List<Order> findAll() {
		StringBuilder sql = new StringBuilder();
		
//		A = ordes(注文)　B = order_items(注文商品) C = items(商品)　
//		D = order_toppings(注文トッピング) E = toppings(トッピング)
		sql.append("SELECT A.id A_id,A.user_id A_user_id,A.status A_status, A.total_price A_total_price, A.order_date A_order_date,");
		sql.append("A.destination_name A_destination_name,A.destination_email A_destination_email,A.destination_zipcode A_destination_zipcode,A.destination_address A_destination_address, A.destination_tel A_destination_tel, ");
		sql.append("A.delivery_time A_delivery_time, A.payment_method A_payment_method, ");
		sql.append("B.id B_id, B.item_id B_item_id, B.order_id B_order_id, B.quantity B_quantity, B.size B_size, ");
		sql.append("C.id C_Id, C.name C_name, C.description C_description, C.image_path C_image_path, C.price_m C_price_m, C.price_l C_price_l, C.image_path C_image_path, C.deleted C_deleted,");
		sql.append("D.id D_id, D.topping_id D_topping_id, D.order_item_id D_order_item_id,");
		sql.append("E.name E_name, E.price_m E_price_m, E.price_l E_price_l ");
		sql.append("FROM " + TABLE_ORDERS + " A LEFT OUTER JOIN " + TABLE_ORDER_ITEM + " B ON A.id = B.order_id ");
		sql.append("LEFT OUTER JOIN " + TABLE_ITEM + " C ON B.item_id = C.id ");
		sql.append("LEFT OUTER JOIN " + TABLE_ORDER_TOPPING + " D ON B.id = D.order_item_id ");
		sql.append("LEFT OUTER JOIN " + TABLE_TOPPING + " E ON E.id = D.topping_id ");
		sql.append("ORDER BY order_date DESC");
		
		List<Order> orderList = template.query(sql.toString(), ORDER_RESULT_SET_EXTRACTOR);
		return orderList;
	}


	public List<Order> findByUserId(Integer userId){
		String sql = "SELECT id,user_id,status,total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address,destination_tel,delivery_time,payment_method "
				+ "FROM " + TABLE_ORDERS + " WHERE user_id = :userId ORDER BY order_date DESC";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id",userId);
		
		List<Order> orderList = template.query(sql, param, ORDER_ROW_MAPPER);
		
		return orderList;
	}	
}
