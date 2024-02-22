package cn.lettle.pubresource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Classroom {
    @TableId(value = "request_id", type = IdType.AUTO)
    private Integer request_id;     // 请求id
    private int building;    // 楼号
    private int floor;           // 层数
    private int classroom;    // 教室号
    private int state;              // 教室当前状态
    private Integer request_uid;    // 申请人uid

    public Classroom(int building, int floor, int classroom, int state, int request_uid)
    {
        this.building = building;
        this.floor = floor;
        this.classroom = classroom;
        this.state = state;
        this.request_uid = request_uid;
    }
}
