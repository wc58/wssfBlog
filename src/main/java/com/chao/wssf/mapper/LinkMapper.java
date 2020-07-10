package com.chao.wssf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chao.wssf.entity.Link;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LinkMapper extends BaseMapper<Link> {


}