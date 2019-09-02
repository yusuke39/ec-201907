package com.example.form;

import java.util.List;

public class ShoppingCartForm {

	/** アイテムID */
	private String itemId;
	/** サイズ */
	private String size;
	/** トッピングリスト */
	private List<Integer> toppingList;
	/** 数量 */
	private String quantity;
	/** 合計金額 */
	private String totalPrice;
	
	private String userId;

	public ShoppingCartForm(String itemId, String size, List<Integer> toppingList, String quantity,
			String totalPrice) {
		super();
		this.itemId = itemId;
		this.size = size;
		this.toppingList = toppingList;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	
	

	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public ShoppingCartForm() {
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<Integer> getToppingList() {
		return toppingList;
	}

	public void setToppingList(List<Integer> toppingList) {
		this.toppingList = toppingList;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}



	@Override
	public String toString() {
		return "ShoppingCartForm [itemId=" + itemId + ", size=" + size + ", toppingList=" + toppingList + ", quantity="
				+ quantity + ", totalPrice=" + totalPrice + ", userId=" + userId + "]";
	}

}
