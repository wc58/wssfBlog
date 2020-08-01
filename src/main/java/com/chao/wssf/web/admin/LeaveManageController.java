package com.chao.wssf.web.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.query.LeaveQuery;
import com.chao.wssf.service.ICommentService;
import com.chao.wssf.service.ILeaveService;
import com.chao.wssf.service.impl.LeaveServiceImpl;
import com.chao.wssf.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class LeaveManageController {

    @Autowired
    private ILeaveService leaveService;

    /**
     * 查询所有评论
     *
     * @return
     */
    @RequestMapping("getLeaves")
    @ResponseBody
    public Map<String, Object> getLeaves(LeaveQuery leaveQuery) {
        return getLeaveMap(false, leaveQuery);
    }

    /**
     * 查询删除评论
     *
     * @return
     */
    @RequestMapping("getDelLeaves")
    @ResponseBody
    public Map<String, Object> getDelLeaves(LeaveQuery leaveQuery) {
        return getLeaveMap(true, leaveQuery);
    }

    /**
     * 查询评论的核心方法
     */
    private Map<String, Object> getLeaveMap(Boolean isDel, LeaveQuery leaveQuery) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Page commentPage = leaveService.getComments(isDel, leaveQuery);
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

    /**
     * 逻辑删除评论
     *
     * @param id
     */
    @RequestMapping("deleteLeave")
    @ResponseBody
    public R deleteLeave(Integer id) {
        try {
            leaveService.deleteById(id);
            return R.OK();
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
    }

    /**
     * 真实删除评论
     *
     * @param id
     */
    @RequestMapping("deleteRealLeave")
    @ResponseBody
    public R deleteRealLeave(Integer id) {
        try {
            leaveService.deleteRealById(id);
            return R.OK();
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
    }


    /**
     * 还原评论
     *
     * @param id
     */
    @RequestMapping("restoreLeave")
    @ResponseBody
    public R restoreLeave(Integer id) {
        try {
            LeaveServiceImpl leaveServiceImpl = (LeaveServiceImpl) this.leaveService;
            leaveService.restoreCommentById(id);
            if (leaveServiceImpl.isRestore) {
                return R.OK();
            }
            R ok = R.ERROR().data("pName", leaveServiceImpl.pName);
            //重置数据
            leaveServiceImpl.isRestore = true;
            leaveServiceImpl.pName = "";
            return ok;
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
    }


    /**
     * 逻辑删除评论
     *
     * @param id
     */
    @RequestMapping("updateLeave")
    @ResponseBody
    public R updateLeave(Integer id, String content) {
        try {
            leaveService.updateLeave(id, content);
            return R.OK();
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
    }

}
