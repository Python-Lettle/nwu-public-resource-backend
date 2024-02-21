package cn.lettle.pubresource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class WallPublish {
    @TableId(value = "publish_id", type = IdType.ASSIGN_ID)
    private Integer publish_id;  // 文章id
    private Integer publish_uid; // 发布者id
    private String publish_text;     // 发布信息
    private Integer publish_state;    // 发布状态
}
