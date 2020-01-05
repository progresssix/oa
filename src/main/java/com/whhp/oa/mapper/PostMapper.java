package com.whhp.oa.mapper;

import com.whhp.oa.pojo.Post;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface PostMapper {
    int deleteByPrimaryKey(Long pid);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Long pid);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

    @Select("select * from post")
    public List<Post> findAll();

    @Delete("delete from post where pid = #{pid}")
    public int deleteById(Long pid);

    @Insert("insert into post values(null,#{pname},#{description})")
    void savePost(Post post);

    @Select("select * from post where pname = #{pname}")
    public Post findByPname(String pname);

    @Select("select * from post where pid = #{pid}")
    public Post findById(Long pid);

    @Update("update post set pname=#{pname},description=#{description} where pid=#{pid}")
    public Integer updatePost(Post post);

    @Select("select * from post where pname=#{pname} and pid !=#{pid}")
    public Post findByPnameNotpid(@Param("pid") Long pid,@Param("pname") String pname);

    @Select("select * from post where pid in (select post_id from user_post where user_id = #{uid})")
    List<Post> findByUid(Long uid);
}