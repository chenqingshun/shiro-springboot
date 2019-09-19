package com.shunshun.shirospringshun.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.shunshun.shirospringshun.filter.MyAuthFilter;
import com.shunshun.shirospringshun.myrealm.MyRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.handler.DefaultWebFilterChain;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class shiroConfig {



    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        Map<String,String> hashMap = new LinkedHashMap<>();
        hashMap.put("/login","anon");
        hashMap.put("/getKaptcha","anon");
        hashMap.put("/product/list","perms[product:list]");
        hashMap.put("/product/add","perms[product:add]");
        hashMap.put("/product/update","perms[product:update]");
        hashMap.put("/index","filter");
        hashMap.put("/**","authc");
        LinkedHashMap<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("filter",myAuthFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(hashMap);
        return shiroFilterFactoryBean;
    }
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager( MyRealm myRealm, CookieRememberMeManager cookieRememberMeManager){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRememberMeManager(cookieRememberMeManager);
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }

    @Bean
    public MyRealm myRealm(){
        return new MyRealm();
    }

    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
    @Bean
    public CookieRememberMeManager cookieRememberMeManager(SimpleCookie simpleCookie){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie);
        return cookieRememberMeManager;
    }

    @Bean
    public SimpleCookie simpleCookie(){
        SimpleCookie cookie = new SimpleCookie();
        cookie.setName("shunCookie");
        cookie.setMaxAge(120);
        cookie.setHttpOnly(true);
        return cookie;
    }

    @Bean
    public MyAuthFilter myAuthFilter(){
        return new MyAuthFilter();
    }
}
