package com.chao.wssf.web.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.Label;
import com.chao.wssf.query.LabelQuery;
import com.chao.wssf.service.ILabelService;
import com.chao.wssf.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class LabelManageController {

    @Autowired
    private ILabelService labelService;

    /**
     * 查询所有标签
     *
     * @return
     */
    @RequestMapping("getLabels")
    @ResponseBody
    public Map<String, Object> getLabels(LabelQuery labelQuery) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Page<Label> commentPage = labelService.getLabels(labelQuery);
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


    @RequestMapping("updateLabel")
    @ResponseBody
    public R updateLabel(Integer id, String name, Integer sort) {
        labelService.updateLabel(id, name, sort);
        return R.OK();
    }


    @RequestMapping("deleteLabel")
    @ResponseBody
    public R deleteLabel(Integer id) {
        try {
            labelService.deleteLabelsById(id);
            return R.OK();
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
    }

    @RequestMapping("addLabel")
    @ResponseBody
    public R addLabel(String name) {
        try {
            labelService.addLabel(name);
            return R.OK();
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
    }

}
