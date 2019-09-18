package com.shunshun.shirospringshun.controller;

import com.shunshun.shirospringshun.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @RequestMapping("/login")
    public String login(User user, Model model, HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getName(),user.getPassword());
        try {
            subject.login(usernamePasswordToken);
            User dbUser = (User) subject.getPrincipal();
            request.getSession().setAttribute("userName",dbUser.getName());
            return "redirect:/index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户不存在");
            return "login";
        }catch (IncorrectCredentialsException e) {
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }
}
