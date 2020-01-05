package com.whhp.oa.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.whhp.oa.pojo.Menu;
import com.whhp.oa.pojo.UserMenu;
import com.whhp.oa.service.MenuService;
import com.whhp.oa.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/usermenu")
public class UserMenuController {
    @Autowired
    private MenuService menuService;
    
    @Autowired
    private UserMenuService userMenuService;

    @RequestMapping("/showMenus")
    @ResponseBody
    public HashMap<String, List> showMenus(Long uid, Model model){
        //查询所有菜单
        List<Menu> menus = menuService.findAll();
        //查询当前id具有的菜单
        List<UserMenu> userMenus = userMenuService.findUserMenuByUid(uid);

        for (UserMenu userMenu : userMenus) {
            Long mid = userMenu.getMid();
            for (Menu menu : menus) {
                if (menu.getMid() == mid){
                    menu.setChecked(true);
                }
            }
        }
        HashMap<String, List> hm = new HashMap<>();
        hm.put("menuitemList",menus);
        return hm;
    }
}
