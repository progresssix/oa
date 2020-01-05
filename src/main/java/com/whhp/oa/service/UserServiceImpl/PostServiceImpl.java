package com.whhp.oa.service.UserServiceImpl;

import com.whhp.oa.mapper.PostMapper;
import com.whhp.oa.pojo.Post;
import com.whhp.oa.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Override
    public List<Post> findAll() {
        return postMapper.findAll();
    }

    @Override
    public int deleteById(Long pid) {
       return postMapper.deleteById(pid);
    }

    @Override
    public void savePost(Post post) {
        postMapper.savePost(post);
    }

    @Override
    public Post findByPname(String name) {
        return postMapper.findByPname(name);
    }

    @Override
    public Post findById(Long pid) {
        return postMapper.findById(pid);
    }

    @Override
    public int update(Post post) {
        return postMapper.updatePost(post);
    }

    @Override
    public Post findByPnameNotpid(Long pid, String pname) {
        return postMapper.findByPnameNotpid(pid,pname);
    }


}
