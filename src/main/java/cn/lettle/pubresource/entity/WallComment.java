/**
 * Created with IntelliJ IDEA.
 * Author: Lettle
 * Date: 2024/2/21
 * Time: 13:27
 * Description:
 */

package cn.lettle.pubresource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class WallComment {
    @TableId(value = "comment_id", type = IdType.ASSIGN_ID)
    private Integer comment_id;         // 评论id
    private Integer comment_uid;        // 评论者id
    private Integer publish_id;         // 评论的文章id
    private String comment_text;        // 评论信息
    private Integer comment_state;      // 评论状态
}
