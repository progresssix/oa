package com.whhp.oa.service;


import com.whhp.oa.pojo.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> findMenuByUid(Long uid);
    List<Menu> findAll();
}
