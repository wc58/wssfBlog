package com.chao.wssf.web.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.User;
import com.chao.wssf.service.ILinkService;
import com.chao.wssf.service.IUserService;
import com.chao.wssf.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class UserManageController {

    @Autowired
    private IUserService userService;

    /**
     * 查询所有评论
     *
     * @param page
     * @param limit
     * @param username
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping("getUsers")
    @ResponseBody
    public Map<String, Object> getLinks(Integer page, Integer limit, String username, String startTime, String endTime) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Page commentPage = userService.getUsers(page, limit, username, startTime, endTime);
            //封装数据
            map.put("code", 0);
            map.put("count", commentPage.getTotal());
            map.put("data", commentPage.getRecords());
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1);
            return map;
        }
    }

    @RequestMapping("deleteUser")
    @ResponseBody
    public R deleteUser(Integer id) {
        try {
            userService.deleteUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
        return R.OK();
    }

    @RequestMapping("updateUser")
    @ResponseBody
    public R updateUser(Integer id, String name, String icon, String thirdId, String email, String banned) {
        try {
            userService.updateUserById(id, name, icon, thirdId, email, banned);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
        return R.OK();
    }

}
