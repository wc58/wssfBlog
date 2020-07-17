package com.chao.wssf.web.admin;

import com.chao.wssf.entity.Admin;
import com.chao.wssf.mapper.AdminMapper;
import com.chao.wssf.service.IAdminService;
import com.chao.wssf.service.impl.AdminServiceImpl;
import com.chao.wssf.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    /**
     * 管理员登录
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public R login(String username, String password, HttpSession session) {
        try {
            //已经登录过
            if (session.getAttribute("admin") != null) {
                return R.OK();
            }
            //验证
            Admin admin = adminService.checkAdmin(username, password);
            if (admin != null) {
                admin.setPassword(null);
                session.setAttribute("admin", admin);
                return R.OK();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
        return R.ERROR();
    }

    /**
     * 管理员登录
     *
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "admin/login";
    }


    /**
     * 管理员首页
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "admin/index";
    }

}
