package com.example.domain;


/**
 * Timestampの参照型を利用するに伴い
 * java.security.Timestampをインポートしているが
 * 不具合が起きる場合は削除してjava.sqlをインポートしてみてください.
 * @author hirokiokazaki
 *
 *
 * 注文が入るドメイン （ショッピングカート、カートに入れた内容は外部キーで紐づいてます).
 * 
 * @author hiranoyuusuke
 *
 */


import java.util.Date;
import java.util.List;

public class Order {


	/**
	 * ID(主キー)
	 */
	private Integer id;
	/**
	 * ユーザーID
	 */
	private Integer userId;
	/**
	 * 状態(o:注文前、1:未入金、2:入金済み、3:発送済、9:キャンセル)
	 */
	private Integer status;
	/**
	 * 合計金額
	 */
	private Integer totalPrice;
	/**
	 * 注文日
	 */
	private Date orderDate;
	/**
	 * 宛先氏名
	 */
	private String destinationName;
	/**
	 * 宛先Eメール
	 */
	private String destinationEmail;
	/**
	 * 宛先郵便番号
	 */
	private String destinationZipcode;
	/**
	 * 宛先住所
	 */
	private String destinationAddress;
	/**
	 * 宛先電話番号
	 */
	private String destinationTel;
	/**
	 * 配達時間
	 */
	private Timestamp deliveryTime;
	/**
	 * 支払方法
	 */
	private Integer paymentMethod;
	/**
	 * ユーザー(ユーザードメインオブジェクト)
	 */
	private User user;
	/**
	 * OrderItem型のList
	 */
	private List<OrderItem> orderItemList;
	
	/**
	 * @return tax(消費税額)
	 */
	public int getTax() {
		int tax = (int) (totalPrice * 0.08);
		return tax;
	}
	
	/**
	 * @return includeTaxTotalPrice(税込合計金額)
	 */
	public int getCalcTotalPrice() {
		int includeTaxTotalPrice = totalPrice + getTax();
		return includeTaxTotalPrice;
	}
	
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", status=" + status + ", totalPrice=" + totalPrice
				+ ", orderDate=" + orderDate + ", destinationName=" + destinationName + ", destinationEmail="
				+ destinationEmail + ", destinationZipcode=" + destinationZipcode + ", destinationAddress="
				+ destinationAddress + ", destinationTel=" + destinationTel + ", deliveryTime=" + deliveryTime
				+ ", paymentMethod=" + paymentMethod + ", user=" + user + ", orderItemList=" + orderItemList + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	
	public String getDestinationEmail() {
		return destinationEmail;
	}
	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}
	public String getDestinationZipcode() {
		return destinationZipcode;
	}
	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}
	public String getDestinationAddress() {
		return destinationAddress;
	}
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	public String getDestinationTel() {
		return destinationTel;
	}
	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}
	public java.sql.Timestamp getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(java.sql.Timestamp timestamp) {
		this.deliveryTime = timestamp;
	}
	public Integer getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
}
