package com.example.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.form.OrderForm;
import com.example.repository.OrderRepository;
import com.example.repository.UserRepository;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public void order(OrderForm form) throws ParseException {
       Order order = orderRepository.shallowLoad(form.getIntId());
       order.setDestinationName(form.getDestinationName());
       
       String delivery = form.getDeliveryDate() + " " + form.getDeliveryHour();
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh");
       Date parseDate = dateFormat.parse(delivery);
       Timestamp timestamp = new java.sql.Timestamp(parseDate.getTime());
       order.setDeliveryTime(timestamp);
       
       order.setDestinationAddress(form.getDestinationAddress());
       order.setDestinationEmail(form.getDestinationEmail());
       order.setDestinationTel(form.getDestinationTel());
       order.setDestinationZipcode(form.getDestinationZipcode());
       int status;
       if(form.getIntPaymentMethod().equals(1)) {
    	   status = 1;
       }else {
    	   status = 2;
       }
       order.setStatus(status);

       order.setPaymentMethod(form.getIntPaymentMethod());
       
       Date orderDate = new Date();
       order.setOrderDate(orderDate);
       
       int totalPrice;
       totalPrice = getTax() + getCalcTotalPrice();
       order.setTotalPrice(totalPrice);

       orderRepository.update(order);
       
	}
	
	private int getCalcTotalPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	private int getTax() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Order showDetail(Integer OrderId) {
		return orderRepository.deepLoad(OrderId);
	}
	

	/**
	 * OrderRepositoryから全ユーザーの注文履歴データを呼び出す.
	 * @return orderHistoryList
	 * hirokiokazaki
	 */
	public List<Order> load() {
		List<Order> orderHistoryList = orderRepository.findAll();
		return orderHistoryList;
	}
	
	/**
	 * OrderRepositoryからログインしているユーザーの注文履歴データを呼び出す.
	 * @return orderHistoryList
	 * hirokiokazaki
	 */
	public List<Order> loadByUserId(Integer userId) {
		List<Order> orderHistoryList = orderRepository.findByStatusThan0AndByUserId(userId);
		return orderHistoryList;
	}
	
	public User load(Integer id) {
		User user = userRepository.findById(id);
		return user;
	}
	
}
