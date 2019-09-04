package com.example.form;

public class OrderForm {
	/** ID */
	private String orderId;
	/** 名前 */
	private String destinationName;
	/** メールアドレス */
	private String destinationEmail;
	/** 郵便番号 */
	private String destinationZipcode;
	/** 住所 */
	private String destinationAddress;
	/** 電話番号 */
	private String destinationTel;
	/** 配達日 */
	private String deliveryDate;
	/** 配達時間 */
	private String deliveryHour;
	/** お支払い方法 */
	private String paymentMethod;
	
	/** 状態 */
	private String status;
	
	public Integer getIntStatus() {
		return Integer.parseInt(status);
	}
	
	public Integer getIntId() {
		return Integer.parseInt(orderId);
	}
	
	public Integer getIntPaymentMethod() {
		return Integer.parseInt(paymentMethod);
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryHour() {
		return deliveryHour;
	}

	public void setDeliveryHour(String deliveryHour) {
		this.deliveryHour = deliveryHour;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderForm [orderId=" + orderId + ", destinationName=" + destinationName + ", destinationEmail="
				+ destinationEmail + ", destinationZipcode=" + destinationZipcode + ", destinationAddress="
				+ destinationAddress + ", destinationTel=" + destinationTel + ", deliveryDate=" + deliveryDate
				+ ", deliveryHour=" + deliveryHour + ", paymentMethod=" + paymentMethod + ", status=" + status + "]";
	}

	




	

	
	
	
	
	

}
