package com.example.domain;

/**
 * 注文したトッピングが入るドメイン.
 * 
 * @author hiranoyuusuke
 *
 */
public class OrderTopping {

	/** トッピングId */
	private Integer id;
	/** オーダーアイテムId */
	private Integer toppingId;
	/** オーダートッピングId */
	private Integer orderItemId;
	/**トッピングオブジェクト */
	private Topping topping;
	
	

	public Topping getTopping() {
		return topping;
	}

	public void setTopping(Topping topping) {
		this.topping = topping;
	}

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

}
