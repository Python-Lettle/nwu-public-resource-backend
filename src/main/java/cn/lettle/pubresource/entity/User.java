package cn.lettle.pubresource.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    @TableId(value = "uid")
    private Integer uid;      // 唯一标识符
    private String name;     // 账户名
    private String pwd;      // 密码
    private String Email;    // 电子邮件
    private int alw_eml;      // 是否接收通知
    private String profile;  // 头像地址
    private int state;       // 状态代码
    private int pmt_grp;      // 权限组代码
    private Date reg_date;  // 注册日期13位时间戳
    private Date log_date;  // 最近登录13位时间戳

    /**
     * 这两条使用 TableField 是因为 mybatis-plus
     *   会自动将下面这两个变量解析为类似 ip_locate 的形式去查表，而我们表中属性是原文
     *   因此使用 @TableField 注解来强制解析成这个就可以了
     */
    @TableField("ipLocate")
    private String ipLocate; // IPv4地址
    @TableField("fieldList")
    private String fieldList;// 字段信息表


    private String role;     // 权限

}
