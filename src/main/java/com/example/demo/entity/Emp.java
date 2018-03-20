package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/20/020.
 */
@Table(name = "emp")
public class Emp {

    @Id
    private Integer eId;
    private String ePortrait;
    private String eName;
    private String ePwd;
    private String eUname;
    private Date eBirthday;
    private Integer eLevel;
    private Integer eSix;
    private Integer eWages;
    private String eHobby;
    @Column(name = "e_deptId")
    private Integer eDeptId;

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public String getePortrait() {
        return ePortrait;
    }

    public void setePortrait(String ePortrait) {
        this.ePortrait = ePortrait;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getePwd() {
        return ePwd;
    }

    public void setePwd(String ePwd) {
        this.ePwd = ePwd;
    }

    public String geteUname() {
        return eUname;
    }

    public void seteUname(String eUname) {
        this.eUname = eUname;
    }

    public Date geteBirthday() {
        return eBirthday;
    }

    public void seteBirthday(Date eBirthday) {
        this.eBirthday = eBirthday;
    }

    public Integer geteLevel() {
        return eLevel;
    }

    public void seteLevel(Integer eLevel) {
        this.eLevel = eLevel;
    }

    public Integer geteSix() {
        return eSix;
    }

    public void seteSix(Integer eSix) {
        this.eSix = eSix;
    }

    public Integer geteWages() {
        return eWages;
    }

    public void seteWages(Integer eWages) {
        this.eWages = eWages;
    }

    public String geteHobby() {
        return eHobby;
    }

    public void seteHobby(String eHobby) {
        this.eHobby = eHobby;
    }

    public Integer geteDeptId() {
        return eDeptId;
    }

    public void seteDeptId(Integer eDeptId) {
        this.eDeptId = eDeptId;
    }
}
