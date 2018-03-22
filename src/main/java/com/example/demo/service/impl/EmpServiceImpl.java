package com.example.demo.service.impl;

import com.example.demo.dao.EmpDao;
import com.example.demo.entity.Emp;
import com.example.demo.entity.EmpVO;
import com.example.demo.service.EmpService;
import com.example.demo.util.DateUtil;
import com.example.demo.util.ImageUtil;
import com.example.demo.util.SwordBCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2018/3/20/020.
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;


    @Override
    public Emp getEmp(Emp param) {
        return empDao.selectOne(param);
    }

    @Override
    public Emp getEmpByName(String username) {
        return empDao.getEmpByName(username);
    }

    @Override
    public void save(EmpVO empVO) {
        Emp emp = new Emp();
        emp.seteName(empVO.geteName());
        emp.seteDeptId(empVO.geteDeptId());
        emp.setePwd(SwordBCrypt.hashpw(empVO.geteName() + empVO.getePwd()));//加密
        empDao.insert(emp);
    }

    @Override
    public Emp editUserInfo(MultipartFile headpic,Integer eId) {
        Emp empDB = empDao.selectByPrimaryKey(eId);
        if(empDB==null){
           return null;
        }

        if (headpic != null && !headpic.isEmpty()) {
            // 头像处理
            // 本地存储地址
            String saveFileName = DateUtil.formatCurDate()
                    + headpic.getOriginalFilename().substring(headpic.getOriginalFilename().lastIndexOf("."));
            String localPath = "D:poho/img/";
            Emp emp = new Emp();
            emp.seteId(eId);
            try {
                ImageUtil.saveFileFromInputStream(headpic.getInputStream(), localPath, saveFileName);
                emp.setePortrait(localPath.replace("D:poho","/upload")+saveFileName);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            int result = empDao.updateByPrimaryKeySelective(emp);
            if(result!=0){
                // 修改成功
                if (!headpic.isEmpty()) {
                    // 删除原来头像的图片
                    String localPath2= "D:poho" + empDB.getePortrait();
                    ImageUtil.deleteFile(localPath2);
                }
            }else{
                return null;
            }
        }else{
            return null;
        }
        return empDao.selectByPrimaryKey(eId);
    }

    @Override
    public Boolean updatePwd(Emp emp) {
        return empDao.updateByPrimaryKeySelective(emp) > 0;
    }

}
