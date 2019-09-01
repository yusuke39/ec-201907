package com.example.domain;


/**
 * 注文トッピングを表すドメインクラス.
 * @author hirokiokazaki
 *
 */
public class OrderTopping {

	/**
	 * ID(主キー)
	 */
	private Integer id;
	
	/**
	 * トッピングID(Toppingドメインと結合)
	 */
	private Integer toppingId;
	
	/**
	 * 注文ID(OrderItemドメインとの結合)
	 */
	private Integer orderItemId;
	
	/**
	 * Toppingドメイン型のtopping
	 */
	private Topping topping;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getToppingId() {
		return toppingId;
	}

	public void setToppingId(Integer toppingId) {
		this.toppingId = toppingId;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Topping getTopping() {
		return topping;
	}

	public void setTopping(Topping topping) {
		this.topping = topping;
	}

	@Override
	public String toString() {
		return "OrderTopping [id=" + id + ", toppingId=" + toppingId + ", orderItemId=" + orderItemId + ", topping="
				+ topping + "]";
	}
	
	
}
