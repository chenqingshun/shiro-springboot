package com.shunshun.shirospringshun.controller;

import com.shunshun.shirospringshun.domain.User;
import com.shunshun.shirospringshun.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.ldap.PagedResultsControl;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @RequestMapping("/login")
    public String login(User user,String remember,String code, Model model, HttpServletRequest request){
        if(code!=null){
            Object verifyCode = request.getSession().getAttribute("verifyCode");
            if (!code.equals(verifyCode)){
                model.addAttribute("msg","验证码不对啊");
                return "/Login";
            }
        }else {
            model.addAttribute("msg","验证码不能为空");
            return "/Login";

        }
        Subject subject = SecurityUtils.getSubject();
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),user.getName(),2);

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getName(),md5Hash.toString());
        if(remember!=null&&remember.equals("1")){
            usernamePasswordToken.setRememberMe(true);
        }

        try {
            subject.login(usernamePasswordToken);
            User dbUser = (User) subject.getPrincipal();
            request.getSession().setAttribute("userName",dbUser.getName());
            request.getSession().setAttribute("id",dbUser.getId());
            return "redirect:/index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户不存在");
            return "login";
        }catch (IncorrectCredentialsException e) {
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/toLogin";
    }

    @RequestMapping("editPassword")
    public String editPassword(User user, HttpServletRequest request, Model model){
        Integer column = userServiceImpl.editPassword(user);
        if (column>0){
            model.addAttribute("passwordMsg","修改成功");
            return "/index";
        }
            model.addAttribute("passwordMsg","修改失败");

        return "/index";
    }
}
