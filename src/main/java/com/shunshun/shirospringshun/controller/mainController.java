package com.shunshun.shirospringshun.controller;

import com.shunshun.shirospringshun.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class mainController {

    @RequestMapping("/index")
    public String index(){
        System.out.println("去index界面");
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request){
        System.out.println("去login界面");
        return "login";
    }
    @RequestMapping("/unAuth")
    public String unAuth(){
        System.out.println("去unAuth界面");
        return "unAuth";
    }
}
