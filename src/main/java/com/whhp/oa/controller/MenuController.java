package com.whhp.oa.controller;

import com.whhp.oa.pojo.Menu;
import com.whhp.oa.pojo.User;
import com.whhp.oa.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/showMenusByUid")
    @ResponseBody
    public HashMap<String,List<Menu>> showMenusByUid(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Menu> menuitemList = menuService.findMenuByUid(user.getUid());
        HashMap<String,List<Menu>> hm = new HashMap<>();
        for (Menu menu : menuitemList) {
            menu.setTarget("right");
        }
        hm.put("menuitemList",menuitemList);
        return hm;
    }
}
