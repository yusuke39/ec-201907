package com.example.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	

	
	public void order(OrderForm form) throws ParseException {
       Order order = orderRepository.load(form.getIntId());
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
       order.setPaymentMethod(form.getIntPaymentMethod());
       orderRepository.update(order);
       
	}
	

	

}
