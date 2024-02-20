package cn.lettle.pubresource.api;

import cn.lettle.pubresource.entity.Message;
import cn.lettle.pubresource.entity.Wall;
import cn.lettle.pubresource.mapper.WallMapper;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
@CrossOrigin
@RequestMapping("/wall/")
public class WallApi {
    public WallMapper wallMapper;
    @Mapper
    @PostMapping("/publish")
    public String publish(@RequestBody JSONObject body_json){
        String publish_id = body_json.getString("publish_id");
        String comments_id = body_json.getString("comments_id");
        String pu_text = body_json.getString("pu_text");
        String co_text = body_json.getString("co_text");

        Wall pu_wall = wallMapper.selectById(publish_id);
        Wall co_wall = wallMapper.selectById(comments_id);

        pu_wall.setPu_text(pu_text);
        co_wall.setPu_text(co_text);

        if( pu_wall.getPu_text() != null || co_wall.getCo_text() != null ) {
            log.info("已提交，审阅中···");
            return Message.publishSuccess();
        }
        else
            return Message.publishFail();
    }

    @PostMapping("/check")
    public String check(@RequestBody JSONObject body_json){

        String publish_id = body_json.getString("publish_id");
        String comments_id = body_json.getString("comments_id");

        Wall pu_wall = wallMapper.selectById(publish_id);
        Wall co_wall = wallMapper.selectById(comments_id);

        if(pu_wall.getPu_text() != null) {
            pu_wall.setPu_state(1);
            log.info("审核通过");
            return Message.checkSuccess();
        }
        else if(co_wall.getCo_text() != null) {
            co_wall.setCo_state(1);
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

        Wall pu_wall = wallMapper.selectById(publish_id);
        Wall co_wall = wallMapper.selectById(comments_id);

        if((pu_wall.getPu_text() != null && pu_wall.getPu_state() == 1) ||
                (co_wall.getCo_text() != null && co_wall.getCo_state() == 1)) {
            log.info("查看校园墙");
            map.put("publish",JSON.toJSONString(pu_wall));
            map.put("comments",JSON.toJSONString(co_wall));
            return JSON.toJSONString(map);
        }
        else {
            map.put("publish","failed");
            map.put("comments","failed");
            return JSON.toJSONString(map);
        }
    }
}
