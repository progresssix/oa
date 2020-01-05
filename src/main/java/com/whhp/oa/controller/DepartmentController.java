package com.whhp.oa.controller;

import com.whhp.oa.pojo.Department;
import com.whhp.oa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @RequestMapping("/list")
    public String list(Model model){
        List<Department> departments = departmentService.finaAll();
        model.addAttribute("departments",departments);
        return "department/list";
    }

    @RequestMapping("/delete")
    public String delete(Long did, HttpServletRequest request){
        departmentService.deleteById(did);
        return "redirect:/department/list.do";
    }

    @RequestMapping("/addPage")
    public String addPage(){
        return "department/add";
    }

    @RequestMapping("/save")
    public String save(Department department){
        departmentService.save(department);
        return "redirect:/department/list.do";
    }

    @RequestMapping("/updatePage")
    public String updatePage(Long did,Model model){
        Department department = departmentService.findById(did);
        model.addAttribute("department",department);
        return "department/update";
    }

    @RequestMapping("/update")
    public String update(Department department){
        departmentService.updateById(department);
        return "redirect:department/list.do";
    }
}
