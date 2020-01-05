package com.whhp.oa.mapper;

import com.whhp.oa.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void findAll(){
        System.out.println(userMapper.findAll().size());
    }

    @Test
    public void findByID(){
        User user = userMapper.findById(15L);
        System.out.println(user);
    }
    @Test
    public void addUser(){
        userMapper.addUser(new User("老王","123","24567889@qq.com","男",4));
    }

    @Test
    public void findAll1(){
        User user = userMapper.selectByPrimaryKey(19L);
        System.out.println(user);
    }

    @Test
    public void updateUser(){
        User user = userMapper.findById(23L);
        user.setUsername("赵敏");
        user.setSex("女");
        userMapper.updateUser(user);
    }

    @Test
    public void deleteById(){
        userMapper.deleteById(23L);
    }
}