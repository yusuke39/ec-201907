package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

@Repository
public class UserRepository {
	/**
	 * userテーブルを操作するリポジトリ.
	 * 
	 * @author hirokiokazaki
	 * 
	 */

	/**
	 * Userオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setZipcode(rs.getString("zipcode"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));

		return user;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * メールアドレスからユーザー情報を取得します.
	 * 
	 * @param email メールアドレス
	 * @return ユーザー情報 存在しない場合はnullを返します
	 */
	public User findByMailAddress(String email) {
		String sql = "select id,name,email,password,zipcode,address,telephone from users where email=:email";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}
	
	public void insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "INSERT INTO users(name, email, password, zipcode, address, telephone) VALUES(:name, :email, :password, :zipcode, :address, :telephone)";
		template.update(sql, param);
	}
	
	
	/**
	 * ユーザー情報の削除
	 * @param id
	 */
	public void deleteByUserId(Integer id) {
		String deleteSql = "DELETE FROM users WHERE id = :id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		template.update(deleteSql, param);
	}
	
	
	/**
	 * 指定したいidのユーザー情報を取得
	 * @param id
	 * @return
	 */
	public User findById(Integer id) {
		String sql = "select id,name,email,password,zipcode,address,telephone from users where id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		User user = template.queryForObject(sql, param,USER_ROW_MAPPER);
		return user;
	}
}

