package cn.lettle.pubresource.api;

import cn.lettle.pubresource.entity.Message;
import cn.lettle.pubresource.entity.User;
import cn.lettle.pubresource.mapper.UserMapper;
import cn.lettle.pubresource.util.SystemDateUtils;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/user/")
public class UserApi {
    @Autowired
    public UserMapper userMapper;
    @PostMapping("/login")
    public String login(@RequestBody JSONObject body_json)
    {
        // 接收参数
        int id = body_json.getIntValue("id");
        String pwd = body_json.getString("password");

        // 查询有无此用户
        User user = userMapper.selectById(id);
        if(user == null)
            return Message.loginFail();         // 没查到用户id
        else
            if(user.getPwd().equals(pwd)) {
                log.info(String.format("%s 登录成功", user.getName()));
                return Message.loginSuccess(user);
            }
            else
                return Message.loginFail();
    }


    @PostMapping("/register")
    public String register(@RequestBody JSONObject body_json)
    {
        // 接收参数
        int id = body_json.getIntValue("id");
        String pwd = body_json.getString("pwd");
        String email = body_json.getString("email");
        String username = body_json.getString("username");

        // 检查是否可以注册
        User check = userMapper.selectById(id);
        if(check == null && id != 0 && pwd != null && email != null) {
            // 新建一个 User 类 并为其设置我们注册的信息
            User user = new User();
            user.setUid(id);
            user.setEmail(email);
            user.setPwd(pwd);
            user.setName(username);
            user.setReg_date(SystemDateUtils.getDaDate());
            user.setRole("stu");
            userMapper.insert(user);
            log.info(String.format("id: %d, name: %s, email: %s 注册成功", id, username, email));
            return Message.registerSuccess();
        }
        return Message.registerFail();
    }
}
