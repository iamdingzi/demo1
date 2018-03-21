package com.example.demo.dao;

import com.example.demo.config.MyMapper;
import com.example.demo.entity.Emp;
import com.example.demo.entity.EmpVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20/020.
 */

@Mapper
public interface EmpDao extends MyMapper<Emp> {

    @Select("select e_id, " +
            "e_portrait, " +
            "e_name, " +
            "e_pwd, " +
            "e_uname, " +
            "e_birthday, " +
            "e_level, " +
            "e_six, " +
            "e_wages, " +
            "e_hobby, " +
            "e_deptId from emp where e_name = #{username}")
    @Results({
            @Result(property = "eId",column = "e_id"),
            @Result(property = "ePortrait",column = "e_portrait"),
            @Result(property = "eName",column = "e_name"),
            @Result(property = "ePwd",column = "e_pwd"),
            @Result(property = "eUname",column = "e_uname"),
            @Result(property = "eBirthday",column = "e_birthday"),
            @Result(property = "eLevel",column = "e_level"),
            @Result(property = "eSix",column = "e_six")
    }
    )
    Emp getEmpByName(String username);

    @Select("<script> select  emp.e_id, " +
            " emp.e_portrait, " +
            " emp.e_name, " +
            " emp.e_pwd, " +
            " emp.e_uname, " +
            " emp.e_birthday, " +
            " emp.e_level, " +
            " emp.e_six, " +
            " emp.e_wages, " +
            " emp.e_hobby, " +
            " emp.e_deptId, " +
            " dept.d_name, " +
            " dept.d_location" +
            " from emp left join dept on emp.e_deptId = dept.d_id" +
            "  <where> " +
            "  <if test='dept !=null and dept != \"\"' >dept.d_name = #{dept}</if>" +
            "  <if test='location !=null and location != \"\"' > and dept.d_location = #{location}</if>" +
            "  <if test='eName !=null and eName != \"\"' > and emp.e_name = #{eName}</if>" +
            "  <if test='eLevel !=null and eLevel != \"\"' > and emp.e_level = #{eLevel}</if>" +
            "  <if test='eSix !=null and eSix != \"\"' > and emp.e_six = #{eSix}</if>" +
            "  <if test='eHobby !=null and eHobby != \"\"' > and emp.e_hobby = #{eHobby}</if>" +
            "</where> " +
            "<if test='sort !=null and sort != \"\"' > order by emp.e_wages ${sort}</if>" +
            "</script>")

    @Results({
            @Result(property = "eId",column = "e_id"),
            @Result(property = "ePortrait",column = "e_portrait"),
            @Result(property = "eName",column = "e_name"),
            @Result(property = "ePwd",column = "e_pwd"),
            @Result(property = "eUname",column = "e_uname"),
            @Result(property = "eBirthday",column = "e_birthday"),
            @Result(property = "eLevel",column = "e_level"),
            @Result(property = "eSix",column = "e_six"),
            @Result(property = "eHobby",column = "e_hobby"),
            @Result(property = "eWages",column = "e_wages"),
            @Result(property = "dept",column = "d_name"),
            @Result(property = "location",column = "d_location"),
    })
    List<EmpVO> selectList(EmpVO param);
}
