package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorServise;

@Controller
@RequestMapping("/administrator")
public class AdministratorContoroller {
	
	@Autowired
	private AdministratorServise administratorServise;
	
	@Autowired
	private HttpSession session;
	
	@ModelAttribute
	public InsertAdministratorForm insertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	@RequestMapping("")
	public String index() {
		return "administrator/administratorLogin";
	}
	
		
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/administratorInsert";
	}
	
	
	/**
	 * 登録画面
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(@Validated InsertAdministratorForm form, BindingResult result) {
		
		if(!form.getPassword().equals(form.getCheckPassword())) {
			System.out.println("パスワード不一致");
			result.rejectValue("checkPassword", null, "パスワードが一致していません");
		}
		
		if(administratorServise.findByMailAddress(form.getMailAddress()) != null){
			result.rejectValue("mailAddress", null, "そのメールアドレスは既に登録されています");
		}
		
		if(result.hasErrors()) {
			return toInsert();
		}
		 
		Administrator administrator = new Administrator (); 
		 BeanUtils.copyProperties(form, administrator);
		 
		 administratorServise.insert(administrator);
		 
		
		return "administrator/administratorLogin";
	}
	
	
	
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator administrator = administratorServise.login(form.getMailAddress(), form.getPassword());
		
		
		if(administrator == null) {
			System.out.println(administrator);
			model.addAttribute("errorMessage" , "メールアドレスまたはパスワードが不正です。");
			
			return "administrator/administratorLogin";
		}
		
		session.setAttribute("administratorName", administrator.getName());
		return "forward:/userInformation/showList";
	}

}
