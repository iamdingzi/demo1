package com.example.demo.service.impl;

import com.example.demo.dao.EmpDao;
import com.example.demo.entity.Emp;
import com.example.demo.entity.EmpVO;
import com.example.demo.service.EmpService;
import com.example.demo.util.SwordBCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20/020.
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;

    @Override
    public List<Emp> getEmpByList() {
        return empDao.selectAll();
    }

    @Override
    public Emp getEmp(Emp param) {
        return empDao.selectOne(param);
    }

    @Override
    public Emp getEmpByName(String username) {
        return empDao.getEmpByName(username);
    }

    @Override
    public Boolean save(EmpVO empVO) {
        Emp emp = new Emp();
        emp.seteName(empVO.geteName());
        emp.seteDeptId(empVO.geteDeptId());
        emp.setePwd(SwordBCrypt.hashpw(empVO.geteName()+empVO.getePwd()));//加密
        return empDao.insert(emp) > 0;
    }

}
