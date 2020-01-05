package com.whhp.oa.service.UserServiceImpl;

import com.whhp.oa.mapper.DepartmentMapper;
import com.whhp.oa.pojo.Department;
import com.whhp.oa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;


    @Override
    public List<Department> finaAll() {
        return departmentMapper.findAll();
    }

    @Override
    public int deleteById(Long did) {
        return departmentMapper.deleteByPrimaryKey(did);
    }

    @Override
    public void save(Department department) {
        departmentMapper.addDepartment(department);
    }

    @Override
    public void updateById(Department department) {
        departmentMapper.UpdateById(department);
    }

    @Override
    public Department findById(Long did) {
        return departmentMapper.findOne(did);
    }
}
