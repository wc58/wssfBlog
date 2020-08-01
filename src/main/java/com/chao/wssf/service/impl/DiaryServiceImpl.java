package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.Diary;
import com.chao.wssf.mapper.DiaryMapper;
import com.chao.wssf.query.CommonQuery;
import com.chao.wssf.service.IDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DiaryServiceImpl implements IDiaryService {


    @Autowired
    private DiaryMapper diaryMapper;

    @Override
    public HashMap<String, List<Diary>> getAllDiaries() {
        QueryWrapper<Diary> diaryQueryWrapper = new QueryWrapper<>();
        diaryQueryWrapper.orderByDesc("create_time");
        List<Diary> diaries = diaryMapper.selectList(diaryQueryWrapper);
        HashMap<String, List<Diary>> map = new LinkedHashMap<>();
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

    /**
     * 获取所有日记
     *
     * @return
     */
    @Override
    public int getAllDiarySize() {
        return diaryMapper.selectCount(new QueryWrapper<>());
    }

    /**
     * 添加或修改日记
     *
     * @param id
     * @param content
     * @return
     */
    @Override
    public Integer addDiary(Integer id, String content) {
        Diary diary = new Diary();
        Date date = new Date();
        diary.setContent(content);
        diary.setUpdateTime(date);
        diary.setCreateTime(date);
        if (id == null) {
            diaryMapper.insert(diary);
            return diary.getId();
        } else {
            diary.setId(id);
            diary.setUpdateTime(date);
            diaryMapper.updateById(diary);
            return id;
        }
    }

    @Override
    public Page getDiary(CommonQuery commonQuery) {
        QueryWrapper<Diary> diaryQueryWrapper = new QueryWrapper<>();
        Date startTime = commonQuery.getStartTime();
        Date endTime = commonQuery.getEndTime();
        if (!StringUtils.isEmpty(startTime)) {
            diaryQueryWrapper.ge("create_time", startTime);
        }
        if (!StringUtils.isEmpty(endTime)) {
            diaryQueryWrapper.le("create_time", endTime);
        }
        Page<Diary> diaryPage = new Page<>(commonQuery.getPage(), commonQuery.getLimit());
        return diaryMapper.selectPage(diaryPage, diaryQueryWrapper);
    }

    @Override
    public void deleteDiaryById(Integer id) {
        diaryMapper.deleteById(id);
    }

    @Override
    public Diary getDiaryById(Integer id) {
        return diaryMapper.selectById(id);
    }
}
