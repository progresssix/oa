package com.whhp.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class IndexController {
    @RequestMapping("/top")
    public String top() {
        return "frame/top";
    }
    @RequestMapping("/left")
    public String left() {
        return "frame/left";
    }
    @RequestMapping("/right")
    public String right() {
        return "frame/right";
    }
    @RequestMapping("/bottom")
    public String bottom() {
        return "frame/bottom";
    }
}
