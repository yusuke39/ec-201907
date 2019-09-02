package com.example.domain;


import java.util.List;

/**
 * 注文商品を表すドメインクラス.
 * 
 * @author hirokiokazaki
 *
 */
public class OrderItem {

	/**
	 * ID(主キー)
	 */
	private Integer id;
	/**
	 * アイテムID(Itemドメインと結合)
	 */
	private Integer itemId;
	/**
	 * オーダーID(Orderドメインと結合)
	 */
	private Integer orderId;
	/**
	 * 数量
	 */
	private Integer quantity;
	/**
	 * サイズ
	 */
	private String size;
	/**
	 * 商品(アイテムドメインオブジェクト)
	 */
	private Item item;
	/**
	 * OrederTopping型のリスト
	 */
	private List<Integer> orderToppingList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Integer> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<Integer> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderToppingList=" + orderToppingList + "]";
	}

	
	/**
	 * (＊↓XXXXXXの計算がわからないので後で修正) 数量、サイズを加味した金額を算出.
	 * 
	 * @return
	 */
//	public int getSubTotal() {
//		return 　XXXXXXXXXX;
//	}

}
