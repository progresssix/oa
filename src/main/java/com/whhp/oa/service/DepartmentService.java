package com.whhp.oa.service;

import com.whhp.oa.mapper.DepartmentMapper;
import com.whhp.oa.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface DepartmentService {
    public List<Department> finaAll();
    public int deleteById(Long did);
    public void save(Department department);
    public void updateById(Department department);
    public Department findById(Long did);
}
