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
	private Character size;
	/**
	 * 商品(アイテムドメインオブジェクト)
	 */
	private Item item;
	/**
	 * OrederTopping型のリスト
	 */
	private List<OrderTopping> orderToppingList;
	
	public Character getSize() {
		return size;
	}

	public void setSize(Character size) {
		this.size = size;
	}

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
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderToppingList=" + orderToppingList + "]";
	}

	
	/**
	 * （商品価格 + トッピングの値段 * トッピングの数）* 注文数.
	 * 
	 * @return 合計金額
	 */
	public int getSubTotal() {
//		System.out.println(this);
		int ItemPrice = 0;
		
		if(size.equals('M')){
			 ItemPrice = item.getPriceM() +  200 * orderToppingList.size();
		} else if(size.equals('L')) {
			ItemPrice = item.getPriceL() + 300 * orderToppingList.size();
		}
		int allPrice = ItemPrice * quantity;
		
		return allPrice;
	}

}
