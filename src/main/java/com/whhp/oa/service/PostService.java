package com.whhp.oa.service;

import com.whhp.oa.pojo.Post;

import java.util.List;

public interface PostService {
    List<Post> findAll();
    int deleteById(Long pid);
    void savePost(Post post);
    Post findByPname(String name);
    Post findById(Long pid);
    int update(Post post);

    Post findByPnameNotpid(Long pid, String pname);
}
