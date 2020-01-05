package com.whhp.oa.service;

import com.whhp.oa.pojo.UserPost;

import java.util.List;

public interface UserPostService {

    void saveUserAndPost(Long uid, Long[] pids);

    List<UserPost> findByUid(Long uid);

    void updatePost(Long uid, Long[] pids);
}
