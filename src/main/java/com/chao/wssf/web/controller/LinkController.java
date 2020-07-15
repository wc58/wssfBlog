package com.chao.wssf.web.controller;

import com.chao.wssf.service.ILinkService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
