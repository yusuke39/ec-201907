package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.form.ShoppingCartForm;
import com.example.repository.OrderRepository;
import com.example.service.OrderItemService;
import com.example.service.ShoppingCartService;

@Controller
@RequestMapping("/shopingCart")
public class ShoppingCartController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired 
	private OrderItemService orderItemService;
	
	@ModelAttribute
	public ShoppingCartForm setShoppingCartForm() {
		return new ShoppingCartForm();
	}

	
	/**
	 * ショッピングカートに商品を加える.
	 * 
	 * @param form
	 * @return　ショッピングカートの中身
	 */
	@RequestMapping("/addItem")
	public String addItemToCart(ShoppingCartForm form, @AuthenticationPrincipal LoginUser loginUser ,Model model) {
		
		//セッションIDを取得して、数値に変換する
		String id = session.getId();
		int x = 0;
		int y = 0;
		for (int i = 0; i + 10 <= id.length(); i += 30) {
		    x = Integer.parseUnsignedInt(id.substring(i, i + 8), 16);
		    y = Integer.parseInt(id.substring(i + 8, i + 10), 16);
		}
		int sessionId = (x + y);
		
		//ショッピングカートに追加する
		Order order = new Order();
		//合計の値段をオーダーオブジェクトに渡す
		order.setTotalPrice(Integer.parseInt(form.getTotalPrice()));
		//ログインしていない場合はsessionIdからランダムの数値をオーダーオブジェクトに渡す
		//ログインしている場合はuser_idを渡す
		int user_id = 0;
		if(loginUser == null) {
			user_id = sessionId;
		} else {
			user_id = loginUser.getUser().getId();
		}
		 order.setUserId(user_id);
	
		OrderItem orderItem = new OrderItem();
		char[] size = form.getSize().toCharArray();
		orderItem.setItemId(Integer.parseInt(form.getItemId()));
		orderItem.setSize(size[0]);
		orderItem.setQuantity(Integer.parseInt(form.getQuantity()));
		List<OrderTopping> orderToppingList = new ArrayList<>();
		
		if(form.getToppingList() != null) {
		for(Integer toppingId : form.getToppingList()) {
			OrderTopping orderTopping = new OrderTopping();
			orderTopping.setToppingId(toppingId);
			orderToppingList.add(orderTopping);
		}
}
		
		orderItem.setOrderToppingList(orderToppingList);
		
		shoppingCartService.addItemToCart(order,orderItem);
		
		return "redirect:/shopingCart/showCart";
	}
	
	/**
	 * ショッピングカートの中身を表示する.
	 * 
	 * @param model
	 * @param loginUser
	 * @return　ショッピングカートの中身を表示
	 */
	@RequestMapping("/showCart")
	public String showCart(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		
		int status = 0;
		
		String id = session.getId();
		int x = 0;
		int y = 0;
		for (int i = 0; i + 10 <= id.length(); i += 30) {
		    x = Integer.parseUnsignedInt(id.substring(i, i + 8), 16);
		    y = Integer.parseInt(id.substring(i + 8, i + 10), 16);
		}
		int sessionId = (x + y);
		
		int user_id = 0;
		if(loginUser == null) {
			user_id = sessionId;
		} else {
			user_id = loginUser.getUser().getId();
		}
		 
		List<Order> orderList = orderRepository.findByStatusAndUserId(status, user_id);
		
		if(orderList.size() == 0) {
			Order order = new Order();
			order.setOrderItemList(new ArrayList<OrderItem>());
			model.addAttribute("order", order);
		} else {
			Order order = orderList.get(0);
			order = orderRepository.deepLoad(order.getId());
			model.addAttribute("order", order);
		}
		return "cart_list";
	}
	
	/**
	 * カートに入ってる商品を削除する操作
	 * @return cart_list(ショッピングカートページ)
	 * hirokiokazaki
	 */
	@RequestMapping("delete")
	public String delete(Integer id,Model model){
		orderItemService.delete(id);
		return "redirect:/shopingCart/showCart";
	}
}
