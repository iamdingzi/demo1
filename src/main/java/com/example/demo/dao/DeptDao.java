package com.example.demo.dao;

import com.example.demo.config.MyMapper;
import com.example.demo.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20/020.
 */
@Mapper
public interface DeptDao extends MyMapper<Dept> {


    @Select("select d_location from dept group by d_location")
    List<String> getLocationList();
}
