package com.example.form;

public class OrderForm {
	/** ID */
	private String id;
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
	/** 配達日時 */
	private String deliveryTime;
	/** お支払い方法 */
	private String paymentMethod;
	
	public Integer getIntId() {
		return Integer.parseInt(id);
	}
	
	public Integer getIntPaymentMethod() {
		return Integer.parseInt(paymentMethod);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "OrderForm [id=" + id + ", destinationName=" + destinationName + ", destinationEmail=" + destinationEmail
				+ ", destinationZipcode=" + destinationZipcode + ", destinationAddress=" + destinationAddress
				+ ", destinationTel=" + destinationTel + ", deliveryTime=" + deliveryTime + ", paymentMethod="
				+ paymentMethod + "]";
	}
	

	
	
	
	
	

}
