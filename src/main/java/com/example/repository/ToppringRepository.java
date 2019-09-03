package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Topping;


@Repository
public class ToppringRepository {

	@Autowired 
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Topping> TOPPING_ROW_MAPPER =  (rs, i) -> {
	
		Topping topping = new Topping();
		
		topping.setId(rs.getInt("id"));
		topping.setName(rs.getString("name"));
		topping.setPriceM(rs.getInt("price_m"));
		topping.setPriceL(rs.getInt("price_l"));
		

		return topping;
		
	};
	
	
	
	public List<Topping> findAlltoppings() {

	String sql = "SELECT id,name,price_m,price_l FROM toppings ORDER BY id";
	
	SqlParameterSource param = new MapSqlParameterSource();
	
	List<Topping> toppingsList = template.query(sql, param, TOPPING_ROW_MAPPER);
	
	return toppingsList;
}
}
