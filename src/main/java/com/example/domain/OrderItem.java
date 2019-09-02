package com.example.domain;

import java.util.List;

/**
 * 注文商品を表すドメインクラス.
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
	private Character size;
	/**
	 * 商品(アイテムドメインオブジェクト)
	 */
	private Item item;
	/**
	 * OrederTopping型のリスト
	 */
	private List<OrderTopping> orderToppingList;
	
	
	/**
	 * (＊↓XXXXXXの計算がわからないので後で修正)
	 * 数量、サイズを加味した金額を算出.
	 * @return
	 */
//	public int getSubTotal() {
//		return 　XXXXXXXXXX;
//	}
	
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
	public void setOrederId(Integer orederId) {
		this.orderId = orederId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Character getSize() {
		return size;
	}
	public void setSize(Character size) {
		this.size = size;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}
	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orederId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + "]";
	}
}
