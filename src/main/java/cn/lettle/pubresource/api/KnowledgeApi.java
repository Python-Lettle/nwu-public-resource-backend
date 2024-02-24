package cn.lettle.pubresource.api;

import cn.lettle.pubresource.entity.Knowledge;
import cn.lettle.pubresource.entity.Message;
import cn.lettle.pubresource.entity.User;
import cn.lettle.pubresource.mapper.KnowledgeMapper;
import cn.lettle.pubresource.mapper.UserMapper;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/knowledge/")
@CrossOrigin
public class KnowledgeApi {
    @Mapper
    @Autowired
    public KnowledgeMapper knowledgeMapper;

    @Autowired
    public UserMapper userMapper;
    /**
     * 获取用户权限等级
     * @param body_json
     * @return
     */
    @PostMapping("power")
    public String secret(@RequestBody JSONObject body_json)
    {
        // TODO: 检查用户登录状态
        /** 获取参数 **/
        Integer id = body_json.getIntValue("id");
        User user = userMapper.selectById(id);               // 用户id
        /** 判断用户权限等级 **/
        if (user.getState() == 1){
            return Message.secretSuccess();
        }
        else
            return Message.secretFail();

    }

    /**
     * 获取课程资料
     * @param body_json
     * @return
     */
    @GetMapping("/detail")
    public String retail_klg(@RequestBody JSONObject body_json)
    {
        // TODO: 检查用户登录状态
        /** 获取参数 **/
        Integer k_id = body_json.getIntValue("id");          // 课程代号
        Knowledge knowledge = knowledgeMapper.selectById(k_id);
        /** 获取权限对应课程资料 **/
        if(secret(body_json).equals("success"))
            return JSON.toJSONString(knowledge.getHigh_text()) + JSON.toJSONString(knowledge.getLow_text());
        else
            return JSON.toJSONString(knowledge.getLow_text());
    }

    /**
     * 设置资料敏感度
     * @param body_json
     * @return
     */
    @PostMapping("/change")
    public String change(@RequestBody JSONObject body_json)
    {
        /** 获取参数 **/
        Integer k_id = body_json.getIntValue("id");          // 课程代号
        int state = body_json.getIntValue("sensitivity");
        /** 设置敏感度参数 0**/
        Knowledge knowledge = knowledgeMapper.selectById(k_id);
        knowledge.setSensitivity(state);
        knowledgeMapper.updateById(knowledge);
        if(knowledge.getSensitivity() == 0 || knowledge.getSensitivity() == 1 || knowledge.getSensitivity() == 2)
            return Message.changeSuccess();
        else
            return Message.changeFail();
    }
}



