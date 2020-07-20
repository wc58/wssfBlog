package com.chao.wssf.web.controller;

import com.chao.wssf.entity.Label;
import com.chao.wssf.pojo.WriteLabel;
import com.chao.wssf.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("label")
public class LabelController {

    @Autowired
    private ILabelService labelService;

    @RequestMapping("getAllLabels")
    @ResponseBody
    public List<WriteLabel> getAllLabels(@RequestParam(required = false) Integer id) {
        List<WriteLabel> writeLabels = new ArrayList<>();
        List<Label> label = labelService.getAllLabel();
        List<Integer> selectLabelId = labelService.getLabelIdsByArticleId(id);
        for (Label l : label) {
            WriteLabel writeLabel = new WriteLabel();
            writeLabel.setName(l.getName());
            writeLabel.setValue(l.getId());
            for (Integer labelId : selectLabelId) {
                if (labelId.equals(l.getId())) {
                    writeLabel.setSelected(true);
                    continue;
                }
            }
            writeLabels.add(writeLabel);
        }
        return writeLabels;
    }

}

