package cn.lettle.pubresource.entity;

import lombok.Data;

@Data
public class User {

    private int id;          // 唯一标识符
    private String name;     // 账户名
    private String pwd;      // 密码
    private String Email;    // 电子邮件
    private int alwEml;      // 是否接收通知
    private String profile;  // 头像地址
    private int state;       // 状态代码
    private int pmtGrp;      // 权限组代码
    private String regDate;  // 注册日期13位时间戳
    private String logDate;  // 最近登录13位时间戳
    private String ipLocate; // IPv4地址
    private String fieldList;// 字段信息表

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPwd() {
        return pwd;
    }

    public User setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }

    public String getEmail() {
        return Email;
    }

    public User setEmail(String email) {
        Email = email;
        return this;
    }

    public int getAlwEml() {
        return alwEml;
    }

    public User setAlwEml(int alwEml) {
        this.alwEml = alwEml;
        return this;
    }

    public String getProfile() {
        return profile;
    }

    public User setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    public int getState() {
        return state;
    }

    public User setState(int state) {
        this.state = state;
        return this;
    }

    public int getPmtGrp() {
        return pmtGrp;
    }

    public User setPmtGrp(int pmtGrp) {
        this.pmtGrp = pmtGrp;
        return this;
    }

    public String getRegDate() {
        return regDate;
    }

    public User setRegDate(String regDate) {
        this.regDate = regDate;
        return this;
    }

    public String getLogDate() {
        return logDate;
    }

    public User setLogDate(String logDate) {
        this.logDate = logDate;
        return this;
    }

    public String getIpLocate() {
        return ipLocate;
    }

    public User setIpLocate(String ipLocate) {
        this.ipLocate = ipLocate;
        return this;
    }

    public String getFieldList() {
        return fieldList;
    }

    public User setFieldList(String fieldList) {
        this.fieldList = fieldList;
        return this;
    }
}
