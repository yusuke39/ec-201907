package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.RegisterUserForm;
import com.example.service.RegisterUserServics;

/**
 * @author junya.yoshioka
 *
 */
@Controller
@RequestMapping("/login")
public class RegisterUserController {

	@Autowired
	public RegisterUserServics registerUserServics;
	
	
	@ModelAttribute
	public RegisterUserForm setUpLoginRegisterUserForm(){
		return new RegisterUserForm();
	}
	
	/**
	 * @return 登録画面
	 */
	@RequestMapping("/toLogin")
	public String toInsert() {
		return "register_user";
	}
	
	/**
	 * @param form
	 * @param result
	 * @return　ログイン画面
	 */
	@RequestMapping("/register_user")
	public String insert(@Validated RegisterUserForm form,BindingResult result) {
		
//		//パスワードが一致しなかった時に"パスワードが一致しません"を表示
		if(!form.getPassword().equals(form.getConfirmationPassword())) {
			result.reject("password","パスワードが一致しません");
		}
		
		
//		//エラーがあれば登録画面に遷移する
		if(result.hasErrors()) {
		    return "/register_user";
		}
		System.out.println(form.toString());
		User domainUser = new User();
		//プロパティのコピー
		BeanUtils.copyProperties(form, domainUser);
		
		
		
		System.out.println(domainUser.toString());
		
        //登録処理
		registerUserServics.insert(domainUser);
		
		return "redirect:/";
	}
}