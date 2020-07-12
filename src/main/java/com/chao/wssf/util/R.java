package com.chao.wssf.util;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
@ToString
public class R {

    private Integer code;
    private String message;
    private Map<String, Object> map = new HashMap<>();


    public R() {

    }

    public R(Map<String, Object> map) {
        this.map = map;
        setStatusForSuccess(this);
    }

    public R(String key, Object value) {
        map.put(key, value);
        setStatusForSuccess(this);
    }

    private void setStatusForSuccess(R r) {
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
    }

    public static R OK() {
        R r = new R();
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static R ERROR() {
        R r = new R();
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public R data(Map<String, Object> map) {
        this.map = map;
        return this;
    }

    public R data(String key, Object value) {
        map.put(key, value);
        return this;
    }

    public R code(Integer code) {
        this.code = code;
        return this;
    }

    public R message(String message) {
        this.message = message;
        return this;
    }

}
