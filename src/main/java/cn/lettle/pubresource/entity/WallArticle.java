package cn.lettle.pubresource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class WallArticle {
    @TableId(value = "article_id", type = IdType.AUTO)
    private Integer article_id;         // 文章id
    private String article_username;    // 发布者用户名
    private Integer article_uid;        // 发布者id
    private String article_text;        // 发布信息
    private Integer article_state;      // 发布状态 --> WallArticleState
    private Date publish_time;          // 发布时间
}
