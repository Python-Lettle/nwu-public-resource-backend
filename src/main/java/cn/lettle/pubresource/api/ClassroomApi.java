package cn.lettle.pubresource.api;

import cn.lettle.pubresource.entity.Classroom;
import cn.lettle.pubresource.entity.Message;
import cn.lettle.pubresource.mapper.ClassroomMapper;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/classroom/")
public class ClassroomApi {
    public ClassroomMapper classroomMapper;
    @Mapper
    @PostMapping("/occupy_cr")
    public String occupy_cr(@RequestBody JSONObject body_json){
        int b_id = body_json.getIntValue("building_id");
        String cr_id = body_json.getString("classroom_id");

        Map<String,Object> map = new HashMap<>();
        map.put(cr_id,b_id);
        List<Classroom> list = classroomMapper.selectByMap(map);

        if(list != null){
            int state = list.get(0).getState();
            if(state == 0) {
                log.info(String.format("Classroom %s%s 未被占用", b_id, cr_id));
                return Message.occupySuccess();
            }
            else {
                log.info("不可被占用");
                return Message.occupyFail();
            }
        }
        else {
            log.info("不可被占用");
            return Message.occupyFail();
        }
    }

    @GetMapping("/retail_cr")
    public String retail_cr(@RequestBody JSONObject body_json){
        int b_id = body_json.getIntValue("building_id");
        String cr_id = body_json.getString("classroom_id");

        Map<String,Object> map = new HashMap<>();
        map.put(cr_id,b_id);
        List<Classroom> list = classroomMapper.selectByMap(map);

        return JSONObject.toJSONString(list.get(0).getState());
    }

}
