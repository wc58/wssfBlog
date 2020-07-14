package com.chao.wssf.web.controller;

import com.chao.wssf.entity.Leave;
import com.chao.wssf.entity.User;
import com.chao.wssf.pojo.FullLeave;
import com.chao.wssf.service.ILeaveService;
import com.chao.wssf.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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


    @PostMapping("topReply")
    @ResponseBody
    public R topReply(Leave leave, HttpServletRequest request) {
        leave.setSite(request.getRemoteAddr());
        if (leave.getPid() == null)
            leave.setPid(0);
        try {
            leaveService.addReply(leave);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
        return R.OK();
    }

}
