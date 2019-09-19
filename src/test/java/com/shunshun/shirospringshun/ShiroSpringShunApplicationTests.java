package com.shunshun.shirospringshun;

import com.shunshun.shirospringshun.dao.UserMapper;
import com.shunshun.shirospringshun.domain.User;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShiroSpringShunApplication.class)
public class ShiroSpringShunApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setId(2);
        user.setName("shunshun");
        user.setPassword(new Md5Hash("12345","shunshun",2).toString());
        Integer integer = userMapper.editPassword(user);
        System.out.println(integer);
    }

}
