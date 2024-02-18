package cn.lettle.pubresource.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class Classroom {

    private int building_id;       // 楼号
    private String classroom_id;   // 教室号
    private int state;             // 教室当前状态
    private int num;               // 教室当前人数

}
