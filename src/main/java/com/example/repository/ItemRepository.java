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
import com.example.domain.Topping;

@Repository
public class ItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final String TABLE_ITEMS = "items";


	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		// ここに結果の処理を書く
		Item item = new Item();

		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDesctiption(rs.getString("desctiption"));
		item.setPriceM(rs.getInt("priceM"));
		item.setPriceL(rs.getInt("priceL"));
		item.setImagePath(rs.getString("imagePath"));
		item.setDeleted(rs.getBoolean("deleted"));
		item.setImagePath(rs.getString("imagePath"));

		return item;
	};
	
	private static final ResultSetExtractor<List<Item>> ITEM_RESULT_SET_EXTRACTOR = (rs) -> {

		List<Item> itemList = new ArrayList<>();
		List<Topping> toppingList = null;

		int preId = -1;

		while (rs.next()) {

			int id = rs.getInt("A_id");

			if (id != preId) {
				Item item = new Item();

				item.setId(rs.getInt("A_id"));
				item.setName(rs.getString("A_name"));
				item.setDesctiption(rs.getString("A_desctiption"));
				item.setPriceM(rs.getInt("A_priceM"));
				item.setPriceL(rs.getInt("A_priceL"));
				item.setImagePath(rs.getString("A_imagePath"));
				item.setDeleted(rs.getBoolean("A_deleted"));
				item.setImagePath(rs.getString("A_imagePath"));	
				
				
				
				toppingList = new ArrayList<>();
				item.setToppingList(toppingList);
				itemList.add(item);
			}
			Integer toppingListId = rs.getInt("C_id");

			if (toppingListId != 0) {

				Topping topping = new Topping();
				topping.setId(rs.getInt("C_id"));

				topping.setName("C_name");
				topping.setPriceM(rs.getInt("C_priceM"));
				topping.setPriceL(rs.getInt("C_priceL"));

				toppingList.add(topping);
			}
			preId = id;
		}
		return itemList;
	};
	
	public List<Item> findAll(){
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	public Item load(Integer itemId) {
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items WHERE id = :itemId";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", itemId);
		
		Item showItemDetail = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		
		return showItemDetail;
	}

}
