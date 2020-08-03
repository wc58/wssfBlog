package com.chao.wssf.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class JsonMapper extends ObjectMapper {

    public JsonMapper() {
        super();
        //取消时间戳
        this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //指定时间格式
        this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
}
