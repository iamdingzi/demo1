package com.example.demo.service;

import com.example.demo.entity.Emp;
import com.example.demo.entity.EmpVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2018/3/20/020.
 */
public interface EmpService {

    /**
     * 查询emp
     * @param param
     * @return
     */
    Emp getEmp(Emp param);

    /**
     * 根据名称查询emp
     * @param username
     * @return
     */
    Emp getEmpByName(String username);

    /**
     * 保存emp
     * @param empVO
     * @return
     */
    void save(EmpVO empVO);

    /**
     * 上传头像
     * @param headpic
     * @param eId
     * @return
     */
    Emp editUserInfo(MultipartFile headpic,Integer eId);

    /**
     * 更新密码
     * @param emp
     * @return
     */
    Boolean updatePwd(Emp emp);
}
