package cn.lettle.pubresource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Classroom {
    @TableId(value = "building_id", type = IdType.AUTO)
    private Integer building_id;       // 楼号
    private String classroom_id;   // 教室号
    private int state;             // 教室当前状态
    private int num;               // 教室当前人数

}
