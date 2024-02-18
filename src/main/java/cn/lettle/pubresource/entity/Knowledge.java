package cn.lettle.pubresource.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class Knowledge {

    private String subject;   // 科目
    private int sensitivity;  // 资料敏感度
    private String low_text;  // 低敏感度资料
    private String high_text; // 高敏感度资料
    private int view_num;     // 阅读数量
}