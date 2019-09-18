package com.shunshun.shirospringshun.config;


import com.shunshun.shirospringshun.myrealm.MyRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.handler.DefaultWebFilterChain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class shiroConfig {

    /***
    * @Description:  这是shiro的过滤器吧?
    * @Param: []
    * @return: org.springframework.web.server.handler.DefaultWebFilterChain
    * @Author: cqs
    * @Date: 2019/9/19 0:16
    */
    /*@Bean
   public DefaultShiroFilterChainDefinition defaultShiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition defaultShiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();
        return defaultShiroFilterChainDefinition;
    }*/
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        Map<String,String> hashMap = new LinkedHashMap<>();
        hashMap.put("/login","anon");
        hashMap.put("/toLogin","anon");
        hashMap.put("/product/list","perms[product:list]");
        hashMap.put("/product/add","perms[product:add]");
        hashMap.put("/product/update","perms[product:update]");

        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
        hashMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(hashMap);
        return shiroFilterFactoryBean;
    }
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager( MyRealm myRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }

    @Bean
    public MyRealm myRealm(){
        return new MyRealm();
    }

}
