package com.chao.wssf.service;

import com.chao.wssf.entity.Diary;

import java.util.HashMap;
import java.util.List;

public interface IDiaryService {


    HashMap<String, List<Diary>> getAllDiaries();

    int getAllDiarySize();
}
