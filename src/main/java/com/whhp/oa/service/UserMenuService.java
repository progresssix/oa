package com.whhp.oa.service;

import com.whhp.oa.pojo.UserMenu;

import java.util.List;

public interface UserMenuService {
    List<UserMenu> findUserMenuByUid(Long uid);
}
