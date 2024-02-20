/**
 * Created with IntelliJ IDEA.
 * Author: Lettle
 * Date: 2024/2/17
 * Time: 15:57
 * Description:
 */

package cn.lettle.pubresource.api;

import cn.lettle.pubresource.entity.LibraryManager;
import cn.lettle.pubresource.entity.Message;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/library/")
public class LibrarySeatApi {
    private static final LibraryManager libraryManager = LibraryManager.getInstance();

    @PostMapping("/occupy")
    public String occupy (@RequestBody JSONObject body_json)
    {
        int floor = body_json.getIntValue("floor");
        int x = body_json.getIntValue("x");
        int y = body_json.getIntValue("y");
        if (libraryManager.occupy(floor, x, y)) {
            log.info(String.format("Floor %s x = %s, y = %s 被占座", floor, x, y));
            return Message.occupySuccess();
        }
        return Message.occupyFail();
    }

    @PostMapping("/release")
    public String release (@RequestBody JSONObject body_json)
    {
        int floor = body_json.getIntValue("floor");
        int x = body_json.getIntValue("x");
        int y = body_json.getIntValue("y");
        if (libraryManager.release(floor, x, y)) {
            log.info(String.format("Floor %s x = %s, y = %s 被释放", floor, x, y));
            return Message.releaseSuccess();
        }
        return Message.releaseFail();
    }

    @GetMapping("/getSeats")
    public String getSeats ()
    {
        return JSON.toJSONString(libraryManager.getSeats());
    }

}
