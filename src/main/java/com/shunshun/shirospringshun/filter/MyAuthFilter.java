package com.shunshun.shirospringshun.filter;

import com.shunshun.shirospringshun.domain.User;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyAuthFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        if(!subject.isAuthenticated()&&subject.isRemembered()){
            Session session = subject.getSession();
            if(session.getAttribute("userName")==null){
            User user = (User) subject.getPrincipal();
                session.setAttribute("userName",user.getName());
                session.setAttribute("id",user.getId());
            }

        }

        // 这个方法本来只返回 subject.isAuthenticated() 现在我们加上 subject.isRemembered()
        // 让它同时也兼容remember这种情况
        return subject.isAuthenticated() || subject.isRemembered();
    }
}
