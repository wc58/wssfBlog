package com.chao.wssf.web.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.Link;
import com.chao.wssf.query.LinkQuery;
import com.chao.wssf.service.ILeaveService;
import com.chao.wssf.service.ILinkService;
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
public class LinkManageController {

    @Autowired
    private ILinkService linkService;

    /**
     * 查询所有评论
     */
    @RequestMapping("getLinks")
    @ResponseBody
    public Map<String, Object> getLinks(LinkQuery linkQuery) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Page commentPage = linkService.getLinks(linkQuery);
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

    @RequestMapping("deleteLink")
    @ResponseBody
    public R deleteLink(Integer id) {
        try {
            linkService.deleteLinkById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
        return R.OK();
    }

    @RequestMapping("updateLink")
    @ResponseBody
    public R updateLink(Integer id, String title, String icon, String url, Integer sort, String des, String del) {
        try {
            linkService.updateLinkById(id, title, icon, url, sort, des, del);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
        return R.OK();
    }

}
