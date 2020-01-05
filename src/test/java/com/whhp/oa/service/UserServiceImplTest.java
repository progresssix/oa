package com.whhp.oa.service;

import com.whhp.oa.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class UserServiceImplTest {

    @Autowired
    private UserService service;

    @Test
    public void addTwoUser() {
        User user1 = new User("张三丰", "456", "245956700@qq.com", "男", 4);
        User user2 = new User("张翠山", "789", "245956700@qq.com", "男", 4);

        service.addTwoUser(user1,user2);
    }
}