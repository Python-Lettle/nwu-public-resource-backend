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
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/library/")
public class LibrarySeatApi {
    private static final long HOUR = 3600000L;
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
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("uid", uid);
        List<LibraryLog> libraryLogs = libraryMapper.selectList(wrapper);
        boolean havSeat = false;
        for (int i=0; i<libraryLogs.size(); i++) {
            LibraryLog nowLog = libraryLogs.get(i);
            if (!isExpired(new Date(), nowLog.getEnd_time(), HOUR)) {
                // 日志没有过期
                havSeat = true;
                break;
            }
        }

        if (havSeat || libraryManager.checkSeatOccupied(floor, pos)) {
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
            libraryLog.setStart_time(now);
            libraryLog.setEnd_time(new Date(now.getTime()+HOUR));
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
        // 检查是否已经有一个座位
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("uid", uid);
        List<LibraryLog> libraryLogs = libraryMapper.selectList(wrapper);
        LibraryLog mySeat = null;
        for (int i=0; i<libraryLogs.size(); i++) {
            LibraryLog nowLog = libraryLogs.get(i);
            if (!isExpired(new Date(), nowLog.getEnd_time(), HOUR)) {
                // 日志没有过期
                mySeat = nowLog;
                break;
            }
        }

        Map<String, String> map = new HashMap<>();
        if (mySeat != null && libraryManager.checkSeatOccupied(mySeat.getFloor_num(), mySeat.getPos())) {
            map.put("floor", String.valueOf(mySeat.getFloor_num()));
            map.put("seat", String.valueOf(mySeat.getPos()));
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

    /**
     * 时间段是否过期了
     */
    public static boolean isExpired (Date nowTime, Date endTime, long during) {
        return endTime.getTime() - nowTime.getTime() > during;
    }

}
