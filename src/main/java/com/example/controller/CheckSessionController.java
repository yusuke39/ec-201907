package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.repository.OrderRepository;

/**
 * ログインした時にDBのsessionIdとブラウザのsessionIdが一緒ならDBのuser_idを入れ替える.
 * 
 * @author hiranoyuusuke
 *
 */
@Controller
@RequestMapping("/check")
public class CheckSessionController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	
	@RequestMapping("/checkSession")
	public String checkSession(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		
		Object id = session.getAttribute("id");
		String sessionNo = id.toString(); 
		
		int x = 0;
		int y = 0;
		for (int i = 0; i + 10 <= sessionNo.length(); i += 30) {
		    x = Integer.parseUnsignedInt(sessionNo.substring(i, i + 8), 16);
		    y = Integer.parseInt(sessionNo.substring(i + 8, i + 10), 16);
		}
		int sessionId = (x + y);
		
		List<Order> orderList = orderRepository.findByStatusAndUserId(0, sessionId);
	
		if(orderList.size() == 0) {
			return "login";
		} else {
			int user_id = loginUser.getUser().getId();
			Order order = orderList.get(0);
			order.setUserId(user_id);
			order.setId(order.getId());
			orderRepository.update(order);
		}
		
		
		return "redirect:/index/showItemList";
	}

}
