package com.shunshun.shirospringshun.dao;

import com.shunshun.shirospringshun.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from t_user where name = #{name}")
    public User findUserByUserName(String name);

    @Select("SELECT p.permission FROM t_user u INNER JOIN t_user_role ur ON u.id=ur.user_id INNER JOIN t_role_permission rp ON ur.role_id=rp.role_id INNER JOIN t_permission p ON rp.permission_id=p.id WHERE u.id =#{id}")
    public List<String> findPermissionById(Integer id);
}
