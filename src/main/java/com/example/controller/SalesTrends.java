package com.example.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sales")
public class SalesTrends{

    @RequestMapping("/graph")
    // ビュー側にグラフで使う値を渡すためにModelを引数にとっておきます。
    public String hello(Model model) {

        // グラフの横軸と縦軸の値を、それぞれString、int型の配列に格納しておきます。

        // 横軸
        // ラベルです。String型の配列に、適当に値を入れておきます。
        String label[] = {"a","b","c","d","e","f","g","a","b","c","d","e","f","g"};

        // 縦軸
        // 具体的な値です。int型で、この値も適当です。
        int point[] = {5,3,7,1,8,3,4,22,34,67,0,3,22,34,67,0,3};

        // Modelに格納します。ビュー側でグラフ用の配列を受け取れるようにしておきます。
        model.addAttribute("label",label);
        model.addAttribute("point",point);

        return "/SalesTrends";
    }

}