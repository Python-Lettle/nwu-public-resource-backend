/**
 * Library Log
 * Author: Lettle
 * Date: 2024/2/19
 * Time: 13:23
 * Description: 用于在数据库中记录占座操作
 */

package cn.lettle.pubresource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class LibraryLog {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer uid;
    @TableField("floor_num")
    private int floor_num;

    private int pos;

    /** 占用状态: 0释放 1占用 **/
    private int state;

    /** 起始、终止时间 **/
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField("start_time")
    private Date start_time;
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField("end_time")
    private Date end_time;

}
