/**
 * Created with IntelliJ IDEA.
 * Author: Lettle
 * Date: 2024/2/17
 * Time: 15:57
 * Description: 
 */

package cn.lettle.pubresource.api;

import cn.lettle.pubresource.entity.LibraryLog;
import cn.lettle.pubresource.entity.User;
import cn.lettle.pubresource.mapper.LibraryMapper;
import cn.lettle.pubresource.mapper.UserMapper;
import cn.lettle.pubresource.util.LibraryManager;
import cn.lettle.pubresource.entity.Message;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/library/")
public class LibrarySeatApi {
    private static final LibraryManager libraryManager = LibraryManager.getInstance();

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LibraryMapper libraryMapper;

    @PostMapping("/occupy")
    public String occupy (@RequestBody JSONObject body_json)
    {
        int floor = body_json.getIntValue("floor");
        int pos = body_json.getIntValue("pos");
        int uid = body_json.getIntValue("id");

        // 先检查是否已经有一个座位
        if (libraryManager.checkSeatOccupied(floor, pos)) {
            return Message.occupyFail();
        }

        User user = userMapper.selectById(uid);

        if (user != null && libraryManager.occupy(floor, pos)) {
            log.info(String.format("Floor %d pos = %d 被 %s 占座", floor, pos, user.getUid()));
            LibraryLog libraryLog = new LibraryLog();
            libraryLog.setUid(uid);
            libraryLog.setFloor_num(floor);
            libraryLog.setPos(pos);
            Date now = new Date();
            long hour = 3600000L;
            libraryLog.setStart_time(now);
            libraryLog.setEnd_time(new Date(now.getTime()+hour));
            libraryMapper.insert(libraryLog);
            return Message.occupySuccess();
        }
        return Message.occupyFail();
    }

    @PostMapping("/release")
    public String release (@RequestBody JSONObject body_json)
    {
        // 获取参数
        int uid = body_json.getIntValue("uid");
        // 查询要释放的记录
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("uid", uid);
        LibraryLog libraryLog = libraryMapper.selectOne(wrapper);
        if (libraryLog != null) {
            int floor = libraryLog.getFloor_num();
            int pos = libraryLog.getPos();
            if (libraryManager.release(floor, pos)) {
                libraryMapper.delete(wrapper);
                log.info(String.format("Floor %d pos = %d 被 %d 释放", floor, pos, uid));
                return Message.releaseSuccess();
            }
        }
        return Message.releaseFail();
    }

    @PostMapping("/getMySeat")
    public String getMySeat(@RequestBody JSONObject body_json) {
        int uid = body_json.getIntValue("uid");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("uid", uid);
        LibraryLog liblog = libraryMapper.selectOne(wrapper);

        Map<String, String> map = new HashMap<>();
        if (liblog != null && libraryManager.checkSeatOccupied(liblog.getFloor_num(), liblog.getPos())) {
            map.put("floor", String.valueOf(liblog.getFloor_num()));
            map.put("seat", String.valueOf(liblog.getPos()));
            map.put("code", "success");
        } else {
            map.put("code", "fail");
        }
        return JSON.toJSONString(map);
    }

    @GetMapping("/getSeats")
    public String getSeats ()
    {
        return JSON.toJSONString(libraryManager.getSeats());
    }

}
