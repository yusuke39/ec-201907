package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;

@Repository
public class OrderRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final String TABLE_ORDERS = "orders";
	private static final String TABLE_ORDER_ITEM = "order_items";
	private static final String TABLE_ORDER_TOPPING = "order_toppings";
	private static final String TABLE_ITEM = "items";
	private static final String TABLE_TOPPING = "toppings";

	// ロウマッパー
	private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, i) -> {
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setUserId(rs.getInt("user_id"));
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
		return order;
	};

	/**
	 * 注文をする.
	 * 
	 * @param id
	 * @return 検索結果が入ったドメイン
	 */
	public Order shallowLoad(Integer id) {
		String sql = "SELECT id, user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method FROM orders WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Order order = template.queryForObject(sql, param, ORDER_ROW_MAPPER);
		return order;
	}

	/**
	 * Formの値をアップデートする
	 * 
	 * @param order
	 */
	public void update(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		String sql = "UPDATE orders SET id =:id, destination_name =:destinationName, destination_email =:destinationEmail, destination_zipcode =:destinationZipcode, destination_address =:destinationAddress, destination_tel =:destinationTel, delivery_time =:deliveryTime, payment_method =:paymentMethod, status =:status, order_date =:orderDate WHERE id=:id";
		template.update(sql, param);

	}

	// リザルトセットエクストラクター
	/**
	 * 5つのテーブルを結合した行にカラムの値を追加していく.
	 */
	private static final ResultSetExtractor<List<Order>> ORDER_RESULT_SET_EXTRACTOR = (rs) -> {
		//注文履歴
		List<Order> orderList = new ArrayList<>();
		//注文商品のリスト
		List<OrderItem> orderItemList = null;
		//注文トッピングのリスト
		List<OrderTopping> orderToppingList = null;
		
		//注文IDが切り替わるタイミングを判定するためのID.
		int preId = -1;
		int preorderItemCheckId = -1;

		
		while(rs.next()) {
			
			//orderテーブルから注文IDを取得.
			int id = rs.getInt("A_id");
			
			//idとpreIdの値が等しくない場合に行う処理.
			//idが同じ場合は注文オブジェクトの生成は行わない.
			if (id != preId) {
				
                //注文オブジェクトを生成
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
			if (orderItemCheckId != 0 && orderItemCheckId != preorderItemCheckId) {
				
//				注文商品オブジェクトを生成
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
				
				//itemオブジェクトに値をセット
				Item item = new Item();
				item.setId(rs.getInt("C_id"));
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

			}
			Integer orderToppingCheckId = rs.getInt("D_id");
			
			
			if (orderToppingCheckId != 0) {
				
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("D_id"));
				orderTopping.setToppingId(rs.getInt("D_topping_id"));
				orderTopping.setOrderItemId(rs.getInt("D_order_item_id"));
				

				//Toppingオブジェクトにセット
				Topping topping = new Topping();
				topping.setId(rs.getInt("E_id"));
				topping.setName(rs.getString("E_name"));
				topping.setPriceM(rs.getInt("E_price_m"));
				topping.setPriceL(rs.getInt("E_price_l"));
				orderTopping.setTopping(topping);

				orderToppingList.add(orderTopping);
			}
				
			preId = id;
			preorderItemCheckId  = orderItemCheckId;
		}
		return orderList;
	};
	
	/**
	 * テーブル結合したSQL文をString型で呼び出すメソッド.
	 * @return JoinSql(5つのテーブルを結合したsql文)
	 * 
	 * hirokiokazaki
	 */
	private String join5TablesSql() {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(
				"SELECT A.id A_id,A.user_id A_user_id,A.status A_status, A.total_price A_total_price, A.order_date A_order_date,");
		sql.append(
				"A.destination_name A_destination_name,A.destination_email A_destination_email,A.destination_zipcode A_destination_zipcode,A.destination_address A_destination_address, A.destination_tel A_destination_tel, ");
		sql.append("A.delivery_time A_delivery_time, A.payment_method A_payment_method, ");
		sql.append("B.id B_id, B.item_id B_item_id, B.order_id B_order_id, B.quantity B_quantity, B.size B_size, ");
		sql.append(
				"C.id C_Id, C.name C_name, C.description C_description, C.image_path C_image_path, C.price_m C_price_m, C.price_l C_price_l, C.image_path C_image_path, C.deleted C_deleted,");
		sql.append("D.id D_id, D.topping_id D_topping_id, D.order_item_id D_order_item_id,");
		sql.append("E.id E_id, E.name E_name, E.price_m E_price_m, E.price_l E_price_l ");
		sql.append("FROM " + TABLE_ORDERS + " A LEFT OUTER JOIN " + TABLE_ORDER_ITEM + " B ON A.id = B.order_id ");
		sql.append("LEFT OUTER JOIN " + TABLE_ITEM + " C ON B.item_id = C.id ");
		sql.append("LEFT OUTER JOIN " + TABLE_ORDER_TOPPING + " D ON B.id = D.order_item_id ");
		sql.append("LEFT OUTER JOIN " + TABLE_TOPPING + " E ON E.id = D.topping_id ");

		String JoinSql =sql.toString();
		
		return JoinSql;
	}

	/**
	 * 注文確認画面用.
	 * @param id
	 * @return 検索結果をリストに詰めて返す
	 */
	public Order deepLoad(Integer OrderId){

		StringBuilder sql = new StringBuilder();
		
		//5つのテーブルを結合するSQL文を表示
		sql.append(join5TablesSql());
		//join5TableSql()で呼び出したSQL文に呼び出す行の条件を指定
		sql.append("WHERE A.id = :id");
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", OrderId);
		List<Order> orderList = template.query(sql.toString(), param, ORDER_RESULT_SET_EXTRACTOR);

		return orderList.get(0);
	}
	
	public List<Order> findAll() {
		StringBuilder sql = new StringBuilder();
		
		//5つのテーブルを結合するSQL文を表示
		sql.append(join5TablesSql());
		//join5TableSql()で呼び出したSQL文に呼び出す行の条件を指定
		sql.append("ORDER BY order_date DESC");
		List<Order> orderList = template.query(sql.toString(), ORDER_RESULT_SET_EXTRACTOR);
		return orderList;
	}

	private SimpleJdbcInsert insert;

	// 自動採番取得用メソッド
	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("orders");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}

	/**
	 * ステータスとユーザーIDを使ってショッピングカートの有無を検索する.
	 * 
	 * @param status
	 * @param userId
	 * @return 検索結果をリストに詰めて返す
	 */
	public List<Order> findByStatusAndUserId(Integer status, Integer userId) {
		String sql = "SELECT id, user_id, status, total_price ,order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, "
				+ "delivery_time, payment_method FROM orders WHERE status = :status AND user_id = :userId";

		SqlParameterSource param = new MapSqlParameterSource().addValue("status", status).addValue("userId", userId);

		List<Order> orderList = template.query(sql, param, ORDER_ROW_MAPPER);

		return orderList;

	}
	public List<Order> findByStatusThan0() {
		String sql = "SELECT id, user_id, status, total_price ,order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, "
				+ "delivery_time, payment_method FROM orders WHERE status = :status AND user_id = :userId";

		SqlParameterSource param = new MapSqlParameterSource();

		List<Order> orderList = template.query(sql, param, ORDER_ROW_MAPPER);

		return orderList;

	}
	

	/**
	 * 注文商品のインサート.
	 * 
	 * @param order
	 * @return 注文した商品が入ったカートのドメイン
	 */
	public Order insert(Order order) {
//		String sql = "INSERT INTO orders(user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode,"
//					+ "	destination_address, destination_tel, delivery_time, payment_method)"
//					+ " VALUES(1, :status, :totalPrice, :orderDate, :destinationName, :destinationEmail, :destinationZipcode,"
//					+ " :destinationAddress, :destinationTel, :deliveryTime, :paymentMethod)";

		// ドメインの名前とSQLの？部分があっていれば自動的に入っていく
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);

//		template.update(sql, param);

		// executeAndReturnKeyが勝手にインサート文を実行してくれる
		Number key = insert.executeAndReturnKey(param);

		order.setId(key.intValue());

		return order;
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
	
	public void update() {
		String sql = "UPDATE";
	}
}
