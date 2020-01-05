package com.whhp.oa.service.UserServiceImpl;

import com.whhp.oa.mapper.UserMapper;
import com.whhp.oa.mapper.UserMenuMapper;
import com.whhp.oa.mapper.UserPostMapper;
import com.whhp.oa.pojo.User;
import com.whhp.oa.service.UserService;
import com.whhp.oa.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserPostMapper userPostMapper;

    @Autowired
    private UserMenuMapper userMenuMapper;

    @Override
    public void addTwoUser(User user1,User user2) {
        userMapper.addUser(user1);
        userMapper.addUser(user2);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void deleteByUid(Long uid) {
        userPostMapper.deleteByUid(uid);
        userMenuMapper.deleteByUid(uid);
        userMapper.deleteByUid(uid);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public Long save(User user) {
        userMapper.save(user);
        return user.getUid();
    }

    @Override
    public User findByUid(Long uid) {
        return userMapper.findById(uid);
    }

    @Override
    public User findByUidAndNotUsername(Long uid, String username) {
        return userMapper.findByUidAndNotUsername(uid,username);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User findByNameAndPwd(User user) {
        String md5Password = MD5Utils.md5(user.getPassword());
        user.setPassword(md5Password);
        return userMapper.findByNameAndPwd(user);
    }
}
