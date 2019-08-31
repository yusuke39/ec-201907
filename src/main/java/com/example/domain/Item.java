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
	private String description;

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
	
	private List<Item> tableList;
	
	public List<Item> getTableList() {
		return tableList;
	}

	public void setTableList(List<Item> tableList) {
		this.tableList = tableList;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", priceM=" + priceM + ", priceL="
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
