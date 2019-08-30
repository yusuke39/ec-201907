package com.example.domain;

import java.util.List;

public class Item {
	
	/**
	 * ID
	 */
	private Integer id;

	/**
	 * name(名前)
	 */
	private String name;

	/**
	 * description(説明)
	 */
	private String desctiption;

	/**
	 * priceM(Mの価格)
	 */
	private Integer priceM;

	/**
	 * priceL(Lの価格)
	 */
	private Integer priceL;

	/**
	 * imagePath(画像パス)
	 */
	private String imagePath;

	/**
	 * deleted(削除フラグ)
	 */
	private Boolean deleted;

	/**
	 * トッピングリスト
	 */
	private List<Item> toppingList;
	

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", desctiption=" + desctiption + ", priceM=" + priceM + ", priceL="
				+ priceL + ", imagePath=" + imagePath + ", deleted=" + deleted + ", toppingList=" + toppingList + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesctiption() {
		return desctiption;
	}

	public void setDesctiption(String desctiption) {
		this.desctiption = desctiption;
	}

	public Integer getPriceM() {
		return priceM;
	}

	public void setPriceM(Integer priceM) {
		this.priceM = priceM;
	}

	public Integer getPriceL() {
		return priceL;
	}

	public void setPriceL(Integer priceL) {
		this.priceL = priceL;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public List<Item> getToppingList() {
		return toppingList;
	}

	public void setToppingList(List<Item> toppingList) {
		this.toppingList = toppingList;
	}
}
