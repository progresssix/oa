package com.whhp.oa.mapper;

import com.whhp.oa.pojo.UserPost;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserPostMapper {
    int insert(UserPost record);

    int insertSelective(UserPost record);

    @Delete("delete from user_post where user_id=#{uid}")
    void deleteByUid(Long uid);

    @Insert("insert into user_post values(#{userId},#{postId})")
    void save(UserPost userPost);

    @Select("select * from user_post where user_id = #{uid}")
    @Results({
            @Result(column = "user_id",property = "userId"),
            @Result(column = "post_id",property = "postId")
    })
    List<UserPost> findByUid(Long uid);
}