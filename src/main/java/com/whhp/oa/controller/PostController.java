package com.whhp.oa.controller;


import com.whhp.oa.pojo.Post;
import com.whhp.oa.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping("/list")
    public String list(Model model){
        List<Post> posts = postService.findAll();
        model.addAttribute("posts",posts);
        return "post/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public HashMap deleteById(Long pid){
        int i = postService.deleteById(pid);
        if (i>0){
            HashMap<String, String> hm = new HashMap<>();
            hm.put("message","删除成功");
            return hm;
        }
        return null;
    }
    @RequestMapping("/addPage")
    public String addPage(){
        return "post/add";
    }
    @RequestMapping("/save")
    public String save(Post post){
        postService.savePost(post);
        return "redirect:/post/list.do";
    }

    @RequestMapping("/checkPname")
    @ResponseBody
    public HashMap<String,String> checkPname(String pname){
        HashMap<String, String> hm = new HashMap<>();
        Post post = postService.findByPname(pname);
        if (post == null){
            hm.put("message","岗位名称可以使用");
        }else {
            hm.put("message","岗位名称重复");
        }
        return hm;
    }

    @RequestMapping("/edit")
    public String edit(Model model,Long pid){
        Post post = postService.findById(pid);
        model.addAttribute("post",post);
        return "post/update";
    }

    @RequestMapping("/update")
    @ResponseBody
    public HashMap<String,String> update(Post post, HttpServletRequest request){
        HashMap<String, String> hm = new HashMap<>();
        int update = postService.update(post);
        if (update>0){
            hm.put("message","更新成功");
            hm.put("url",request.getContextPath()+"/post/list.do");
        }else {
            hm.put("message","更新失败");
            hm.put("url",request.getContextPath()+"/post/list.do");
        }
        return hm;
    }

    @RequestMapping("/checkUpdatePname")
    @ResponseBody
    public HashMap<String,String> checkUpdatePname(Long pid,String pname){
        Post post = postService.findByPnameNotpid(pid, pname);
        HashMap<String,String> hm = new HashMap<>();
        if (post ==null){
            hm.put("message","岗位名称可以使用");
        }else {
            hm.put("message","岗位名称重复");
        }
        return hm;
    }
}
