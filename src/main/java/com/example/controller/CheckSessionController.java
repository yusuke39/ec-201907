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
import com.example.domain.OrderItem;
import com.example.repository.OrderItemRepository;
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
	
	@Autowired
	private OrderItemRepository orderItemRepository;

		
	@RequestMapping("/checkSession")
	public String checkSession(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		
		Object id = session.getAttribute("id");
		
		//ユーザーが何も注文敷いていない状態でログインした時(sessionIdが取得できないので、ここでログインさせる）
		if(id == null) {
			return "redirect:/index/showItemList";
		}
		String sessionNo = id.toString(); 
		
		
		//sessionIDを数字に直す処理
		int x = 0;
		int y = 0;
		for (int i = 0; i + 10 <= sessionNo.length(); i += 30) {
		    x = Integer.parseUnsignedInt(sessionNo.substring(i, i + 8), 16);
		    y = Integer.parseInt(sessionNo.substring(i + 8, i + 10), 16);
		}
		int sessionId = (x + y);
		int user_id = loginUser.getUser().getId();
		List<Order> orderSessionList = orderRepository.findByStatusAndUserId(0, sessionId);
		List<Order> orderUserList = orderRepository.findByStatusAndUserId(0, user_id);
		
		if(orderSessionList.size() == 0 && orderUserList.size() == 0) {
			return "redirect:/index/showItemList";
		}
		
		if(orderUserList.size() == 0) {
			// ユーザIDで検索し、Orderが存在しない場合
			Order orderSession = orderSessionList.get(0);
			orderSession.setUserId(user_id);
			orderRepository.update(orderSession);
			return "redirect:/index/showItemList";
		} else {
			// ユーザIDで検索し、Orderが存在した場合
			Order orderUser = orderUserList.get(0);
			Order orderSession = orderSessionList.get(0);
			orderRepository.deleteByOrderId(orderSession.getId());
			for(OrderItem orderItemSession : orderSession.getOrderItemList()) {
				orderItemSession.setOrderId(orderUser.getId());
				orderItemRepository.update(orderItemSession);
			}
			return "redirect:/index/showItemList";
		}
	}

}
