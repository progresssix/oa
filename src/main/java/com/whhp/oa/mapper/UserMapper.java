package com.whhp.oa.mapper;

import com.whhp.oa.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    @Select("select * from user where uid = #{uid}")
    @Results(@Result(column = "department_id",property = "departmentId"))
    User findById(Long uid);

    @Insert("insert into user values(null,#{username},#{password},#{email},#{sex},#{departmentId})")
    void addUser(User user);

    @Update("update user set username=#{username},password=#{password},email=#{email},sex=#{sex},department_id=#{departmentId} where uid=#{uid}")
    void updateUser(User user);

    @Delete("delete from user where uid=#{uid}")
    void deleteById(Long uid);

    @Select("select * from user")
    @Results({
            @Result(column = "department_id",property = "departmentId"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "department_id",property = "department",
                    one = @One(select = "com.whhp.oa.mapper.DepartmentMapper.findOne")),
            @Result(column = "uid",property = "posts",
                    many = @Many(select = "com.whhp.oa.mapper.PostMapper.findByUid"))
    })
    List<User> findAll();

    @Delete("delete from user where uid = #{uid}")
    void deleteByUid(Long uid);

    @Select("select * from user where username=#{username}")
    User findByUsername(String username);

    @Insert("insert into user values(null,#{username},#{password},#{email},#{sex},#{departmentId})")
    @Options(useGeneratedKeys = true,keyProperty = "uid")
    void save(User user);

    @Select("select * from user where username = #{username} and uid != #{uid}")
    User findByUidAndNotUsername(@Param("uid") Long uid,@Param("username") String username);

    @Select("select * from user where username=#{username} and password=#{password}")
    User findByNameAndPwd(User user);
}