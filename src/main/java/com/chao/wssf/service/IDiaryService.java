package com.chao.wssf.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.Diary;
import com.chao.wssf.query.CommonQuery;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public interface IDiaryService {


    HashMap<String, List<Diary>> getAllDiaries();

    int getAllDiarySize();

    Integer addDiary(Integer id, String content);

    Page getDiary(CommonQuery commonQuery);

    void deleteDiaryById(Integer id);

    Diary getDiaryById(Integer id);
}
