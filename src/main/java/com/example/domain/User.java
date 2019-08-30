package com.example.domain;


/**
 * User(利用者)を表すドメインクラス
 * @author hirokiokazaki
 *
 */
public class User {
	private Integer id;
	/** 名前 */
	private String name;
	/** Eメール */
	private String email;
	/** パスワード */
	private String password;
	/** 郵便番号 */
	private String zipcode;
	/** 住所 */
	private String address;
	/** 電話番号 */
	private String telephone;
	/** 確認用パスワード */
	private String confirmationPassword;
	
	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", zipcode="
				+ zipcode + ", address=" + address + ", telephone=" + telephone + ", confirmationPassword="
				+ confirmationPassword + ", getConfirmationPassword()=" + getConfirmationPassword() + ", getId()="
				+ getId() + ", getName()=" + getName() + ", getEmail()=" + getEmail() + ", getPassword()="
				+ getPassword() + ", getZipcode()=" + getZipcode() + ", getAddress()=" + getAddress()
				+ ", getTelephone()=" + getTelephone() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setonfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}