package com.shunshun.shirospringshun.myrealm;

import com.shunshun.shirospringshun.dao.UserMapper;
import com.shunshun.shirospringshun.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<String> permissions = userMapper.findPermissionById(user.getId());
        if (permissions!=null){
            for(String str : permissions){
                simpleAuthorizationInfo.addStringPermission(str);
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User dbUser =userMapper.findUserByUserName(token.getUsername());
        if(dbUser==null){
            return null;
        }
        new SimpleAuthenticationInfo();
        return new SimpleAuthenticationInfo(dbUser,dbUser.getPassword(),"");
    }
}
