package com.chao.wssf.web.controller;

import com.chao.wssf.service.IDiaryService;
import com.chao.wssf.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("diary")
public class DiaryController {

    @Autowired
    private IDiaryService diaryService;

    /**
     * 跳转到日记页
     *
     * @param model
     * @param key
     * @return
     */
    @RequestMapping("page")
    public String page(Model model, @RequestParam(required = false) String key) {
        if (key != null && key.equals("20020508oifurhaysiuhfgieuawhgiuhyefgieuhf")) {
            R r = checkKey(key);
            //如果没有密钥直接跳到首页
            if (r.getCode() != 1000)
                return "index";
        }
        model.addAttribute("diaries", diaryService.getAllDiaries());
        return "diary";
    }

    @RequestMapping("key")
    @ResponseBody
    public R key(@RequestParam String key) {
        return checkKey(key);
    }

    /**
     * 校验密钥
     *
     * @param key
     * @return
     */
    private R checkKey(String key) {
        SimpleDateFormat hh = new SimpleDateFormat("hh");
        SimpleDateFormat dd = new SimpleDateFormat("dd");
        Date date = new Date();
        int hhInteger = Integer.parseInt(hh.format(date));
        int ddInteger = Integer.parseInt(dd.format(date));
        //密码公式：小时 x 日 x 2002
        int rightKey = hhInteger * ddInteger * 2002;

        try {
            if (key != null) {
                if (Integer.valueOf(key).equals(rightKey))
                    return R.OK();
            }
        } catch (NumberFormatException e) {
            //可能出现转换错误
            e.printStackTrace();
            return R.ERROR();
        }
        return R.ERROR();
    }

}
