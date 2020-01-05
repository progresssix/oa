package com.whhp.oa.service;

import com.whhp.oa.pojo.User;

import java.util.List;

public interface UserService {
    void addTwoUser(User user1, User user2);
    List<User> findAll();
    void deleteByUid(Long uid);

    User findByUsername(String username);

    Long save(User user);

    User findByUid(Long uid);

    User findByUidAndNotUsername(Long uid, String username);

    void updateUser(User user);

    User findByNameAndPwd(User user);
}
