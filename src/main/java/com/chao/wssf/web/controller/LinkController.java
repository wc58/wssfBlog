package com.chao.wssf.web.controller;

import com.chao.wssf.entity.Link;
import com.chao.wssf.entity.User;
import com.chao.wssf.service.ILinkService;
import com.chao.wssf.util.R;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequestMapping("link")
@Controller
public class LinkController {


    @Autowired
    private ILinkService linkService;

    @RequestMapping("page")
    public String page(Model model) {
        model.addAttribute("links", linkService.getAllLinks());
        return "link";
    }

    /**
     * 添加友链
     *
     * @return
     */
    @RequestMapping("applyLink")
    @ResponseBody
    public R applyLink(String title, String icon, String url, String desc, HttpSession session) {

        User user = (User) session.getAttribute("user");
        Integer id = user.getId();
        Link link = linkService.getLinkByUserId(id);
        //表示已经申请过
        if (link != null) {
            return R.ERROR();
        } else {
            try {
                linkService.addLink(title, icon, url, desc, id);
                session.setAttribute("isApply", title);
            } catch (Exception e) {
                e.printStackTrace();
                return R.ERROR();
            }
            return R.OK();
        }
    }

}
