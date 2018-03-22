package com.example.demo.service;

import com.example.demo.entity.Emp;
import com.example.demo.entity.EmpVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20/020.
 */
public interface EmpService {

    List<Emp> getEmpByList();

    Emp getEmp(Emp param);

    Emp getEmpByName(String username);

    Boolean save(EmpVO empVO);

    Emp editUserInfo(MultipartFile headpic,Integer eId);

    Boolean updatePwd(Emp emp);
}
