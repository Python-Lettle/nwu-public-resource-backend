package cn.lettle.pubresource.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class msg_wall {

    private String publish_id;  // 发布者id
    private String pu_text;     // 发布信息
    private String pu_state;    // 发布状态
    private String comments_id; // 评论者id
    private String co_text;     // 评论信息
    private String co_state;    // 评论状态
}
