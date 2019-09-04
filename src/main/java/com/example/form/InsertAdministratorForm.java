package com.example.form;

import javax.validation.constraints.NotBlank;

/**
 * @author junya.yoshioka
 *
 */
public class InsertAdministratorForm {
	
	/**	名前 */
	@NotBlank(message = "氏名を入力してください")
	private String name;
	/**	メールアドレス */
	@NotBlank(message = "メールアドレスを入力してください")
	private String mailAddress;
	/**	パスワード */
	@NotBlank(message = "パスワードを入力してください")
	private String password;
	/**	確認用パスワード */
	@NotBlank(message = "確認用パスワードを入力してください")
	private String checkPassword;
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMailAddress() {
		return mailAddress;
	}


	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getCheckPassword() {
		return checkPassword;
	}


	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}


	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ ", checkPassword=" + checkPassword + ", getCheckPassword()=" + getCheckPassword() + ", getName()="
				+ getName() + ", getMailAddress()=" + getMailAddress() + ", getPassword()=" + getPassword()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}

