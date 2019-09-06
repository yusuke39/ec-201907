package com.example.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.repository.OrderRepository;

@Controller
@RequestMapping("/sales")
public class SalesTrends{

	@Autowired
	private OrderRepository orderRepository;
	
    @SuppressWarnings("null")
	@RequestMapping("/graph")
    // ビュー側にグラフで使う値を渡すためにModelを引数にとっておきます。
    public String hello(Model model) {

    	 List<Integer> orderList = orderRepository.findByTotalPrice();
    	 List<Integer> oldOrderList = orderRepository.findByTotalPrice();
//    	 List<Date> label = new ArrayList<>();
    	 
    	Integer point[] = new Integer[orderList.size()];
    	for(int i = 0; i < orderList.size(); i++) {
    		System.out.println(orderList.get(i));
    		point[i] = orderList.get(i);
//    		 pointorderList.get(i);
//    		 label.add(orderList.get(i).getOrderDate());
    	}
    	
        // グラフの横軸と縦軸の値を、それぞれString、int型の配列に格納しておきます。

        // 横軸
        // ラベルです。String型の配列に、適当に値を入れておきます。
//        String label[] = {"a","b","c","d","e","f","g","a","b","c","d","e","f","g"};
        String dateLabel[] = {"2018_10月","2018_11","2018_12","2019_","2019_2","2019_3","2019_4","2019_5","2019_6","2019_7","2019_8","2019_9"};
        
        String oldDateLabel[] = {"2017_10月","2017_11","2017_12","2018_","2018_2","2018_3","2018_4","2018_5","2018_6","2018_7","2018_8","2018_9"};

        // 縦軸
        // 具体的な値です。int型で、この値も適当です。
//        Integer point[] = {5,3,7,1,8,3,4,22,34,67,0,3,22,34,67,0,3};

        // Modelに格納します。ビュー側でグラフ用の配列を受け取れるようにしておきます。
        model.addAttribute("label",dateLabel);
        model.addAttribute("oldDateLabel",oldDateLabel);
        model.addAttribute("point",point);

        return "/SalesTrends";
//        return "/SalesTrends";
    }
    @SuppressWarnings("null")
    @RequestMapping("/graph2")
    public String oldhello(Model model) {

   	 List<Integer> orderList = orderRepository.findByTotalPrice();
   	 List<Integer> oldOrderList = orderRepository.findByOldTotalPrice();
   	 
   	 
   	Integer point[] = new Integer[orderList.size()];
   	Integer pointOld[] = new Integer[oldOrderList.size()];
   	
   	for(int i = 0; i < orderList.size(); i++) {
   		point[i] = orderList.get(i);
   	}
	for(int i = 0; i < oldOrderList.size(); i++) {
		pointOld[i] = oldOrderList.get(i);
	
	}
       // グラフの横軸と縦軸の値を、それぞれString、int型の配列に格納しておきます。

       // 横軸
       // ラベルです。String型の配列に、適当に値を入れておきます。
//       String label[] = {"a","b","c","d","e","f","g","a","b","c","d","e","f","g"};
//       String dateLabel[] = {"10月","11月","12月","1月","2月","3月","4月","5月","6月","7月","8月","9月"};
       String dateLabel[] = {"2018_10月","2018_11","2018_12","2019_","2019_2","2019_3","2019_4","2019_5","2019_6","2019_7","2019_8","2019_9"};
//       String oldDateLabel[] = {"2017_10月","2017_11","2017_12","2018_","2018_2","2018_3","2018_4","2018_5","2018_6","2018_7","2018_8","2018_9"};

       // 縦軸
       // 具体的な値です。int型で、この値も適当です。
//       Integer point[] = {5,3,7,1,8,3,4,22,34,67,0,3,22,34,67,0,3};

       // Modelに格納します。ビュー側でグラフ用の配列を受け取れるようにしておきます。
       model.addAttribute("label","2018_10月,2018_11");
       StringBuilder strPoint = new StringBuilder("[");
       for(Integer sales : point) {
    	   strPoint.append(sales + ",");
       }
       strPoint.setLength(strPoint.length()-1);
       strPoint.append("]");
       
       
       StringBuilder strPointOld = new StringBuilder("[");
       for(Integer oldSales : pointOld) {
    	strPointOld.append(oldSales+ ",");
       }
//       strPointOld.setLength(strPoint.length()-1);
       strPointOld.append("]");
       
       System.out.println(strPoint);
       System.out.println();
       System.out.println(strPointOld);
       
       model.addAttribute("point",strPoint);
       model.addAttribute("pointOld",strPointOld);
    
       return "/grahu2";
}
}