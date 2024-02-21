package cn.lettle.pubresource.api;

import cn.lettle.pubresource.entity.Message;
import cn.lettle.pubresource.entity.WallComment;
import cn.lettle.pubresource.entity.WallPublish;
import cn.lettle.pubresource.mapper.WallCommentMapper;
import cn.lettle.pubresource.mapper.WallPublishMapper;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
@CrossOrigin
@RequestMapping("/wall/")
public class WallApi {
    @Autowired
    public WallPublishMapper wallPublishMapper;

    @Autowired
    public WallCommentMapper wallCommentMapper;

    /**
     * 发表文章
     * @param body_json
     * @return
     */
    @Mapper
    @PostMapping("/publish")
    public String publish(@RequestBody JSONObject body_json){
        String publish_id = body_json.getString("publish_id");
        String comments_id = body_json.getString("comments_id");
        String publish_text = body_json.getString("publish_text");
        String comment_text = body_json.getString("comment_text");

        WallPublish publish_wallPublish = wallPublishMapper.selectById(publish_id);
        WallComment comment_wallPublish = wallCommentMapper.selectById(comments_id);

        publish_wallPublish.setPublish_text(publish_text);
        comment_wallPublish.setComment_text(comment_text);

        if( publish_wallPublish.getPublish_text() != null || comment_wallPublish.getComment_text() != null ) {
            log.info("已提交，审阅中···");
            return Message.publishSuccess();
        }
        else
            return Message.publishFail();
    }

    /**
     * 审核文章
     * @param body_json
     * @return
     */
    @PostMapping("/check")
    public String check(@RequestBody JSONObject body_json){

        String publish_id = body_json.getString("publish_id");
        String comments_id = body_json.getString("comments_id");

        WallPublish publish_wallPublish = wallPublishMapper.selectById(publish_id);
        WallComment comment_wallPublish = wallCommentMapper.selectById(comments_id);

        if(publish_wallPublish.getPublish_text() != null) {
            publish_wallPublish.setPublish_state(1);
            log.info("审核通过");
            return Message.checkSuccess();
        }
        else if(comment_wallPublish.getComment_text() != null) {
            comment_wallPublish.setComment_state(1);
            log.info("评论审核通过");
            return Message.checkSuccess();
        }
        else
            return Message.checkFail();
    }

    @GetMapping("/retail")
    public String wall_retail(@RequestBody JSONObject body_json){
        Map<String,String> map = new HashMap<>();

        String publish_id = body_json.getString("publish_id");
        String comments_id = body_json.getString("comments_id");

        WallPublish publish_wallPublish = wallPublishMapper.selectById(publish_id);
        WallComment comment_wallPublish = wallCommentMapper.selectById(comments_id);

        if((publish_wallPublish.getPublish_text() != null && publish_wallPublish.getPublish_state() == 1) ||
                (comment_wallPublish.getComment_text() != null && comment_wallPublish.getComment_state() == 1)) {
            log.info("查看校园墙");
            map.put("publish",JSON.toJSONString(publish_wallPublish));
            map.put("comments",JSON.toJSONString(comment_wallPublish));
            return JSON.toJSONString(map);
        }
        else {
            map.put("publish","failed");
            map.put("comments","failed");
            return JSON.toJSONString(map);
        }
    }
}
