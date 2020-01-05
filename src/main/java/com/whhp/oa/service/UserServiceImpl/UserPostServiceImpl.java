package com.whhp.oa.service.UserServiceImpl;

import com.whhp.oa.mapper.UserPostMapper;
import com.whhp.oa.pojo.UserPost;
import com.whhp.oa.service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserPostServiceImpl implements UserPostService {

    @Autowired
    private UserPostMapper userPostMapper;

    @Override
    public void saveUserAndPost(Long uid, Long[] pids) {
        for (Long pid : pids) {
            userPostMapper.save(new UserPost(uid,pid));
        }
    }

    @Override
    public List<UserPost> findByUid(Long uid) {
        return userPostMapper.findByUid(uid);
    }

    @Override
    public void updatePost(Long uid, Long[] pids) {
        userPostMapper.deleteByUid(uid);
        for (Long pid : pids) {
            userPostMapper.save(new UserPost(uid,pid));
        }
    }
}
