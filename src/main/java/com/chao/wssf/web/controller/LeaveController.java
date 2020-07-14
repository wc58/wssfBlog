package com.chao.wssf.web.controller;

import com.chao.wssf.pojo.FullLeave;
import com.chao.wssf.service.ILeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("leave")
public class LeaveController {

    @Autowired
    private ILeaveService leaveService;

    @RequestMapping("leavePage")
    public String getAllLeaves(Model model) {
        List<FullLeave> leaves = leaveService.getAllFullComments();
        model.addAttribute("leaves", leaves);
        return "leave";
    }


}
