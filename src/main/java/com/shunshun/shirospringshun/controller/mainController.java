package com.shunshun.shirospringshun.controller;

import com.shunshun.shirospringshun.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mainController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        System.out.println("去login界面");
        return "login";
    }
    @RequestMapping("/unAuth")
    public String unAuth(){
        System.out.println("去unAuth界面");
        return "unAuth";
    }
}
