package cn.lettle.pubresource.api;

import cn.lettle.pubresource.entity.Knowledge;
import cn.lettle.pubresource.entity.User;
import cn.lettle.pubresource.mapper.KnowledgeMapper;
import cn.lettle.pubresource.mapper.UserMapper;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/knowledge/")
@CrossOrigin
public class KnowledgeApi {
    public KnowledgeMapper knowledgeMapper;
    public UserMapper userMapper;
    @Mapper
    @GetMapping("/retail_klg")
    public String retail_klg(@RequestBody JSONObject body_json)
    {
        int id = body_json.getIntValue("id");
        User user = userMapper.selectById(id);

        String subject = body_json.getString("subjects");
        QueryWrapper<Knowledge> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("subjects",subject);
        Knowledge knowledge = knowledgeMapper.selectOne(queryWrapper);

        if(user.getState() == 1){
            return JSON.toJSONString(knowledge.getHigh_text());
        }
        else
            return JSON.toJSONString(knowledge.getLow_text());
    }
}
