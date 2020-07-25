package com.chao.wssf.web.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.service.IDiaryService;
import com.chao.wssf.service.ILinkService;
import com.chao.wssf.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class DiaryManageController {

    @Autowired
    private IDiaryService diaryService;

    /**
     * 查询所有评论
     *
     * @param page
     * @param limit
     * @param startTime
     * @param endTime
     * @return
     */

    @RequestMapping("getDiary")
    @ResponseBody
    public Map<String, Object> getDiary(Integer page, Integer limit, String startTime, String endTime) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Page commentPage = diaryService.getDiary(page, limit, startTime, endTime);
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
     * 添加日记
     *
     * @param id
     * @return
     */
    @RequestMapping("addDiary")
    @ResponseBody
    public R addDiary(Integer id, String content) {
        try {
            Integer selectId = diaryService.addDiary(id, content);
            return R.OK().data("id", selectId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
    }

    @RequestMapping("deleteDiary")
    @ResponseBody
    public R deleteDiary(Integer id) {
        try {
            diaryService.deleteDiaryById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
        return R.OK();
    }
}
