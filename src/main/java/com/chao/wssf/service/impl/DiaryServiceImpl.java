package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chao.wssf.entity.Diary;
import com.chao.wssf.mapper.DiaryMapper;
import com.chao.wssf.service.IDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class DiaryServiceImpl implements IDiaryService {


    @Autowired
    private DiaryMapper diaryMapper;

    @Override
    public HashMap<String, List<Diary>> getAllDiaries() {
        QueryWrapper<Diary> diaryQueryWrapper = new QueryWrapper<>();
        diaryQueryWrapper.eq("del", "0").orderByAsc("create_time");
        List<Diary> diaries = diaryMapper.selectList(diaryQueryWrapper);
        HashMap<String, List<Diary>> map = new HashMap<>();
        SimpleDateFormat year = new SimpleDateFormat("yyyy");

        for (Diary diary : diaries) {
            Date time = diary.getCreateTime();
            String yearStr = year.format(time);
            if (map.get(yearStr) == null) {
                map.put(yearStr, new ArrayList<>());
            }
            map.get(yearStr).add(diary);
        }

        return map;
    }
}
