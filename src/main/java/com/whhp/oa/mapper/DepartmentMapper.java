package com.whhp.oa.mapper;

import com.whhp.oa.pojo.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long did);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Long did);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    /*
     *查询所有的部门
     * @return  集合存储所有部门信息
     * */
    @Select("select * from department")
    public List<Department> findAll();

    @Delete("delete from department where did = #{did}")
    void deleteById(Long did);

    @Insert("insert into department values(null,#{dname},#{description})")
    void addDepartment(Department department);

    @Update("update department set dname=#{dname},description=#{description} where did=#{did}")
    void UpdateById(Department department);

    @Select("select * from department where did = #{did}")
    Department findOne(Long did);
}