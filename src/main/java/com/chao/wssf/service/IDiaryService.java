package com.chao.wssf.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.Diary;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public interface IDiaryService {


    HashMap<String, List<Diary>> getAllDiaries();

    int getAllDiarySize();

    Integer addDiary(Integer id, String content);

    Page getDiary(Integer page, Integer limit, String startTime, String endTime) throws ParseException;

    void deleteDiaryById(Integer id);

    Diary getDiaryById(Integer id);
}
