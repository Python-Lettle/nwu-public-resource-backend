package cn.lettle.pubresource.api;

import cn.lettle.pubresource.entity.Message;
import cn.lettle.pubresource.entity.User;
import cn.lettle.pubresource.mapper.UserMapper;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/user/")
public class UserApi {
    public UserMapper userMapper;
    @Mapper
    @PostMapping("/login")
    public String login(@RequestBody JSONObject body_json)
    {
        int id = body_json.getIntValue("id");
        String pwd = body_json.getString("pwd");
        User user = userMapper.selectById(id);
        if(user == null)
            return Message.loginFail();
        else
            if(user.getPwd().equals(pwd)) {
                log.info("登录成功");
                return Message.loginSuccess();
            }
            else
                return Message.loginFail();
    }


    @PostMapping("/register")
    public String register(@RequestBody JSONObject body_json)
    {
        int id = body_json.getIntValue("id");
        String pwd = body_json.getString("pwd");
        String email = body_json.getString("email");
        if(id != 0 && pwd != null && email != null) {
            log.info("注册成功");
            return Message.registerSuccess();
        }
        return Message.registerFail();
    }
}
