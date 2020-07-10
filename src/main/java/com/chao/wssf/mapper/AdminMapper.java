package com.chao.wssf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chao.wssf.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}