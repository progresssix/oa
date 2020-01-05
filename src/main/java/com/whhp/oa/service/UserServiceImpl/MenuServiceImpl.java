package com.whhp.oa.service.UserServiceImpl;

import com.whhp.oa.mapper.MenuMapper;
import com.whhp.oa.mapper.UserMenuMapper;
import com.whhp.oa.pojo.Menu;
import com.whhp.oa.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findMenuByUid(Long uid) {
        return menuMapper.findMenuByUid(uid);
    }

    @Override
    public List<Menu> findAll() {
        return menuMapper.findAll();
    }
}
