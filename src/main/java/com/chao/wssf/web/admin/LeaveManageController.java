package com.chao.wssf.web.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
     * @param page
     * @param limit
     * @param username
     * @param content
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping("getLeaves")
    @ResponseBody
    public Map<String, Object> getLeaves(Integer page, Integer limit, String username, String content, String startTime, String endTime) {
        return getLeaveMap(false, page, limit, username, content, startTime, endTime);
    }

    /**
     * 查询删除评论
     *
     * @param page
     * @param limit
     * @param username
     * @param content
     * @param contentSize
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping("getDelLeaves")
    @ResponseBody
    public Map<String, Object> getDelLeaves(Integer page, Integer limit, String username, String content, String contentSize, String startTime, String endTime) {
        return getLeaveMap(true, page, limit, username, content, startTime, endTime);
    }

    /**
     * 查询评论的核心方法
     *
     * @param page
     * @param limit
     * @param username
     * @param content
     * @param startTime
     * @param endTime
     * @return
     */
    private Map<String, Object> getLeaveMap(Boolean isDel, Integer page, Integer limit, String username, String content, String startTime, String endTime) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Page commentPage = leaveService.getComments(isDel, page, limit, username, content, startTime, endTime);
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
