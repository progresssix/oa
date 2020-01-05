package com.whhp.oa.mapper;

import com.whhp.oa.pojo.UserMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserMenuMapper {
    int insert(UserMenu record);

    int insertSelective(UserMenu record);

    @Delete("delete from user_menu where uid=#{uid}")
    void deleteByUid(Long uid);

    @Select("select * from user_menu where uid = #{uid}")
    List<UserMenu> findUserMenuByUid(Long uid);
}