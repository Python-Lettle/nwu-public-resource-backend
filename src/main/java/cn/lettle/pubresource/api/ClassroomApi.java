package cn.lettle.pubresource.api;

import cn.lettle.pubresource.entity.Classroom;
import cn.lettle.pubresource.entity.Message;
import cn.lettle.pubresource.entity.User;
import cn.lettle.pubresource.mapper.ClassroomMapper;
import cn.lettle.pubresource.mapper.UserMapper;
import cn.lettle.pubresource.util.ClassroomManager;
import cn.lettle.pubresource.util.ClassroomState;
import cn.lettle.pubresource.util.UserState;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/classroom/")
@CrossOrigin
public class ClassroomApi {
    @Autowired
    private ClassroomMapper classroomMapper;
    private UserMapper userMapper;
    private static final ClassroomManager classroomManager = ClassroomManager.getInstance();

    /**
     * 向数据库中添加请求，但不更改 classManager 中存储的占用情况
     */
    @PostMapping("/occupy")
    public String occupy(@RequestBody JSONObject body_json){
        int building = body_json.getIntValue("building");
        int floor = body_json.getIntValue("floor");
        int classroom = body_json.getIntValue("classroom");
        int uid = body_json.getIntValue("uid");

        User user = userMapper.selectById(uid);
        Classroom classroom1 = classroomManager.occupy(building, floor, classroom, uid);
        if (classroom1 != null  && user != null && user.getState() >= UserState.SUPER) {
            classroomMapper.insert(classroom1);
            return Message.occupySuccess();
        }
        return Message.occupyFail();
    }

    /**
     * 更改 classManager 中存储的占用情况
     */
    @PostMapping("/release")
    public String release(@RequestBody JSONObject body_json) {
        /** 获取参数 **/
        int request_id = body_json.getIntValue("request_id");
        /** 根据 id 获取占用的教室 **/
        Classroom classroom = classroomMapper.selectById(request_id);
        if (classroomManager.release(classroom)) {
            classroom.setState(ClassroomState.NONE);
            classroomMapper.updateById(classroom);
            return Message.releaseSuccess();
        }
        return Message.releaseFail();
    }

    @PostMapping("/examine")
    public String examine(@RequestBody JSONObject body_json) {
        /** 获取参数 **/
        int request_id = body_json.getIntValue("request_id");
        int state = body_json.getIntValue("state");

        /** 判断 state 为成功或失败的一种 **/
        if (state == ClassroomState.PASS || state == ClassroomState.FAIL) {
            // 获取库中的信息
            Classroom classroom = classroomMapper.selectById(request_id);
            // 设置为新的状态
            classroom.setState(state);
            // Manager 审核
            if (classroomManager.examine(classroom)){
                classroomMapper.updateById(classroom);
                return Message.examineSuccess();
            } else {
                return Message.message("examine", "occupied");
            }
        }

        return Message.examineFail();
    }


    @GetMapping("/detail")
    public String detail() {
        return JSONObject.toJSONString(classroomManager.getClassrooms());
    }

}
