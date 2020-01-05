package com.whhp.oa.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whhp.oa.mapper.UserPostMapper;
import com.whhp.oa.pojo.Department;
import com.whhp.oa.pojo.Post;
import com.whhp.oa.pojo.User;
import com.whhp.oa.pojo.UserPost;
import com.whhp.oa.service.DepartmentService;
import com.whhp.oa.service.PostService;
import com.whhp.oa.service.UserPostService;
import com.whhp.oa.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
@Transactional
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserPostService userPostService;

    @RequestMapping("/list")
    public String list(Model model,@RequestParam(defaultValue = "1") Integer currPage,@RequestParam(defaultValue = "3") Integer pageSize){
        PageHelper.startPage(currPage,pageSize);
        List<User> users = userService.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        model.addAttribute("users",users);
        model.addAttribute("page",pageInfo);
        return "user/list";
    }

    @RequestMapping("/delete")
    public String delete(Long uid){
        userService.deleteByUid(uid);
        return "redirect:/user/list.do";
    }

    @RequestMapping("/addPage")
    public String addPage(Model model){
        List<Department> departments = departmentService.finaAll();
        List<Post> posts = postService.findAll();
        model.addAttribute("departments",departments);
        model.addAttribute("posts",posts);
        return "user/add";
    }

    @RequestMapping("/checkUsername")
    @ResponseBody
    public HashMap<String,String> checkUsername(String username){
        User user = userService.findByUsername(username);
        HashMap<String, String> hm = new HashMap<>();
        if (user==null){
            hm.put("message","用户名可以使用");
        }else {
            hm.put("message","用户名已重复");
        }
        return hm;
    }

    @RequestMapping("/save")
    public String save(User user, Long[] pids){
        Long uid = userService.save(user);
        userPostService.saveUserAndPost(uid,pids);
        return "redirect:/user/list.do";
    }

    @RequestMapping("/edit")
    public String edit(Long uid,Model model){
        User user = userService.findByUid(uid);
        List<Department> departments = departmentService.finaAll();
        List<Post> posts = postService.findAll();
        List<UserPost> userPosts = userPostService.findByUid(uid);
        model.addAttribute("user",user);
        model.addAttribute("departments",departments);
        model.addAttribute("posts",posts);
        model.addAttribute("userPosts",userPosts);
        return "user/update";
    }

    @RequestMapping("checkUpdateUserName")
    @ResponseBody
    public HashMap<String,String> checkUpdateUserName(Long uid,String username){
        User user = userService.findByUidAndNotUsername(uid,username);
        HashMap<String, String> hm = new HashMap<>();
        if (user==null){
            hm.put("message","用户名可以使用");
        }else {
            hm.put("message","用户名已重复");
        }
        return hm;
    }

    @RequestMapping("/update")
    public String update(User user,Long[] pids){
        userService.updateUser(user);
        userPostService.updatePost(user.getUid(),pids);
        return "redirect:/user/list.do";
    }

    @RequestMapping("/login")
    public String login(User user, Model model, HttpSession session){
        User existuser = userService.findByNameAndPwd(user);
        if (existuser==null){
            model.addAttribute("message","用户名或者密码错误");
            return "login";
        }else {
            session.setAttribute("user",existuser);
            return "frame/index";
        }

    }
}
