package com.whhp.oa.service.UserServiceImpl;

import com.whhp.oa.mapper.UserMenuMapper;
import com.whhp.oa.pojo.UserMenu;
import com.whhp.oa.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserMenuServiceImpl implements UserMenuService {
    @Autowired
    private UserMenuMapper userMenuMapper;

    @Override
    public List<UserMenu> findUserMenuByUid(Long uid) {
        return userMenuMapper.findUserMenuByUid(uid);
    }
}
